package com.mazegame.ScoresAndNames;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * The type Score name algorithm.
 */
public class ScoreNameAlgorithm {
    private StringProperty name;
    private Integer score;
    private  String algorithmType;

    /**
     * Instantiates a new Score name algorithm.
     *
     * @param name          the name
     * @param score         the score
     * @param algorithmType the algorithm type
     */
    public ScoreNameAlgorithm(String name,Integer score,String algorithmType){
        this.name = new SimpleStringProperty(name);
        this.score = score;
        this.algorithmType = algorithmType;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * Sets algorithm type.
     *
     * @param algorithmType the algorithm type
     */
    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name.get();
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Integer getScore() {
        return score;
    }

    /**
     * Gets algorithm type.
     *
     * @return the algorithm type
     */
    public String getAlgorithmType() {
        return algorithmType;
    }

    @Override
    public String toString(){
        return "< Name: " + name +", Algorithm: " + algorithmType + ", Score: " + String.valueOf(score)+">";
    }

}
