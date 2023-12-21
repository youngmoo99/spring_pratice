package com.in28minutes.ret.webservices.restfulwebservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.in28minutes.ret.webservices.restfulwebservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
