package com.shagunbandi.playground.microbatchingGround.steps;
import org.springframework.batch.item.ItemProcessor;

import com.shagunbandi.playground.microbatchingGround.model.Person;

public class Processor implements ItemProcessor<Person, String> {

	@Override
	public String process(Person data) throws Exception {
		return data.toString();
	}

}