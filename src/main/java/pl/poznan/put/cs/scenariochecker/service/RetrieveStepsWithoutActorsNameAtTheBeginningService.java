package pl.poznan.put.cs.scenariochecker.service;

import org.springframework.stereotype.Service;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.List;

@Service
public class RetrieveStepsWithoutActorsNameAtTheBeginningService {

    /**
     * @param steps     list contains every step from scenario
     * @param allActors list contains actors too keep
     * @return list of steps only with actors
     */
    public List<Step> retrieveStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> allActors) {
        List<Step> resultList = new ArrayList<>();
        for (Step s : steps) {
            if (ScenarioHelper.notBeginsWithActorsName(s, allActors) && !ScenarioHelper.isSpecialStep(s)) {
                resultList.add(s);
            } else if (ScenarioHelper.isSpecialStep(s)) {
                resultList.addAll(retrieveStepsWithoutActorsNameAtTheBeginning(s.getSubSteps(), allActors));
            }
        }
        return resultList;
    }

    public String numberTheStepsOfTheScenario(List<Step> steps, String prefix) {
        StringBuilder numeratedScenario = new StringBuilder();
        StringBuilder subprefix = new StringBuilder("\t" + prefix);
        int currentNumber = 1;
        for (Step s : steps) {
            numeratedScenario.append(prefix + currentNumber + ". ");
            numeratedScenario.append(s.getContent() + "\n");
            if (s.getSubSteps().size() != 0) {
                numeratedScenario.append(numberTheStepsOfTheScenario(s.getSubSteps(), subprefix.append(currentNumber + ".").toString()));
            }
            currentNumber += 1;
        }
        return numeratedScenario.toString();
    }
}
