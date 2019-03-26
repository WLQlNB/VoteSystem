package com.jxau.service;

import java.util.List;

import com.jxau.pojo.Subject;
import com.jxau.pojo.User;


public interface SubjectService {
	public void add(Subject subject,User user) throws Exception;
	public List getSubjects() throws Exception;
	public Subject getSubject(int id) throws Exception;
	public void modify(Subject subject, User attribute) throws Exception;
}

