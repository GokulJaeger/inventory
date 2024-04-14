package com.java.inventory.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table(name = "roles")
@Entity
public class Role implements Serializable {
	
	// Add serialVersionUID for class versioning
    private static final long serialVersionUID = 83749273234L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(name = "description", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, name = "createdAt")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedAt")
    private Date updatedAt;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEnum getName() {
		return this.name;
	}

	public void setName(RoleEnum name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
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
