package pl.poznan.put.cs.scenariochecker.transformations;

import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class CountSpecialStepsScenarioStrategyTest {

    private Scenario scenario;
    private CountSpecialStepsScenarioStrategy countSpecialStepsScenarioStrategy;
    private Step oneStep;
    private Step stepWithNestedSteps;
    private Step stepWithSixSpecialSteps;

    @Before
    public void setUp() throws Exception {
        this.scenario = new Scenario();
        this.countSpecialStepsScenarioStrategy = new CountSpecialStepsScenarioStrategy();
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("IF step", Arrays.asList(oneStep));
        Step secondNestepStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestepStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestepStep, oneStep));
        stepWithSixSpecialSteps = new Step("IF step",Arrays.asList(secondNestepStep,oneStep,stepWithNestedSteps));
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenZeroSteps_expectZero() {
        scenario.setSteps(Collections.emptyList());
        countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(0, scenario.getNumberOfSpecialSteps());//Arrays.asList(oneStep)
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenOneSteps_expectZero() {
        scenario.setSteps(Arrays.asList(oneStep));
        countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(0, scenario.getNumberOfSpecialSteps());//Arrays.asList(oneStep)
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenNestedSteps_expectThree() {
        scenario.setSteps(Arrays.asList(stepWithNestedSteps));
        countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(3, scenario.getNumberOfSpecialSteps());
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenNestedSteps_expectSix() {
        scenario.setSteps(Arrays.asList(stepWithSixSpecialSteps));
        countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(6, scenario.getNumberOfSpecialSteps());
    }

}