package com.hytx.chapterService.dao;

import java.util.List;

import com.hytx.chapterService.model.Attach;

public interface AttachDao{

    int insert(Attach record);

	Attach selectById(Integer id);

	List<Attach> selectAttach();
}