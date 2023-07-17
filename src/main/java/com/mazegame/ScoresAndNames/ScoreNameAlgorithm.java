package com.mazegame.ScoresAndNames;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ScoreNameAlgorithm {
    private StringProperty name;
    private Integer score;
    private  String algorithmType;

    public ScoreNameAlgorithm(String name,Integer score,String algorithmType){
        this.name = new SimpleStringProperty(name);
        this.score = score;
        this.algorithmType = algorithmType;
    }
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }
    public String getName() {
        return name.get();
    }

    public Integer getScore() {
        return score;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    @Override
    public String toString(){
        return "< Name: " + name +", Algorithm: " + algorithmType + ", Score: " + String.valueOf(score)+">";
    }

}
