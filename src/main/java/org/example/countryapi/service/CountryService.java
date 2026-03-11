package org.example.countryapi.service;

import org.example.countryapi.model.Country;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {
    private List<Country> cache = new ArrayList<>();
    private long lastFetchTime = 0;

    public List<Country> getCountries() {

        long now = System.currentTimeMillis();

        // Check if cache is empty or older than 10 minutes
        if (cache.isEmpty() || now - lastFetchTime > 600000) {

            RestTemplate restTemplate = new RestTemplate();

            String url = "https://restcountries.com/v3.1/all?fields=name,capital,region,population,flags";

            // Call external API and get response as a list of maps
            List<Map<String, Object>> response =
                    restTemplate.getForObject(url, List.class);

            cache.clear();

            // Extract required fields from API response and populate cache
            for (Map<String, Object> item : response) {

                Map nameMap = (Map) item.get("name");
                String name = nameMap.get("common").toString();

                List capitals = (List) item.get("capital");
                String capital = capitals != null ? capitals.get(0).toString() : "";

                String region = item.get("region").toString();

                long population = Long.parseLong(item.get("population").toString());

                Map flags = (Map) item.get("flags");
                String flag = flags.get("png").toString();

                cache.add(new Country(name, capital, region, population, flag));
            }

            lastFetchTime = now;
        }

        return cache;
    }

    public List<Country> search(String keyword) {

        List<Country> result = new ArrayList<>();

        for (Country c : getCountries()) { // Always get latest cached data

            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(c);
            }
        }

        return result;
    }
}
