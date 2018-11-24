package pl.poznan.put.cs.scenariochecker.transformations;

import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CountStepsWithoutActorsNameAtTheBeginningTest {

    private Scenario scenario;
    private CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy countStepsWithoutActorsNameAtTheBeginning;
    private Step oneStep;
    private Step stepWithNestedSteps;

    @Before
    public void setUp() {
        List<String> actors = Collections.singletonList("Bibliotekarz");
        this.scenario = new Scenario();
        this.scenario.setActors(actors);
        scenario.setSystemActor("System");
        this.countStepsWithoutActorsNameAtTheBeginning = new CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy();
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("Wrong step", Collections.singletonList(oneStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(firstNestepStep, oneStep));
    }

    @Test
    public void testCountStepsWithoutActors_givenZeroSteps_expectZero() {
        scenario.setSteps(Collections.emptyList());
        int actual = countStepsWithoutActorsNameAtTheBeginning.processScenario(scenario);
        assertEquals(0, actual);
    }

    @Test
    public void testCountStepsWithoutActors_givenOneSteps_expectOne() {
        scenario.setSteps(Collections.singletonList(oneStep));
        int actual = countStepsWithoutActorsNameAtTheBeginning.processScenario(scenario);
        assertEquals(1, actual);
    }

    @Test
    public void testCountStepsWithoutActors_givenNestedSteps_expectTwo() {
        scenario.setSteps(Collections.singletonList(stepWithNestedSteps));
        int actual = countStepsWithoutActorsNameAtTheBeginning.processScenario(scenario);
        assertEquals(2, actual);
    }
}
