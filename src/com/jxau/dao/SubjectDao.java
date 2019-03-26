package com.jxau.dao;

import com.jxau.util.base.BaseDao;
public interface SubjectDao extends BaseDao {
	public int getGenerateId() throws Exception;
	public int getUserCount(int subjectId) throws Exception;
}

