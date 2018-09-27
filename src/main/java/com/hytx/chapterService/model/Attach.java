package com.hytx.chapterService.model;

import java.util.Date;

import lombok.Data;

@Data
public class Attach {

	private Integer id;
	
	private String fname;
	
	private String ftype;
	
	private String fkey;
	
	private Integer authorId;
	
	private Integer created;
	
	private Date fCreated;
}
