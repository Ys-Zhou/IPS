package beens;

/**
 * JavaBean: Log
 *
 * @author Zhou Yan
 */
public class Log {

    private String androidID;
    private String markerMac;
    private String date;
    private int rssi;

    public Log() {

        androidID = null;
        markerMac = null;
        date = null;
        rssi = 0;
    }

    public String getAndroidID() {

        return androidID;
    }

    public void setAndroidID(String iAndroidID) {

        androidID = iAndroidID;
    }

    public String getMarkerMac() {

        return markerMac;
    }

    public void setMarkerMac(String iMarkerMac) {

        markerMac = iMarkerMac;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String iDate) {

        date = iDate;
    }

    public int getRssi() {

        return rssi;
    }

    public void setRssi(int iRssi) {

        rssi = iRssi;
    }
}
