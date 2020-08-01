package com.soap.ws.model;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PROGRAM")
@Data @AllArgsConstructor @NoArgsConstructor
public class Program implements Serializable
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Integer id;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "TYPE")
	private String type;	// Movie, TV Show
	
	@Column(name = "PRODUCER")
	private String producer;
	
	@Column(name = "DIRECTOR")
	private String director;
	
	@Column(name = "CONTENT")
	@Lob
    private byte[] content;	// Image
	
	@Column(name = "RUNNING_TIME")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime runningTime;
	
	@Column(name = "ADDED_BY")
	private Integer addedBy;
	
	@Column(name = "UPDATED_BY")
	private Integer updatedBy;
	
	@Column(name = "ADDED_DATE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime addedDate;
	
	@Column(name = "UPDATED_DATE")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private LocalDateTime updatedDate;
	
	@Column(name = "IS_DELETED")
	private boolean isDeleted;
}
