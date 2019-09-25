package org.ecommerce.purchase;

import java.sql.Timestamp;

public class Purchase {
	long id;
	long user_id;
	Timestamp created_at;
	String created_at_text; 
	Timestamp updated_at;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public String getCreated_at_text() {
		return created_at_text;
	}
	public void setCreated_at_text(String created_at_text) {
		this.created_at_text = created_at_text;
	}
	
}
