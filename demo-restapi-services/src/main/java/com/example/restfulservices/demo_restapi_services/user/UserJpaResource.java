package com.example.restfulservices.demo_restapi_services.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.example.restfulservices.demo_restapi_services.jpa.PostRepository;
import com.example.restfulservices.demo_restapi_services.jpa.UserRepository;

import jakarta.persistence.PostRemove;
import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	
	private UserRepository repository;
	
	private PostRepository postRepository;
	
	public UserJpaResource( UserRepository repository, PostRepository postRepository)
	{
		this.repository = repository;
		this.postRepository = postRepository;
	}
	
	//Get all users
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return repository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public EntityModel<User> retrieveUserDetails(@PathVariable int id)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id:"+id);
		}
		
		// We are using Hateoas for sending links and data together both
		EntityModel<User> entityModel = EntityModel.of(user.get());
		
		WebMvcLinkBuilder link =  linkTo(methodOn(UserJpaResource.class).retrieveAllUsers());
		
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUserDetails(@PathVariable int id)
	{
		repository.deleteById(id);
		
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user)
	{
		User createdUser = repository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(createdUser.getId())
						.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retrievePostsForUser(@PathVariable int id)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id: "+ id);
		}
		
		return user.get().getPosts();
		
	}
	
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post)
	{
		Optional<User> user = repository.findById(id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id: "+ id);
		}
		
		post.setUser(user.get());
		
		@Valid
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{user_id}/posts/{post_id}")
	public Post reterievePostForUserWithPostId(@PathVariable int user_id, @PathVariable int post_id)
	{
		
		Optional<User> user = repository.findById(user_id);
		
		if(user.isEmpty())
		{
			throw new UserNotFoundException("id: "+ user_id);
		}
		
		
		List<Post> posts = user.get().getPosts();
		
		 
		Optional<Post> result = posts.stream()
                .filter(post -> post.getId().equals(post_id)) // Check if the post ID matches
                .findFirst(); // Return the first match (if any)

        return result.orElse(null); // Return the post or null if not found
    
	
		
	}
	
	
	
	

}
