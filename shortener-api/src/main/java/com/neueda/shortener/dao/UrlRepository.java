package com.neueda.shortener.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neueda.shortener.entity.Url;

public interface UrlRepository extends JpaRepository<Url, Integer> {
	@Query("from Url where shortUrlString=:shortUrlString")
	public Url findByShortUrlCode(@Param("shortUrlString")String shortUrlString);
	
	@Query("from Url where fullUrl=:fullUrl")
	public Url findByFullUrl(@Param("fullUrl")String fullUrl);
}