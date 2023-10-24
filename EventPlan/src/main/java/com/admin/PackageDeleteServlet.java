package com.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.DBConnect;

@WebServlet("/delete")
public class PackageDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int id = Integer.parseInt(request.getParameter("pid"));

			PackageInterfaceImplement pi = new PackageInterfaceImplement(DBConnect.getConnection());
			boolean f = pi.deletePackages(id);

			HttpSession session = request.getSession();

			if (f) {
				session.setAttribute("succMsg", "Package Deleted Sucessfully");
				response.sendRedirect("admin/allPackages.jsp");
			} else {
				session.setAttribute("failedMsg", "Something wrong on server");
				response.sendRedirect("admin/allPackages.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
