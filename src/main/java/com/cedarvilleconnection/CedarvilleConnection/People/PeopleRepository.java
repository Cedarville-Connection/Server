package com.cedarvilleconnection.CedarvilleConnection.People;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cedarvilleconnection.CedarvilleConnection.People.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {
	public List<People> findByName(String fullName);
	public People findByUsername(String username);
}
