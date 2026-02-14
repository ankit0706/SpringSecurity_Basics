package com.sample.secureapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChampionsController {

    /**
     * enhanced switch case using lambda function
     * @param year
     * @return
     */
    @GetMapping("/getChampion")
    public String getChampionName(@RequestParam int year){
        String prefix =  "Champions for " + year;
        return switch (year) {
            case 2007, 2024 ->prefix + " India";
            case 2009 -> prefix + " Pakistan";
            case 2010, 2022 -> prefix + " England";
            case 2012, 2016 -> prefix + " West Indies";
            case 2014 -> prefix + " Sri Lanka";
            case 2021 -> prefix + " Australia";
            default -> "Invalid year";
        };
    }
}
