package com.restfulwebservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RestController
public class filteringController {
    @GetMapping("/filtering")
    public MappingJacksonValue retrieveBean()
    {
        TestBean testBean =  new TestBean("test1","test2","test3");

        //Dynamic filtering
        FilterProvider filters = getFilterProvider(new HashSet<String>(Arrays.asList("field1","field3")));;

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(testBean);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveListBean()
    {
        List<TestBean> lstBeans = Arrays.asList(new TestBean("test1","test2","test3"), new TestBean("test4","test5","test6"));

        //Dynamic filtering
        FilterProvider filters = getFilterProvider(new HashSet<String>(Arrays.asList("field2","field3")));

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(lstBeans);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    private FilterProvider getFilterProvider(HashSet<String> fields) {
        //Dynamic filtering
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept(fields);

        return new SimpleFilterProvider().addFilter("testBeanFilter",filter);
    }


}
