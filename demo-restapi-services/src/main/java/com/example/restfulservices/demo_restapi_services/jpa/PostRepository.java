package com.example.restfulservices.demo_restapi_services.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restfulservices.demo_restapi_services.user.Post;


public interface PostRepository extends JpaRepository<Post, Integer> {

}
