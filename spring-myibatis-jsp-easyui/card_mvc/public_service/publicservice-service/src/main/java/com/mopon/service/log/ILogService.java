package com.mopon.service.log;

import java.util.List;

import com.mopon.entity.log.AccessApiLog;
import com.mopon.entity.log.BusinessLog;
import com.mopon.entity.log.OperaterLog;
import com.mopon.entity.log.SysExecptionLog;
import com.mopon.util.Page;

public interface ILogService {
	
	public List<AccessApiLog>  queryAccinlogs(Page<AccessApiLog> accinlogs);
	
	public List<BusinessLog> queryBusslogs(Page<BusinessLog> busslogs);
	
	public List<OperaterLog> queryOprlogs(Page<OperaterLog> oprlogs);
	
	public List<SysExecptionLog> querySyserrlogs(Page<SysExecptionLog> syserrlogs);	
	
	public void InsertAccinlogs(AccessApiLog accinlogs);	
	
	public void InsertBusslogs(BusinessLog busslogs);
	
	public void InsertOptelogs(OperaterLog oprlogs);
	
	public void InsertSyserrlogs(SysExecptionLog syserrorlogs);
	
	public AccessApiLog  querySingelAccinlogs(String id);
	
	public BusinessLog querySingelBusslogs(String id);
	
	public OperaterLog querySingelOprlogs(String id);
	
	public SysExecptionLog querySingelSyserrlogs(String id);
	
	
	
}
