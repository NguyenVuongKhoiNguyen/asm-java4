package com.poly.movies.models.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="Videos")
@Entity
public class Video {
	@Id
	private String id;
	private String title;
	private String poster;
	private String duration;
	private Date releasedDate;
	private String genre;
	private String country;
	private double imdb;
	private String description;
	private String video;
	private int views;
	private boolean active;
	private String production;
	private boolean trending;
	
	@OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
	private Set<Favorite> favorites = new HashSet<>();	
	
	@OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
	private Set<Share> shares = new HashSet<>();
	
	public Integer getFavoritesSize() {
		return favorites.size();
	}
	
	public Integer getSharesSize() {
		return shares.size();
	}
}
