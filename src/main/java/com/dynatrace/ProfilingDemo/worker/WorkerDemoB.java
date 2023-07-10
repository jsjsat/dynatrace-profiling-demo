package com.dynatrace.ProfilingDemo.worker;

import com.dynatrace.ProfilingDemo.db.UserDatabase;
import com.dynatrace.ProfilingDemo.model.Job;
import com.dynatrace.ProfilingDemo.model.User;

/**
 * Demo where one thread is busy calculating
 * due to incorrect exit condition
 */
public class WorkerDemoB extends Worker {
	
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
		// Problem: Negative rewards will never exit this method
		// Fix by changing to n <=1
		if (n == 0 || n == 1) {
			return n;
		}
		int fib = 1;
		int prevFib = 1;

		for (int i = 2; i != n; i++) {
			int temp = fib;
			fib += prevFib;
			prevFib = temp;
			if (i < 0) {
				i = 0;
			}
		}
		return fib;
	}
}
