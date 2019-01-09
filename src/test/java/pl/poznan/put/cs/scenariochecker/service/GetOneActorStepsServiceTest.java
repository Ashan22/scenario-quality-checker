package pl.poznan.put.cs.scenariochecker.service;

import org.junit.Before;
import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Scenario;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.junit.Assert.*;

public class GetOneActorStepsServiceTest {

    private Scenario scenario;
    private Step oneStep;
    private Step secondNestedStep;
    private GetOneActorStepsService scenarioService;
    private List<Step> flatList;
    private List<Step> expectedFlatList;

    @Before
    public void setUp() {
        scenario = new Scenario();
        scenarioService = new GetOneActorStepsService();
        oneStep = new Step("oneStep", Collections.emptyList());
        Step firstNestedStep = new Step("IF step", Collections.singletonList(oneStep));
        secondNestedStep = new Step("FOR EACH step", Arrays.asList(oneStep, firstNestedStep));

        expectedFlatList=new ArrayList<>(Arrays.asList(oneStep,oneStep,firstNestedStep,secondNestedStep));
        flatList = new ArrayList<>();
    }

    @Test
    public void createFlatListOfSteps_whenEmptyStepList_expectedEmptyList() {
        scenario.setSteps(EMPTY_LIST);
        scenarioService.createFlatListOfSteps(scenario.getSteps(), flatList);
        assertEquals(EMPTY_LIST,flatList);
    }

    @Test
    public void createFlatListOfSteps_whenListHasOneStep_expectedListWithOneElement() {
        scenario.setSteps(Collections.singletonList(oneStep));
        scenarioService.createFlatListOfSteps(scenario.getSteps(), flatList);
        assertEquals(Collections.singletonList(oneStep),flatList);
    }

    @Test
    public void createFlatListOfSteps_whenListHasNestedSteps_expectedFlatList() {
        scenario.setSteps(Collections.singletonList(secondNestedStep));
        scenarioService.createFlatListOfSteps(scenario.getSteps(), flatList);
        assertEquals(expectedFlatList,flatList);
    }
}