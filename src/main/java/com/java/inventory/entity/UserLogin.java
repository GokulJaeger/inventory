package com.java.inventory.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.TimeZone;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "userLogin")
public class UserLogin implements Serializable {
	
	// Add serialVersionUID for class versioning
    private static final long serialVersionUID = 87540234874L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
	@JsonBackReference
	private User user;

	@Column(name = "email", unique = true, length = 100, nullable = false)
	private String email;
	
	@Column(name = "accessToken", nullable = false)
	private String accessToken;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loggedAt", nullable = false)
	private Date loggedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Date getLoggedAt() {
		return loggedAt;
	}

	public void setLoggedAt(Date loggedAt) {
		this.loggedAt = loggedAt;
	}
	
	@PrePersist
	private void prePersist() {
		Date currentDate = new Date();
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		loggedAt = currentDate;
	}

	@PreUpdate
	private void preUpdate() {
		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
		loggedAt = new Date();
	}


}
