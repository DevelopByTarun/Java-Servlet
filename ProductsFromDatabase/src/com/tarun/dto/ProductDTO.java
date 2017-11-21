package com.tarun.dto;

public class ProductDTO {
	
	/*
	 * declare variables
	 */
	private int id;
	private String name;
	private String descr;
	private String image;
	private double price;
	private String status;
	
	/*
	 * create setters and getters
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDesc(String descr) {
		this.descr = descr;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
