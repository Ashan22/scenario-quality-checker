package pl.poznan.put.cs.scenariochecker.model;

import lombok.Data;

import java.util.List;

@Data
public class Step {
    private String content;
    List<Step> subSteps;
}
