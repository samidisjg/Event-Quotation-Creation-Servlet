package s_supplier;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/s_quoteDeleteServlet")
public class s_quoteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		String qid = request.getParameter("paramName");
		// String qid = request.getPathInfo();
		boolean isTrue;

		isTrue = s_supplierDBModel.deleteQuote(qid);

		if (isTrue == true) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("supplierHome.jsp");
			dispatcher.forward(request, response);
		} else {
			List<s_quote> ViewAllQuotes = s_supplierDBModel.ViewAllQuotes(qid);
			request.setAttribute("ViewAllQuotes", ViewAllQuotes);

			RequestDispatcher dispatcher = request.getRequestDispatcher("s_quoteView.jsp");
			dispatcher.forward(request, response);
		}

	}

}
