package com.soap.ws.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.util.ObjectUtils;

import com.soap.ws.config.xsd.Program;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Conversion 
{
	public static com.soap.ws.model.Program convertXSDProgramToModelProgram(Program xsdProgram)
	{
		com.soap.ws.model.Program modelProgram = new com.soap.ws.model.Program();
		
		if(!ObjectUtils.isEmpty(xsdProgram))
		{
			try
			{
				modelProgram.setId(xsdProgram.getId());
				modelProgram.setTitle(xsdProgram.getTitle());
				modelProgram.setType(xsdProgram.getType());
				modelProgram.setProducer(xsdProgram.getProducer());
				modelProgram.setDirector(xsdProgram.getDirector());
				modelProgram.setContent(xsdProgram.getContent());
							
				try
				{
					modelProgram.setRunningTime(xsdProgram.getRunningTime().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
				}
				catch(Exception e)
				{
					log.error("Erroe in coversion : GregorianCalendar to LocalDateTime : {} ",e.getMessage());
				}
				
				modelProgram.setAddedBy(xsdProgram.getAddedBy());
				modelProgram.setUpdatedBy(xsdProgram.getUpdatedBy());
			}
			catch(Exception e)		{
				log.error("Erroe in coversion : XSDProgram To ModelProgram : {} ",e.getMessage());
			}
		}
		else
			log.error("XSD Program is Null !");
		return modelProgram;
	}
	
	
	public static Program convertModelProgramToXSDProgram(com.soap.ws.model.Program modelProgram)
	{
		Program xsdProgram = new Program();
		if(!ObjectUtils.isEmpty(modelProgram))
		{
			try
			{
				xsdProgram.setId(modelProgram.getId());
				
				xsdProgram.setTitle(modelProgram.getTitle());
				xsdProgram.setType(modelProgram.getType());
				xsdProgram.setProducer(modelProgram.getProducer());
				xsdProgram.setDirector(modelProgram.getDirector());
				xsdProgram.setContent(modelProgram.getContent());
						
				try {
					xsdProgram.setRunningTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(modelProgram.getRunningTime().toString()));
				} 
				catch (DatatypeConfigurationException e) {			
					log.error("Error in coversion : LocalDateTime to XMLGregorianCalendar : {}",e.getMessage());
				}
				
				xsdProgram.setAddedBy(modelProgram.getAddedBy());
				xsdProgram.setUpdatedBy(modelProgram.getUpdatedBy());	
			} 
			catch (Exception e) {			
				log.error("Error in coversion : ModelProgram To XSDProgram : {}",e.getMessage());
			}
		}
		else
			log.error("Model Program is Null !");
		return xsdProgram;
	}
}
