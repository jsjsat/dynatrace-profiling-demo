package com.dynatrace.ProfilingDemo.worker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dynatrace.ProfilingDemo.model.Job;

public abstract class Worker extends Thread{

	Logger logger = LoggerFactory.getLogger(Worker.class);
	private Job job;
	
	public void run() {
			final String workerName = this.getClass().getSimpleName() + "-" + job.getId();
			this.setName(workerName);

			logger.info(workerName + " started!");
			this.doJob(job);
			logger.info(workerName + " finished!");
	}
	
	public void setJob(Job job) {
		this.job = job;
	}
	
	protected abstract void doJob(Job job);

	public final void kill() {
		super.stop();
		logger.info(this.getName() + " stopped!");
	}
}
