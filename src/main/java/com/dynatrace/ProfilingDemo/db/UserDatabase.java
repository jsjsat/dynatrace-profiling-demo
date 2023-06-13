package com.dynatrace.ProfilingDemo.db;

import java.util.ArrayList;
import java.util.List;

import com.dynatrace.ProfilingDemo.model.User;

public class UserDatabase {
	final static List<User> users = new ArrayList<User>();
	static {
		users.add(new User("Joe", 0));
		users.add(new User("Sally", 0));
		users.add(new User("Bert", 0));
		users.add(new User("Dan", 0));
		users.add(new User("Chris", 0));
		users.add(new User("Mark", 0));
		users.add(new User("Perry", 0));
		users.add(new User("Simon", 0));	
		users.add(new User("Tom", 0));
		users.add(new User("Leo", 0));
	}
	public static List<User> getUsers()  { return users; }
	
	public static User getUser(String username) {
		return users.stream().filter(u -> u.getName().equals(username)).findFirst().orElse(null);
	}
	
	public static void resetPoints() {
		users.forEach(user -> user.setPoints(0L));
	}
}
