package pl.poznan.put.cs.scenariochecker.controller.strategy;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;

public class CountStepsScenarioStrategy extends ScenarioStrategy {
    @Override
    public void processScenario(Scenario scenario) {
        int numberOfSteps = countSteps(scenario.getSteps());
        scenario.setNumberOfSteps(5);
    }

    private int countSteps(List<Step> steps) {
        for(Step step: steps){
            
        }
    }
}
