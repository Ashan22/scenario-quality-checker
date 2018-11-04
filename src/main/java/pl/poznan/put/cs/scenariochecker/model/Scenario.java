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
    private int numberOfStepsWithoutActors;
    private int numberOfSpecialSteps;
}