package com.soap.ws.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.soap.ws.constant.KeyConstant;
import com.soap.ws.exception.ResourceNotFoundException;
import com.soap.ws.model.Program;
import com.soap.ws.repository.ProgramRepository;

@Service
public class ProgramDaoImpl implements ProgramDao 
{
	@Autowired
	private ProgramRepository programRepository;

	@Override
	public Program saveProgram(Program program)
	{
		program.setAddedDate(LocalDateTime.now());
		program.setUpdatedDate(LocalDateTime.now());
		program.setDeleted(false);
		
		return programRepository.save(program);
	}

	@Override
	public Program getProgram(Integer id) 
	{
		return programRepository.findByIdAndIsDeletedFalse(id);
	}

	@Override
	public List<Program> getPrograms()
	{
		return programRepository.findByIsDeletedFalse();
	}

	@Override
	public Program updateProgram(Program program)
	{
		Program oldProgram = getProgram(program.getId());
		
		if(!ObjectUtils.isEmpty(oldProgram))
		{
			program.setAddedDate(oldProgram.getAddedDate());
			program.setUpdatedDate(LocalDateTime.now());
			program.setDeleted(false);
			
			return programRepository.save(program);
		}
		else
			throw new ResourceNotFoundException("Program", "ID", program.getId());
	}

	@Override
	public String deleteProgram(Integer id)
	{
		Program program = getProgram(id);
		
		if(!ObjectUtils.isEmpty(program))
		{
			program.setUpdatedDate(LocalDateTime.now());
			program.setDeleted(true);
			
			programRepository.save(program);
			
			return KeyConstant.MSG_DELETE_STATUS_SUCCESS;
		}
		return KeyConstant.MSG_DELETE_STATUS_FAIL;
	}

}
