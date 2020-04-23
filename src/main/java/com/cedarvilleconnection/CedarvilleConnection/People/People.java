package com.cedarvilleconnection.CedarvilleConnection.People;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "people")
@JsonIgnoreProperties({"posts","hibernateLazyInitializer", "handler", "follower", "following"})
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "People.findByName", query = "SELECT p FROM People p "
		+ "WHERE CONCAT(LOWER(p.first_name), ' ', LOWER(p.last_name)) like CONCAT('%', LOWER(?1), '%')")
@NamedQuery(name = "People.findByUsername", query = "SELECT p FROM People p, User u WHERE u.Username = ?1 and " +
	"u.Username = p.username")
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

	@Column(name = "username", nullable = false)
	private String Username;
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}

	private List<People> follower;
//	@ManyToMany(cascade = {CascadeType.ALL})
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="followers", joinColumns =@JoinColumn(name="user_id"),
			inverseJoinColumns = @JoinColumn(name ="friend_id")
	)
	public List<People> getFollower() {
		return follower;
	}
	public void setFollower(List<People> follower) {
		this.follower = follower;
	}
	public void removeFollower(People person){
		follower.remove(person);
	}
	public void addFollower(People person){
		follower.add(person);
	}

	private List<People> following;
	@ManyToMany(fetch = FetchType.LAZY,mappedBy = "follower")
//	@JsonIdentityInfo(
//			generator = ObjectIdGenerators.PropertyGenerator.class,
//			property = "id"
//	)
//	@JoinTable(name="followers", joinColumns =@JoinColumn(name="friend_id"),
//			inverseJoinColumns = @JoinColumn(name ="user_id")
//	)
	public List<People> getFollowing() {
		return following;
	}

	public void setFollowing(List<People> following) {
		this.following = following;
	}

	public void addFollowing(People person){
		following.add(person);
	}

	public void removeFollowing(People person){
		following.remove(person);
	}


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


