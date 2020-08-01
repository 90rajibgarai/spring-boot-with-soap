package com.soap.ws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.soap.ws.config.xsd.DeleteProgramRequest;
import com.soap.ws.config.xsd.GetAllProgramsRequest;
import com.soap.ws.config.xsd.GetAllProgramsResponse;
import com.soap.ws.config.xsd.GetMessageResponse;
import com.soap.ws.config.xsd.GetProgramRequest;
import com.soap.ws.config.xsd.GetProgramResponse;
import com.soap.ws.config.xsd.SaveProgramRequest;
import com.soap.ws.config.xsd.UpdateProgramRequest;
import com.soap.ws.constant.KeyConstant;
import com.soap.ws.service.ProgramService;

@Endpoint
public class ProgramController 
{
	@Autowired
	private ProgramService programService;
	
//-----------------------------------------------SAVE PROGRAM DETAILS-------------------------------------------------

	@PayloadRoot(namespace = KeyConstant.NAMESPACE, localPart = "saveProgramRequest")
	@ResponsePayload
	public GetProgramResponse saveProgram(@RequestPayload SaveProgramRequest saveProgramRequest)
	{
		GetProgramResponse getProgramResponse = new GetProgramResponse();
				
		getProgramResponse.setProgram(programService.saveProgram(saveProgramRequest));		
		return getProgramResponse;
	}
	
//---------------------------------------------GET ALL PROGRAMS-------------------------------------------------------

	@PayloadRoot(namespace = KeyConstant.NAMESPACE, localPart = "getAllProgramsRequest")
	@ResponsePayload
	public GetAllProgramsResponse getPrograms(@RequestPayload GetAllProgramsRequest getAllProgramsRequest)
	{
		GetAllProgramsResponse getAllProgramsResponse = new GetAllProgramsResponse();
				
		getAllProgramsResponse.getPrograms().addAll(programService.getPrograms());		
		return getAllProgramsResponse;
	}	
		
//---------------------------------------------GET PROGRAM DETAIL BY ID------------------------------------------------
	
	@PayloadRoot(namespace = KeyConstant.NAMESPACE, localPart = "getProgramRequest")
	@ResponsePayload
	public GetProgramResponse getProgram(@RequestPayload GetProgramRequest getProgramRequest)
	{
		GetProgramResponse getProgramResponse = new GetProgramResponse();
				
		getProgramResponse.setProgram(programService.getProgram(getProgramRequest.getId()));		
		return getProgramResponse;
	}	
	
//-----------------------------------------------UPDATE PROGRAM DETAILS-------------------------------------------------

	@PayloadRoot(namespace = KeyConstant.NAMESPACE, localPart = "updateProgramRequest")
	@ResponsePayload
	public GetProgramResponse updateProgram(@RequestPayload UpdateProgramRequest updateProgramRequest)
	{
		GetProgramResponse getProgramResponse = new GetProgramResponse();
				
		getProgramResponse.setProgram(programService.updateProgram(updateProgramRequest));		
		return getProgramResponse;
	}
	
//---------------------------------------------DELETE PROGRAM DETAIL BY ID----------------------------------------------
	
	@PayloadRoot(namespace = KeyConstant.NAMESPACE, localPart = "deleteProgramRequest")
	@ResponsePayload
	public GetMessageResponse deleteProgram(@RequestPayload DeleteProgramRequest deleteProgramRequest)
	{
		GetMessageResponse getMessageResponse = new GetMessageResponse();
				
		getMessageResponse.setMessgae(programService.deleteProgram(deleteProgramRequest.getId()));		
		return getMessageResponse;
	}	
	
//----------------------------------------------------END--------------------------------------------------------------

}
