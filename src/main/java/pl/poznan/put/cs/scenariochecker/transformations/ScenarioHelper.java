package pl.poznan.put.cs.scenariochecker.transformations;

import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.List;

/** Scenario helper class
 * @author Kamil Pluciński
 * @author Artur Mierzwa
 * @author Szymon Kukla
 */
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
    public static boolean notBeginsWithActorsName(Step step, List<String> actors) {
        return actors.stream().noneMatch(a -> step.getContent().startsWith(a));
    }
}