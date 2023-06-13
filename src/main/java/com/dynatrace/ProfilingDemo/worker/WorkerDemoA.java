package com.dynatrace.ProfilingDemo.worker;

import com.dynatrace.ProfilingDemo.db.UserDatabase;
import com.dynatrace.ProfilingDemo.model.Job;
import com.dynatrace.ProfilingDemo.model.User;

/**
 * Demo where one thread is busy calculating
 * due to incorrect exit condition
 */
public class WorkerDemoA extends Worker{

	public void doJob(Job job) {
		final User user1 = UserDatabase.getUser(job.getUser1());
		final User user2 = UserDatabase.getUser(job.getUser2());
		long calculatedReward = calculateReward(job.getReward());
		synchronized (user1) {
			user1.addPoints(calculatedReward);
		}
		synchronized (user2) {
			user2.addPoints(calculatedReward);
		}
	}

	private long calculateReward(int n) {
		if (n <= 1) {
			return n;
		}
		return calculateReward(n - 1) + calculateReward(n - 2);
	}
}
