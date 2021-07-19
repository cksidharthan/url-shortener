package com.neueda.shortener.controller;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.neueda.shortener.entity.Url;
import com.neueda.shortener.helper.ShortenerRestResponse;
import com.neueda.shortener.service.UrlService;

@RestController
@CrossOrigin
@RequestMapping("url")
public class UrlController {

	Logger logger = LogManager.getLogger(UrlController.class);

	private UrlService urlService;

	@Autowired
	public UrlController(UrlService urlService) {
		this.urlService = urlService;
	}

	@GetMapping(path = "/all", produces = "application/json")
	public ShortenerRestResponse<List<Url>> findAll() {
		try {
			List<Url> urlList = urlService.findAll();
			logger.debug("url list found " + urlList);
			if (urlList.size() > 0) {
				return new ShortenerRestResponse<List<Url>>(HttpStatus.OK, "URLs Found", urlList);
			} else if (urlList.size() == 0) {
				logger.info("empty url list found " + urlList);
				return new ShortenerRestResponse<List<Url>>(HttpStatus.NO_CONTENT, "No URLs available", urlList);
			} else {
				return new ShortenerRestResponse<List<Url>>(HttpStatus.NOT_FOUND, "Error occured", urlList);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ShortenerRestResponse<List<Url>>(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured", null);
		}

	}

	@GetMapping(path = "/{url_code}", produces = "application/json")
	public RedirectView getUrlbyCode(@PathVariable String url_code) {
		RedirectView redirectView = new RedirectView();
		Url url = urlService.findByShortUrlCode(url_code);
		if (url == null) {
			redirectView.setStatusCode(HttpStatus.NOT_FOUND);
			redirectView.setUrl("http://localhost:8090/error");
		} else {
			logger.info("url found " + url.getFullUrl());
			redirectView.setUrl(url.getFullUrl());
		}
		return redirectView;
	}

	@PostMapping(path = "/", produces = "application/json")
	public ShortenerRestResponse<Url> addUrl(@RequestBody Url urlObject) throws Exception {
		Url urlSaved;
		try {
			if (!urlObject.getFullUrl().startsWith("http")) {
				urlObject.setFullUrl("https://" + urlObject.getFullUrl());
			}
			String generatedString = RandomStringUtils.randomAlphanumeric(10);
			urlObject.setShortUrlString(generatedString);
			urlSaved = urlService.save(urlObject);
			logger.info("url saved " + urlObject);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ShortenerRestResponse<Url>(HttpStatus.INTERNAL_SERVER_ERROR, "URL Not Saved", null);
		}
		return new ShortenerRestResponse<Url>(HttpStatus.OK, "URL added", urlSaved);
	}

	@DeleteMapping(value = "/{url_id}", produces = "application/json")
	public ShortenerRestResponse<String> deleteUById(@PathVariable int url_id) {
		try {
			urlService.deleteById(url_id);
			logger.info("url deleted ");
			return new ShortenerRestResponse<String>(HttpStatus.OK, "URL deleted", "URL Deleted from database");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ShortenerRestResponse<String>(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured",
					"No Records Deleted");
		}

	}

}
