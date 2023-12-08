package com.rmsoft.service;

import java.util.List;

import com.rmsoft.domain.dto.BookDTO;

public interface BookService {
	public boolean insertBook(BookDTO bookDTO);
	
	public boolean modifyBook(BookDTO bookDTO);
	
	public List<BookDTO> getBookList();
	
	public BookDTO getBookDetail(Long bookId);
	
	public boolean deleteBook(Long bookId);
}
