package org.example.countryapi.controller;

import org.example.countryapi.model.Country;
import org.example.countryapi.service.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/countries")
public class CountryController {
    // Service layer injected via constructor
    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    //Returns full list of countries from service (cached)
    @GetMapping
    public List<Country> getCountries() {
        return service.getCountries();
    }

    //Returns filtered country list matching the keyword
    @GetMapping("/search")
    public List<Country> search(@RequestParam String q) {
        return service.search(q);
    }
}
