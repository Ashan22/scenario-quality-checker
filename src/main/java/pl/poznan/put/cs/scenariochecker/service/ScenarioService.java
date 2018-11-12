package pl.poznan.put.cs.scenariochecker.service;

import org.springframework.stereotype.Service;
import pl.poznan.put.cs.scenariochecker.model.Step;
import pl.poznan.put.cs.scenariochecker.transformations.ScenarioHelper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScenarioService {

    public List<Step> retrieveStepsWithoutActorsNameAtTheBeginning(List<Step> steps, List<String> allActors){
        List<Step> resultList = new ArrayList<>();
        for (Step s : steps) {
            if (!ScenarioHelper.beginsWithActorsName(s, allActors) && !ScenarioHelper.isSpecialStep(s)) {
                resultList.add(s);
            } else if (ScenarioHelper.isSpecialStep(s)) {
               resultList.addAll(retrieveStepsWithoutActorsNameAtTheBeginning(s.getSubSteps(), allActors));
            }
        }
        return resultList;
    }


}
