package org.ecommerce.reviews;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listing")
public class ReviewList {
	private List<Review> items;

	public ReviewList() {
	}

	public ReviewList(List<Review> items) {
		this.items = items;
	}

	@XmlElement(name = "data")
	public List<Review> getItems() {
		return items;
	}
}
