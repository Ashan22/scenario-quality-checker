package pl.poznan.put.cs.scenariochecker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsWithoutActors;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.service.ScenarioCheckerService;

@RestController
public class ScenarioController {

    private final ScenarioCheckerService scenarioCheckerService;
    private ScenarioStrategy scenarioStrategy;
    private Scenario scenario;

    @Autowired
    public ScenarioController(ScenarioCheckerService scenarioCheckerService) {
        this.scenarioCheckerService = scenarioCheckerService;
    }

    @PostMapping("/check")
    @ResponseBody
    public String check(@RequestBody Scenario scenario) {
        this.scenario = scenarioCheckerService.transform(scenario);
        return "scenario transformed";
    }

    @GetMapping("/count")
    public String count() {
        this.scenarioStrategy = new CountStepsScenarioStrategy();
        this.scenarioStrategy.processScenario(this.scenario);
        return String.valueOf(this.scenario.getNumberOfSteps());
    }

    @GetMapping("/steps/withoutActor")
    public String countStepsWithoutActor(){
        this.scenarioStrategy = new CountStepsWithoutActors();
        this.scenarioStrategy.processScenario(this.scenario);
        return String.valueOf(this.scenario.getNumberOfStepsWithoutActors());
    }
}