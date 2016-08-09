package com.hcsc.bluechip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcsc.bluechip.dto.MethodDetailsDto;
import com.hcsc.bluechip.entity.MethodDetails;
import com.hcsc.bluechip.repo.MethodDetailsJPA;
import com.hcsc.bluechip.utils.ModelEntityMapper;

@Service
public class MethodService {

	 @Autowired
	 MethodDetailsJPA methodDetailsJPA;
	 
	public MethodDetailsDto saveMethodDetails(MethodDetailsDto reqObject){
		MethodDetails entityObj=(MethodDetails) ModelEntityMapper.converObjectToPoJo(reqObject, MethodDetails.class);
		methodDetailsJPA.saveAndFlush(entityObj);
		reqObject.setId(entityObj.getId());
		return reqObject;
	}
}
