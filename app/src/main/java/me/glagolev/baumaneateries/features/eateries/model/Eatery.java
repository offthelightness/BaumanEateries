package me.glagolev.baumaneateries.features.eateries.model;

/**
 * Модель столовой с необходимыми данными
 */
public class Eatery {

    private String id;
    private EateryType type;
    private String name;
    private String location;
    private String description;
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

    public String getDescription() {
        return description;
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
