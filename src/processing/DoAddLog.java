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

        String androidID = log.getAndroidID();
        String markerMac = log.getMarkerMac();
        String date = log.getDate();
        int rssi = log.getRssi();

        String sql = String
                .format("INSERT INTO log(androidid, markermac, time, rssi) VALUES('%s', '%s', '%s', %d)",
                        androidID, markerMac, date, rssi);

        dBConnector.runUpdate(sql);
    }
}