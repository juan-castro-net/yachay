package org.ecommerce.purchase;

import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Vector;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.ecommerce.cart.Item;
import org.ecommerce.cart.ItemList;
import org.ecommerce.category.Category;
import org.ecommerce.category.CategoryList;
import org.ecommerce.product.Product;
import org.ecommerce.product.ProductList;
import org.ecommerce.util.BaseDato;

@Path("/purchase")
public class PurchaseService {

	@GET
	@Path("/items/{purchase_id}")
	@Produces("application/json")
	public ItemList getItems(@Context HttpServletRequest request, @PathParam(value = "purchase_id") int purchase_id) {
		ArrayList lista = new ArrayList();
		
		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		Statement sentencia1 = null;
		ResultSet rs1 = null;
		String sql = "";
		
		try {
			conexion1 = basedato.getConnection();
			sentencia1 = conexion1.createStatement();
			
			HttpSession session = request.getSession();
			long user_id = (long)session.getAttribute("user_id");
			
			if (purchase_id == 0) {
				sql = "select id from purchases";
				sql += " where user_id = " + user_id;
				sql += " order by created_at DESC limit 1";
				rs1 = sentencia1.executeQuery(sql);
				if (rs1.next()) {
					purchase_id = rs1.getInt("id");
				}
			}
					
					
			sql = "select purchase_items.id as item_id, * from purchases, purchase_items, products"; 
			sql += " where purchases.id = purchase_items.purchase_id"; 
			sql += " and purchase_items.product_id = products.id"; 
			sql += " and purchase_id = " + purchase_id;
					
			rs1 = sentencia1.executeQuery(sql);
			while (rs1.next()) {
				long id = rs1.getInt("item_id");
				double quantity = rs1.getDouble("quantity");
				double price = rs1.getDouble("price");
				long product_id = rs1.getLong("product_id");
				String product_name = rs1.getString("name");
				String short_description = rs1.getString("short_description");
				String product_icon = rs1.getString("icon");
				
				Item item = new Item();
				item.setId(id);
				item.setQuantity(quantity);
				item.setPrice(price);
				item.setProduct_id(product_id);
				item.setProduct_name(product_name);
				item.setProduct_description(short_description);
				item.setProduct_icon(product_icon);
				
				lista.add(item);
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
		
		return new ItemList(lista);
	}
	
	@GET
	@Path("/list")
	@Produces("application/json")
	public PurchaseList getPurchases(@Context HttpServletRequest request) {
		ArrayList lista = new ArrayList();
		
		HttpSession session = request.getSession();
		long user_id = (long)session.getAttribute("user_id");
		
		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		Statement sentencia1 = null;
		ResultSet rs1 = null;
		String sql = "";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			conexion1 = basedato.getConnection();
			sentencia1 = conexion1.createStatement();
			
			sql = "select * from purchases";
			sql += " where user_id = " + user_id; 
			sql += " order by purchases.created_at DESC";
			
			rs1 = sentencia1.executeQuery(sql);
			while (rs1.next()) {
				long id = rs1.getInt("id");
				Timestamp created_at = rs1.getTimestamp("created_at");
				Timestamp updated_at = rs1.getTimestamp("updated_at");
				
				//String created_at_text = new SimpleDateFormat("dd/MM/yyyy").format(created_at.getTime());
				String created_at_text = dateFormat.format(created_at.getTime());
				
				Purchase purchase = new Purchase();
				purchase.setId(id);
				purchase.setUser_id(user_id);
				purchase.setCreated_at(created_at);
				purchase.setCreated_at_text(created_at_text);
				
				lista.add(purchase);
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
		
		return new PurchaseList(lista);
	}

}
