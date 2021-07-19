package com.neueda.shortener.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.neueda.shortener.dao.UrlRepository;
import com.neueda.shortener.entity.Url;
import com.neueda.shortener.service.UrlService;

/** 
 * Implementation of URL Shortener Service interface
 * @author sid
 *
 */

@Service
public class UrlServiceImpl implements UrlService {
	
	private UrlRepository urlRepository;
	
	public UrlServiceImpl(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	/** 
	 * Service to fetch all the urls from database using URL Jpa repository
	 */
	public List<Url> findAll() {
		return urlRepository.findAll();
	}

	/**
	 * Gets the URL object for the given short url code
	 */
	
	public Url findByShortUrlCode(String code) {
		return urlRepository.findByShortUrlCode(code);
	}

	/**
	 * Saves the URL object
	 * 
	 * Logic:
	 * Checks if the URL object for the full url is present
	 * If yes, gets the url object from database
	 * If no, saves the url object and returns back the same object.
	 */
	
	public Url save(Url urlObject) {
		Url url = urlRepository.findByFullUrl(urlObject.getFullUrl());
		if (url == null) {
			Url newUrl = urlRepository.save(urlObject);
			return newUrl;
		} else {
			return url;
		}
	}

}
