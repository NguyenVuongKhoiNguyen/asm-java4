package com.poly.movies.models.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Users")
@Entity
public class UserDTO {
	@Id
	private String id;
	private String password;
	private String email;
	private String fullname;
	private String photo;
	private boolean admin; // 1 is admin
	private Date createdDate;
}
