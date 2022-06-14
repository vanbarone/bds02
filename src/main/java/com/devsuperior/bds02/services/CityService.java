package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.services.exceptions.ExceptionDatabase;
import com.devsuperior.bds02.services.exceptions.ExceptionEntityNotFound;


@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		
		List<City> list = repository.findAll(Sort.by("name"));
		
		return list.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public CityDTO insert(CityDTO dto) {
		
		City entity = new City();
		
		setarDados(entity, dto);
		
		entity = repository.save(entity);
		
		return new CityDTO(entity);
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			throw new ExceptionEntityNotFound("Id not Found = " + id);
		} catch (DataIntegrityViolationException ex) {
			throw new ExceptionDatabase("Integrity violation");
		}
	}
	
	private void setarDados(City entity, CityDTO dto) {
		
		entity.setName(dto.getName());
	}
	
}
