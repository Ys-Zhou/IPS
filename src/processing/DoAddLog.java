package processing;

import beens.Log;
import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.Iterator;

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
        String date = log.getDate();
        String flag = log.getFlag();
        Iterator<HashMap.Entry<String, JSONArray>> beacons = log.getBeacons();

        while (beacons.hasNext()) {
            HashMap.Entry<String, JSONArray> beacon = beacons.next();
            String mac = beacon.getKey();
            String rssis = beacon.getValue().toString();
            String sql = String
                    .format("INSERT INTO log(androidid, time, flag, mac, rssis) VALUES('%s', '%s', '%s', '%s', '%s')",
                            androidID, date, flag, mac, rssis);
            dBConnector.runUpdate(sql);
        }
    }
}