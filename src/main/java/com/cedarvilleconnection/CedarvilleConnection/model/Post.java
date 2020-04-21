package com.cedarvilleconnection.CedarvilleConnection.model;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.model.People;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    private long id;
    private String content;


    private People user;
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    public People getUser() {
        return user;
    }

    public void setUser(People user) {
        this.user = user;
    }


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
