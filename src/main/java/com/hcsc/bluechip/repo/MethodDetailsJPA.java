package com.hcsc.bluechip.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcsc.bluechip.entity.MethodDetails;

public interface MethodDetailsJPA extends JpaRepository< MethodDetails, Long>{

	public List<MethodDetails> findByMethodName(String methodName);
	
}
