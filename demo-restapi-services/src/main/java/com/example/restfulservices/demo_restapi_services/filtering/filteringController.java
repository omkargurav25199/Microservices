package com.example.restfulservices.demo_restapi_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class filteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering()
	{
		SomeBean someBean = new SomeBean("value1", "value2", "value3", "value4");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters );
		
		return mappingJacksonValue;
	}
	
	
	@GetMapping("/filtering-list")
	public List<SomeBean> filteringList()
	{
		return Arrays.asList(new SomeBean("Omkar", "pass1", "value3", "value4"), 
							new SomeBean("Shriya", "pass2", "value3", "value4"),
							new SomeBean("Swayambhu", "pass3", "value3", "value4"),
							new SomeBean("Sanjeev", "pass4", "value3", "value4"));
	}
	
}
