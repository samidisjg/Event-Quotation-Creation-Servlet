package s_supplier;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class s_supplierDBModel {
	
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static boolean isSuccess;
	
	
	
	
	public static List<s_order> validate(String orderId) {

		ArrayList<s_order> order = new ArrayList<>(); // storing the order details to the arraylist

		// validate
		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();
			String sql = "select * from supplier_orders where orderId = '" + orderId + "'";
			// to run sql
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				int supplierId = rs.getInt(2);
				String Id = rs.getString(1);
				String orders = rs.getString(3);

				s_order o1 = new s_order(orderId, supplierId, orders);
				order.add(o1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return order;
	}
	
	
	
	public static List<s_quote> ViewAllQuotes(String orderId) {
		ArrayList<s_quote> s_quoteLists = new ArrayList<>(); // Initialize the list to store quotes

		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();

			String sql = "select * from supplier_quotation";
			rs = stmt.executeQuery(sql);


			
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int qty = rs.getInt(3);
				double uprice = rs.getDouble(4);
				double iprice = rs.getDouble(5);

				s_quote q1 = new s_quote(id, name, qty, uprice, iprice);
				s_quoteLists.add(q1);
			}
			
			
			
			
			/*
			 * while (rs.next()) {
			 * 
			 * String name = rs.getString(1); double qty = rs.getDouble(2); double uprice =
			 * rs.getDouble(3); double iprice = rs.getDouble(4);
			 * 
			 * s_quote squote = new s_quote(name,qty,uprice,iprice);
			 */
			/*
			 * squote.setItemName(rs.getString("itemName"));
			 * squote.setQuantity(rs.getInt("quantity"));
			 * squote.setUnitprice(rs.getDouble("unitPrice"));
			 * squote.setItemprice(rs.getDouble("itemPrice"));
			 */

			/*
			 * s_quoteList.add(squote); }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return s_quoteLists;
	}
	
	
	
	
	public static boolean insertQuoteList(List<s_quote> s_quoteList, String quote_no, double totalPrice) {
	    boolean isSuccess = true; // Initialize to true
	    
	    try {
	        con = DBConnect.getConnection();
	        stmt = con.createStatement();

	        // Loop to construct and execute SQL statements
	        for (s_quote squote : s_quoteList) {
	            String sql = "Insert into supplier_quotation(quoteId, itemName, quantity, unitPrice, itemPrice) " +
	                    " values ('"+quote_no+"', '"+squote.getItemName()+"', '"+squote.getQuantity()+"', '"+squote.getUnitPrice()+"', '"+squote.getItemPrice()+"')";

	            int rs = stmt.executeUpdate(sql);

	            // Check if the current iteration fails and set isSuccess accordingly
	            if (rs <= 0) {
	                isSuccess = false;
	            }
	        }

	        // After the loop, insert the total price
	        String sql1 = "Insert into supplier_total values ('"+quote_no+"', '"+totalPrice+"')";
	        int rs1 = stmt.executeUpdate(sql1);

	        // Check if the total price insertion fails and set isSuccess accordingly
	        if (rs1 <= 0) {
	            isSuccess = false;
	        }
	    }
	    catch(Exception e) {
	        e.printStackTrace();
	        isSuccess = false; // Set to false in case of an exception
	    } finally {
	    }
	    return isSuccess;
	}
	
	public static boolean deleteQuote(String qid) {
		
		try {
			
			con = DBConnect.getConnection();
	        stmt = con.createStatement();
			
	        String sql = "Delete from supplier_quotation where quoteId = '"+qid+"'";
	        String sql1 = "Delete from supplier_total where quoteId = '"+qid+"'";
	        
	        int rs = stmt.executeUpdate(sql);
	        int rs1 =  stmt.executeUpdate(sql1);
	        
	        if(rs > 0 && rs1 > 0) {
	        	isSuccess = true;
	        }
	        else {
	        	isSuccess = false;
	        }
	        
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return isSuccess;
	}
	
	
	
	public static List<s_quote> viewEditform(String qid1) {
		ArrayList<s_quote> viewquote = new ArrayList<>(); // Initialize the list to store quotes

		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();

			String sql = "select * from supplier_quotation where quoteId = '"+qid1+"'";
			rs = stmt.executeQuery(sql);


			
			while (rs.next()) {
				System.out.println("Before result In update");
			
				String id = rs.getString(1);
				String name = rs.getString(2);
				int qty = rs.getInt(3);
				double uprice = rs.getDouble(4);
				double iprice = rs.getDouble(5);

				s_quote q1 = new s_quote(id,name, qty, uprice, iprice);
				viewquote.add(q1);
				
				System.out.println("Query work");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return viewquote;
	}
		
	
	public static boolean updateQuote(List<s_quote> s_quoteList, String qid, double totalPrice) {

		try {
			con = DBConnect.getConnection();
			stmt = con.createStatement();

			for (s_quote squote : s_quoteList) {
				String sql = "update supplier_quotation set quantity= '" + squote.getQuantity() + "',unitPrice = '"
						+ squote.getUnitPrice() + "',itemPrice =  '" + squote.getItemPrice() + "' where quoteId = '"
						+ qid + "' and itemName = '" + squote.getItemName() + "'";

		

				int rs = stmt.executeUpdate(sql);
				if (rs > 0) {
					
					isSuccess = true;
				} else {
					isSuccess = false;
					break;
				}
			}

			String sql1 = "update supplier_total set total = '" + totalPrice + "' where quoteId = '" + qid + "'";
			int rs1 = stmt.executeUpdate(sql1);

			// Check if the total price Updation fails and set isSuccess accordingly
			if (rs1 <= 0) {
				isSuccess = false;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return isSuccess;

	}
	
}
