package org.ecommerce.cart;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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

import org.ecommerce.util.BaseDato;

@Path("/cart")
public class ItemService {

	@GET
	@Path("/items")
	@Produces("application/json")
	//@Consumes("application/json")
	public Response getItemsCount(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		ArrayList cart = (ArrayList) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList();
		}

		long items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/update")
	@Produces("application/json")
	//@Consumes("application/json")
	public Response updateItems(@Context HttpServletRequest request, String data) {

		JsonReader reader = Json.createReader(new StringReader(data));
		JsonObject jsonObject = reader.readObject();
		JsonArray jArray = jsonObject.getJsonArray("data");

		if (jArray.size() == 0) {
		}

		HttpSession session = request.getSession();
		ArrayList cart = (ArrayList) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList();
		}

		for (int i = 0; i < jArray.size(); i++) {
			JsonObject o = jArray.get(i).asJsonObject();

			int product_id = o.getInt("product_id");
			double quantity = (double) o.getInt("quantity");

			Item item = (Item) cart.get(i);
			item.setQuantity(quantity);

			cart.set(i, item);
		}

		session.setAttribute("cart", cart);

		for (int i = 0; i < cart.size(); i = i + 1) {
			Item item = (Item) cart.get(i);
			long id = item.getProduct_id();
			double qty = item.getQuantity();
			System.out.println(id + "," + qty);
		}

		long items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/add/{product_id}")
	@Produces("application/json")
	public Response addItem(@Context HttpServletRequest request, @PathParam(value = "product_id") int product_id) {
		HttpSession session = request.getSession();
		ArrayList cart = (ArrayList) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList();
		}

		for (int i = 0; i < cart.size(); i = i + 1) {
			Item item = (Item) cart.get(i);
			long id = item.getProduct_id();

			if (id == product_id) {
				long items = cart.size();
				String result = "{\"items\":" + items + "}";
				return Response.status(401).entity(result).build();
			}
		}

		Item item = new Item();
		item.setProduct_id(product_id);
		item.setQuantity(1);

		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		Statement sentencia1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			conexion1 = basedato.getConnection();
			sentencia1 = conexion1.createStatement();
			sql = "select * from products where id = " + product_id;
			rs1 = sentencia1.executeQuery(sql);

			if (rs1.next()) {
				// long id = rs1.getInt("id");
				String name = rs1.getString("name");
				String short_description = rs1.getString("short_description");
				double pricing = rs1.getDouble("pricing");
				String icon = rs1.getString("icon");

				item.setProduct_name(name);
				item.setProduct_description(short_description);
				item.setPrice(pricing);
				item.setProduct_icon(icon);
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			basedato.close(rs1);
			basedato.close(sentencia1);
			basedato.close(conexion1);
		}
		
		cart.add(item);

		session.setAttribute("cart", cart);

		long items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/del/{product_id}")
	@Produces("application/json")
	public Response delItem(@Context HttpServletRequest request, @PathParam(value = "product_id") int product_id) {
		HttpSession session = request.getSession();
		ArrayList cart = (ArrayList) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList();
		}

		for (int i = 0; i < cart.size(); i = i + 1) {
			Item item = (Item) cart.get(i);
			long id = item.getProduct_id();

			if (id == product_id) {
				cart.remove(i);
			}
		}

		session.setAttribute("cart", cart);

		long items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

	@GET
	@Path("/list")
	@Produces("application/json")
	public ItemList getItems(@Context HttpServletRequest request) {
		ArrayList<Item> cart = new ArrayList<Item>();

		HttpSession session = request.getSession();

		cart = (ArrayList<Item>) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList<Item>();
		}

		return new ItemList(cart);
	}

	public int insertPurchase(long user_id) {
		int purchase_id = 0;

		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		//Statement sentencia1 = null;
		ResultSet rs1 = null;
		PreparedStatement sentenciaPreparada1 = null;
		String sql = "";
		
		try {
			conexion1 = basedato.getConnection();
			
			sql = "INSERT INTO purchases (user_id)";
			sql = sql + " VALUES (?) RETURNING id";
			
			sentenciaPreparada1 = conexion1.prepareStatement(sql);
			sentenciaPreparada1.setLong(1, user_id);
			rs1 = sentenciaPreparada1.executeQuery();
			
			if(rs1.next()){ 
				purchase_id = rs1.getInt("id");
			}
			
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			basedato.close(rs1);
			basedato.close(sentenciaPreparada1);
			basedato.close(conexion1);
		}
		
		return purchase_id;
	}

	@GET
	@Path("/checkout")
	@Produces("application/json")
	public Response updateItems(@Context HttpServletRequest request) {
		HttpSession session = request.getSession();
		long user_id = (long)session.getAttribute("user_id");
		String username = (String) session.getAttribute("username");
		ArrayList cart = (ArrayList) session.getAttribute("cart");

		if (cart == null) {
			cart = new ArrayList();
		}

		if (cart.size() > 0) {
			long purchase_id = insertPurchase(user_id);
			
			BaseDato basedato = new BaseDato();
			Connection conexion1 = null;
			Statement sentencia1 = null;
			ResultSet rs1 = null;
			String sql = "";
			String mensaje = "";
			int insertados = 0;
			
			sql = "INSERT INTO purchase_items (purchase_id, product_id, price, quantity) VALUES ";
			String separator = "";
			for (int i = 0; i < cart.size(); i = i + 1) {
				Item item = (Item) cart.get(i);
				long product_id = item.getProduct_id();
				double quantity = item.getQuantity();
				double price = item.getPrice();
				
				sql = sql + separator + "(" + purchase_id + "," + product_id + "," + price + "," + quantity + ")";
				separator = ", "; 
			}
		
			try {
				conexion1 = basedato.getConnection();
				sentencia1 = conexion1.createStatement();
				insertados = sentencia1.executeUpdate(sql);		
			} 
			catch (Exception e) {
				System.out.println("Error: " + e.toString());
			}
			finally {
				basedato.close(rs1);
				basedato.close(sentencia1);
				basedato.close(conexion1);
			}
		}
		
		cart = new ArrayList();
		session.setAttribute("cart", cart);

		long items = cart.size();
		String result = "{\"items\":" + items + "}";
		return Response.status(201).entity(result).build();
	}

}
