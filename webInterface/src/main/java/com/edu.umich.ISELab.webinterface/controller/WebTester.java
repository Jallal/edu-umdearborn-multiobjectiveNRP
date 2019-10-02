package com.edu.umich.ISELab.webinterface.controller;

import edu.umich.ISELab.core.backlog.WorkItem;
import edu.umich.ISELab.core.grooming.Grooming;
import edu.umich.ISELab.execution.ExploreWebUI;
import edu.umich.ISELab.optimization.solution.GroomingSolution;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.variables.GroomingVariable;
import edu.umich.ISELab.optimization.variables.Variable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebTester {

    public static void main(String[] args) throws Exception {

        String[][] data = new String[9][4];

        //Our Taks
       /* data[0][0] = "Task 1";
        data[1][0] = "Task 2";
        data[2][0] = "Task 3";
        data[3][0] = "Task 4";
        data[4][0] = "Task 5";
        data[5][0] = "Task 6";
        data[6][0] = "Task 7";
        data[7][0] = "Task 8";
        data[8][0] = "Task 9";


        //People
        data[0][1] = "Jallal elhazzat";
        data[1][1] = "Ashley Snyder";
        data[2][1] = "Sri Padadda";
        data[3][1] = "Steve Snyder";
        data[4][1] = "John DOe";
        data[5][1] = "Ed Doe";
        data[6][1] = "Moe Ali";
        data[7][1] = "Stephan Moe";
        data[8][1] = "Steve Snyder";

        // X Axis timeline

        data[0][2] = String.valueOf(new Date(2019, 5, 1));
        data[1][2] = String.valueOf(new Date(2019, 5, 1));
        data[2][2] = String.valueOf(new Date(2019, 5, 1));
        data[3][2] = String.valueOf(new Date(2019, 5, 1));
        data[4][2] = String.valueOf(new Date(2019, 5, 1));
        data[5][2] = String.valueOf(new Date(2019, 5, 1));
        data[6][2] = String.valueOf(new Date(2019, 5, 1));
        data[7][2] = String.valueOf(new Date(2019, 5, 1));
        data[8][2] = String.valueOf(new Date(2019, 5, 1));


        // Y Axis timeline
        data[0][3] = String.valueOf(new Date(2019, 5, 5));
        data[1][3] = String.valueOf(new Date(2019, 5, 10));
        data[2][3] = String.valueOf(new Date(2019, 5, 12));
        data[3][3] = String.valueOf(new Date(2019, 5, 6));
        data[4][3] = String.valueOf(new Date(2019, 5, 12));
        data[5][3] = String.valueOf(new Date(2019, 5, 15));
        data[6][3] = String.valueOf(new Date(2019, 5, 15));
        data[7][3] = String.valueOf(new Date(2019, 5, 10));
        data[8][3] = String.valueOf(new Date(2019, 5, 10));*/


        ExploreWebUI webUI = new ExploreWebUI();
        List<Solution> solutionForWeb = webUI.getSolutionForWeb();

        try {
            Map<Integer, List<WorkItem>> solutionToValues = new HashMap<>();

            for (int i = 0; i < solutionForWeb.size(); i++) {
                GroomingSolution solution = (GroomingSolution) solutionForWeb.get(i);
                List<Variable> variablesList = solution.getVariables();
                GroomingVariable variable = (GroomingVariable) variablesList.get(0);
                List<Grooming> grooming = variable.getRefactorings();

                int k = 0;
                int d = 0;
                for (Grooming groum : grooming) {
                    for (WorkItem item : groum.getWorkItems()) {
                        if (d < 9) {
                            //if (k == 0) {
                            data[d][0] = item.getWorkItemID();
                            //} else if (k == 1) {
                            data[d][1] = item.getPerson().getName();

                            // } else if (k == 2) {
                            data[d][2] = String.valueOf(new Date(2019, 5, 1));

                            // } else if (k == 3) {
                            data[d][3] = String.valueOf(new Date(2019, 5, 10));

                            // }
                            d++;
                        }
                    }
                    d=0;
                }

            }
        } catch (Exception e) {
            System.out.println("*****************************************************************************");
            System.out.println(e);
            System.out.println("*****************************************************************************");

        }

        System.out.println("*****************************************************************************");
        System.out.println(data.toString());
        System.out.println("*****************************************************************************");


    }
}
