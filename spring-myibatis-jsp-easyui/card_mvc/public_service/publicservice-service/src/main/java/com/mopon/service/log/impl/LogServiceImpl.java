package com.mopon.service.log.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mopon.dao.log.AccessInLogDao;
import com.mopon.dao.log.BussinessLogDao;
import com.mopon.dao.log.OperateLogDao;
import com.mopon.dao.log.SystemErrorLogDao;
import com.mopon.entity.log.AccessApiLog;
import com.mopon.entity.log.BusinessLog;
import com.mopon.entity.log.OperaterLog;
import com.mopon.entity.log.SysExecptionLog;
import com.mopon.service.log.ILogService;
import com.mopon.util.Page;

/**
 * 日志管理
 * @author liuxi
 *
 */
@Service
public class LogServiceImpl implements ILogService{

	@Autowired
	private AccessInLogDao accinDao;
	
	@Autowired
	private BussinessLogDao bussDao;
	
	@Autowired
	private OperateLogDao operDao;
	
	@Autowired
	private SystemErrorLogDao syserrDao;	
	
	@Override
	public void InsertAccinlogs(AccessApiLog accinlogs) {
		accinDao.insert(accinlogs);
	}

	@Override
	public void InsertBusslogs(BusinessLog busslogs) {
		bussDao.insert(busslogs);
	}

	@Override
	public void InsertOptelogs(OperaterLog oprlogs) {
		operDao.insert(oprlogs);
	}

	@Override
	public void InsertSyserrlogs(SysExecptionLog syserrorlogs) {
		syserrDao.insert(syserrorlogs);
	}

	@Override
	public AccessApiLog querySingelAccinlogs(String id) {
		return accinDao.queryAccessInLogsById(id);
	}

	@Override
	public BusinessLog querySingelBusslogs(String id) {
		return bussDao.queryBussinessLogsById(id);
	}

	@Override
	public OperaterLog querySingelOprlogs(String id) {
		return operDao.queryOperateLogsById(id);
	}

	@Override
	public SysExecptionLog querySingelSyserrlogs(String id) {
		return syserrDao.querySystemErrorLogsById(id);
	}

	@Override
	public List<AccessApiLog> queryAccinlogs(Page<AccessApiLog> accinlogs) {
		return accinDao.queryPageList(accinlogs);
	}

	@Override
	public List<BusinessLog> queryBusslogs(Page<BusinessLog> busslogs) {
		return bussDao.queryPageList(busslogs);
	}

	@Override
	public List<OperaterLog> queryOprlogs(Page<OperaterLog> oprlogs) {
		return operDao.queryPageList(oprlogs);
	}

	@Override
	public List<SysExecptionLog> querySyserrlogs(Page<SysExecptionLog> syserrlogs) {
		return syserrDao.queryPageList(syserrlogs);
	}
	
	/*
	@Override
	public List<BusinessLog> queryBusinessLogByPage(Page<BusinessLog> page) {
		System.out.println(page);
		return null;//logDao.queryBusinessLogByPage(page);
	}

	@Override
	public List<OperaterLog> queryOperaterLogByPage(Page<OperaterLog> page) {
		return null;//logDao.queryOperaterLogByPage(page);
	}

	@Override
	public List<SysExecptionLog> querySysExecptionLogByPage(
			Page<SysExecptionLog> page) {
		return null;//logDao.querySysExecptionLogByPage(page);
	}

	@Override
	public List<AccessApiLog> queryAccessApiLogByPage(Page<AccessApiLog> page) {
		return null;// logDao.queryAccessApiLogByPage(page);
	}*/

	
}
