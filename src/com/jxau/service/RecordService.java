package com.jxau.service;

import java.util.List;

import com.jxau.pojo.Record;

public interface RecordService {
	public void vote(List<Record> records) throws Exception;
}
