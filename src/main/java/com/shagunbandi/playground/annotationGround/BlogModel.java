package com.shagunbandi.playground.annotationGround;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Validated
public class BlogModel {

	@NotNull
	private String title;
	
	@NotBlank
	private String author;
	
	@Size(min=1, max=3)
	private String[] contributors;
	
}
