package com.residential.foundation.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.*;

@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username")
		})
@Data
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String username;
	@Column
	private String password;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Collection<Role> roles = new HashSet<>();

	public User(){}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
