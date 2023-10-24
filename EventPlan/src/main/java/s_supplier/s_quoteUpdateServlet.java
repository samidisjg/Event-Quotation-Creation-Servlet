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


@WebServlet("/s_quoteUpdateServlet")
public class s_quoteUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		List<s_quote> s_quoteList = new ArrayList<>();

		double totalPrice = 0;
		String qid = request.getParameter("qid");
		
		for(int i=1; i<=3; i++) {
			s_quote squote = new s_quote();
			squote.setItemName(request.getParameter("Iname"+i));
			squote.setQuantity(Double.parseDouble(request.getParameter("qty"+i)));
			squote.setUnitPrice(Double.parseDouble(request.getParameter("Uprice"+i)));
			squote.setItemPrice(squote.getQuantity() * squote.getUnitPrice());
			
			totalPrice = totalPrice + squote.getItemPrice();
			s_quoteList.add(squote);
		}
		
		
			boolean isTrue;
	
			isTrue = s_supplierDBModel.updateQuote(s_quoteList,qid,totalPrice);
			
			if(isTrue == true) {
				RequestDispatcher dis = request.getRequestDispatcher("s_quoteView.jsp");
				dis.forward(request, response);
			}else {
				RequestDispatcher dis = request.getRequestDispatcher("unsuccess.jsp");
				dis.forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
