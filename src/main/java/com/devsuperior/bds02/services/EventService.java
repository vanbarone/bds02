package com.devsuperior.bds02.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ExceptionEntityNotFound;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = true)
	public List<EventDTO> findAll() {
		
		List<Event> list = repository.findAll();
		
		return list.stream().map(x -> new EventDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public EventDTO findById(Long id) {
		
		try {
			Event entity = repository.getOne(id);
			
			return new EventDTO(entity);
			
		} catch (EntityNotFoundException ex) {
			throw new ExceptionEntityNotFound("Id not found: " + id);
		}
	}
	
	@Transactional
	public EventDTO update(Long id, EventDTO dto) {
		
		try {
			Event entity = repository.getOne(id);
			
			setarCampos(entity, dto);
			
			entity = repository.save(entity);
			
			return new EventDTO(entity);
			
		} catch (EntityNotFoundException ex) {
			throw new ExceptionEntityNotFound("Id not found: " + id);
		}
	}
	
	private void setarCampos(Event entity, EventDTO dto) {
		
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		
		entity.setCity(new City(dto.getCityId(), ""));
	}

}
