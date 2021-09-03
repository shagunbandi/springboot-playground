package com.shagunbandi.playground.annotationGround;

import org.springframework.stereotype.Service;

@Service
public class AnnotationService {

	public String printMe(String str) {
		return str;
	}

}
