package pl.poznan.put.cs.scenariochecker.transformations;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ReturnSubScenariosStrategyTest {

    private static final String EXPECTED_OUTPUT_LEVEL_ZERO = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[]}";
    private static final String EXPECTED_OUTPUT_LEVEL_ONE = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[],\"content\":\"IF step\"}]}";
    private static final String EXPECTED_OUTPUT_LEVEL_TWO = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[],\"content\":\"IF step\"}],\"content\":\"IF step\"}]}";
    private static final String EXPECTED_OUTPUT_LEVEL_THREE = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[],\"content\":\"IF step\"}],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"IF step\"}]}";
    private static final String EXPECTED_OUTPUT_LEVEL_ONEHUNDRER = "{\"actors\":[\"Actor\"],\"systemActor\":\"System Actor\",\"title\":\"Title\",\"steps\":[{\"subSteps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"},{\"subSteps\":[{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"FOR EACH step\"},{\"subSteps\":[],\"content\":\"oneStep\"}],\"content\":\"IF step\"}],\"content\":\"IF step\"}]}";

    private Scenario scenario;
    private ReturnSubScenariosStrategy returnSubScenariosStrategy;
    private Step stepWithSixSpecialSteps;


    @Before
    public void setUp() {
        this.scenario = new Scenario();
        scenario.setActors(new ArrayList<>(Collections.singleton("Actor")));
        scenario.setTitle("Title");
        scenario.setSystemActor("System Actor");
        this.returnSubScenariosStrategy = new ReturnSubScenariosStrategy();
        Step oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestepStep = new Step("IF step", Collections.singletonList(oneStep));
        Step secondNestepStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestepStep));
        Step stepWithNestedSteps = new Step("IF step", Arrays.asList(secondNestepStep, oneStep));
        stepWithSixSpecialSteps = new Step("IF step",Arrays.asList(secondNestepStep, oneStep, stepWithNestedSteps));
    }

    @Test(expected = ValueException.class)
    public void processScenario_givenNegativeLevelNumber_expectException() {
        scenario.setLevel(-1);
        returnSubScenariosStrategy.processScenario(scenario);
    }

    @Test()
    public void processScenario_givenZeroLevelNumber_whenLevelIsEqualZero() {
        scenario.setLevel(0);
        assertEquals(EXPECTED_OUTPUT_LEVEL_ZERO, returnSubScenariosStrategy.processScenario(scenario));
    }

    @Test
    public void processScenario_whenLevelIsEqualOne(){
        scenario.setLevel(1);
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_ONE, returnSubScenariosStrategy.processScenario(scenario));
    }


    @Test
    public void processScenario_whenLevelIsEqualTwo(){
        scenario.setLevel(2);
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_TWO, returnSubScenariosStrategy.processScenario(scenario));
    }

    @Test
    public void processScenario_whenLevelIsEqualThree(){
        scenario.setLevel(3);
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_THREE, returnSubScenariosStrategy.processScenario(scenario));
    }

    @Test
    public void processScenario_whenLevelIsEqualOneHundrer(){
        scenario.setLevel(100);
        scenario.setSteps(Collections.singletonList(stepWithSixSpecialSteps));
        assertEquals(EXPECTED_OUTPUT_LEVEL_ONEHUNDRER, returnSubScenariosStrategy.processScenario(scenario));
    }
}