package pl.poznan.put.cs.scenariochecker.service;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
public class ScenarioServiceTests {

    private static final String EXPECTED_OUTPUT_LEVEL_ZERO = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[]}";
    private static final String EXPECTED_OUTPUT_LEVEL_ONE = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[],\"content\":\"IF step\"}]}";
    private static final String EXPECTED_OUTPUT_LEVEL_TWO = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[],\"content\":\"IF step\"}],\"content\":\"IF step\"}]}";
    private static final String EXPECTED_OUTPUT_LEVEL_THREE = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[],\"content\":\"IF step\"}],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"IF step\"}]}";
    private static final String EXPECTED_OUTPUT_LEVEL_ONEHUNDRER = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"IF step\"}]}";

    private Scenario scenario;
    private Step noActorStep;
    private Step actorStep;
    private Step nestedStep;
    private Step stepWithSixSpecialSteps;

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public ScenarioService scenarioService() {
            return new ScenarioService();
        }
    }

    @Autowired
    private ScenarioService scenarioService;

    @Before
    public void setUp() {
        scenario = new Scenario();
        scenario.setActors(new ArrayList<>(Collections.singletonList("Actor")));

        noActorStep = new Step();
        noActorStep.setContent("Sample step");

        actorStep = new Step();
        actorStep.setContent("Actor step");

        nestedStep = new Step();
        nestedStep.setContent("IF nested step");

        scenario.setTitle("Title");
        scenario.setSystemActor("System Actor");
        Step oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("IF step", Collections.singletonList(oneStep));
        Step secondNestepStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestepStep));
        Step stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestepStep, oneStep));
        stepWithSixSpecialSteps = new Step("IF step",Arrays.asList(secondNestepStep, oneStep, stepWithNestedSteps));
    }

    @Test
    public void testScenarioService_givenOneStep_expectOne() {
        List<Step> steps = new ArrayList<>();
        steps.add(noActorStep);
        int resultListSize = scenarioService.retrieveStepsWithoutActorsNameAtTheBeginning(steps, scenario.getActors()).size();
        assertEquals(1, resultListSize);
    }

    @Test
    public void testScenarioService_givenZeroSteps_expectZero() {
        List<Step> steps = new ArrayList<>();
        steps.add(actorStep);
        int resultListSize = scenarioService.retrieveStepsWithoutActorsNameAtTheBeginning(steps, scenario.getActors()).size();
        assertEquals(0, resultListSize);
    }

    @Test
    public void testScenarioService_givenActorStepWithNestedNoActorStep_expectOne() {
        List<Step> steps = new ArrayList<>();
        nestedStep.setSubSteps(new ArrayList<>(Collections.singletonList(noActorStep)));
        steps.add(nestedStep);
        int resultListSize = scenarioService.retrieveStepsWithoutActorsNameAtTheBeginning(steps, scenario.getActors()).size();
        assertEquals(1, resultListSize);
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
    public void processScenario_whenLevelIsEqualOne(){
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_ONE, scenarioService.getSubscenariosToDepthLevel(scenario,1));
    }


    @Test
    public void processScenario_whenLevelIsEqualTwo(){
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_TWO, scenarioService.getSubscenariosToDepthLevel(scenario,2));
    }

    @Test
    public void processScenario_whenLevelIsEqualThree(){
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_THREE, scenarioService.getSubscenariosToDepthLevel(scenario, 3));
    }

    @Test
    public void processScenario_whenLevelIsEqualOneHundrer(){
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_ONEHUNDRER, scenarioService.getSubscenariosToDepthLevel(scenario,100));
    }
}
