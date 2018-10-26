package pl.poznan.put.cs.scenariochecker.model;

import lombok.Data;

import java.util.List;

@Data
public class Scenario {
    private String title;
    private List<String> actors;
    private String systemActor;
    private List<Step> steps;
}
