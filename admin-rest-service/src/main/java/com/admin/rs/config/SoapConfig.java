package com.admin.rs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig 
{
	@Bean
	public Jaxb2Marshaller marshaller() 
	{
		Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
		marshaller.setPackagesToScan("com.admin.rs.config.xsd");
		return marshaller;
	}
}
