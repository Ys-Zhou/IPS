package processing;

import java.sql.ResultSet;

public class DoGetDetail {

    private DBConnector dBConnector;

    public DoGetDetail() throws Exception {

        dBConnector = DBConnector.Instance();
    }

    public ResultSet getDetail() throws Exception {

        String sql = "SELECT * FROM `ips`.`detail`";
        return dBConnector.runQuery(sql);
    }
}