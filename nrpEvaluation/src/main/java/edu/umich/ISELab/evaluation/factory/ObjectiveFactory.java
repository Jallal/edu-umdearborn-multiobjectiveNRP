package edu.umich.ISELab.evaluation.factory;

import edu.umich.ISELab.evaluation.Objective;
import edu.umich.ISELab.evaluation.qualityattributes.NumberOfNRPOptimization;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODEffectiveness;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODExtendibility;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODFlexibility;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODFunctionality;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODReusability;
import edu.umich.ISELab.evaluation.qualityattributes.QMOODUnderstandability;

public class ObjectiveFactory {

    public static Objective getObjective(String name) {

        if (name.equalsIgnoreCase("Effectiveness")) {
            return new QMOODEffectiveness();
        }
        if (name.equalsIgnoreCase("Extendibility")) {
            return new QMOODExtendibility();
        }
        if (name.equalsIgnoreCase("Flexibility")) {
            return new QMOODFlexibility();
        }
        if (name.equalsIgnoreCase("Functionality")) {
            return new QMOODFunctionality();
        }
        if (name.equalsIgnoreCase("Reusability")) {
            return new QMOODReusability();
        }
        if (name.equalsIgnoreCase("Understandability")) {
            return new QMOODUnderstandability();
        }
        if (name.equalsIgnoreCase("Number Of Refactorings")) {
            return new NumberOfNRPOptimization();
        }
        /*if (name.equalsIgnoreCase("Coupling")) {
            return new Coupling();
        }
        if (name.equalsIgnoreCase("Cohesion")) {
            return new Cohesion();
        }
        if (name.equalsIgnoreCase("Complexity")) {
            return new Complexity();
        }*/

        return null;
    }

}
