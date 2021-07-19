package com.neueda.shortener.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.neueda.shortener.dao.UrlRepository;
import com.neueda.shortener.entity.Url;
import com.neueda.shortener.service.UrlService;

@Service
public class UrlServiceImpl implements UrlService {
	
	private UrlRepository urlRepository;
	
	public UrlServiceImpl(UrlRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	public List<Url> findAll() {
		return urlRepository.findAll();
	}

	public Url findById(int id) {
		Optional<Url> optionalUrl = urlRepository.findById(id);
		Url urlObject = null;
		if (optionalUrl.isPresent()) {
			urlObject = optionalUrl.get();
		} else {
			throw new RuntimeException("unrecogonized url - " + id);
		}
		return urlObject;
	}
	
	public Url findByShortUrlCode(String code) {
		return urlRepository.findByShortUrlCode(code);
	}

	public Url save(Url urlObject) {
		Url url = urlRepository.findByFullUrl(urlObject.getFullUrl());
		if (url == null) {
			Url newUrl = urlRepository.save(urlObject);
			return newUrl;
		} else {
			return url;
		}
	}

	public void deleteById(int id) {
		urlRepository.deleteById(id);
	}

}
