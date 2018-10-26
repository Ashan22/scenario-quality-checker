package pl.poznan.put.cs.scenariochecker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.service.ScenarioCheckerService;

@RestController
@RequestMapping("/check")
public class ScenarioCheckerController {

    private final ScenarioCheckerService scenarioCheckerService;

    @Autowired
    public ScenarioCheckerController(ScenarioCheckerService scenarioCheckerService) {
        this.scenarioCheckerService = scenarioCheckerService;
    }

    @PostMapping
    @ResponseBody
    public String check(@RequestBody Scenario scenario) {
        scenarioCheckerService.transform(scenario);
        return "scenario transformed";
    }

}


