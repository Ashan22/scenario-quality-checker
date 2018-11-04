package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CountSpecialStepsScenarioStrategy extends ScenarioStrategy {

    @Override
    public void processScenario(Scenario scenario) {
<<<<<<< HEAD
        scenario.setNumberOfSpecialSteps(countSpecialSteps(scenario.getSteps()));
    }

=======

        scenario.setNumberOfSpecialSteps(countSpecialSteps(scenario.getSteps()));
    }
>>>>>>> b22096b24e78419f15860ed990a86dead566547c

    private int countSpecialSteps(List<Step> steps) {
        return  steps.
                stream().
                filter(ScenarioHelper::isSpecialStep).
                map(step -> countSpecialSteps(step.getSubSteps()) + 1).
                mapToInt(Integer::intValue).
                sum();
    }
}

