package s_supplier;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/s_editViewServlet")
public class s_editViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String qid1 = request.getParameter("paramName1");
		try {
			//List<s_quote> s_quoteLists = new ArrayList<>();
			
			List<s_quote>  viewquote = s_supplierDBModel.viewEditform(qid1);
			request.setAttribute("viewquote", viewquote);
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dis = request.getRequestDispatcher("s_updateQuote.jsp");
		dis.forward(request, response);

	}


	}


