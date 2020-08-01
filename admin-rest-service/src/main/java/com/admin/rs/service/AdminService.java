package com.admin.rs.service;

import java.util.List;

import com.admin.rs.model.Program;

public interface AdminService 
{
	public Program saveProgram(Program program);
	
	public List<Program> getPrograms();

	public Program getProgram(Integer id);

	public Program updateProgram(Program program, Integer id);

	public String deleteProgram(Integer id);
}
