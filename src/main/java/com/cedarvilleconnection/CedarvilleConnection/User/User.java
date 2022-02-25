package com.cedarvilleconnection.CedarvilleConnection.User;

import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "User.findByUsername", query = "SELECT u from User u WHERE u.Username = ?1")
public class User {
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator="gen")
	@Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
	private long id;
	public long getId() {return id;	}
	public void setId(long id) {this.id = id;}


	@Column(name = "username", nullable = false)
	private String Username;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

	@Column(name = "password", nullable = false)
	private String Password;
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	@Column(name = "enabled", nullable = false)
	private boolean Enabled;
	public boolean isEnabled() {
		return Enabled;
	}
	public void setEnabled(boolean enabled) {
		Enabled = enabled;
	}


}


