package com.shagunbandi.playground.microbatchingGround.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shagunbandi.playground.microbatchingGround.model.Person;
import com.shagunbandi.playground.microbatchingGround.steps.Processor;
import com.shagunbandi.playground.microbatchingGround.steps.Reader;
import com.shagunbandi.playground.microbatchingGround.steps.Writer;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() throws Exception {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.flow(orderStep1()).end().build();
	}
	
	@Bean
    public ItemReader<Person> reader() throws Exception {
        return new Reader();
    }

	@Bean
	public Step orderStep1() throws Exception {
		return stepBuilderFactory.get("orderStep1").<Person, String> chunk(1).reader(reader())
				.processor(new Processor()).writer(new Writer()).build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}
