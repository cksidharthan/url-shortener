package com.neueda.shortener.service;

import java.util.List;

import com.neueda.shortener.entity.Url;

/**
 * Interface for URL shortener service
 * @author sid
 *
 */
public interface UrlService {
	
	public List<Url> findAll();

	public Url findByShortUrlCode(String code);

	public Url save(Url urlObject);

}
