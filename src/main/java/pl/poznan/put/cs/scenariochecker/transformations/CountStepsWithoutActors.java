package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;

public class CountStepsWithoutActors extends ScenarioStrategy{

    @Override
    public void processScenario(Scenario scenario) {
        scenario.setNumberOfStepsWithoutActors(countStepsWithoutActor(scenario.getSteps()));
    }

    private int countStepsWithoutActor(List<Step> steps){
        return  steps.
                stream().
                filter(step -> ScenarioHelper.isActorStep(step)).
                map(step -> countStepsWithoutActor(step.getSubSteps()) + 1).
                mapToInt(Integer::intValue).
                sum();
    }
}
