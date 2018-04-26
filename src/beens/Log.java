package beens;

import net.sf.json.JSONArray;

import java.util.HashMap;
import java.util.Iterator;

/**
 * JavaBean: Log
 *
 * @author Zhou Yan
 */
public class Log {

    private String androidID;
    private String date;
    private String flag;
    private HashMap<String, JSONArray> beacons;

    public Log() {

        androidID = null;
        date = null;
        flag = null;
        beacons = new HashMap<>();
    }

    public String getAndroidID() {

        return androidID;
    }

    public void setAndroidID(String iAndroidID) {

        androidID = iAndroidID;
    }

    public String getDate() {

        return date;
    }

    public void setDate(String iDate) {

        date = iDate;
    }

    public String getFlag() {

        return flag;
    }

    public void setFlag(String iFlag) {

        flag = iFlag;
    }

    public Iterator<HashMap.Entry<String, JSONArray>> getBeacons() {

        return beacons.entrySet().iterator();
    }

    public boolean addBeacons(String key, JSONArray value) {
        if (!beacons.containsKey(key)) {
            beacons.put(key, value);
            return true;
        }
        return false;
    }
}
