package com.rmsoft.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.rmsoft.domain.BookDTO;

@Mapper
public interface BookMapper {
	public int insertBook(BookDTO params);

}
