package com.shagunbandi.playground.microbatchingGround.steps;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.shagunbandi.playground.microbatchingGround.model.Person;
import com.shagunbandi.playground.microbatchingGround.repository.PersonRepository;

public class Reader implements ItemReader<Person> {

	@Autowired
	private PersonRepository repo;

	private int nextPersonIndex;
	private List<Person> personData;

	public Reader() {
		System.out.println("READERrrrr");
	}

	@PostConstruct
	private void initialize() {
		System.out.println("Iniitlise");
		personData = repo.findAll();
		System.out.println(personData.size());
		nextPersonIndex = 0;
	}

	@Override
	public Person read() throws Exception {
		System.out.println("Read");
		System.out.println(nextPersonIndex);
		System.out.println(personData.size());
		if (nextPersonIndex < personData.size()) {
			System.out.println(personData.get(nextPersonIndex));
			return personData.get(nextPersonIndex++);
		}
		return null;
	}

}