package com.restfulwebservices.restfulwebservices.dynamoExample.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.restfulwebservices.restfulwebservices.dynamoExample.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonRepository {
    @Autowired
    private DynamoDBMapper mapper;

    public Person create(Person person)
    {
        mapper.save(person);
        return person;
    }

    public Person findPersonById(String id)
    {
        return mapper.load(Person.class,id);
    }

    public String delete(Person person)
    {
         mapper.delete(person);
         return "Successfully removed";
    }

    public String edit(Person person)
    {
        mapper.save(person,dynamoDBSaveExpression(person));
        return "Reord updated";
    }

    private DynamoDBSaveExpression dynamoDBSaveExpression(Person person)
    {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();

        Map<String, ExpectedAttributeValue> expectedAttributeValueMap = new HashMap<>();

        expectedAttributeValueMap.put("personId", new ExpectedAttributeValue(new AttributeValue().withS(person.getPersonId())));

        dynamoDBSaveExpression.setExpected(expectedAttributeValueMap);

        return dynamoDBSaveExpression;
    }
}
