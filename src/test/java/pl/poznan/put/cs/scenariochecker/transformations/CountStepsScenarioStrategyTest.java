package pl.poznan.put.cs.scenariochecker.transformations;

import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;

public class CountStepsScenarioStrategyTest {

    private Scenario scenario;
    private CountStepsScenarioStrategy countStepsScenarioStrategy;
    private Step oneStep;
    private Step stepWithNestedSteps;

    @Before
    public void setUp() {
        this.scenario = new Scenario();
        this.countStepsScenarioStrategy = new CountStepsScenarioStrategy();
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("IF step", Arrays.asList(oneStep));
        Step secondNestepStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestepStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestepStep, oneStep));
    }

    @Test
    public void testCountStepsScenarioStrategy_givenZeroSteps_expectZero() {
        scenario.setSteps(Collections.emptyList());
        countStepsScenarioStrategy.processScenario(scenario);
        assertEquals(0, scenario.getNumberOfSteps());//Arrays.asList(oneStep)
    }

    @Test
    public void testCountStepsScenarioStrategy_givenOneSteps_expectOne() {
        scenario.setSteps(Arrays.asList(oneStep));
        countStepsScenarioStrategy.processScenario(scenario);
        assertEquals(1, scenario.getNumberOfSteps());
    }

    @Test
    public void testCountStepsScenarioStrategy_givenNestedSteps_expectThree() {
        scenario.setSteps(Arrays.asList(stepWithNestedSteps));
        countStepsScenarioStrategy.processScenario(scenario);
        assertEquals(6, scenario.getNumberOfSteps());
    }
}