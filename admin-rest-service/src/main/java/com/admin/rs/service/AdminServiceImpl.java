package com.admin.rs.service;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.admin.rs.config.xsd.DeleteProgramRequest;
import com.admin.rs.config.xsd.GetAllProgramsRequest;
import com.admin.rs.config.xsd.GetAllProgramsResponse;
import com.admin.rs.config.xsd.GetMessageResponse;
import com.admin.rs.config.xsd.GetProgramRequest;
import com.admin.rs.config.xsd.GetProgramResponse;
import com.admin.rs.config.xsd.SaveProgramRequest;
import com.admin.rs.config.xsd.UpdateProgramRequest;
import com.admin.rs.constant.KeyConstant;
import com.admin.rs.model.Program;
import com.admin.rs.util.Conversion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService
{
	@Autowired
	private Jaxb2Marshaller marshaller;	
	
	private WebServiceTemplate template;

	@Override
	public Program saveProgram(Program program)
	{
		Program saveProgram = new Program();		 
		try 
		{
			template = new WebServiceTemplate(marshaller);	
			
			SaveProgramRequest saveProgramRequest = new SaveProgramRequest();			
			
			saveProgramRequest.setTitle(program.getTitle());
			saveProgramRequest.setProducer(program.getProducer());
			saveProgramRequest.setDirector(program.getDirector());
			saveProgramRequest.setType(program.getType());
			saveProgramRequest.setContent(program.getContent());
			
			try {
				saveProgramRequest.setRunningTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(program.getRunningTime().toString()));
			} 
			catch (DatatypeConfigurationException e) {			
				log.error("Error in coversion : LocalDateTime to XMLGregorianCalendar : {}",e.getMessage());
			}
			
			saveProgramRequest.setAddedBy(program.getAddedBy());
			saveProgramRequest.setUpdatedBy(program.getUpdatedBy());			
			
			GetProgramResponse getProgramResponse = (GetProgramResponse) template.marshalSendAndReceive(KeyConstant.SOAP_PROVIDER_URI, saveProgramRequest);
			
			if(!ObjectUtils.isEmpty(getProgramResponse) && !ObjectUtils.isEmpty(getProgramResponse.getProgram()))
				saveProgram = Conversion.convertXSDProgramToModelProgram(getProgramResponse.getProgram());
		}
		catch(Exception e) {log.error("Error in Save Program : {}", e.getMessage());}
		return saveProgram;
	}

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
				for (com.admin.rs.config.xsd.Program program : getAllProgramsResponse.getPrograms()) 
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

	@Override
	public Program updateProgram(Program program, Integer id)
	{
		Program updateProgram = new Program();		 
		try 
		{
			template = new WebServiceTemplate(marshaller);	
			
			UpdateProgramRequest updateProgramRequest = new UpdateProgramRequest();			
			
			updateProgramRequest.setId(id);
			updateProgramRequest.setTitle(program.getTitle());
			updateProgramRequest.setProducer(program.getProducer());
			updateProgramRequest.setDirector(program.getDirector());
			updateProgramRequest.setType(program.getType());
			updateProgramRequest.setContent(program.getContent());
			
			try {
				updateProgramRequest.setRunningTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(program.getRunningTime().toString()));
			} 
			catch (DatatypeConfigurationException e) {			
				log.error("Error in coversion : LocalDateTime to XMLGregorianCalendar : {}",e.getMessage());
			}
			
			updateProgramRequest.setAddedBy(program.getAddedBy());
			updateProgramRequest.setUpdatedBy(program.getUpdatedBy());				
			
			GetProgramResponse getProgramResponse = (GetProgramResponse) template.marshalSendAndReceive(KeyConstant.SOAP_PROVIDER_URI, updateProgramRequest);
			
			if(!ObjectUtils.isEmpty(getProgramResponse) && !ObjectUtils.isEmpty(getProgramResponse.getProgram()))
				updateProgram = Conversion.convertXSDProgramToModelProgram(getProgramResponse.getProgram());
		}
		catch(Exception e) {log.error("Error in Update Program : {}", e.getMessage());}
		return updateProgram;
	}

	@Override
	public String deleteProgram(Integer id)
	{
		String response = "";		 
		try 
		{
			template = new WebServiceTemplate(marshaller);	
			
			DeleteProgramRequest deleteProgramRequest = new DeleteProgramRequest();
			deleteProgramRequest.setId(id);
			
			GetMessageResponse getMessageResponse = (GetMessageResponse) template.marshalSendAndReceive(KeyConstant.SOAP_PROVIDER_URI, deleteProgramRequest);
			
			if(!ObjectUtils.isEmpty(getMessageResponse))
				response = getMessageResponse.getMessgae();
		}
		catch(Exception e) {log.error("Error in Delete Program : {}", e.getMessage());}
		return response;
	}	
}
