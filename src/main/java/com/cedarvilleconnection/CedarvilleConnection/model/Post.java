package com.cedarvilleconnection.CedarvilleConnection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    private long id;
    private String content;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return this.id;
    }
    public void setId(long id){
        this.id = id;
    }

    @Column(name = "content")
    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }
}
