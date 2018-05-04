package processing;

import java.sql.ResultSet;

public class DoGetMacList {

    private DBConnector dBConnector;

    public DoGetMacList() throws Exception {

        dBConnector = DBConnector.Instance();
    }

    public ResultSet getMacList() throws Exception {

        String sql = "SELECT `mac` FROM `ips`.`beacon`";
        return dBConnector.runQuery(sql);
    }
}