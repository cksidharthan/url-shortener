package com.neueda.shortener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.servlet.view.RedirectView;

import com.neueda.shortener.dao.UrlRepository;
import com.neueda.shortener.entity.Url;
import com.neueda.shortener.helper.ShortenerRestResponse;
import com.neueda.shortener.service.impl.UrlServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class ShortenerApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UrlRepository urlRepository;
	
	@Mock
	private UrlRepository urlRepository2;
	
	@InjectMocks
	UrlServiceImpl urlService;

	@Before
	public void init() {
		Url url1 = new Url(1, "https://google.com", "123");
		Url url2 = new Url(2, "https://bing.com", "1234");
		Url url3 = new Url(3, "https://duckduckgo.com", "12345");

		urlRepository.save(url1);
		urlRepository.save(url2);
		urlRepository.save(url3);
	}

	@After
	public void tearDown() {
		urlRepository.deleteAll();
	}
	
	@Test
	public void getFullUrlTest() throws Exception {
		ShortenerRestResponse<List<Url>> body = this.restTemplate.getForObject("/url/all", ShortenerRestResponse.class);
		assertEquals(body.getResponsePayload().size(), 3);
		assertEquals(body.getStatusCode(), HttpStatus.OK);
		assertEquals(body.getResponseMessage(), "URLs Found");
	}
	
	@Test
	public void addUrlTest() throws Exception {
		Url url1 = new Url(10, "https://googleorg.com", "1236");
		ShortenerRestResponse<Url> body = this.restTemplate.postForObject("/url/", url1, ShortenerRestResponse.class);
		assertEquals(body.getStatusCode(), HttpStatus.OK);
		assertEquals(body.getResponseMessage(), "URL added");
	}
	
	@Test
	public void addUrlTestWithoutHttp() throws Exception {
		Url url1 = new Url(10, "googleorg.com", "1236");
		ShortenerRestResponse<Url> body = this.restTemplate.postForObject("/url/", url1, ShortenerRestResponse.class);
		assertEquals(body.getStatusCode(), HttpStatus.OK);
		assertEquals(body.getResponseMessage(), "URL added");
	}
	
	@Test
	public void addUrlTestAlreadyPresent() throws Exception {
		Url url1 = new Url(10, "google.com", "1236");
		ShortenerRestResponse<Url> body = this.restTemplate.postForObject("/url/", url1, ShortenerRestResponse.class);
		assertEquals(body.getStatusCode(), HttpStatus.OK);
		assertEquals(body.getResponseMessage(), "URL added");
	}
	
	@Test
	public void whenShortCodeExistsRetunLongUrl() {
		String shortCode="123";
		Url url = new Url(1, "https://google.com", "123");
		Mockito.when(urlRepository2.findByShortUrlCode(shortCode)).thenReturn(url);
		assertEquals(url, urlService.findByShortUrlCode(shortCode));
	}
	
	@Test
	public void getUrlbyCodeTest() {
		RedirectView body = this.restTemplate.getForObject("/url/123", RedirectView.class);
		assertEquals(body, null);
	}

}
