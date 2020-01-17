package com.Szumski.myFilms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    @GetMapping(value = "/search")
    public String search(){
        return "search-test";
    }

}
