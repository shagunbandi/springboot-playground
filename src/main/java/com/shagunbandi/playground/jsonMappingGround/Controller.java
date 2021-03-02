package com.shagunbandi.playground.jsonMappingGround;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mapping")
@CrossOrigin
public class Controller {

	@PostMapping
	public ExtendingEntity getSame(@RequestBody ExtendingEntity entity) {
		return entity;
	}

}
