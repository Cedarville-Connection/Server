package com.cedarvilleconnection.CedarvilleConnection.Reaction;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class ReactionPK implements Serializable {
	private static final long serialVersionUID = 832375193078074626L;
	
	@Basic(optional = false)
    @Column(name = "user_id", nullable = false, unique = true)
    private long user;
	
    @Basic(optional = false)
    @Column(name = "post_id", nullable = false, unique = true)
    private long post;
    
	public long getUser() {
		return user;
	}
	public void setUser(long user) {
		this.user = user;
	}
	public long getPost() {
		return post;
	}
	public void setPost(long post) {
		this.post = post;
	}
}
