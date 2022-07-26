package com.example.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable {
	private static final long serialVersionUID = -7474833938724371950L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String description;

	@ManyToMany
	@JoinTable(name = "profiles_sections_map", joinColumns = {
			@JoinColumn(name = "profile_id") }, inverseJoinColumns = { @JoinColumn(name = "section_id") })
	private List<Section> sections = new ArrayList<>();

	@ManyToMany(mappedBy = "profiles")
	private Set<User> users = new HashSet<>();

	public Profile() {
		super();
	}

	public Profile(Integer id) {
		this.id = id;
	}

	public Profile(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Profile(Integer id, String name, String description) {
		this(id);
		this.name = name;
		this.description = description;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public void addSection(Section section) {
		sections.add(section);
		section.getProfiles().add(this);
	}

	public void removeSection(Section section) {
		sections.remove(section);
		section.getProfiles().remove(this);
	}

	public void addSections(Set<Section> sections) {
		this.sections.addAll(sections);
		sections.forEach(section -> section.getProfiles().add(this));
	}

	public void removeSections(Set<Section> sections) {
		this.sections.removeAll(sections);
		sections.forEach(section -> section.getProfiles().remove(this));
	}

	public static Profile from(String query) {
		Profile profile = new Profile(query, query);
		profile.addSection(new Section(query));
		return profile;
	}
}
