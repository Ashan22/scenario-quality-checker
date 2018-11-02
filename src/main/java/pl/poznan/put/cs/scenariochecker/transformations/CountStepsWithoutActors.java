package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;
import java.util.function.Predicate;

public class CountStepsWithoutActors extends ScenarioStrategy {

    @Override
    public void processScenario(Scenario scenario) {
        scenario.setNumberOfStepsWithoutActors(countStepsWithoutActor(scenario.getSteps(), scenario.getActors()));
    }

    private int countStepsWithoutActor(List<Step> steps, List<String> actors) {
        return steps.
                stream().
                filter(step -> ScenarioHelper.isNoActorStep(step, actors)).
                map(step -> countStepsWithoutActor(step.getSubSteps(), actors) + 1).
                mapToInt(Integer::intValue).
                sum();
    }
}
