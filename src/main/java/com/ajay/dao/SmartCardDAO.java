package com.ajay.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ajay.card.SmartCard;

@Repository
public interface SmartCardDAO extends PagingAndSortingRepository<SmartCard, Long>{

	
}
