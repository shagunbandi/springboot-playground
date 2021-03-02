package com.shagunbandi.playground.microbatchingGround.controller;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shagunbandi.playground.microbatchingGround.model.Person;
import com.shagunbandi.playground.microbatchingGround.repository.PersonRepository;

@RestController
@RequestMapping("person")
@CrossOrigin
public class PersonController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;

	@Autowired
	private PersonRepository repo;

	@PostMapping
	public Person addPerson(@RequestBody Person person) {
		return repo.save(person);
	}

	@GetMapping("/invokejob")
	public String handle() throws Exception {

		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.toJobParameters();
		jobLauncher.run(processJob, jobParameters);

		return "Batch job has been invoked";
	}

	@GetMapping
	public List<Person> getAll() throws Exception {

		return repo.findAll();
	}

}
