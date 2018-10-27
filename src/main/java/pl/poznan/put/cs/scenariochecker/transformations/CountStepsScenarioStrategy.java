package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;

public class CountStepsScenarioStrategy extends ScenarioStrategy {

    /**
     * @param scenario This method is responsible for proccessing scenario when user want to count number of steps
     */
    @Override
    public void processScenario(Scenario scenario) {
        scenario.setNumberOfSteps(countSteps(scenario.getSteps()));
    }

    /**
     * @param steps
     * @return number of steps in this step with steps in every substeps
     */
    private int countSteps(List<Step> steps) {
        long deepCount = steps.stream()
                .filter(step -> ScenarioHelper.isSpecialStep(step))
                .map(step -> countSteps(step.getSubSteps()))
                .mapToLong(Integer::intValue)
                .sum();
        return (int) (steps.size() + deepCount);
    }
}