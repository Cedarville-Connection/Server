package com.cedarvilleconnection.CedarvilleConnection.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedarvilleconnection.CedarvilleConnection.model.PeoplePost;

@Repository
public interface PeoplePostRepository extends JpaRepository<PeoplePost, Long> {

}
