package com.neueda.shortener.service;

import java.util.List;

import com.neueda.shortener.entity.Url;

public interface UrlService {
	
	public List<Url> findAll();

	public Url findById(int id);
	
	public Url findByShortUrlCode(String code);

	public Url save(Url urlObject);

	public void deleteById(int id);	
}
