package pl.poznan.put.cs.scenariochecker.service;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import lombok.extern.slf4j.Slf4j;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GetOneActorStepsService {

    /**
     * @param scenario
     * @param actor
     * @return Pretty json array with steps that belong only for actor provided in functions argument
     */
    public String getListOfActorsSteps(Scenario scenario, String actor) {
        JsonArray actorsListJson = new JsonArray();
        List<Step> flatStepsList = new ArrayList<>();
        createFlatListOfSteps(scenario.getSteps(), flatStepsList);
        log.info("Created flat list of actor steps");
        flatStepsList.removeIf(step -> !step.getContent().startsWith(actor));
        flatStepsList.forEach(step -> actorsListJson.add(step.getContent()));
        log.info("Removed steps which did not belong to actor");
        return new GsonBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(actorsListJson);
    }

    /**
     * @param steps Every this step and substeps will be flatten
     * @param flatStepsList output list where steps will be saved
     */
    void createFlatListOfSteps(List<Step> steps, List<Step> flatStepsList) {
        for (Step step : steps) {
            if (ScenarioHelper.isSpecialStep(step)) {
                createFlatListOfSteps(step.getSubSteps(), flatStepsList);
            }
            flatStepsList.add(step);
        }
    }

}
