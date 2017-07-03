package processing;

import java.sql.ResultSet;

public class DoGetDetail {

	private DBConnector dBConnector;

	public DoGetDetail() throws Exception {

		dBConnector = DBConnector.Instance();
	}

	public ResultSet getDetail() throws Exception {

		String sql = "SELECT * FROM detail";
		ResultSet resultSet = dBConnector.runQuery(sql);
		return resultSet;
	}
}