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
		String markerId = log.getMarkerId();
		String date = log.getDate();
		int from = log.getFrom();
		int to = log.getTo();

		String sql = String
				.format("INSERT INTO log(userid, markerid, time, fromlv, tolv) VALUES('%s', '%s', '%s', %d, %d)",
						userId, markerId, date, from, to);

		dBConnector.runUpdate(sql);
	}
}