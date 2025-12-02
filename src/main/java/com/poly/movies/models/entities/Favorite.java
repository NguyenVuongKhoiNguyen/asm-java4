package com.poly.movies.models.entities;

import java.util.Date;

import org.apache.tomcat.jakartaee.commons.lang3.builder.ToStringExclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="Favorites")
@Entity
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; //can't work with int because it automatically set 0 to id in which trigger identity insert
	private String userId;
	private String videoId;
	private Date likedDate;
	
	@ManyToOne
	@JoinColumn(name ="Userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="Videoid")
	private Video video;
	
}
