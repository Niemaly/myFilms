package com.Szumski.myFilms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestSearchController {

    @GetMapping(value = "/search")
    public String search(){
        return "search-test";
    }



    @GetMapping(value = "/allRequests")
    public String allRequests(){
        return "allOtherPostMethods";
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String exceptionHandlerCheck(){
        return "index";
    }

}
