import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // Read request
        JSONObject jsonIn = JSONObject.fromObject(request
                .getParameter("jsonIn"));
        String androidID = jsonIn.getString("androidID");
        String markerMac = jsonIn.getString("markerMac");
        String date = jsonIn.getString("date");
        int rssi = jsonIn.getInt("rssi");

        // Transform to JavaBean
        Log log = new Log();
        log.setAndroidID(androidID);
        log.setMarkerMac(markerMac);
        log.setDate(date);
        log.setRssi(rssi);

        // Create response data
        JSONObject jsonOut = new JSONObject();

        try {

            DoAddLog doAddLog = new DoAddLog(log);
            doAddLog.addLog();
            // Succeeded
            jsonOut.put("addLogStmt", 1);
        } catch (Exception e) {

            // Failed
            jsonOut.put("addLogStmt", 0);
        }

        // Send response
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(jsonOut.toString());
        out.flush();
        out.close();
    }
}
