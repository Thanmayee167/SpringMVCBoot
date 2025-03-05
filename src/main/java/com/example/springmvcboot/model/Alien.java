package com.example.springmvcboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Alien {
	@Id
	@JsonProperty("id")  // This will make the field appear as "id" in JSON
	private int aid;

	@JsonProperty("name")  // This will make the field appear as "name" in JSON
	private String aname;

	// Default constructor required by JPA
	public Alien() {
	}

	public Alien(int aid, String aname) {
		this.aid = aid;
		this.aname = aname;
	}
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + "]";
	}
	
	
}