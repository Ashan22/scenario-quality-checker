package pl.poznan.put.cs.scenariochecker.service;

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
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;


@RunWith(SpringRunner.class)
public class ScenarioServiceTests {
    private Scenario scenario;
    private Step noActorStep;
    private Step actorStep;
    private Step nestedStep;

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

    @Test
    public void testScenarioService_givenOneStep() {
        List<Step> steps = new ArrayList<>();
        noActorStep.setSubSteps(new ArrayList<>());
        steps.add(noActorStep);
        String resultString = scenarioService.numberTheStepsOfTheScenario(steps, "");
        assertEquals("1. Sample step\n", resultString);
    }

    @Test
    public void testScenarioService_givenSubStep() {
        List<Step> steps = new ArrayList<>();
        noActorStep.setSubSteps(new ArrayList<>());
        actorStep.setSubSteps(new ArrayList<>());
        steps.add(noActorStep);
        nestedStep.setSubSteps(new ArrayList<>(Collections.singletonList(actorStep)));
        steps.add(nestedStep);
        String resultString = scenarioService.numberTheStepsOfTheScenario(steps, "");
        assertEquals("1. Sample step\n2. IF nested step\n\t2.1. Actor step\n", resultString);
    }

    @Test
    public void testScenarioService_givenTwoStepAndSubStep() {
        List<Step> steps = new ArrayList<>();
        noActorStep.setSubSteps(new ArrayList<>());
        actorStep.setSubSteps(new ArrayList<>());
        steps.add(noActorStep);
        nestedStep.setSubSteps(new ArrayList<>(Collections.singletonList(actorStep)));
        steps.add(nestedStep);
        steps.add(noActorStep);
        String resultString = scenarioService.numberTheStepsOfTheScenario(steps, "");
        assertEquals("1. Sample step\n2. IF nested step\n\t2.1. Actor step\n3. Sample step\n", resultString);
    }

}
