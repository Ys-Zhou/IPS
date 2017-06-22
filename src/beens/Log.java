package beens;

/**
 * JavaBean: Log
 * 
 * @author Zhou Yan
 */
public class Log {

	private String userId;
	private String markerId;
	private String date;
	private int from;
	private int to;

	public Log() {

		userId = null;
		markerId = null;
		date = null;
		from = -1;
		to = -1;
	}

	public String getUserId() {

		return userId;
	}

	public void setUserId(String iUserId) {

		userId = iUserId;
	}

	public String getMarkerId() {

		return markerId;
	}

	public void setMarkerId(String iMarkerId) {

		markerId = iMarkerId;
	}

	public String getDate() {

		return date;
	}

	public void setDate(String iDate) {

		date = iDate;
	}

	public int getFrom() {

		return from;
	}

	public void setFrom(int iFrom) {

		from = iFrom;
	}

	public int getTo() {

		return to;
	}

	public void setTo(int iTo) {

		to = iTo;
	}
}
