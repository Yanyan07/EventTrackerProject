package com.skilldistillery.hiker.entities;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="single_hiking")
public class SingleHiking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private double distance;
	@Column(name="hiking_date")
	private LocalDate hikingDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="hiker_id")
	private Hiker hiker;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="trail_id")
	private Trail trail;
	
	public SingleHiking() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public LocalDate getHikingDate() {
		return hikingDate;
	}
	public void setHikingDate(LocalDate hikingDate) {
		this.hikingDate = hikingDate;
	}
	
	public Hiker getHiker() {
		return hiker;
	}
	public void setHiker(Hiker hiker) {
		this.hiker = hiker;
	}

	public Trail getTrail() {
		return trail;
	}
	public void setTrail(Trail trail) {
		this.trail = trail;
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
		SingleHiking other = (SingleHiking) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "SingleHiking [id=" + id + ", distance=" + distance + ", hikingDate=" + hikingDate + "]";
	}

}
