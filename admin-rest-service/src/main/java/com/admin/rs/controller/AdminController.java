package com.admin.rs.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.admin.rs.model.Program;
import com.admin.rs.service.AdminService;

@Path("/programs")
public class AdminController
{
	@Autowired
	private AdminService adminService;
	
//-------------------------------------------------------------------SAVE PROGRAM----------------------------
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program saveProgram(Program program)
	{
		return adminService.saveProgram(program);
	}
	
//-------------------------------------------------------------------GET ALL PROGRAMS--------------------------
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Program> getPrograms()
	{
		return adminService.getPrograms();
	}
	
//---------------------------------------------GET PROGRAM BY ID------------------------------------------------
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Program getProgram(@PathParam("id") Integer id)
	{
		return adminService.getProgram(id);
	}
	
//-----------------------------------------------UPDATE PROGRAM-------------------------------------------------

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Program updateProgram(Program program, @PathParam("id") Integer id)
	{
		return adminService.updateProgram(program, id);
	}
	
//---------------------------------------------DELETE PROGRAM DETAIL BY ID----------------------------------------------

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProgram(@PathParam("id") Integer id)
	{
		return adminService.deleteProgram(id);
	}
	
}
