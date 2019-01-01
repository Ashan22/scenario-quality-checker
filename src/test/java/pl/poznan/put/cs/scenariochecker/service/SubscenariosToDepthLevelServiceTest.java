package pl.poznan.put.cs.scenariochecker.service;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static pl.poznan.put.cs.scenariochecker.service.GetSubScenariosOutputs.*;

public class SubscenariosToDepthLevelServiceTest {

    private Scenario scenario;
    private Step stepWithSixSpecialSteps;
    private SubscenariosToDepthLevelService scenarioService;

    @Before
    public void setUp() {
        scenarioService = new SubscenariosToDepthLevelService();

        scenario = new Scenario();
        scenario.setActors(new ArrayList<>(Collections.singletonList("Actor")));

        Step noActorStep = new Step();
        noActorStep.setContent("Sample step");

        Step actorStep = new Step();
        actorStep.setContent("Actor step");

        Step nestedStep = new Step();
        nestedStep.setContent("IF nested step");

        scenario.setTitle("Title");
        scenario.setSystemActor("System Actor");
        Step oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestedStep = new Step("IF step", Collections.singletonList(oneStep));
        Step secondNestedStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestedStep));
        Step stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestedStep, oneStep));
        stepWithSixSpecialSteps = new Step("IF step", Arrays.asList(secondNestedStep, oneStep, stepWithNestedSteps));
    }

    @Test(expected = ValueException.class)
    public void processScenario_givenNegativeLevelNumber_expectException() {
        scenarioService.getSubscenariosToDepthLevel(scenario, -1);
    }

    @Test()
    public void processScenario_givenZeroLevelNumber_whenLevelIsEqualZero() {
        assertEquals(EXPECTED_OUTPUT_LEVEL_ZERO, scenarioService.getSubscenariosToDepthLevel(scenario, 0));
    }

    @Test
    public void processScenario_whenLevelIsEqualOne() {
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_ONE, scenarioService.getSubscenariosToDepthLevel(scenario, 1));
    }


    @Test
    public void processScenario_whenLevelIsEqualTwo() {
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_TWO, scenarioService.getSubscenariosToDepthLevel(scenario, 2));
    }

    @Test
    public void processScenario_whenLevelIsEqualThree() {
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_THREE, scenarioService.getSubscenariosToDepthLevel(scenario, 3));
    }

    @Test
    public void processScenario_whenLevelIsEqualOneHundred() {
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_ONE_HUNDRED, scenarioService.getSubscenariosToDepthLevel(scenario, 100));
    }

}