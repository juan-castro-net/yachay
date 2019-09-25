package org.ecommerce.category;

import java.sql.Timestamp;

public class Category {
	long id;
	int published;
	String name;
	String icon;
	Timestamp created_at;
	Timestamp updated_at;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
		this.published = published;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
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
	
}
