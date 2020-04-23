package com.cedarvilleconnection.CedarvilleConnection.Reaction;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReactionPK implements Serializable {
	private static final long serialVersionUID = 832375193078074626L;
	
	@Basic(optional = false)
    @Column(name = "user_id")
    private long user;
	
    @Basic(optional = false)
    @Column(name = "post_id")
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
