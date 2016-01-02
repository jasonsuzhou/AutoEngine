package test.com.mh.base.persist.impl;

public class Order {

	private int id;// ORDER_ID
	private String orderNo;// ORDER_NO
	private float orderPrice;// ORDER_PRICE

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public float getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(float orderPrice) {
		this.orderPrice = orderPrice;
	}

}
