package com.cedarvilleconnection.CedarvilleConnection.Authority;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
@EntityListeners(AuditingEntityListener.class)
@NamedQuery(name = "Authority.findByUsername", query = "SELECT authority FROM Authority WHERE username = ?1")
public class Authority implements Serializable {
    @Id
    @GenericGenerator(name="gen",strategy="increment")
    @GeneratedValue(generator="gen")
    @Column(name = "ID", unique = true, nullable = false, precision = 15, scale = 0)
    int id;

    @Column(name = "username", nullable = false)
    String username;

    @Column(name = "authority", nullable = false)
    String authority;

    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
