package pl.poznan.put.cs.scenariochecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.service.GetOneActorStepsService;
import pl.poznan.put.cs.scenariochecker.service.RetrieveStepsWithoutActorsNameAtTheBeginningService;
import pl.poznan.put.cs.scenariochecker.service.SubscenariosToDepthLevelService;
import pl.poznan.put.cs.scenariochecker.transformations.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ScenarioController {

    private ScenarioStrategy scenarioStrategy;

    @PostMapping("/count")
    public String count(@RequestBody Scenario scenario) {
        log.info("post count");
        this.scenarioStrategy = new CountStepsScenarioStrategy();
        int numberOfSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(numberOfSteps);
    }

    @PostMapping("/count-steps-without-actor")
    public String countStepsWithoutActor(@RequestBody Scenario scenario) {
        log.info("post count-step-without-actor");
        this.scenarioStrategy = new CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy();
        int stepsWithoutActorsNameAtTheBeginning = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(stepsWithoutActorsNameAtTheBeginning);
    }

    @PostMapping("/steps-without-actor")
    public List<Step> retrieveStepsWithoutActor(@RequestBody Scenario scenario) {
        log.info("post steps without actor");
        List<String> allActors = new ArrayList<>(scenario.getActors());
        allActors.add(scenario.getSystemActor());
        RetrieveStepsWithoutActorsNameAtTheBeginningService scenarioService = new RetrieveStepsWithoutActorsNameAtTheBeginningService();
        return scenarioService.retrieveStepsWithoutActorsNameAtTheBeginning(scenario.getSteps(), allActors);
    }

    @PostMapping("/special-steps")
    public String countSpecialSteps(@RequestBody Scenario scenario) {
        log.info("post special-steps");
        this.scenarioStrategy = new CountSpecialStepsScenarioStrategy();
        int specialSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(specialSteps);
    }

    @PostMapping("/scenarios/numerated")
    public String numberTheSteps(@RequestBody Scenario scenario) {
        log.info("post numerated scenario");
        RetrieveStepsWithoutActorsNameAtTheBeginningService scenarioService = new RetrieveStepsWithoutActorsNameAtTheBeginningService();
        return scenarioService.numberTheStepsOfTheScenario(scenario.getSteps(), "");
    }

    @PostMapping("/levels/{level}")
    ResponseEntity<String> returnSubScenarios(@RequestBody Scenario scenario, @PathVariable String level) {
        log.info("post levels");
        SubscenariosToDepthLevelService scenarioService = new SubscenariosToDepthLevelService();
        return ResponseEntity.ok()
                .body(scenarioService.getSubscenariosToDepthLevel(scenario, Integer.valueOf(level)));
    }

    @PostMapping("/steps/{actor}")
    ResponseEntity<String> returnListOfActorsSteps(@RequestBody Scenario scenario, @PathVariable String actor) {
        log.info("post steps %s", actor);
        GetOneActorStepsService scenarioService = new GetOneActorStepsService();
        return ResponseEntity.ok()
                .body(scenarioService.getListOfActorsSteps(scenario, actor));
    }
}