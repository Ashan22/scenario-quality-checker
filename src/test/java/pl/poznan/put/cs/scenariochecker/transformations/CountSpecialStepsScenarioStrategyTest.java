package pl.poznan.put.cs.scenariochecker.transformations;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountSpecialStepsScenarioStrategyTest {

    @Mock
    private CountSpecialStepsScenarioStrategy countSpecialStepsScenarioStrategy;

    private Scenario scenario;
    private Step oneStep;
    private Step stepWithNestedSteps;
    private Step stepWithSixSpecialSteps;

    @Before
    public void setUp() throws Exception {
        this.scenario = new Scenario();
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("IF step", Collections.singletonList(oneStep));
        Step secondNestepStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestepStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestepStep, oneStep));
        stepWithSixSpecialSteps = new Step("IF step",Arrays.asList(secondNestepStep,oneStep,stepWithNestedSteps));

        when(countSpecialStepsScenarioStrategy.processScenario(any(Scenario.class))).thenCallRealMethod();
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenZeroSteps_expectZero() {
        scenario.setSteps(Collections.emptyList());
        int actual = countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(0, actual);
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenOneSteps_expectZero() {
        scenario.setSteps(Collections.singletonList(oneStep));
        int actual = countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(0, actual);
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenNestedSteps_expectThree() {
        scenario.setSteps(Collections.singletonList(stepWithNestedSteps));
        int actual = countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(3, actual);
    }

    @Test
    public void testCountSpecialStepsScenarioStrategy_givenNestedSteps_expectSix() {
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        int actual = countSpecialStepsScenarioStrategy.processScenario(scenario);
        assertEquals(6, actual);
    }

}