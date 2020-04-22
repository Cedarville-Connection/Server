package com.cedarvilleconnection.CedarvilleConnection.Post;

import com.cedarvilleconnection.CedarvilleConnection.Post.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
//    Page<Post> findByPeopleId(Long userId, Pageable pageable);
//    Option<Post> findByIdAndPostId(Long id, Long userId);
}
