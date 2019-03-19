package me.glagolev.baumaneateries.features.menu.model;

import java.util.LinkedHashMap;

import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class Dish {
    private String name;
    private Integer weight;
    private Integer cost;
    private EateryType eateryType;
    private LinkedHashMap<DishElements, Integer> elements;
    private Integer calorie;

    public String getName() {
        return name;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getCost() {
        return cost;
    }

    public EateryType getEateryType() {
        return eateryType;
    }

    public LinkedHashMap<DishElements, Integer> getElements() {
        return elements;
    }

    public Integer getCalorie() {
        return calorie;
    }
}
