package pl.poznan.put.cs.scenariochecker.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.transformations.*;

@RestController
public class ScenarioController {

    private ScenarioStrategy scenarioStrategy;

    @PostMapping("/count")
    public String count(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountStepsScenarioStrategy();
        int numberOfSteps = (int) this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(numberOfSteps);
    }

    @PostMapping("/steps-without-actor")
    public String countStepsWithoutActor(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy();
        int stepsWithoutActorsNameAtTheBeginning = (int) this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(stepsWithoutActorsNameAtTheBeginning);
    }


    @PostMapping("/special-steps")
    public String countSpecialSteps(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new CountSpecialStepsScenarioStrategy();
        int specialSteps = (int) this.scenarioStrategy.processScenario(scenario);
        return String.valueOf(specialSteps);
    }

    @PostMapping("/scenarios/levels")
    public String returnSubScenarios(@RequestBody Scenario scenario) {
        this.scenarioStrategy = new ReturnSubScenariosStrategy();
        return (String) this.scenarioStrategy.processScenario(scenario);
    }
}