package com.greedy.product;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name="product_test")
@Table(name="TBL_PRODUCT")
@SequenceGenerator(name="productSeq", sequenceName = "SEQ_PRODUCT_NO")
public class Product {
	
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productSeq")
	private int productId;
	
	@Column(name="PRODUCT_NAME", nullable = false)
	private String name;
	@Column(name="PRODUCT_PRICE", nullable = false)
	private int price;
	@Column(name="RELEASE_DATE", nullable = false)
	private java.util.Date releaseDate;
	
	@Column(name="CATEGORY", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductCategory category;
	
	
	public Product() {}


	public Product(int productId, String name, int price, Date releaseDate, ProductCategory category) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.releaseDate = releaseDate;
		this.category = category;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public java.util.Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(java.util.Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public ProductCategory getCategory() {
		return category;
	}


	public void setCategory(ProductCategory category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", releaseDate="
				+ releaseDate + ", category=" + category + "]";
	}
	
}