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


@WebServlet("/s_quoteInsertServlet")
public class s_quoteInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        if (request.getSession().getAttribute("formSubmitted") != null) {
            // Redirect to a different page to prevent duplicate submission
            response.sendRedirect("supplierHome.jsp");
            return;
        }


		
		List<s_quote> s_quoteList = new ArrayList<>();
		String quote_no = QuoteNumberGenerator.generateRandomQuoteNumber();
	    
		double totalPrice = 0;
		
		for(int i=1; i<=3; i++) {
			s_quote squote = new s_quote();
			squote.setItemName(request.getParameter("Iname"+i));
			squote.setQuantity(Double.parseDouble(request.getParameter("qty"+i)));
			squote.setUnitPrice(Double.parseDouble(request.getParameter("Uprice"+i)));
			squote.setItemPrice(squote.getQuantity() * squote.getUnitPrice());
			
			totalPrice = totalPrice + squote.getItemPrice();
			s_quoteList.add(squote);
		}
		
	
		try {
		boolean isTrue;
		//request.setAttribute("s_quoteList",s_quoteList);
		//isTrue = s_supplierDBModel.insertQuote(name, qty, unit);
		isTrue = s_supplierDBModel.insertQuoteList(s_quoteList, quote_no, totalPrice);
		if(isTrue == true) {
			
			// Add JavaScript to reset the form after success
			request.setAttribute("successMessage", "Quotation Created Successfully!");
			 response.getWriter().println("<script>resetForm();</script>");
			RequestDispatcher dis = request.getRequestDispatcher("supplierHome.jsp");
			dis.forward(request,response);
		
		}else {
			
			RequestDispatcher dis2 = request.getRequestDispatcher("unsuccess.jsp");
			dis2.forward(request, response);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}

			
	}
	
	

}
