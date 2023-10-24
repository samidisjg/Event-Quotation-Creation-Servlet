package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/log")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		String username = request.getParameter("uname");
		String password = request.getParameter("psw");

		boolean isTrue;
		isTrue = UserDBUtil.validate(username, password);

		if (isTrue) {
			List<User> userDetails = UserDBUtil.getCustomer(username);
			request.setAttribute("userDetails", userDetails);

			HttpSession session = request.getSession();
			session.setAttribute("username", username);

			if ("admin2000".equals(username)) {
				RequestDispatcher adminDis = request.getRequestDispatcher("admin/adminView.jsp");
				adminDis.forward(request, response);
			} else if ("Supplier2000".equals(username)) {
				RequestDispatcher supplierDis = request.getRequestDispatcher("supplierHome.jsp");
				supplierDis.forward(request, response);
			} else {
				RequestDispatcher dis = request.getRequestDispatcher("userView.jsp");
				dis.forward(request, response);
			}

		} else {
			out.println("<script type='text/javascript'>");
			out.println("alert('Your User Name or Password is Incorrect');");
			out.println("location='login.jsp'");
			out.println("</script>");
		}

	}

}
