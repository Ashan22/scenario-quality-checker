package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy extends ScenarioStrategy {

    @Override
    public int processScenario(Scenario scenario) {
        List<String> allActors = new ArrayList<>(scenario.getActors());
        allActors.add(scenario.getSystemActor());
        return countStepsWithoutActorsNameAtTheBeginning(scenario.getSteps(), allActors);
    }

    /**
     * @param steps
     * @param allActors
     * @return number of steps without actors name
     */
    private int countStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> allActors) {
        int counter = 0;

        for (Step s : steps) {
            if (!ScenarioHelper.beginsWithActorsName(s, allActors) && !ScenarioHelper.isSpecialStep(s)) {
                counter++;
            } else if (ScenarioHelper.isSpecialStep(s)) {
                counter += countStepsWithoutActorsNameAtTheBeginning(s.getSubSteps(), allActors);
            }
        }

        return counter;
    }
}