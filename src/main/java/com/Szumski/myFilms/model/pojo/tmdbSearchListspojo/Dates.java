package com.Szumski.myFilms.model.pojo.tmdbSearchListspojo;

import java.util.HashMap;
import java.util.Map;

public class Dates {

    private String maximum;
    private String minimum;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
