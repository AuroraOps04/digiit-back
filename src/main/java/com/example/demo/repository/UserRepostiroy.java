package com.example.demo.repository;

import com.example.demo.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepostiroy extends JpaRepository<User, Integer> {

  User findByUsername(String username);

}
