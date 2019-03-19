package me.glagolev.baumaneateries.features.menu.model;

import java.util.List;

import me.glagolev.baumaneateries.features.eateries.model.EateryType;

public class Dish {

    private String id;
    private String name;
    private Integer weight;
    private Integer price;
    private EateryType eateryType;
    private List<DishElement> elements;
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

    public Integer getPrice() {
        return price;
    }

    public EateryType getEateryType() {
        return eateryType;
    }

    public List<DishElement> getElements() {
        return elements;
    }

    public Integer getElementCount(Element element) {
        for (DishElement dishElement: elements) {
            if (dishElement.getElement() == element) {
                return dishElement.getCount();
            }
        }
        throw new IllegalArgumentException("unknown Element " + element);
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
                ", price=" + price +
                ", eateryType=" + eateryType +
                ", elements=" + elements +
                ", calorie=" + calorie +
                '}';
    }
}
