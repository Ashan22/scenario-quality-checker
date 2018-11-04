package pl.poznan.put.cs.scenariochecker.transformations;

import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class countStepsWithoutActorsNameAtTheBeginningTest {

    private Scenario scenario;
    private countStepsWithoutActorsNameAtTheBeginning countStepsWithoutActorsNameAtTheBeginning;
    private Step oneStep;
    private Step stepWithNestedSteps;

    @Before
    public void setUp() {
        List<String> actors = Arrays.asList("Bibliotekarz");
        this.scenario = new Scenario();
        this.scenario.setActors(actors);
        this.countStepsWithoutActorsNameAtTheBeginning = new countStepsWithoutActorsNameAtTheBeginning();
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("Bibliotekarz step", Arrays.asList(oneStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(firstNestepStep, oneStep));
    }

    @Test
    public void testCountStepsWithoutActors_givenZeroSteps_expectZero() {
        scenario.setSteps(Collections.emptyList());
        countStepsWithoutActorsNameAtTheBeginning.processScenario(scenario);
        assertEquals(0, scenario.getNumberOfStepsWithoutActors());
    }

    @Test
    public void testCountStepsWithoutActors_givenOneSteps_expectOne() {
        scenario.setSteps(Arrays.asList(oneStep));
        countStepsWithoutActorsNameAtTheBeginning.processScenario(scenario);
        assertEquals(1, scenario.getNumberOfStepsWithoutActors());
    }

    @Test
    public void testCountStepsWithoutActors_givenNestedSteps_expectThree() {
        scenario.setSteps(Arrays.asList(stepWithNestedSteps));
        countStepsWithoutActorsNameAtTheBeginning.processScenario(scenario);
        assertEquals(2, scenario.getNumberOfStepsWithoutActors());
    }
}
