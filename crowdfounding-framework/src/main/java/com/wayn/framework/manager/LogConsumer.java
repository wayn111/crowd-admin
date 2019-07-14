package com.wayn.framework.manager;

import com.wayn.commom.domain.Log;
import com.wayn.commom.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 日志异步处理线程
 */
@Component
public class LogConsumer implements Runnable {

	@Autowired
	private LogQueue queue;

	/**
	 * 日志临时存储总数
	 */
	@Value("${logHanderTempNum}")
	private Integer logHanderNum;

	@Autowired
	private LogService logService;

	@PostConstruct
	public void init() {
		new Thread(this, "logAsyncHanderThread").start();
	}

	@Override
	public void run() {
		while (true) {
			logHander();
		}
	}

	private void logHander() {
		List<Log> temp = new ArrayList<>();
		try {
			while (temp.size() <= logHanderNum) {
				Log log = queue.take();
				temp.add(log);
			}
			if (temp.size() != 0) {
				logService.insertBatch(temp);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
