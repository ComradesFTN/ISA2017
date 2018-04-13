package isa.projekat.domain;

public class Bid {
	
	private long userId;
	
	private int price;

	public Bid(long userId, int price) {
		super();
		this.userId = userId;
		this.price = price;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getprice() {
		return price;
	}

	public void setprice(int price) {
		this.price = price;
	}

}
