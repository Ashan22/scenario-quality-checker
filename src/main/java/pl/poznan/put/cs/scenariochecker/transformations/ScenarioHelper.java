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
     * @return true when step is step without Actor's
     */
    public static boolean isNoActorStep(Step step, List<String> actors) {
        for (String actor : actors) {
            if (step.getContent().contains(actor)) return false;
        }
        return true;
    }
}