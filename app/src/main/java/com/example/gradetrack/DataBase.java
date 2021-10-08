package com.example.gradetrack;

import java.util.ArrayList;
import java.util.Arrays;

public class DataBase {

    private static DataBase enrolledCourses;

    private static ArrayList<Course> enrolled;

    public DataBase(){
        if(enrolled == null){
            enrolled = new ArrayList<Course>();
            initData();
        }
    }

    public static ArrayList<Course> getEnrolled() {
        return enrolled;
    }

    public static synchronized  DataBase getInstance(){
        if(enrolledCourses != null){
            return enrolledCourses;
        }else{
            enrolledCourses = new DataBase();
            return enrolledCourses;
        }
    }

    public void initData(){
        //TODO make user add courses to the database form the beginning.
        //enrolled.add(new Course("Hello World"));
    }
    public static Course getCourse(String courseName) {
        int i = 0;
        while (!enrolled.get(i).getName().equals(courseName) && i + 1 <= enrolled.size()) {
            i++;
        }

        if (enrolled.get(i).getName().equals(courseName)) {
            return enrolled.get(i);
        } else {
            return null;
        }
    }

    /**
     * This method creates a new component and adds it to the specified course in the data base.
     * @param courseName this is the specified course
     * @param componentName This is the given component name (by the user)
     * @param componentScores This is a score array that will signify individual scores in the sub components
     * @param weight This is the TOTAL weight of a component (not individual subcomponents)
     * @return returns true for coding purposes.
     */
    public boolean createNewComponent(String courseName, String componentName, ArrayList<Integer> componentScores, double weight){
        Course current = getCourse(courseName);
        Component newComponent = new Component(componentName,componentScores,weight);
        current.getAssessments().add(newComponent); // check other method for adding components from the Course class.
        return true;
    }

    public ArrayList<Integer> getComponentScores(String courseName, String componentName){
        Course current = getCourse(courseName);
        ArrayList<Component> currComponents =  current.getAssessments();

    //Search for matching component name

        for(int i = 0; i < currComponents.size(); i++){
            if(currComponents.get(i).getName().equals(componentName)){
                return currComponents.get(i).getComponent(); // getComponent returns an array list of all the scores in the component. Not the object itself.
            }
        }
        return null; // meaning component does not exist.
    }

    public Component getComponent(String courseName, String componentName){
        Course current = getCourse(courseName);
        ArrayList<Component> currComponents =  current.getAssessments();

        //Search for matching component name

        for(int i = 0; i < currComponents.size(); i++){
            if(currComponents.get(i).getName().equals(componentName)){
                return currComponents.get(i); //  returns  the component
            }
        }
        return null; // meaning component does not exist.
    }

    public double getOverallAverage(){

        if(enrolled.size() == 0)
            return -1.0;

        double sum = 0;
        int size = 0;

        for(int i = 0; i < enrolled.size(); i++){
            sum += enrolled.get(i).getCurrentAverage(); // add the sums

            if(enrolled.get(i).getCurrentAverage() != -1.0){
                    size++;
            }
        }

        return sum/size;
    }

    public Course getMin(){
        if(enrolled.size() == 0)
        {
            return new Course("No Course Yet");
        }
        Course holder = enrolled.get(0);
        for(int i = 0; i < enrolled.size(); i++){
            if(holder.getCurrentAverage() > enrolled.get(i).getCurrentAverage()){
                holder = enrolled.get(i);
            }
        }

        return holder;
    }

    public Course getMax(){
        if(enrolled.size() == 0)
        {
            return new Course("No Course Yet");
        }
        Course holder = enrolled.get(0);
        for(int i = 0; i < enrolled.size(); i++){
            if(holder.getCurrentAverage() < enrolled.get(i).getCurrentAverage()){
                holder = enrolled.get(i);
            }
        }

        return holder;
    }
}
