package com.cedarvilleconnection.CedarvilleConnection.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cedarvilleconnection.CedarvilleConnection.People.People;
import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "comment")
@JsonIgnoreProperties({"post"})
public class Comment {
	private long id;
	
	@Column(name = "contents", nullable = false)
	private String contents;
	
	private Post post;
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="post_id"))
    public Post getPost() {
        return post;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    
    private People user;
    @OneToOne
    @JoinColumn(foreignKey=@ForeignKey(name="user_id"))
    public People getUser() {
        return user;
    }
    public void setUser(People user) {
        this.user = user;
    }
	
	@Id
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getContents() {
		return this.contents;
	}
	
	public void setContents(String newContents) {
		this.contents = newContents;
	}
}
