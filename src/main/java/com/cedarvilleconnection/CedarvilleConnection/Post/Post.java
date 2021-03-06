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
import com.cedarvilleconnection.CedarvilleConnection.Reaction.Reaction;

@Entity
@Table(name = "post")
@EntityListeners(AuditingEntityListener.class)
public class Post {

    private long id;
    private String content;
    private Timestamp timestamp;
    private String pt;
    
    @Transient
    private boolean currentUserLiked = false;

    @Column(name = "timestamp")
    public Timestamp getTimestamp() {
		return timestamp;
	}
    
	public void setTimestamp(Timestamp timestamp) {
		try {
			this.pt = new PrettyTime(ESTDate()).format(timestamp);
		} catch(Exception e) {
		}
		this.timestamp = timestamp;
	}
	
	@Transient
	public String getPrettyTime() {
		return this.pt;
	}

	@Transient
	public static Date ESTDate() {
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	

	private List<Reaction> reactions;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
	public List<Reaction> getReactions() {
		return reactions;
	}
	public void setReactions(List<Reaction> reactions) {
		this.reactions = reactions;
	}
	
	@Transient
	public boolean getUserLiked() {
		return currentUserLiked;
	}
	public void setUserHasLiked() {
		this.currentUserLiked = true;
	}
	
	@Transient
	public int getAmountOfLikes() {
		int amountOfLikes = 0;
		for(Reaction r : reactions) {
			if(r.getType() == Reaction.LIKE) {
				amountOfLikes++;
			}
		}
		return amountOfLikes;
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
