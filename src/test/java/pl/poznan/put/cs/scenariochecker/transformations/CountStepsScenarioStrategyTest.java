package pl.poznan.put.cs.scenariochecker.transformations;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountStepsScenarioStrategyTest {

    @Mock
    private CountStepsScenarioStrategy countStepsScenarioStrategy;

    private Scenario scenario;
    private Step oneStep;
    private Step stepWithNestedSteps;

    @Before
    public void setUp() {
        this.scenario = new Scenario();
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("IF step", Collections.singletonList(oneStep));
        Step secondNestepStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestepStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestepStep, oneStep));

        when(countStepsScenarioStrategy.processScenario(any(Scenario.class))).thenCallRealMethod();
    }

    @Test
    public void testCountStepsScenarioStrategy_givenZeroSteps_expectZero() {
        scenario.setSteps(Collections.emptyList());
        int actual = countStepsScenarioStrategy.processScenario(scenario);
        assertEquals(0, actual);//Arrays.asList(oneStep)
    }

    @Test
    public void testCountStepsScenarioStrategy_givenOneSteps_expectOne() {
        scenario.setSteps(Collections.singletonList(oneStep));
        int actual = countStepsScenarioStrategy.processScenario(scenario);
        assertEquals(1, actual);
    }

    @Test
    public void testCountStepsScenarioStrategy_givenNestedSteps_expectThree() {
        scenario.setSteps(Collections.singletonList(stepWithNestedSteps));
        int actual = countStepsScenarioStrategy.processScenario(scenario);
        assertEquals(6, actual);
    }
}