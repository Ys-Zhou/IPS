package beens;

/**
 * JavaBean: Log
 * 
 * @author Zhou Yan
 */
public class Log {

	private String userId;
	private String btId;
	private String date;
	private int logType;

	public Log() {

		userId = null;
		btId = null;
		date = null;
		logType = -1;
	}

	public Log(String iUserId, String iBtId, String iDate, int iLogType) {

		userId = iUserId;
		btId = iBtId;
		date = iDate;
		logType = iLogType;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String iUserId) {

		userId = iUserId;
	}

	public String getBtId() {

		return btId;
	}

	public void setBtId(String iBtId) {

		btId = iBtId;
	}

	public int getLogType() {

		return logType;
	}

	public void setLogType(int iLogType) {

		logType = iLogType;
	}

	public String getDate() {

		return date;
	}

	public void setDate(String iDate) {

		date = iDate;
	}
}
