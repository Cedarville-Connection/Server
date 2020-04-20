package com.cedarvilleconnection.CedarvilleConnection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "people_post_comment")
public class PeoplePostComment {
	
	@Column(name = "comment_user_id", nullable = false)
	private long comment_user_id;
	
	@Column(name = "comment_id", nullable = false)
	private long comment_id;
	
	@Column(name = "post_id", nullable = false)
	private long post_id;

	public long getComment_user_id() {
		return comment_user_id;
	}

	public void setComment_user_id(long comment_user_id) {
		this.comment_user_id = comment_user_id;
	}

	public long getComment_id() {
		return comment_id;
	}

	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}

	public long getPost_id() {
		return post_id;
	}

	public void setPost_id(long post_id) {
		this.post_id = post_id;
	}
	
}
