import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;

import net.sf.json.JSONArray;

import processing.DoGetMacList;


/**
 * Servlet implementation class GetDetail
 *
 * @author Zhou Yan
 */
@WebServlet("/GetMacList")
public class GetMacList extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetMacList() {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        JSONArray array = new JSONArray();
        try {

            DoGetMacList doGetMacList = new DoGetMacList();
            ResultSet resultSet = doGetMacList.getMacList();

            while (resultSet.next()) {
                array.add(resultSet.getString("mac"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Send response
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(array.toString());
        out.flush();
        out.close();
    }
}