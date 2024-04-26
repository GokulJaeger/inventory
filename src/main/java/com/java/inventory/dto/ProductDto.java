package com.java.inventory.dto;

import java.util.List;

import com.java.inventory.entity.Product;
import com.java.inventory.entity.Utils;

public class ProductDto {
	private Integer id;
	private String name;
	private String cost;
	private String barcode;
	private String quantity;
	private String brand;
	private String desc1;
	private String desc2;
	private String desc3;
	private String desc4;
	private String desc5;
	private String desc6;
	private String desc7;
	private String desc8;
	private String desc9;
	private String desc10;
	private String location;
	private String image;
	private Utils utils;
	private List<Product> productList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDesc1() {
		return desc1;
	}
	public void setDesc1(String desc1) {
		this.desc1 = desc1;
	}
	public String getDesc2() {
		return desc2;
	}
	public void setDesc2(String desc2) {
		this.desc2 = desc2;
	}
	public String getDesc3() {
		return desc3;
	}
	public void setDesc3(String desc3) {
		this.desc3 = desc3;
	}
	public String getDesc4() {
		return desc4;
	}
	public void setDesc4(String desc4) {
		this.desc4 = desc4;
	}
	public String getDesc5() {
		return desc5;
	}
	public void setDesc5(String desc5) {
		this.desc5 = desc5;
	}
	public String getDesc6() {
		return desc6;
	}
	public void setDesc6(String desc6) {
		this.desc6 = desc6;
	}
	public String getDesc7() {
		return desc7;
	}
	public void setDesc7(String desc7) {
		this.desc7 = desc7;
	}
	public String getDesc8() {
		return desc8;
	}
	public void setDesc8(String desc8) {
		this.desc8 = desc8;
	}
	public String getDesc9() {
		return desc9;
	}
	public void setDesc9(String desc9) {
		this.desc9 = desc9;
	}
	public String getDesc10() {
		return desc10;
	}
	public void setDesc10(String desc10) {
		this.desc10 = desc10;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Utils getUtils() {
		return utils;
	}
	public void setUtils(Utils utils) {
		this.utils = utils;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
}
