package com.cedarvilleconnection.CedarvilleConnection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "PeoplePost")
@Table(name = "people_post")
@EntityListeners(AuditingEntityListener.class)
public class PeoplePost {

    private long userId;
    private long postId;
    
    @Id
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long id){
        this.userId = id;
    }

    @Column(name = "post_id")
    public long getContent(){
        return this.postId;
    }

    public void setContent(long postId){
        this.postId = postId;
    }
}