package com.cedarvilleconnection.CedarvilleConnection.People;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "people")
@JsonIgnoreProperties({"posts"})
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "People.findByName", query = "SELECT p FROM People p "
		+ "WHERE CONCAT(LOWER(p.first_name), ' ', LOWER(p.last_name)) like CONCAT('%', LOWER(?1), '%')")
@NamedQuery(name = "People.findByUsername", query = "SELECT p FROM User u, People p WHERE u.Username = ?1 " +
	"AND u.PersonId = p.id")
public class People {
	@Column(name = "id", nullable = false)
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

	private List<Post> posts;
//	public void addPost(Post post) {
//		this.posts.add(post);
//	}
//
//	public void removePost(Post post) {
//		this.posts.remove(post);
//	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public List<Post> getPosts() {
		return this.posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
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


