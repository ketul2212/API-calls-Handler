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
	
	public boolean equals(Object object) {
		if(this == object)
			return true;
		else {
			if(object instanceof User) {
				User u = (User)object;
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
