package com.poly.movies.models.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Favorite> favorites = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Share> shares = new HashSet<>();
	
	public Integer getUserFavoritesSize() {
		return favorites.size();
	}
	
	public Integer getUserSharesSize() {
		return shares.size();
	}
}
