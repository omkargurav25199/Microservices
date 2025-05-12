package com.example.restfulservices.demo_restapi_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulservices.demo_restapi_services.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
