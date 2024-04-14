package com.java.inventory.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Table(name = "users")
@Entity
public class User implements UserDetails, Serializable {

	// Add serialVersionUID for class versioning
	private static final long serialVersionUID = 1874329503L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "userId", nullable = false)
	private String userId;

	@Column(name = "fullName", nullable = false)
	private String fullName;

	@Column(name = "email", unique = true, length = 100, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "jwtToken")
	private String jwtToken;

	@Column(name = "platform")
	private String platform;

	@Column(name = "deviceId")
	private String deviceId;

	@Column(name = "isActive", nullable = false)
	private boolean isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt", updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updatedAt", nullable = false)
	private Date updatedAt;

	@OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	@JoinColumn(name = "roleId", referencedColumnName = "id", nullable = false)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "userLogin", referencedColumnName = "id")
	@JsonManagedReference
	private UserLogin userLogin;

	public Role getRole() {
		return role;
	}

	public User setRole(Role role) {
		this.role = role;

		return this;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.getName().toString());

		return List.of(authority);
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return this.id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getJwtToken() {
		return this.jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getPlatform() {
		return this.platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserLogin getUserLogin() {
		return userLogin;
	}

	public User setUserLogin(UserLogin userLogin) {
		this.userLogin = userLogin;
		return this;
	}

	// Custom override method to return email as username (UserDetails)
	@Override
	public String getUsername() {
		if (email == null) {
			return "guest@gmail.com";
		} else {
			return this.email;
		}
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