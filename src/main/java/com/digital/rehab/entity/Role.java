package com.digital.rehab.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String role; 
	
	@JsonBackReference
	@ManyToMany(mappedBy = "roles",cascade = {CascadeType.ALL,CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();
	
	@JsonBackReference
	@ManyToMany(mappedBy = "roles",cascade = {CascadeType.ALL,CascadeType.REMOVE}, fetch = FetchType.LAZY)
	private List<User> doctors = new ArrayList<>();
}
