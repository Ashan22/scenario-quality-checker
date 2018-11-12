package pl.poznan.put.cs.scenariochecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.service.ScenarioService;
import pl.poznan.put.cs.scenariochecker.transformations.CountSpecialStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioStrategy;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ScenarioController {

    private ScenarioStrategy scenarioStrategy;

    private final ScenarioService scenarioService;

    @Autowired
    public ScenarioController(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @PostMapping("/count")
    public String count(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountStepsScenarioStrategy();
        int numberOfSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(numberOfSteps);
    }

    @PostMapping("/count-steps-without-actor")
    public String countStepsWithoutActor(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy();
        int stepsWithoutActorsNameAtTheBeginning = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(stepsWithoutActorsNameAtTheBeginning);
    }

    @PostMapping("/steps-without-actor")
    public List<Step> retrieveStepsWithoutActor(@RequestBody Scenario scenario) {
        List<String> allActors = new ArrayList<>(scenario.getActors());
        allActors.add(scenario.getSystemActor());
        return scenarioService.retrieveStepsWithoutActorsNameAtTheBeginning(scenario.getSteps(), allActors);
    }

    @PostMapping("/special-steps")
    public String countSpecialSteps(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountSpecialStepsScenarioStrategy();
        int specialSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(specialSteps);
    }
}