package com.cedarvilleconnection.CedarvilleConnection.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.model.Post;

@Entity
@Table(name = "people")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "People.findByName", query = "SELECT p FROM People p "
		+ "WHERE CONCAT(LOWER(p.first_name), ' ', LOWER(p.last_name)) like CONCAT('%', LOWER(?1), '%')")
public class People {
	private long id;

	@Column(name = "first_name", nullable = false)
	private String first_name;

	@Column(name = "last_name", nullable = false)
	private String last_name;

	private String profilePic;
	private String address;
	private String email;
	private int gender;
	private Date date;

	private Set<Post> post;

	@OneToMany(mappedBy = "user")
	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}


	@Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String firstName) {
		this.first_name = firstName;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String lastName) {
		this.last_name = lastName;
	}

	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "profile_pic", nullable = false)
	public String getProfile_pic() {
		return profilePic;
	}

	public void setProfile_pic(String profilePic) {
		this.profilePic = profilePic;
	}

	@Column(name = "address", nullable = false)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "gender", nullable = false)
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "dob", nullable = false)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}


