package com.client.rs.service;

import java.util.List;

import com.client.rs.model.Program;


public interface ClientService
{
	public List<Program> getPrograms();

	public Program getProgram(Integer id);
}
