package Cucumber;

import com.endava.grocy.enums.DataKeys;

import java.util.*;

public class ScenarioContext {
    private Map<String, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public void setContext(DataKeys key, Object value) {
        scenarioContext.put(key.toString(), value);
    }

    public Object getContext(DataKeys key) {
        return scenarioContext.get(key.toString());
    }

    public Boolean isContains(DataKeys key) {
        return scenarioContext.containsKey(key.toString());
    }

}
