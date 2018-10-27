package pl.poznan.put.cs.scenariochecker.transformations;


import org.junit.Test;
import pl.poznan.put.cs.scenariochecker.model.Step;

import java.util.Collections;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ScenarioHelperTest {

    @Test
    public void testIsSpecialTest_whenStepWithIf_expectTrue() {
        Step step = new Step("IF test", Collections.emptyList());
        assertTrue(ScenarioHelper.isSpecialStep(step));
    }

    @Test
    public void testIsSpecialTest_whenStepWithFor_Each_expectTrue() {
        Step step = new Step("FOR EACH test", Collections.emptyList());
        assertTrue(ScenarioHelper.isSpecialStep(step));
    }

    @Test
    public void testIsSpecialTest_whenStepWithFOREACH_expectFalse() {
        Step step = new Step("FOREACH test", Collections.emptyList());
        assertFalse(ScenarioHelper.isSpecialStep(step));
    }

    @Test
    public void testIsSpecialTest_whenStepWithIfInBadPlace_expectFalse() {
        Step step = new Step("test IF", Collections.emptyList());
        assertFalse(ScenarioHelper.isSpecialStep(step));
    }

    @Test
    public void testIsSpecialTest_whenStepWithForEachInBadPlace_expectFalse() {
        Step step = new Step("test FOR EACH", Collections.emptyList());
        assertFalse(ScenarioHelper.isSpecialStep(step));
    }

}