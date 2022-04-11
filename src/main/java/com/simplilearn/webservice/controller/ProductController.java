package com.simplilearn.webservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.simplilearn.webservice.model.Product;

@RestController
@RequestMapping("/api/consumer")
public class ProductController {
	
	//formulate api url
	final String PRODUCT_URI = "http://localhost:8090/product";
	final String PRODUCTS_URI = PRODUCT_URI+"s";
	final String SEARCH_URI ="http://localhost:8090/search?name=";
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		// create a rest client
		RestTemplate restTemplate = new RestTemplate();
		
		// trigger get products http request
		ResponseEntity<List> response = restTemplate.getForEntity(PRODUCTS_URI, List.class);
		
		// collect and return response
		return response.getBody();
	}

	@GetMapping("/product/{id}")
	public Product getOneProduct(@PathVariable("id") int id){
		// create a rest client
		RestTemplate restTemplate = new RestTemplate();
		
		// trigger get one product http request
		ResponseEntity<Product> response = restTemplate.getForEntity(PRODUCT_URI+"/"+id, Product.class);
		
		// collect and return response
		return response.getBody();
	}
	
	@GetMapping("/product/search")
	public List<Product> getOneProduct(@RequestParam("name") String name){
		// create a rest client
		RestTemplate restTemplate = new RestTemplate();
		
		// trigger get one product http request
		ResponseEntity<List> response = restTemplate.getForEntity(SEARCH_URI+name, List.class);
		
		// collect and return response
		return response.getBody();
	}
	
	
	@PutMapping("/products")
	public String updateOneProduct(@RequestBody Product product){
		// create a rest client
		RestTemplate restTemplate = new RestTemplate();
		
		// trigger update one product http request
		restTemplate.put(PRODUCTS_URI, product);
		
		// collect and return response
		return "Product is update successflly ";
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable("id") long id) {
		// use rest template
		RestTemplate restTemplate = new RestTemplate();
		
		// consume url endpoint
		restTemplate.delete(PRODUCTS_URI+"/"+id);
		return "Product is deleted sucessfully !";
	}
	
}
