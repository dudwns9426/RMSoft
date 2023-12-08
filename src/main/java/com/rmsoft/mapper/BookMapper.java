package com.rmsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rmsoft.domain.dto.BookDTO;

@Mapper
public interface BookMapper {
	public int insertBook(BookDTO bookDTO);
	
	public int modifyBook(BookDTO bookDTO);
	
	public List<BookDTO> getBookList();
	
	public BookDTO getBookDetail(Long bookId);
	
	public int deleteBook(Long bookId);
}
