package com.cedarvilleconnection.CedarvilleConnection.Reaction;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cedarvilleconnection.CedarvilleConnection.People.People;
import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "reaction")
@JsonIgnoreProperties({"post", "user"})
public class Reaction implements Serializable {
	private static final long serialVersionUID = -3886610813005318839L;

	
	public static final int LIKE = 1;
	
	@EmbeddedId
    protected ReactionPK reactionPk;
	
	@Column(name = "type")
    private int type;

    @Column(name = "status")
    private String status;
    @JoinColumn(name = "post_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Post post;
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private People user;
    
    
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		if(reactionPk == null) {
			reactionPk = new ReactionPK();
		}
		reactionPk.setPost(post.getId());
		this.post = post;
	}
	public People getUser() {
		return user;
	}
	public void setUser(People user) {
		if(reactionPk == null) {
			reactionPk = new ReactionPK();
		}
		reactionPk.setUser(user.getId());
		this.user = user;
	}
	
	@Transient
    public long getPostId() {
    	return reactionPk.getPost();
    }
	@Transient
    public long getUserId() {
    	return reactionPk.getUser();
    }
    
    
}
