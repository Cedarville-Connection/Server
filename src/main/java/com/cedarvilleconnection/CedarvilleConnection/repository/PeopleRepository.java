package com.cedarvilleconnection.CedarvilleConnection.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedarvilleconnection.CedarvilleConnection.model.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

}
