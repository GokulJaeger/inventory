package com.java.inventory.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1878421503L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "cost")
    private String cost;
    
    @Column(name = "barcode")
    private String barcode;
    
    @Column(name = "quantity")
    private String quantity;
    
    @Column(name = "brand")
    private String brand;
    
    @Column(name = "desc1")
    private String desc1;
    
    @Column(name = "desc2")
    private String desc2;
    
    @Column(name = "desc3")
    private String desc3;
    
    @Column(name = "desc4")
    private String desc4;
    
    @Column(name = "desc5")
    private String desc5;
    
    @Column(name = "desc6")
    private String desc6;
    
    @Column(name = "desc7")
    private String desc7;
    
    @Column(name = "desc8")
    private String desc8;
    
    @Column(name = "desc9")
    private String desc9;
    
    @Column(name = "desc10")
    private String desc10;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "isActive", nullable = false)
	private boolean isActive;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "utilId", referencedColumnName = "id")
    @JsonManagedReference
    private Utils utils;

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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Utils getUtils() {
		return utils;
	}

	public void setUtils(Utils utils) {
		this.utils = utils;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@PrePersist
	private void prePersist() {
		Date currentDate = new Date();
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		createdAt = currentDate;
		updatedAt = currentDate;
	}

	@PreUpdate
	private void preUpdate() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		updatedAt = new Date();
	}
}
