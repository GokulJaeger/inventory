package com.java.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.inventory.entity.Utils;

public interface UtilsRepository extends JpaRepository<Utils, Integer> {

	Optional<Utils> findByName(String name);

	void deleteByName(String name);
}
