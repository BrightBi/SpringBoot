package com.bright.learn.controller;

import com.bright.learn.domain.Agency;
import com.bright.learn.service.AgencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/jdbc")
public class JdbcController {
	
	@Autowired
	private AgencyService agencyService;

	@PostMapping(value = "/insert")
	@ResponseBody
	public int insert(@RequestBody Agency agency) {
		return agencyService.insert(agency);
	}
	
	@PostMapping(value = "/update")
	@ResponseBody
	public int update(@RequestBody Agency agency) {
		return agencyService.update(agency);
	}
	
	@GetMapping(value = "/query/{id}")
	@ResponseBody
	public Agency query(@PathVariable("id") Long id) {
		return agencyService.query(id);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	@ResponseBody
	public int delete(@PathVariable("id") Long id) {
		return agencyService.delete(id);
	}
}
