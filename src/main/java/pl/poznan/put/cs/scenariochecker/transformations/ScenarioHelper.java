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
}
