package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Step;

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
    public static boolean isActorStep(Step step) {
        return !step.getContent().startsWith("Bibliotekarz");
    }

}
