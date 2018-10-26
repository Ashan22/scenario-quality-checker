package pl.poznan.put.cs.scenariochecker.service;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class ScenarioCheckerService {

    private final String[] transforms;

    public ScenarioCheckerService(String[] transforms){
        this.transforms = transforms;
    }

    public String transform(String text){
        // of course normally it would to something based on transforms
        return text.toUpperCase();
    }
}
