package farias.paulino.kauan.SpringRestPerson.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import farias.paulino.kauan.SpringRestPerson.data.vo.v2.PersonVOV2;
import farias.paulino.kauan.SpringRestPerson.model.Person;

@Service
public class PersonMapper {
	public PersonVOV2 convertPersonToVO(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setAddress(person.getAddress());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setId(person.getId());
		vo.setGender(person.getGender());
		vo.setBirthDay(new Date());
		return vo;
	}

	public Person convertVoToPerson(PersonVOV2 person) {
		Person entity = new Person();
		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setId(person.getId());
		entity.setGender(person.getGender());
		return entity;
	}
}