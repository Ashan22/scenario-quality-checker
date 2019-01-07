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
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CountStepsWithoutActorsNameAtTheBeginningTest {

    @Mock
    private CountStepsWithoutActorsNameAtTheBeginningScenarioStrategy countStepsWithoutActorsNameAtTheBeginning;

    private Scenario scenario;
    private Step oneStep;
    private Step stepWithNestedSteps;

    @Before
    public void setUp() {
        List<String> actors = Collections.singletonList("Bibliotekarz");
        this.scenario = new Scenario();
        this.scenario.setActors(actors);
        scenario.setSystemActor("System");
        this.oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestedStep = new Step("Wrong step", Collections.singletonList(oneStep));
        stepWithNestedSteps = new Step("IF step", Arrays.asList(firstNestedStep, oneStep));

        when(countStepsWithoutActorsNameAtTheBeginning.processScenario(any(Scenario.class))).thenCallRealMethod();
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
