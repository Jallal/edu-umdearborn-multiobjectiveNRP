package thiagodnf.doupr.evaluation.factory;

import thiagodnf.doupr.evaluation.Objective;
import thiagodnf.doupr.evaluation.qualityattributes.Cohesion;
import thiagodnf.doupr.evaluation.qualityattributes.Complexity;
import thiagodnf.doupr.evaluation.qualityattributes.Coupling;
import thiagodnf.doupr.evaluation.qualityattributes.NumberOfNRPOptimization;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODEffectiveness;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODExtendibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFlexibility;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODFunctionality;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODReusability;
import thiagodnf.doupr.evaluation.qualityattributes.QMOODUnderstandability;

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
        if (name.equalsIgnoreCase("Coupling")) {
            return new Coupling();
        }
        if (name.equalsIgnoreCase("Cohesion")) {
            return new Cohesion();
        }
        if (name.equalsIgnoreCase("Complexity")) {
            return new Complexity();
        }

        return null;
    }

}
