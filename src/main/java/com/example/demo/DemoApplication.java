package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


//@SpringBootApplication
public class DemoApplication {

	private static final String url = "http://94.198.50.185:7081/api/users";
	private static final String url3 = "http://94.198.50.185:7081/api/users/3";
	static RestTemplate restTemplate = new RestTemplate();

	static String cookie ;


	public static void main(String[] args) {


//		SpringApplication.run(DemoApplication.class, args);

	get();
	post();
  put();
		delete();



	}


private static void get(){
// создае headers
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
// создае сущность
	HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
// запрос
	ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

// ответ
	HttpStatus statusCode = result.getStatusCode();
	System.out.println("status code - " + statusCode);

	String user = result.getBody();//помощью getBody(). Это и будет тот самый 6-ти значны
	System.out.println("response body - " + user);

	HttpHeaders responseHeaders = result.getHeaders();
	System.out.println("response headers - " + responseHeaders.getFirst("Set-Cookie"));
	 cookie = responseHeaders.getFirst("Set-Cookie");

}


	private static void post() {

		User user = new User(3,"James","Brown",32);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Cookie", cookie);
		HttpEntity<User> entity = new HttpEntity<User>(user,headers);

		ResponseEntity<String> result = restTemplate.exchange(
				url, HttpMethod.POST, entity, String.class);

		HttpStatus statusCode = result.getStatusCode();
		System.out.println("status code - " + statusCode);

		String body = result.getBody();//помощью getBody(). Это и будет тот самый 6-ти значны
		System.out.println("response body - " + body);

		HttpHeaders responseHeaders = result.getHeaders();
		System.out.println("response headers - " + responseHeaders);

	}

	private static void put() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Cookie", cookie);

		HttpEntity<User> entity = new HttpEntity<User>(new User(3,"Thomas","Shelby",32),headers);

		ResponseEntity<String> result = restTemplate.exchange(
				url, HttpMethod.PUT, entity, String.class);

		HttpStatus statusCode = result.getStatusCode();
		System.out.println("status code - " + statusCode);

		String body = result.getBody();//помощью getBody(). Это и будет тот самый 6-ти значны
		System.out.println("response body - " + body);

		HttpHeaders responseHeaders = result.getHeaders();
		System.out.println("response headers - " + responseHeaders);

	}

	private static void delete() {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Cookie", cookie);

		HttpEntity<User> entity = new HttpEntity<User>(headers);

		ResponseEntity<String> result = restTemplate.exchange(
				url3, HttpMethod.DELETE, entity, String.class);

		HttpStatus statusCode = result.getStatusCode();
		System.out.println("status code - " + statusCode);

		String body = result.getBody();//помощью getBody(). Это и будет тот самый 6-ти значны
		System.out.println("response body - " + body);

		HttpHeaders responseHeaders = result.getHeaders();
		System.out.println("response headers - " + responseHeaders);

	}




}






