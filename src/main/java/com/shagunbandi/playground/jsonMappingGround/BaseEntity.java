package com.shagunbandi.playground.jsonMappingGround;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BaseEntity {

	@JsonProperty("id")
	private final String id;

	@JsonProperty("number")
	private final int number;
	

}
