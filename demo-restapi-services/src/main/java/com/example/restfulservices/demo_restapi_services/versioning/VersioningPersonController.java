package com.example.restfulservices.demo_restapi_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	
	
	//URI Versioning
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson()
	{
		return new PersonV1("Omkar Gurav");
	}
	
	@GetMapping("/v2/person")
	public PersonV2 getSeccondVersionOfPerson()
	{
		return new PersonV2("Omkar", "Gurav");
		
	}
	
	//Parameterized Versioning
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter()
	{
		return new PersonV1("Hey Omkar Gurav");
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSeccondVersionOfPersonRequestParameter()
	{
		return new PersonV2("Omkar", "Gurav");
		
	}
	
	
	//Header Versioning
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader()
	{
		return new PersonV1("Hey Omkar Gurav");
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader()
	{
		return new PersonV2("Omkar", "Gurav");
	}
	
	
//	//MediaType Versioning

	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader()
	{
		return new PersonV1("Hey Omkar Gurav");
	}
	
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader()
	{
		return new PersonV2("Omkar", "Gurav");
	}

}
