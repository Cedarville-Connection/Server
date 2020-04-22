package com.cedarvilleconnection.CedarvilleConnection.Post;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.persistence.*;

import com.cedarvilleconnection.CedarvilleConnection.Comment.Comment;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.People.People;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    private long id;
    private String content;
    private Timestamp timestamp;
    private String pt;

    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
		return timestamp;
	}
    
	public void setTimestamp(Timestamp timestamp) {
		this.pt = new PrettyTime(getESTDate()).format(timestamp);
		this.timestamp = timestamp;
	}
	
	@Transient
	public String getPrettyTime() {
		return this.pt;
	}

	@Transient
	public Date getESTDate() {
		// FIXME: not working correctly
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		f.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
		try {
			return f.parse(f.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


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
