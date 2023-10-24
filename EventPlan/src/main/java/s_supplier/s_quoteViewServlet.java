package s_supplier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/s_quoteViewServlet")
public class s_quoteViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	      String orderId = "JPU6HPD8";
		try {
			//List<s_quote> s_quoteLists = new ArrayList<>();
			
			List<s_quote>  s_quoteLists = s_supplierDBModel.ViewAllQuotes(orderId );
			request.setAttribute("s_quoteLists", s_quoteLists);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dis = request.getRequestDispatcher("s_quoteView.jsp");
		dis.forward(request, response);

	}

}
