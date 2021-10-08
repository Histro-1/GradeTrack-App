package com.example.gradetrack;

import java.util.ArrayList;

/*
 * Every assessment has a weight and its scores
 * And since we have the weight and it's scores we can get the average
 */
public class Component {

    ArrayList<Integer> componentScores; //holds scores for the component
    double weight;



    String name;

    public Component(){

    }
    public Component(String name, ArrayList<Integer> component, double weight) {
        this.componentScores = component;
        this.weight = weight;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setWeight(double newWeight) {
        weight = newWeight;
    }

    public ArrayList<Integer> getComponentScoresArray() {
        return componentScores;
    }

    public void setComponentScores(ArrayList<Integer> componentScores) {
        this.componentScores = componentScores;
    }

    public void addToComponent(int score) {
        componentScores.add(score);
    }

    public void removeFromComponent(int index) {
        componentScores.remove(index);
    }

    public ArrayList<Integer> getComponent() {
        return componentScores;
    } // returns the array of scores for the component

    public int getComponentScores(int index) {
        return componentScores.get(index);
    }

    public double getWeight() {
        return weight;
    }

    public double getTotalWeight() {            // calculates the total weight for the component (total weight for Quiz 1, Quiz 2, ....)
        return weight * componentScores.size();
    }


    public double getComponentAverage() {
        double componentAvg = 0;
        for (int i = 0; i < componentScores.size(); i++) {
            componentAvg += componentScores.get(i) * weight;
        }

        return componentAvg;
    }

    /*
     * Print out the component and its score
     */
    public String toString() {
        String results = "";

        if (componentScores.size() == 0) {
            return name + ":\n";
        }

        for (int i = 0; i < componentScores.size(); i++) {
            if (componentScores.size() <= 1)
                results += name + ": " + componentScores.get(i) + "\n";
            else
                results += name + (i + 1) + ": " + componentScores.get(i) + "\n";

        }

        return results;
    }
}
