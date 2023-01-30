package com.example.h2.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Information {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PRIMARY_KEY")
    private long  id;
	
	@Column(name = "NAME")
    private String name;
	
	@Column(name = "DESCRIPTION")
    private String description;
	
	@Column(name = "UPDATED_TIMESTAMP")
    private String time;
	
	public Information() {
		super();
	}

	public Information(long id, String name, String description, String time) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.time = time;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, name, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Information other = (Information) obj;
		return Objects.equals(description, other.description) && id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(time, other.time);
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getTime() {
		return time;
	}



	public void setTime(String time) {
		this.time = time;
	}
	
	
    
    


    
    

}
