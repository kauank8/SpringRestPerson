package farias.paulino.kauan.SpringRestPerson.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farias.paulino.kauan.SpringRestPerson.data.vo.v1.PersonVO;
import farias.paulino.kauan.SpringRestPerson.data.vo.v2.PersonVOV2;
import farias.paulino.kauan.SpringRestPerson.exceptions.ResourceNotFoundException;
import farias.paulino.kauan.SpringRestPerson.mapper.DozerMapper;
import farias.paulino.kauan.SpringRestPerson.mapper.custom.PersonMapper;
import farias.paulino.kauan.SpringRestPerson.model.Person;
import farias.paulino.kauan.SpringRestPerson.repository.PersonRepository;

@Service
public class PersonServices {
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;

	public List<PersonVO> findAll() {
		logger.info("Finding all person");
		
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}

	public PersonVO findById(Long id) {
		logger.info("Finding one person");
		
		var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO create(PersonVO personVO) {
		logger.info("Creating one person");
		var entity = DozerMapper.parseObject(personVO, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 personVOV2) {
		logger.info("Creating one person with V2");
		var entity = mapper.convertVoToPerson(personVOV2);
		var vo = mapper.convertPersonToVO(repository.save(entity));
		return vo;
	}
	
	public PersonVO update(PersonVO personVO) {
		logger.info("updating one person");
		
		var entity = repository.findById(personVO.getId()).orElseThrow
				(()-> new ResourceNotFoundException("No records found for this id"));
		entity.setFirstName(personVO.getFirstName());
		entity.setLastName(personVO.getLastName());
		entity.setGender(personVO.getGender());
		entity.setAddress(personVO.getAddress());
		
		var vo = DozerMapper.parseObject(repository.save(entity),PersonVO.class);
		return vo;
	}
	public void delete(Long id) {
		logger.info("deleting one person");
		
		var entity = repository.findById(id).orElseThrow
				(()-> new ResourceNotFoundException("No records found for this id"));
		
		repository.delete(entity);
	}
}
