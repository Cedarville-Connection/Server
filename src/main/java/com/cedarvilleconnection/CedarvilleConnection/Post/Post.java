package com.cedarvilleconnection.CedarvilleConnection.Post;

import java.util.List;

import javax.persistence.*;

import com.cedarvilleconnection.CedarvilleConnection.Comment.Comment;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.People.People;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    private long id;
    private String content;


    private People user;
    @ManyToOne
    @JoinColumn(foreignKey=@ForeignKey(name="user_id"))
    public People getUser() {
        return user;
    }

    public void setUser(People user) {
        this.user = user;
    }
    
    
    
    private List<Comment> comments;
    
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}

	public void removePost(Comment comment) {
		this.comments.remove(comment);
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
