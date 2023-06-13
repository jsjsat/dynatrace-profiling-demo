package com.dynatrace.ProfilingDemo.db;


import java.util.ArrayList;
import java.util.List;

import com.dynatrace.ProfilingDemo.model.Job;

public class JobDatabase {

	final static List<Job> jobs = new ArrayList<Job>();
	static {
		jobs.add(new Job("job1", "Joe", "Sally", 43));
		jobs.add(new Job("job2", "Leo", "Bert", 44));
		jobs.add(new Job("job3", "Dan", "Simon", 45));
		jobs.add(new Job("job4", "Sally", "Joe", 38));
		jobs.add(new Job("job5", "Mark", "Chris", -47));
		jobs.add(new Job("job6", "Tom", "Perry", 46));
	}
	public static List<Job> getJobs() {
		return jobs;
	}
}
