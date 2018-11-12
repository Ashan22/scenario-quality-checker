package pl.poznan.put.cs.scenariochecker.model;

import lombok.Data;

import java.util.List;

@Data
public class Scenario {
    private String title;
    private String systemActor;
    private Integer level;
    private List<Step> steps;
    private List<String> actors;
}