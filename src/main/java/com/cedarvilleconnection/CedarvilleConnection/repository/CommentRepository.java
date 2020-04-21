package com.cedarvilleconnection.CedarvilleConnection.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedarvilleconnection.CedarvilleConnection.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
}