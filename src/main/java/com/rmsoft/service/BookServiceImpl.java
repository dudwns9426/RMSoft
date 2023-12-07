package com.rmsoft.service;

import java.util.List;

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
		 
		return result == 1 ;
	}

	@Override
	public boolean modifyBook(BookDTO bookDTO) {
		int result = bookMapper.modifyBook(bookDTO);
		return result == 1 ;
	}
	
	@Override
	public List<BookDTO> getBookList() {
		List<BookDTO> books = bookMapper.getBookList();
		return books;
	}

	@Override
	public BookDTO getBookDetail(Long bookId) {		
		return bookMapper.getBookDetail(bookId);
	}

	@Override
	public boolean deleteBook(Long bookId) {
		int result = bookMapper.deleteBook(bookId);
		return result == 1 ;
	}

	

}
