package com.rmsoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.rmsoft.domain.dto.RentDTO;
import com.rmsoft.domain.dto.RentRequest;

@Mapper
public interface RentMapper {
	public List<RentDTO> getRentHistory(Long bookId);
	
	public int createRent(RentRequest rentRequest);
	
	public int updateReturn(Long bookId);
}
