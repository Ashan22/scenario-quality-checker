package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/** Scenario strategy for counting special steps
 * @author Artur Mierzwa
 */
public class CountSpecialStepsScenarioStrategy extends ScenarioStrategy {

    /**
     * @param scenario This method is responsible for proccessing scenario when user want to count number of steps
     */
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

