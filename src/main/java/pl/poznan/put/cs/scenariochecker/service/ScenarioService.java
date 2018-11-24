package pl.poznan.put.cs.scenariochecker.service;

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
public class ScenarioService {

    public List<Step> retrieveStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> allActors) {
        List<Step> resultList = new ArrayList<>();
        for (Step s : steps) {
            if (!ScenarioHelper.beginsWithActorsName(s, allActors) && !ScenarioHelper.isSpecialStep(s)) {
                resultList.add(s);
            } else if (ScenarioHelper.isSpecialStep(s)) {
                resultList.addAll(retrieveStepsWithoutActorsNameAtTheBeginning(s.getSubSteps(), allActors));
            }
        }
        return resultList;
    }

    /**
     * @param scenario
     * @return New scenario which contains steps only for chosen level
     */
    public String processScenario(Scenario scenario) {
        maxLevel = scenario.getLevel();
        JSONArray nestedSteps = new JSONArray();

        if (maxLevel < 0) {
            throw new ValueException("Level cannot be lower than 1");
        } else if (maxLevel > 0) {
            nestedSteps = createRecursivelyNestedStepsJson(scenario.getSteps(), 1);
        }

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
     * @param steps        the list of steps on current level
     * @param currentLevel
     * @return json array of steps for chosen level
     */
    private JSONArray createRecursivelyNestedStepsJson(List<Step> steps, int currentLevel) {
        JSONArray currentStepsArray = new JSONArray();
        steps.forEach(step ->
                currentStepsArray.add(new JSONObject()
                        .appendField("content", step.getContent())
                        .appendField("subSteps", currentLevel >= maxLevel ?
                                new JSONArray() :
                                createRecursivelyNestedStepsJson(step.getSubSteps(), currentLevel + 1)))
        );
        return currentStepsArray;
    }
}
