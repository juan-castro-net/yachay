package org.ecommerce.reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.ecommerce.cart.Item;
import org.ecommerce.util.BaseDato;

@Path("/review")
public class ReviewService {
	
	@POST
	@Path("/add")
	@Produces("application/json")
	public Response addReview(@Context HttpServletRequest request, Review review) {
		HttpSession session = request.getSession();
		long user_id = (long)session.getAttribute("user_id");
		String username = (String) session.getAttribute("username");
		
		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		PreparedStatement sentenciaPreparada1 = null;
		String sql = "";
		String mensaje = "";
		int insertados = 0;
		
		try {
			conexion1 = basedato.getConnection();
						
			sql = "INSERT INTO reviews (product_id, user_id, rating, comment)";
			sql += " VALUES (?, ?, ?, ?)";
			
			sentenciaPreparada1 = conexion1.prepareStatement(sql); 
			sentenciaPreparada1.setLong(1, review.getProduct_id()); 
			sentenciaPreparada1.setLong(2, user_id);
			sentenciaPreparada1.setDouble(3, review.getRating());
			sentenciaPreparada1.setString(4, review.getComment());
			
			insertados = sentenciaPreparada1.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			basedato.close(sentenciaPreparada1);
			basedato.close(conexion1);
		}
		
		if(insertados > 0){ 
			mensaje = "{\"message\":\"Review inserted successfully\"}"; 
			return Response.status(200).entity(mensaje).build(); 
		} 
		else{ 
			mensaje = "{\"message\":\"Error inserting review\"}"; 
			return Response.status(400).entity(mensaje).build(); 
		}
		
	}

	
	@GET
	@Path("/list/{product_id}")
	@Produces("application/json")
	public ReviewList getReviews(@PathParam(value = "product_id") int product_id) {
		ArrayList lista = new ArrayList();
		BaseDato basedato = new BaseDato();
		Connection conexion1 = null;
		Statement sentencia1 = null;
		ResultSet rs1 = null;
		String sql = "";
		try {
			conexion1 = basedato.getConnection();
			sentencia1 = conexion1.createStatement();
			
			sql = "select * from reviews, users where reviews.user_id = users.id";
			sql = sql + " and reviews.product_id = " + product_id;
			sql = sql + " order by reviews.created_at DESC";
			
			rs1 = sentencia1.executeQuery(sql);
			while (rs1.next()) {
				long id = rs1.getLong("id");
				long user_id = rs1.getLong("user_id");
				String username = rs1.getString("username");
				//long product_id = rs1.getLong("product_id");
				double rating = rs1.getDouble("rating");
				String comment = rs1.getString("comment");
				Timestamp created_at = rs1.getTimestamp("created_at");
				
				String created_at_text = new SimpleDateFormat("dd/MM/yyyy").format(created_at.getTime());
				
				Review review = new Review();
				review.setId(id);
				review.setUser_id(user_id);
				review.setUsername(username);
				review.setComment(comment);
				review.setRating(rating);
				review.setCreated_at(created_at);
				review.setCreated_at_text(created_at_text);
				lista.add(review);
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		finally {
			basedato.close(rs1);
			basedato.close(sentencia1);
			basedato.close(conexion1);
		}
		
		return new ReviewList(lista);
	}
}
