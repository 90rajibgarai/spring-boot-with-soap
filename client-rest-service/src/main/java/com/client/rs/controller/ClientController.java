package com.client.rs.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.client.rs.model.Program;
import com.client.rs.service.ClientService;


@Path("/programs")
public class ClientController 
{
	@Autowired
	private ClientService clientService;
	
//-------------------------------------------------------------------GET ALL PROGRAMS--------------------------

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms()
	{
		return clientService.getPrograms();
	}
	
//---------------------------------------------GET PROGRAM BY ID------------------------------------------------
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("id") Integer id)
	{
		return clientService.getProgram(id);
	}
}
