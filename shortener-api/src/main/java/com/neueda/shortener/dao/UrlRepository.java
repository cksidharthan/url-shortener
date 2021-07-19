package com.neueda.shortener.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neueda.shortener.entity.Url;

/**
 * 
 * Repository file that Extends JPA repository to map it with URL Table
 * @author sid
 *
 */

public interface UrlRepository extends JpaRepository<Url, Integer> {
	
	/**
	 * Gets the Short URL string to fetch the row that contains information of full URL
	 * @param shortUrlString
	 * @return Url object
	 */
	
	@Query("from Url where shortUrlString=:shortUrlString")
	public Url findByShortUrlCode(@Param("shortUrlString")String shortUrlString);
	
	/**
	 * Gets the FullUrl string to fetch the row that contains information
	 * @param fullUrl
	 * @return Url object
	 */
	
	@Query("from Url where fullUrl=:fullUrl")
	public Url findByFullUrl(@Param("fullUrl")String fullUrl);
}