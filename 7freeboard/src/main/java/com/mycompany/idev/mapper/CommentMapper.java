package com.mycompany.idev.mapper;

import java.util.List;

import com.mycompany.idev.dto.Comments;

public interface CommentMapper {
	
	public void insert(Comments dto);
	public List<Comments> list(int mref);
	public void commentCountUp(int idx);
	public void delete(int idx);
	public void commentCountDown(int idx);
	public void updateCmtCount(int idx);
	
}
