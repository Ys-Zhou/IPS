import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import beens.Log;
import processing.DoAddLog;

/**
 * Servlet implementation class UploadData
 *
 * @author Zhou Yan
 */

@WebServlet("/UploadData")
public class UploadData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UploadData() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        // Read request
        JSONObject jsonIn = JSONObject.fromObject(request
                .getParameter("jsonIn"));

        // Transform to JavaBean
        Log log = new Log();

        log.setAndroidID(jsonIn.getString("androidID"));
        log.setDate(jsonIn.getString("date"));
        log.setFlag(jsonIn.getString("flag"));

        JSONArray beacons = jsonIn.getJSONArray("beacons");
        for (int i = 0; i < beacons.size(); i++) {
            JSONObject beacon = beacons.getJSONObject(i);
            String mac = beacon.getString("mac");
            JSONArray rssis = beacon.getJSONArray("rssis");
            log.addBeacons(mac, rssis);
        }

        // Create response data
        JSONObject jsonOut = new JSONObject();

        try {

            DoAddLog doAddLog = new DoAddLog(log);
            doAddLog.addLog();
            // Succeeded
            jsonOut.put("addLogStmt", 101);
        } catch (Exception e) {
            e.printStackTrace();
            // Failed
            jsonOut.put("addLogStmt", 100);
        }

        // Send response
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonOut.toString());
        out.flush();
        out.close();
    }
}