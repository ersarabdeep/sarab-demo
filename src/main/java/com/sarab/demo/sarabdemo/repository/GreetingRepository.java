package com.sarab.demo.sarabdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sarab.demo.sarabdemo.model.Greeting;

public interface GreetingRepository extends JpaRepository<Greeting, Integer> {

}
