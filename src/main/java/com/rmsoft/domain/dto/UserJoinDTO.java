package com.rmsoft.domain.dto;

import lombok.Data;

@Data
public class UserJoinDTO {
	private String email;
	private String password;
	private String name;
	private String birthDate;
}
