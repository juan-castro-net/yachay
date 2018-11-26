package org.ecommerce.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDato {

	public Connection getConnection() {
		Connection conexion = null;

		String driver = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/ecommerce";
		String user = "ecommerce";
		String password = "ecommerce";

		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}

		return conexion;
	}
	
	public void close(ResultSet rs1) {
		try {
			rs1.close();
		}
		catch(Exception e) {
			System.out.println("Error " + e.toString());
		}
	}

	public void close(Statement stm1) {
		try {
			stm1.close();
		}
		catch(Exception e) {
			System.out.println("Error " + e.toString());
		}
	}
		
	public void close(PreparedStatement pstm1) {
		try {
			pstm1.close();
		}
		catch(Exception e) {
			System.out.println("Error " + e.toString());
		}
	}
	
	public void close(Connection con1) {
		try {
			con1.close();
		}
		catch(Exception e) {
			System.out.println("Error " + e.toString());
		}
	}
}
