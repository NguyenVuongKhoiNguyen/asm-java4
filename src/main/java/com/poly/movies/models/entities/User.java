package com.poly.movies.models.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.lang3.builder.ToStringExclude;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
	private Date createdDate;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Favorite> favorites = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Share> shares = new ArrayList<>();
}
