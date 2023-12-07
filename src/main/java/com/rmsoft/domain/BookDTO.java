package com.rmsoft.domain;

import lombok.Data;

@Data
public class BookDTO {
    private Long bookId;
    private String title;
    private String writer;
    private String publisher;
    private int price;
}
