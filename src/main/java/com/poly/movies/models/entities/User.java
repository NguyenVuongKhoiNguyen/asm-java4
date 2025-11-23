package com.poly.movies.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Users")
@Entity
public class User {
	@Id
	private String id;
	private String password;
	private String email;
	private String fullname;
	private String photo;
	private boolean admin; //1 is admin
}
