package org.ecommerce.reviews;

import java.sql.Timestamp;

public class Review {
	long id;
	long product_id;
	long user_id;
	String username;
	double rating;
	String comment;
	int approved;
	int spam;
	Timestamp created_at;
	String created_at_text;
	Timestamp updated_at;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getApproved() {
		return approved;
	}
	public void setApproved(int approved) {
		this.approved = approved;
	}
	public int getSpam() {
		return spam;
	}
	public void setSpam(int spam) {
		this.spam = spam;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public String getCreated_at_text() {
		return created_at_text;
	}
	public void setCreated_at_text(String created_at_text) {
		this.created_at_text = created_at_text;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	
}
