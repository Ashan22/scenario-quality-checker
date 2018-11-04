package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.List;

public class countStepsWithoutActorsNameAtTheBeginning extends ScenarioStrategy {

    @Override
    public void processScenario(Scenario scenario) {
        scenario.setNumberOfStepsWithoutActors(countStepsWithoutActorsNameAtTheBeginning(scenario.getSteps(), scenario.getActors()));
    }

    private int countStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> actors) {
        return steps.
                stream().
                filter(step -> ScenarioHelper.isActorStep(step, actors)).
                map(step -> countStepsWithoutActorsNameAtTheBeginning(step.getSubSteps(), actors) + 1).
                mapToInt(Integer::intValue).
                sum();
    }
}