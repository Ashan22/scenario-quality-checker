package pl.poznan.put.cs.scenariochecker.model;

import lombok.Data;

import java.util.List;

@Data
public class Step {
    private String content;
    List<Step> subSteps;

    public Step(String content, List<Step> subSteps) {
        this.content = content;
        this.subSteps = subSteps;
    }

    public String getContent() {
        return content;
    }

    public List<Step> getSubSteps() {
        return subSteps;
    }

}
