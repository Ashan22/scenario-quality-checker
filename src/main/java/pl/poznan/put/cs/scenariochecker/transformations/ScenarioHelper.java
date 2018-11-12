package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;

public class ScenarioHelper {

    private ScenarioHelper() {
    }

    /**
     * @param step
     * @return true when step is special step
     */
    public static boolean isSpecialStep(Step step) {
        return step.getContent().startsWith("IF") || step.getContent().startsWith("FOR EACH");
    }

    /**
     * @param step, list of actors
     * @return true if given step begins with actor's name
     */
    public static boolean beginsWithActorsName(Step step, List<String> actors) {
        return actors.stream().anyMatch(a -> step.getContent().startsWith(a));
    }
}