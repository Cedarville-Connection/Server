package com.cedarvilleconnection.CedarvilleConnection.Reaction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cedarvilleconnection.CedarvilleConnection.People.People;
import com.cedarvilleconnection.CedarvilleConnection.Post.Post;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
//	public List<Reaction> findByIds(long postId, long userId);
//	Reaction findByPostIdAndUserId(long postId, long userId);
	Reaction findByPostAndUser(Post post, People user);
}
