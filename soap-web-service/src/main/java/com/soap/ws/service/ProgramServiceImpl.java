package com.soap.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soap.ws.config.xsd.Program;
import com.soap.ws.config.xsd.SaveProgramRequest;
import com.soap.ws.config.xsd.UpdateProgramRequest;
import com.soap.ws.dao.ProgramDao;
import com.soap.ws.util.Conversion;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProgramServiceImpl implements ProgramService
{
	@Autowired
	private ProgramDao programDao;
	
//------------------------------------------------------------------------------SAVE PROGRAM INFO------------------	
	@Override
	public Program saveProgram(SaveProgramRequest saveProgramRequest)
	{
		com.soap.ws.model.Program program = new com.soap.ws.model.Program();
		
		program.setTitle(saveProgramRequest.getTitle());
		program.setType(saveProgramRequest.getType());
		program.setProducer(saveProgramRequest.getProducer());
		program.setDirector(saveProgramRequest.getDirector());
		program.setContent(saveProgramRequest.getContent());
			
		try {
			program.setRunningTime(saveProgramRequest.getRunningTime().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
		}
		catch(Exception e) { log.error("Erroe in coversion GregorianCalendar to LocalDateTime : {} ",e.getMessage()); }
		
		program.setAddedBy(saveProgramRequest.getAddedBy());
		program.setUpdatedBy(saveProgramRequest.getUpdatedBy());
		
		return Conversion.convertModelProgramToXSDProgram(programDao.saveProgram(program));
	}	
	
//---------------------------------------------------------------------------------GET ALL PROGRAMS INFO----------------------------------

	@Override
	public List<Program> getPrograms()
	{
		List<Program> xsdPrograms = new ArrayList<>();
		
		List<com.soap.ws.model.Program> modelPrograms = programDao.getPrograms();
		
		for (com.soap.ws.model.Program program : modelPrograms) 
		{
			xsdPrograms.add(Conversion.convertModelProgramToXSDProgram(program));
		}		
		return xsdPrograms;
	}
	
//----------------------------------------------------------------------------------------GET PROGRAM INFO--------------
	@Override
	public Program getProgram(Integer id)
	{		
		return Conversion.convertModelProgramToXSDProgram(programDao.getProgram(id));
	}
	
//---------------------------------------------------------------------------------UPDATE PROGRAM INFO-----------------------------------
	@Override
	public Program updateProgram(UpdateProgramRequest updateProgramRequest)
	{
		com.soap.ws.model.Program program = new com.soap.ws.model.Program();
		
		program.setId(updateProgramRequest.getId());
		program.setTitle(updateProgramRequest.getTitle());
		program.setType(updateProgramRequest.getType());
		program.setProducer(updateProgramRequest.getProducer());
		program.setDirector(updateProgramRequest.getDirector());
		program.setContent(updateProgramRequest.getContent());
			
		try	{ 
			program.setRunningTime(updateProgramRequest.getRunningTime().toGregorianCalendar().toZonedDateTime().toLocalDateTime()); 
		}
		catch(Exception e) { log.error("Erroe in coversion GregorianCalendar to LocalDateTime : {} ",e.getMessage()); }
		
		program.setAddedBy(updateProgramRequest.getAddedBy());
		program.setUpdatedBy(updateProgramRequest.getUpdatedBy());
		
		return Conversion.convertModelProgramToXSDProgram(programDao.updateProgram(program));
	}
	
//----------------------------------------------------------------------------------DELETE PROGRAM INFO----------------------------------
	@Override
	public String deleteProgram(Integer id)
	{
		return programDao.deleteProgram(id);
	}
	
//-------------------------------------------------------------------------------END---------------------------------------------------

	

}
