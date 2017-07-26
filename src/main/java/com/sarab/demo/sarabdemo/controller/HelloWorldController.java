package com.sarab.demo.sarabdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sarab.demo.sarabdemo.service.GreetingService;

@RestController
public class HelloWorldController {
	
	@Autowired
	private GreetingService greetingService;
	
	@RequestMapping(value = "/hello/{personName}", method=RequestMethod.GET)
	public String helloWorld(@PathVariable(name="personName") String personName) {
		return greetingService.getGreeting() + " " + personName;
	}	
}
