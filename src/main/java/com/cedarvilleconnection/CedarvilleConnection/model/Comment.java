package com.cedarvilleconnection.CedarvilleConnection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	private long id;
	
	@Column(name = "contents", nullable = false)
	private String contents;
	
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
