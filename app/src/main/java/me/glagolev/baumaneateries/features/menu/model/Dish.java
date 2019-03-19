package me.glagolev.baumaneateries.features.menu.model;

import java.util.List;
import java.util.Map;

import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class Dish {

    private String id;
    private String name;
    private Integer weight;
    private Integer cost;
    private EateryType eateryType;
    private List<DishElements> elements;
    private Integer calorie;

    public String getId() {
        return id;
    }

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

    public List<DishElements> getElements() {
        return elements;
    }

    public Integer getCalorie() {
        return calorie;
    }


    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", cost=" + cost +
                ", eateryType=" + eateryType +
                ", elements=" + elements +
                ", calorie=" + calorie +
                '}';
    }
}
