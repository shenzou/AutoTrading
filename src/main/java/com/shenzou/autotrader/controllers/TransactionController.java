package com.shenzou.autotrader.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.shenzou.autotrader.dao.TransactionRepository;

@Controller
public class TransactionController {

	 @Autowired
     TransactionRepository transacRepo;
	
	
}
