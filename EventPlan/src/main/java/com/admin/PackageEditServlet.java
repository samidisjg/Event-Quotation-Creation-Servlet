package com.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.DBConnect;

@WebServlet("/edit_Packages")
public class PackageEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String packageName = request.getParameter("pname");
			String venue = request.getParameter("venue");
			String packagePrice = request.getParameter("packagePrice");
			String onePersonFoodprice = request.getParameter("onePersonFoodprice");
			String foodDesc = request.getParameter("foodDesc");
			String decoPkgs = request.getParameter("decoPkgs");
			String decoDesc = request.getParameter("decoDesc");
			String entertainment = request.getParameter("entertainment");
			String photographer = request.getParameter("photographer");
			String status = request.getParameter("status");
			
			PackageDetails p = new PackageDetails();
			
			p.setPackageID(id);
			p.setPackageName(packageName);
			p.setVenue(venue);
			p.setPackagePrice(packagePrice);
			p.setFoodPerPersonPrice(onePersonFoodprice);
			p.setFoodDescription(foodDesc);
			p.setDecorationPackage(decoPkgs);
			p.setDecorationDescription(decoDesc);
			p.setEntertainment(entertainment);
			p.setPhotographers(photographer);
			p.setStatus(status);
			
			PackageInterfaceImplement pi = new PackageInterfaceImplement(DBConnect.getConnection());
			boolean f = pi.updatePackages(p);
			
			HttpSession session = request.getSession();
			
			if(f) {
				session.setAttribute("succMsg", "Package Updated Sucessfully");
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
