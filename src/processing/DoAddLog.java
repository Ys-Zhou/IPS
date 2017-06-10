package processing;

import beens.Log;

/**
 * Processing Class: DoAddLog
 * 
 * @author Zhou Yan
 */
public class DoAddLog {

	private Log log;
	private DBConnector dBConnector;

	public DoAddLog(Log iLog) throws Exception {

		log = iLog;
		dBConnector = DBConnector.Instance();
	}

	public void addLog() throws Exception {

		String userId = log.getUserId();
		String btId = log.getBtId();
		String date = log.getDate();
		int logType = log.getLogType();

		String sql = "INSERT INTO log VALUES('" + userId + "','" + btId + "','"
				+ date + "'," + logType + ")";

		dBConnector.runUpdate(sql);
	}
}
