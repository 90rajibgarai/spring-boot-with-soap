package com.soap.ws.dao;

import java.util.List;

import com.soap.ws.model.Program;

public interface ProgramDao
{
	public Program saveProgram(Program program);
	
	public Program getProgram(Integer id);
	
	public List<Program> getPrograms();
	
	public Program updateProgram(Program program);
	
	public String deleteProgram(Integer id);
}
