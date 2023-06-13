package com.dynatrace.ProfilingDemo.model;

public class Job {
	final String id;
	final String user1;
	final String user2;
	final int reward;
	
	public Job(String id, String user1, String user2, int reward) {
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
		this.reward = reward;
	}

	public String getId() {
		return id;
	}

	public String getUser1() {
		return user1;
	}

	public String getUser2() {
		return user2;
	}

	public int getReward() {
		return reward;
	}
}
