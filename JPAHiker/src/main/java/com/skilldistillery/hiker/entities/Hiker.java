package com.skilldistillery.hiker.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Hiker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String gender;
	
	@OneToMany(mappedBy="hiker")
	private List<SingleHiking> singleHikings;
	
	public Hiker() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public List<SingleHiking> getSingleHikings() {
		return singleHikings;
	}
	public void setSingleHikings(List<SingleHiking> singleHikings) {
		this.singleHikings = singleHikings;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hiker other = (Hiker) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Hiker [id=" + id + ", name=" + name + ", gender=" + gender + "]";
	}

}
