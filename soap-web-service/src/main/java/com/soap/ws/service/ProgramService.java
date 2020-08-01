package com.soap.ws.service;

import java.util.List;

import com.soap.ws.config.xsd.Program;
import com.soap.ws.config.xsd.SaveProgramRequest;
import com.soap.ws.config.xsd.UpdateProgramRequest;

public interface ProgramService 
{
	public Program saveProgram(SaveProgramRequest saveProgramRequest);
	
	public List<Program> getPrograms();
	
	public Program getProgram(Integer id);

	public Program updateProgram(UpdateProgramRequest updateProgramRequest);

	public String deleteProgram(Integer id);
}
