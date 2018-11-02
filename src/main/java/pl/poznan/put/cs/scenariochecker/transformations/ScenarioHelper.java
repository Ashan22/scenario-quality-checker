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
     * @param step
     * @return true when step is step without Actor's
     */
    public static boolean isNoActorStep(Step step, List<String> actors) {
        return !actors.stream.anyMatch(actor->step.getContent().contains(actor));
    }
}