package com.Szumski.myFilms.controllers.operationsForControllers;

import com.Szumski.myFilms.model.pojo.tmdbSearchListspojo.AllList;
import org.springframework.web.client.RestTemplate;


public class AllListReturner {

    public static AllList returnAllList(RestTemplate restTemplate, String string){
        return restTemplate.getForObject(
                string,
                AllList.class
        );
    }
}
