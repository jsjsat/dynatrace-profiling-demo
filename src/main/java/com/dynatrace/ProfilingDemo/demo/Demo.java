package com.dynatrace.ProfilingDemo.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.dynatrace.ProfilingDemo.db.JobDatabase;
import com.dynatrace.ProfilingDemo.db.UserDatabase;
import com.dynatrace.ProfilingDemo.model.Job;
import com.dynatrace.ProfilingDemo.model.User;
import com.dynatrace.ProfilingDemo.worker.Worker;

public class Demo<T extends Worker> {

	List<T> workers = new ArrayList<>();
	public List<User> runJobs(Supplier<T> workerSupplier) {

		// Start a thread for each job in the database
		for (Job job : JobDatabase.getJobs()) {
			T worker = workerSupplier.get();
			worker.setJob(job);
			workers.add(worker);
			worker.start();
		}

		// Wait for all jobs to finish
		for (Worker worker : workers) {
			try {
				worker.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// return all users with their new point totals
		return UserDatabase.getUsers();
	}

	public void stopWorkers() {
		workers.forEach((worker) -> worker.kill());
	}
}
