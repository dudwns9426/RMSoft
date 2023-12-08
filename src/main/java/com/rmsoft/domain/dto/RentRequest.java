package com.rmsoft.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentRequest {
	private Long bookId;
	private Long userId;
}
