package com.rmsoft.domain.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long bookId;
    private String title;
    private String writer;
    private String publisher;
    private int price;
}
