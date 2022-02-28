package com.sabas.blog.blogapi.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="users", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username"}),
		@UniqueConstraint(columnNames = {"email"})
})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String username;
	private String email;
	private String password;
	
	                    // Cuando haga una query de User en está consulta carga los roles tambien
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // Tipos de carga, cascade actualiza tambien los roles
	@JoinTable(name="user_roles",
				joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
				inverseJoinColumns =@JoinColumn(name="role_id", referencedColumnName = "id"))
	
	
	private Set<Role> roles;
}
