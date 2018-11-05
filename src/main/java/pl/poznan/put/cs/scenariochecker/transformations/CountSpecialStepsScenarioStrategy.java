package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountSpecialStepsScenarioStrategy extends ScenarioStrategy {

    @Override
    public int processScenario(Scenario scenario) {
        return countSpecialSteps(scenario.getSteps());
    }

    /**
     * @param steps
     * @return number of special steps
     */
    private int countSpecialSteps(List<Step> steps) {
        return  steps.
                stream().
                filter(ScenarioHelper::isSpecialStep).
                map(step -> countSpecialSteps(step.getSubSteps()) + 1).
                mapToInt(Integer::intValue).
                sum();
    }
}

