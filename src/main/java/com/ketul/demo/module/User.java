package com.ketul.demo.module;

public class User {

	private int id;
	private String email;

	public User() {
		super();
	}
	
	public User(int id, String email) {
		super();
		this.id = id;
		this.email = email;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + "]";
	}
	
	public boolean equals(Object o) {
		if(this == o)
			return true;
		else {
			if(o instanceof User) {
				User u = (User)o;
				if(u.getId() == id)
					return true;
				else
					return false;
			}
			else
				return false;
		}
	}
	
	public int hashCode() {
		return id;
	}
}
