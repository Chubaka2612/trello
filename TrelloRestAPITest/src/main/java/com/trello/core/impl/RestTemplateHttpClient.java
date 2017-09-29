package com.trello.core.impl;

import java.net.URI;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.trello.core.TrelloHttpClient;
import com.trello.exception.TrelloHttpException;

public class RestTemplateHttpClient implements TrelloHttpClient {

	private RestTemplate restTemplate;

	public RestTemplateHttpClient() {
		restTemplate = new RestTemplate();
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter
				.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	}

	public <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
		try {
			return restTemplate.postForObject(url, object, objectClass, params);
		} catch (RestClientException e) {
			throw new TrelloHttpException(e);
		}
	}

	public <T> T get(String url, Class<T> objectClass, String... params) {
		try {
			return restTemplate.getForObject(url, objectClass, params);
		} catch (RestClientException e) {
			throw new TrelloHttpException(e);
		}
	}

	public <T> T putForObject(String url, T object, Class<T> objectClass, String... params) {
		try {
			return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity(object), objectClass, params).getBody();
		} catch (RestClientException e) {
			throw new TrelloHttpException(e);
		}
	}

	public void delete(String url, String... params) {
		try {
			restTemplate.delete(url, params);
		} catch (RestClientException e) {
			throw new TrelloHttpException(e);
		}
	}

	public URI postForLocation(String url, Object object, String... params) {
		try {
			return restTemplate.postForLocation(url, object, params);
		} catch (RestClientException e) {
			throw new TrelloHttpException(e);
		}
	}
}
