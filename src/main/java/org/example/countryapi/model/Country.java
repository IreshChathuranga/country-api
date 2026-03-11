package org.example.countryapi.model;

public class Country {
    private String name;
    private String capital;
    private String region;
    private long population;
    private String flag;

    public Country(String name, String capital, String region, long population, String flag) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.flag = flag;
    }

    public String getName() { return name; }
    public String getCapital() { return capital; }
    public String getRegion() { return region; }
    public long getPopulation() { return population; }
    public String getFlag() { return flag; }
}
