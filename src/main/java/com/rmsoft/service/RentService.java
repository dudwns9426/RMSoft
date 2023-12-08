package com.rmsoft.service;

import java.util.List;

import com.rmsoft.domain.dto.RentDTO;

public interface RentService {
	public List<RentDTO> getRentHistory(Long bookId);
	
	public boolean createRent(Long bookId, Long userId);
	
	public boolean updateReturn(Long bookId);

}
