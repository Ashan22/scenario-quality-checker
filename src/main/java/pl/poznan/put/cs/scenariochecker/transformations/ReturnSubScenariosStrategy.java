package pl.poznan.put.cs.scenariochecker.transformations;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;
import java.util.Objects;

public class ReturnSubScenariosStrategy extends ScenarioStrategy {

    private int maxLevel;

    /**
     * @param scenario
     * @return New scenario which contains steps only for chosen level
     */
    @Override
    public String processScenario(Scenario scenario) {
        maxLevel = scenario.getLevel();
        if (maxLevel < 0) {
            throw new ValueException("Level cannot be lower than 1");
        }

        JSONArray nestedSteps = createRecursivelyNestedStepsJson(scenario.getSteps(), 1);
        JSONArray actorsJsonArray = new JSONArray();
        actorsJsonArray.addAll(scenario.getActors());

        return new JSONObject()
                .appendField("title", scenario.getTitle())
                .appendField("systemActor", scenario.getSystemActor())
                .appendField("actors", actorsJsonArray)
                .appendField("steps", nestedSteps)
                .toJSONString();
    }

    /**
     * @param steps the list of steps on current level
     * @param currentLevel
     * @return json array of steps for chosen level
     */
    private JSONArray createRecursivelyNestedStepsJson(List<Step> steps, int currentLevel) {
        JSONArray currentStepsArray = new JSONArray();
        if(maxLevel > 0) {
            steps.forEach(step ->
                    currentStepsArray.add(new JSONObject()
                            .appendField("content", step.getContent())
                            .appendField("subSteps", currentLevel >= maxLevel ?
                                    new JSONArray() :
                                    createRecursivelyNestedStepsJson(step.getSubSteps(), currentLevel + 1)))
            );
        }
        return currentStepsArray;
    }

}
