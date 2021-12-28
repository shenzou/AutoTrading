package com.shenzou.autotrader.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.shenzou.autotrader.persistence.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>{
	
	
}
