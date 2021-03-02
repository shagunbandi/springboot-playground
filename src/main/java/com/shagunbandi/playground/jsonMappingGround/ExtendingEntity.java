package com.shagunbandi.playground.jsonMappingGround;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtendingEntity extends BaseEntity {

	public ExtendingEntity(String id, int number) {
		super(id, number);
		// TODO Auto-generated constructor stub
	}

}
