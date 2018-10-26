package pl.poznan.put.cs.scenariochecker.model;

import lombok.Data;

import java.util.List;

@Data
public class Scenario {
    private String title;
    private String systemActor;
    private List<Step> steps;
    private List<String> actors;

    private int numberOfSteps;

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getActors() {
        return actors;
    }

    public String getSystemActor() {
        return systemActor;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
