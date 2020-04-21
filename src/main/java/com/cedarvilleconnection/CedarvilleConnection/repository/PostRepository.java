package com.cedarvilleconnection.CedarvilleConnection.repository;

import com.cedarvilleconnection.CedarvilleConnection.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
//    Page<Post> findByPeopleId(Long userId, Pageable pageable);
//    Option<Post> findByIdAndPostId(Long id, Long userId);
}
