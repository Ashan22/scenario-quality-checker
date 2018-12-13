package pl.poznan.put.cs.scenariochecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.transformations.*;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.service.ScenarioService;
import pl.poznan.put.cs.scenariochecker.transformations.CountSpecialStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class ScenarioController {

    private ScenarioStrategy scenarioStrategy;

    private final ScenarioService scenarioService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

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
        return scenarioService.retrieveStepsWithoutActorsNameAtTheBeginning(scenario.getSteps(), allActors);
    }

    @PostMapping("/special-steps")
    public String countSpecialSteps(@RequestBody Scenario scenario) {
        log.info("post special-steps");
        this.scenarioStrategy = new CountSpecialStepsScenarioStrategy();
        int specialSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(specialSteps);
    }

    @PostMapping("/levels/{level}")
    ResponseEntity<String> returnSubScenarios(@RequestBody Scenario scenario, @PathVariable String level) {
        log.info("post levels");
        return ResponseEntity.ok()
                .body(scenarioService.getSubscenariosToDepthLevel(scenario,Integer.valueOf(level)));
    }
}