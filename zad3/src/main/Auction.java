package main;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Auction implements Serializable {
	
	private String category;
	private String name;
	private int price;
	private String description;
	private Date endTime;
	
		
	public Auction(String category, String name, String price, String description,
			String endTime) {
		super();
		this.category = category;
		this.name = name;		
		this.price = Integer.parseInt(price);
		this.description = description;
		
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, Integer.parseInt(endTime));
        this.endTime = calendar.getTime();
	}
	
	public Auction(String category, String name, String price) {
		super();
		this.category = category;
		this.name = name;		
		this.price = Integer.parseInt(price);
	}
	
	
	public String printDescription(){
		String desc="";
		desc += " Category: " + category + "\n";
		desc += " Name: " + name+ "\n";
		desc += " Current price: " + price+ "\n";
		desc += " Description: " + description+ "\n";
		desc += " End time: " + endTime+ " sec\n";
		return desc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auction other = (Auction) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	


	public void setPrice(int price) {
		this.price = price;
	}	

	public int getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

	public Date getEndTime() {
		return endTime;
	}
	
		
}
