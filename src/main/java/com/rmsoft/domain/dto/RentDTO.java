package com.rmsoft.domain.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class RentDTO {
	private String bookTitle;
	private String userName;
	private String returnYn;
	private Timestamp rentDate;
	private Timestamp dueDate;
}
