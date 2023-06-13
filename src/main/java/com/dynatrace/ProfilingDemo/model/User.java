package com.dynatrace.ProfilingDemo.model;

public class User {
	private final String name;
	private long points;

	public User(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}

	public long getPoints() {
		return points;
	}

	public void addPoints(long addedPoints) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		points += addedPoints;
	}
	
	public void setPoints(long points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", points=" + points + "]";
	}
}
