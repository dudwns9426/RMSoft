package com.rmsoft.service;

import org.springframework.stereotype.Service;

import com.rmsoft.domain.BookDTO;
import com.rmsoft.mapper.BookMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
	
	private final BookMapper bookMapper;

	@Override
	public boolean insertBook(BookDTO params) {
		int result = bookMapper.insertBook(params);
		 
		return result == 1;
	}

}
