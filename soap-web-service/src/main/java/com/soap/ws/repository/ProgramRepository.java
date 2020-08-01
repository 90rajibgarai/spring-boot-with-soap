package com.soap.ws.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.soap.ws.model.Program;

@Repository
public interface ProgramRepository extends CrudRepository<Program, Integer>
{
	public Program findByIdAndIsDeletedFalse(Integer id);

	public List<Program> findByIsDeletedFalse();

}
