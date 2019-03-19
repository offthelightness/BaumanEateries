package me.glagolev.baumaneateries.features.eateries.model;

public class Eatery {

    private String id;
    private EateryType type;
    private String name;
    private String location;
    private Integer openFrom;
    private Integer closedTo;
    private Integer dayFrom;
    private Integer dayTo;

    public String getId() {
        return id;
    }

    public EateryType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Integer getOpenFrom() {
        return openFrom;
    }

    public Integer getClosedTo() {
        return closedTo;
    }

    public Integer getDayFrom() {
        return dayFrom;
    }

    public Integer getDayTo() {
        return dayTo;
    }
}
