package pl.poznan.put.cs.scenariochecker.service;

import com.google.gson.GsonBuilder;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscenariosToDepthLevelService {
    /**
     * @param scenario
     * @param maxLevel target depth level
     * @return New scenario which contains steps only for chosen level
     */
    public String getSubscenariosToDepthLevel(Scenario scenario, Integer maxLevel) {
        JSONArray nestedSteps = new JSONArray();

        if (maxLevel < 0) {
            throw new ValueException("Level cannot be lower than 1");
        } else if (maxLevel > 0) {
            nestedSteps = createRecursivelyNestedStepsJson(scenario.getSteps(), 1, maxLevel);
        }

        JSONArray actorsJsonArray = new JSONArray();
        actorsJsonArray.addAll(scenario.getActors());

        JSONObject json = new JSONObject()
                .appendField("title", scenario.getTitle())
                .appendField("systemActor", scenario.getSystemActor())
                .appendField("actors", actorsJsonArray)
                .appendField("steps", nestedSteps);

        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(json);
    }

    /**
     * @param steps        the list of steps on current level
     * @param currentLevel current depth level
     * @param maxLevel     target depth level
     * @return json array of steps for chosen level
     */
    private JSONArray createRecursivelyNestedStepsJson(List<Step> steps, Integer currentLevel, Integer maxLevel) {
        JSONArray currentStepsArray = new JSONArray();
        steps.forEach(step ->
                currentStepsArray.add(new JSONObject()
                        .appendField("content", step.getContent())
                        .appendField("subSteps", currentLevel >= maxLevel ?
                                new JSONArray() :
                                createRecursivelyNestedStepsJson(step.getSubSteps(), currentLevel + 1, maxLevel)))
        );
        return currentStepsArray;
    }
}
