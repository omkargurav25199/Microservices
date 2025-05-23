package com.example.restfulservices.demo_restapi_services.user;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {
	
	@Autowired
	private UserDaoService userService;
	
	public UserResource(UserDaoService userService)
	{
		this.userService = userService;
	}
	
	//Get all users
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers()
	{
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserDetails(@PathVariable int id)
	{
		User user = userService.findById(id);
		
		if(user == null)
		{
			throw new UserNotFoundException("id:"+id);
		}
		
		// We are using Hateoas for sending links and data together both
		EntityModel<User> entityModel = EntityModel.of(user);
		
		WebMvcLinkBuilder link =  linkTo(methodOn(UserResource.class).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserDetails(@PathVariable int id)
	{
		userService.deleteUserById(id);
		
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User createdUser = userService.createUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	

}
