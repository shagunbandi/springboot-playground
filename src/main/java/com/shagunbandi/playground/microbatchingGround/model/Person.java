package com.shagunbandi.playground.microbatchingGround.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name="person")
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String lastName;
	
	@NotNull
	private String firstName;
}