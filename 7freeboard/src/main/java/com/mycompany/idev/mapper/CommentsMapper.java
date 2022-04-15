package com.mycompany.idev.mapper;

import com.mycompany.idev.dto.Comments;

public interface CommentsMapper {
	public void insert(Comments dto);
	public Comments list(int mref);
	public void commentCountUp(int idx);
	public void commentCountDown(int idx);
	public void delete(int idx);
	public void updateCmtCount(int idx);
}
