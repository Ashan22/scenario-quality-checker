package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;

public abstract class ScenarioStrategy {

    public abstract Object processScenario(Scenario scenario);
}
