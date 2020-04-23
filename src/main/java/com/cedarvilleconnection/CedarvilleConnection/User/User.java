package com.cedarvilleconnection.CedarvilleConnection.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.cedarvilleconnection.CedarvilleConnection.Post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    private long Id;
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        this.Id = id;
    }

    @Column(name = "username", nullable = false)
    private String Username;
    public String getUsername() {
        return Username;
    }
    public void setUsername(String username) {
        this.Username = username;
    }

    @Column(name = "password", nullable = false)
    private String Password;
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        this.Password = password;
    }

    @Column(name = "person_id", nullable = false)
    private boolean PersonId;
    public boolean getPerson() {
        return PersonId;
    }
    public void setPerson(boolean personId) {
        this.PersonId = personId;
    }

    @Column(name = "enabled", nullable = false)
    private boolean Enabled;
    public boolean isEnabled() {
        return Enabled;
    }
    public void setEnabled(boolean enabled) {
        this.Enabled = enabled;
    }

}
