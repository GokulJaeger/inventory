package com.java.inventory.entity;

import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "name",unique = true, nullable = false)
    private String name;
    
    @Column(name = "cost", nullable = false)
    private String cost;
    
    @Column(name = "barcode", nullable = false)
    private String barcode;
    
    @Column(name = "quantity", nullable = false)
    private String quantity;
    
    @Column(name = "brand", nullable = false)
    private String brand;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "location", nullable = false)
    private String location;
    
    @Column(name = "image", nullable = false)
    private String image;
    
    @Column(name = "isActive", nullable = false)
	private boolean isActive;
    
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "utilId")
    @JsonBackReference
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
