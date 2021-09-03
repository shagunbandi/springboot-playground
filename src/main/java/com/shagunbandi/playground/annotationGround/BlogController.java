package com.shagunbandi.playground.annotationGround;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@RequestMapping("blogs")
@Slf4j
public class BlogController {

	@PostMapping
	@LogExecutionTime(roles = { "User" })
	public String getMapping(@RequestBody BlogModel blog, String user) {

		System.out.println("Argument added from aspect: " + user);
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<BlogModel>> res = validator.validate(blog);
		log.info(String.valueOf(res.isEmpty()));
		return String.valueOf(res.isEmpty());

	}

}