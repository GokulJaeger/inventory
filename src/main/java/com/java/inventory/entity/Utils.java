package com.java.inventory.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "utils")
public class Utils {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    
	    @Column(name = "name")
	    private String name;
	    
	    @Temporal(TemporalType.TIMESTAMP)
		@Column(name = "createdAt", updatable = false)
		private Date createdAt;

		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "updatedAt", nullable = false)
		private Date updatedAt;
	        
	    @OneToMany(mappedBy = "utils", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    @JsonManagedReference
	    private Set<Product> products = new HashSet<>();
	    
	    @Column(name = "isActive", nullable = false)
		private boolean isActive;

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

		public Set<Product> getProducts() {
			return products;
		}

		public void setProducts(Set<Product> products) {
			this.products = products;
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
