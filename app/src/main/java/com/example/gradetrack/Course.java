package com.example.gradetrack;
import java.util.ArrayList;
import java.util.Scanner;

public class Course implements Comparable<Course> {
    /*
     * A typical course would have a Quiz(multiple), a midterm(multiple), and a final(multiple is possible)
     *
     * Each component would also have their own weighting
     */
    String courseName;
    private ArrayList<Component> assessments = new ArrayList<>();



    /*
     * Let the constructor initialize the quiz, midterms, finals, and their respective weightings
     */
    public Course(){
        courseName="";
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public Course(String courseName, ArrayList<Integer> quiz, ArrayList<Integer> midterm, ArrayList<Integer> finals, double quizWeight, double midtermWeight, double finalWeight) {
        this.courseName = courseName;

        assessments.add(new Component("Quiz", quiz, quizWeight / 100));
        assessments.add(new Component("Midterm", midterm, midtermWeight / 100));
        assessments.add(new Component("Finals", finals, finalWeight / 100));
    }

    public void setName(String name) {
        this.courseName = name;
    }

    public String getName() {
        return courseName;
    }

    public ArrayList<Component> getAssessments() {
        return assessments;
    }

    /*
     * Add a component to the course: Quiz, midterm, lab, finals, readings/participation
     */

    public boolean addComponent(String name, double weight) {

        boolean added = false;

        ArrayList<Integer> newComponentScores = new ArrayList<>();
        assessments.add(new Component(name, newComponentScores, weight));
        added = true;

        return added;

    }

    public boolean addComponent(Component component) {

        boolean added = false;
        assessments.add(component);
        added = true;

        return added;

    }
    /*
     * Remove a component from the course: Quiz, midterm, lab, finals, readings/participation
     */

    public boolean removeComponent(String name) {

        boolean removed = false;

        for (int i = 0; i < assessments.size(); i++) {
            if (name.equals(assessments.get(i).getName())) {
                assessments.remove(i);
                removed = true;
                break;
            }

        }

        return removed;

    }

    /*
     * Add new score to an assessments
     */

    public boolean addScoresTo(String name, int score) {
        boolean exists = false;

        for (int i = 0; i < assessments.size(); i++) {
            if (assessments.get(i).getName().equals(name)) {
                assessments.get(i).addToComponent(score);
                exists = true;
            }
        }

        return exists;
    }

    /*
     * if we have quiz 1, 2, 3
     * name is "quiz", and index is the quiz number we wish to remove
     *
     */
    public boolean removeScoresFrom(String name, int index) {
        boolean removed = false;
        boolean exists = false;
        ArrayList<Integer> ourComponent = new ArrayList<>(); // we want to remove a score from this component

        for (int i = 0; i < assessments.size(); i++) {
            if (assessments.get(i).getName().equals(name)) {
                ourComponent = assessments.get(i).getComponent();

                exists = true;
                break;
            }
        }
        if (!exists) {
            return false;
        }
        if (ourComponent.size() > index) {
            ourComponent.remove(index);
            removed = true;
        }

        return removed;
    }

    public boolean updateScoresOf(String name, int index, int newScore) {
        boolean updated = false;
        boolean exists = false;
        ArrayList<Integer> ourComponent = new ArrayList<>(); // we want to update a score from this component

        for (int i = 0; i < assessments.size(); i++) {
            if (assessments.get(i).getName().equals(name)) {
                ourComponent = assessments.get(i).getComponent();

                exists = true;
                break;
            }
        }
        if (!exists) {
            return false;
        }

        if (ourComponent.size() > index) {
            ourComponent.remove(index);
            ourComponent.add(index, newScore);
            updated = true;
        }

        return updated;

    }


    /*
     * get current average for the course
     */

    public double getCurrentAverage() {

        double average = 0;
        double totalWeighting = 0;
        for (int i = 0; i < assessments.size(); i++) {
            average += assessments.get(i).getComponentAverage();
            totalWeighting += assessments.get(i).getTotalWeight();
        }
        if(totalWeighting == 0)
            return -1.0;
        return (average / totalWeighting);
    }

    public String toString() {
        String results = courseName + "\n";

        for (int i = 0; i < assessments.size(); i++)
            results += assessments.get(i);

        results += "Course Average is currently :" + getCurrentAverage() + " %";
        return results;
    }

    @Override
    public int compareTo(Course other) {
       if(other.getCurrentAverage() > this.getCurrentAverage()){
           return 1;
       }
       else if(other.getCurrentAverage() < this.getCurrentAverage()){
           return -1;
       }
       else{
           return 0;
       }
    }
}



