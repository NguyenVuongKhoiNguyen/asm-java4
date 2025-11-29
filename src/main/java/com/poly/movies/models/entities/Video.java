package com.poly.movies.models.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
	private int likes;
	private int dislikes;
}
