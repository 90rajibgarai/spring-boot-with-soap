package com.admin.rs.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @AllArgsConstructor @NoArgsConstructor
public class Program 
{
	private Integer id;	
	
	private String title;	
	
	private String type;	// Movie, TV Show	
	
	private String producer;	
	
	private String director;
			
    private byte[] content;	// Image	
	
	private LocalDateTime runningTime;	
	
	private Integer addedBy;	
	
	private Integer updatedBy;	
	
	private LocalDateTime addedDate;
		
	private LocalDateTime updatedDate;
		
	private boolean isDeleted;
}
