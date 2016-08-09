package com.hcsc.bluechip.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

import com.hcsc.bluechip.dto.MethodDetailsDto;
import com.hcsc.bluechip.service.MethodService;



@ManagedBean
@SessionScoped
@Log4j
public class MethodBean {

	@ManagedProperty(value="#{methodService}")
	@Getter @Setter
	MethodService methodService;
	
	@Getter @Setter
	MethodDetailsDto reqObject;
	
	public void saveMethod(){
		methodService.saveMethodDetails(reqObject);
	}
}
