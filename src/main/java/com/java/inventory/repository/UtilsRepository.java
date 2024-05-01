package com.java.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import com.java.inventory.entity.Utils;

public interface UtilsRepository extends JpaRepository<Utils, Integer> {

//	@Query("FROM Utils WHERE name = (:uname) AND isActive = true")
//	Optional<Utils> findByName(@Param(value = "uname") String name);
	Optional<Utils> findByName(String name);

	void deleteByName(String name);
}
