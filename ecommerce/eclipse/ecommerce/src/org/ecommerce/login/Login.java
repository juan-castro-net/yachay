package org.ecommerce.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ecommerce.util.BaseDato;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		long user_id = 0;
		
		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		Statement sentencia1 = null;
		ResultSet rs1 = null;
		String sql = "";
		
		try {
			conexion1 = basedato.getConnection();
			sentencia1 = conexion1.createStatement();
			sql = "select id from users where username = '" + username + "'"; 
			rs1 = sentencia1.executeQuery(sql); 
			if(rs1.next()){ 
				user_id = rs1.getLong("id");
				System.out.println(user_id + "," + username);
			}
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			basedato.close(rs1);
			basedato.close(sentencia1);
			basedato.close(conexion1);
		}
			
		session.setAttribute("user_id", user_id);
		session.setAttribute("username", username);
		response.sendRedirect("index.jsp");
	}

}
