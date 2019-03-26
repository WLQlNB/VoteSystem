package com.jxau.service.impl;

import java.util.Date;
import java.util.List;

import com.jxau.dao.RecordDao;
import com.jxau.dao.SubjectDao;
import com.jxau.dao.impl.RecordDaoImpl;
import com.jxau.dao.impl.SubjectDaoImpl;
import com.jxau.pojo.Record;
import com.jxau.pojo.Subject;
import com.jxau.service.RecordService;
import com.jxau.util.exception.RuleException;



public class RecordServiceImpl implements RecordService {
    private SubjectDao subjectDao;
    private RecordDao recordDao;
    public RecordServiceImpl(){
    	this.subjectDao=new SubjectDaoImpl();
    	this.recordDao=new RecordDaoImpl();
    }
    
	@Override
	public void vote(List<Record> records) throws Exception {
		Subject subject= (Subject)subjectDao.getModel(records.get(0).getSubject().getId());
		if(subject.getNumber()==1 && records.size()!=1 ||
		   subject.getNumber()==2 && records.size()<2
		){
			throw new RuleException("没有按照单选和多选类别进行选择");
		}
		
		//验证时间：
		long curr = new Date().getTime();
		if(curr<subject.getStartTime() || curr>subject.getEndTime()){
			throw new RuleException("没有在项目规定的时间内投票");
		}
		
		//开始投票
		for(Record record:records){
			recordDao.insert(record);
		}
	}

}
