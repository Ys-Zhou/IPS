package processing;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC
 * 
 * @author Zhou Yan
 */
public class DBConnector {

	private static int QUERY_RETRIES = 10;
	private static int DEF_ISOLATION = Connection.TRANSACTION_READ_COMMITTED;

	private String dbUrl;
	private String password;
	private String username;
	private String jdbcClassName;
	private Connection dbCon;

	private static DBConnector myInstance = null;

	/**
	 * Construct DB
	 */
	private DBConnector() throws Exception {
		connectAsDefaultLibrary();
	}

	/**
	 * Connect to DB
	 */
	private DBConnector(String inUrl, String inJdbcClassName,
			String inUserName, String inPassWord) throws Exception {
		dbUrl = inUrl;
		jdbcClassName = inJdbcClassName;
		username = inUserName;
		password = inPassWord;
		closeConnections();
		connect();
	}

	/**
	 * Create DB connection
	 */
	public void connectAsDefaultLibrary() throws Exception {
		dbUrl = "jdbc:mysql://localhost:3306/ips";
		jdbcClassName = "com.mysql.jdbc.Driver";
		username = "root";
		password = "zhou";
		closeConnections();
		connect();
	}

	/**
	 * Connect to DB
	 */
	private void connect() throws Exception {

		Driver driver = (Driver) Class.forName(jdbcClassName).newInstance();
		DriverManager.registerDriver(driver);

		dbCon = DriverManager.getConnection(dbUrl, username, password);
	}

	/**
	 * Close connections
	 */
	private void closeConnections() throws Exception {
		if (dbCon != null) {
			dbCon.close();
		}
	}

	/**
	 * Get connector instance
	 */
	public static DBConnector Instance() throws Exception {
		if (myInstance == null) {
			myInstance = new DBConnector();
			myInstance.connectAsDefaultLibrary();
		}
		return myInstance;
	}

	/**
	 * Get DB Statement
	 */
	private Statement getStmt(boolean isAutoCommit, int isolationLevel)
			throws SQLException {
		dbCon.setAutoCommit(isAutoCommit);
		dbCon.setTransactionIsolation(isolationLevel);
		return dbCon.createStatement();
	}

	/**
	 * Execute a sql query
	 */
	public ResultSet runQuery(String sqlQuery) throws Exception {

		Statement dbStatement = this.getStmt(true, DEF_ISOLATION);
		return dbStatement.executeQuery(sqlQuery);
	}

	/**
	 * Execute a sql update
	 */
	public int runUpdate(String sqlQuery) throws Exception {

		Statement dbStatement = getStmt(true, DEF_ISOLATION);
		int rowCount = dbStatement.executeUpdate(sqlQuery);
		dbStatement.close();
		return rowCount;
	}

	/**
	 * Execute a sql query transaction
	 */
	public ResultSet runChainedQuery(String sqlQuery) throws Exception {

		int retry = 0;
		boolean txnSuccess = false;
		Statement dbStatement = null;
		ResultSet resultSet = null;

		// Connect to the database.
		// Retry the query until complete or timeout.
		while (!txnSuccess && retry++ < QUERY_RETRIES) {
			try {
				dbStatement = getStmt(false,
						Connection.TRANSACTION_READ_COMMITTED);
				// Execute the query.
				resultSet = dbStatement.executeQuery(sqlQuery);
				// Commit the transaction.
				dbCon.commit();
				txnSuccess = true;
			} catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				// If deadlocked, try again after 10 msec
				if (se.getSQLState().equals("40P01")) {
					Thread.sleep(10);
				} else {
					dbCon.setTransactionIsolation(DEF_ISOLATION);
					throw se;
				}
			} catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
			}
		}
		return resultSet;
	}

	/**
	 * Execute a sql update transaction
	 */
	public boolean runChainedUpdate(String[] sqlQuery) throws Exception {
		int retry = 0;
		boolean txnSuccess = false;
		Statement dbStatement = null;

		while (!txnSuccess && retry++ < QUERY_RETRIES) {
			// Begin a new transaction.
			try {
				dbStatement = getStmt(false,
						Connection.TRANSACTION_READ_COMMITTED);
				// For each sql statement, perform the update.
				for (int i = 0; i < sqlQuery.length; i++) {
					dbStatement.executeUpdate(sqlQuery[i]);
				}
				// Commit the transaction and close.
				dbCon.commit();
				dbStatement.close();
				txnSuccess = true;
			} catch (SQLException se) {
				dbCon.rollback();
				dbStatement.close();
				// If deadlocked, try again after 10 msec
				if (se.getSQLState().equals("40P01")) {
					Thread.sleep(10);
				} else {
					dbCon.setTransactionIsolation(DEF_ISOLATION);
					throw se;
				}
			} catch (Exception e) {
				dbCon.rollback();
				dbStatement.close();
				dbCon.setTransactionIsolation(DEF_ISOLATION);
				throw e;
			}
		}
		return txnSuccess;
	}
}