package pl.poznan.put.cs.scenariochecker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.transformations.CountSpecialStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioStrategy;

@RestController
public class ScenarioController {

    private ScenarioStrategy scenarioStrategy;

    @PostMapping("/count")
    public String count(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountStepsScenarioStrategy();
        int numberOfSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(numberOfSteps);
    }

    @PostMapping("/steps-without-actor")
    public String countStepsWithoutActor(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy();
        int stepsWithoutActorsNameAtTheBeginning = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(stepsWithoutActorsNameAtTheBeginning);
    }


    @PostMapping("/special-steps")
    public String countSpecialSteps(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountSpecialStepsScenarioStrategy();
        int specialSteps = this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(specialSteps);
    }
}