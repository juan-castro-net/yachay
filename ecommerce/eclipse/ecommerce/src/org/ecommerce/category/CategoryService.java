package org.ecommerce.category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.ecommerce.util.BaseDato;

@Path("/category")
public class CategoryService {

	@GET
	@Path("/list")
	@Produces("application/json")
	// @Produces("application/xml")
	public CategoryList getProductos() {
		ArrayList lista = new ArrayList();
		
		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		Statement sentencia1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			conexion1 = basedato.getConnection();
			sentencia1 = conexion1.createStatement();
			sql = "select * from categories";
			rs1 = sentencia1.executeQuery(sql);
			while (rs1.next()) {
				long id = rs1.getInt("id");
				String name = rs1.getString("name");
				String icon = rs1.getString("icon");
				
				Category product = new Category();
				product.setId(id);
				product.setName(name);
				product.setIcon(icon);
				lista.add(product);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			basedato.close(rs1);
			basedato.close(sentencia1);
			basedato.close(conexion1);
		}
		
		return new CategoryList(lista);
	}
}
