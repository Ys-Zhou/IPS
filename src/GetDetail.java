import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.ResultSet;

import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import processing.DoGetDetail;

/**
 * Servlet implementation class GetDetail
 * 
 * @author Zhou Yan
 */
@WebServlet("/GetDetail")
public class GetDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetDetail() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		JSONObject jsonOut = new JSONObject();

		try {

			DoGetDetail doGetDetail = new DoGetDetail();
			ResultSet resultSet = doGetDetail.getDetail();

			int count = 0;
			while (resultSet.next()) {
				JSONArray array = new JSONArray();
				array.add(resultSet.getString(2));
				array.add(resultSet.getString(3));
				jsonOut.put(resultSet.getString(1), array);
				count++;
			}

			jsonOut.put("count", count);
		} catch (Exception e) {

			jsonOut.put("count", -1);
		}

		// Send response
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(jsonOut.toString());
		out.flush();
		out.close();
	}
}