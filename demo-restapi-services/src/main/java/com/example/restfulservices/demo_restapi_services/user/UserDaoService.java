package com.example.restfulservices.demo_restapi_services.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	
	private static int usersCount = 0;
	
	private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Eve", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Jim", LocalDate.now().minusYears(20)));
		users.add(new User(++usersCount, "Jones", LocalDate.now().minusYears(40)));
	}
	

	public List<User> findAll() {
		
		return users;
	}


	public User findById(Integer id) {
		
		return users.stream()
				.filter(user -> user.getId().equals(id))
				.findFirst().orElse(null);
	}


	public User createUser(User user) {
		
		user.setId(++usersCount);
		users.add(user);
		return user;
		
	}


	public void deleteUserById(int id) {
		
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		
		users.removeIf(predicate);
		
	}

}
