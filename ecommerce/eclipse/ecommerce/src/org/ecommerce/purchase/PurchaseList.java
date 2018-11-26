package org.ecommerce.purchase;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listing")
public class PurchaseList {
	private List<Purchase> items;

	public PurchaseList() {
	}

	public PurchaseList(List<Purchase> items) {
		this.items = items;
	}

	@XmlElement(name = "data")
	public List<Purchase> getItems() {
		return items;
	}
}
