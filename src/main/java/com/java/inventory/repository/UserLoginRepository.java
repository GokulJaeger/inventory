package com.java.inventory.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.java.inventory.entity.UserLogin;


@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

//	@Query("FROM UserLogin WHERE userId = (:id)")
//	public UserLogin findUserLoginById(@Param("id") Integer userId);

	Optional<UserLogin> findById(Integer id);
}
