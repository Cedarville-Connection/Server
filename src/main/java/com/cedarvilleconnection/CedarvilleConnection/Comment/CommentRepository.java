package com.cedarvilleconnection.CedarvilleConnection.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedarvilleconnection.CedarvilleConnection.Comment.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}