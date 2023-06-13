package com.dynatrace.ProfilingDemo.worker;

import com.dynatrace.ProfilingDemo.db.UserDatabase;
import com.dynatrace.ProfilingDemo.model.Job;
import com.dynatrace.ProfilingDemo.model.User;

/**
 * Demo that concatenates 1000x1000 strings via
 * 1) normal String concatenation (inefficient)
 * 2) Stringbuilder (much more efficient on allocations)
 */
public class WorkerDemoD extends Worker {

	public void doJob(Job job) {
		final User user1 = UserDatabase.getUser(job.getUser1());
		final User user2 = UserDatabase.getUser(job.getUser2());
		
		for (int i = 0; i < 1000; i++) {
			doStringConcatenation();
			doStringBuilderConcatenation();
		}
		
		user1.addPoints(1000);
		user2.addPoints(1000);
	}

	private String doStringBuilderConcatenation() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 1000; i++) {
			sb.append(i);
		}

		return sb.toString();
	}

	private String doStringConcatenation() {
		String result = "";
		for (int i = 0; i < 1000; i++) {
			result += i;
		}
		return result;
	}
}
