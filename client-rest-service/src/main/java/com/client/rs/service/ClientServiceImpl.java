package com.client.rs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.client.rs.config.xsd.GetAllProgramsRequest;
import com.client.rs.config.xsd.GetAllProgramsResponse;
import com.client.rs.config.xsd.GetProgramRequest;
import com.client.rs.config.xsd.GetProgramResponse;
import com.client.rs.constant.KeyConstant;
import com.client.rs.model.Program;
import com.client.rs.util.Conversion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService
{
	@Autowired
	private Jaxb2Marshaller marshaller;	
	
	private WebServiceTemplate template;

	@Override
	public List<Program> getPrograms() 
	{
		List<Program> programs = new ArrayList<>();		
		try 
		{
			template = new WebServiceTemplate(marshaller);		
			GetAllProgramsResponse getAllProgramsResponse = (GetAllProgramsResponse) template.marshalSendAndReceive(KeyConstant.SOAP_PROVIDER_URI, new GetAllProgramsRequest());			
			
			if(!ObjectUtils.isEmpty(getAllProgramsResponse))
			{
				for (com.client.rs.config.xsd.Program program : getAllProgramsResponse.getPrograms()) 
					programs.add(Conversion.convertXSDProgramToModelProgram(program));
			}		
		}
		catch(Exception e) {log.error("Error in Get Programs : {}", e.getMessage());}
		return programs;
	}

	@Override
	public Program getProgram(Integer id)
	{
		Program program = new Program();		 
		try 
		{
			template = new WebServiceTemplate(marshaller);	
			
			GetProgramRequest getProgramRequest = new GetProgramRequest();
			getProgramRequest.setId(id);
			
			GetProgramResponse getProgramResponse = (GetProgramResponse) template.marshalSendAndReceive(KeyConstant.SOAP_PROVIDER_URI, getProgramRequest);
			
			if(!ObjectUtils.isEmpty(getProgramResponse) && !ObjectUtils.isEmpty(getProgramResponse.getProgram()))
				program = Conversion.convertXSDProgramToModelProgram(getProgramResponse.getProgram());
		}
		catch(Exception e) {log.error("Error in Get Program : {}", e.getMessage());}
		return program;
	}
}
