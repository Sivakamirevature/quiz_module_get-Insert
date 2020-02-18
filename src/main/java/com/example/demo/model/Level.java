package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="levels")
public class Level {
	@Id
	@Column(name="level_id")
	int levelId;
	@Column(name="level_name")
	String levelName;
	
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int leveId) {
		this.levelId = leveId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}