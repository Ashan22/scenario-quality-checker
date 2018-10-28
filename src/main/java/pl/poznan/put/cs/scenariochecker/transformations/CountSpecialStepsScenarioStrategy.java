package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;

public class CountSpecialStepsScenarioStrategy extends ScenarioStrategy {

    @Override
    public void processScenario(Scenario scenario) {
        scenario.setNumberOfSpecialSteps(countSteps(scenario.getSteps()));
    }

    private int countSteps(List<Step> steps) {
        long deepCount = steps.stream()
                .filter(step -> ScenarioHelper.isSpecialStep(step))
                .map(step -> countSteps(step.getSubSteps()))
                .mapToLong(Integer::intValue)
                .sum();
        return (int) (1 + deepCount);
    }

}
