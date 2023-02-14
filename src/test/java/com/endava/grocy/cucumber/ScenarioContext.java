package com.endava.grocy.cucumber;

import com.endava.grocy.enums.DataKeys;

import java.util.HashMap;
import java.util.Map;


public class ScenarioContext {
    private static ScenarioContext singleton;

    private Map<String, Object> scenarioContext = new HashMap<>();

    private ScenarioContext() {
    }

    public static ScenarioContext getInstance() {
        if (singleton == null) {
            return singleton = new ScenarioContext();
        }
        return singleton;
    }

    public void setScenarioContext(DataKeys key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getScenarioContext(DataKeys key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(DataKeys key) {
        return scenarioContext.containsKey(key.toString());
    }

}
