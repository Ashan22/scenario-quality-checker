package pl.poznan.put.cs.scenariochecker.service;

import org.springframework.stereotype.Service;
import pl.poznan.put.cs.scenariochecker.model.Scenario;

@Service
public class ScenarioCheckerService {

    public Scenario transform(Scenario scenario){
        System.out.println(scenario);
        return scenario;
    }
}
