package com.neueda.shortener.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="URL")
public class Url implements Serializable {
	private static final long serialVersionUID = -6850263394671725762L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", nullable = false, unique = true)
	@JsonIgnore
	private Integer id;
	
	@Column(name="FULL_URL")
	private String fullUrl;
	
	@Column(name="SHORT_URL_STRING")
	private String shortUrlString;
	
	public Url() {
	}

	public Url(Integer id, String fullUrl, String shortUrlString) {
		this.id = id;
		this.fullUrl = fullUrl;
		this.shortUrlString = shortUrlString;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortUrlString() {
		return shortUrlString;
	}

	public void setShortUrlString(String shortUrlString) {
		this.shortUrlString = shortUrlString;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", fullUrl=" + fullUrl + ", shortUrlString=" + shortUrlString + "]";
	}
	
	
}
