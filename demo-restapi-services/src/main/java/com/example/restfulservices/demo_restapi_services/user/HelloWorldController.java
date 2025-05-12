package com.example.restfulservices.demo_restapi_services.user;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
		private MessageSource messageSource;
		Locale locale = LocaleContextHolder.getLocale();
		
		//Constructor Injection
		public HelloWorldController(MessageSource messageSource)
		{
			this.messageSource = messageSource;
			
		}
		
		@GetMapping(path = "/hello-world")
		public String helloWorld()
		{
			return "Hey, Hello World!!";
		}
		
		@GetMapping(path = "/hello-world-internationalized")
		public String helloWorldInt()
		{
			return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
		}
		
		
		
}


