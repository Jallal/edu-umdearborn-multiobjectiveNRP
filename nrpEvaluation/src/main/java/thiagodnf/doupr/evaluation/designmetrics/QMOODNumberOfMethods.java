package thiagodnf.doupr.evaluation.designmetrics;

/**
 * This class implements the <b>Number of Methods (NOM)</b> design metric. It is
 * the number of methods declared in a class. Design Property: <i>Complexity</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODNumberOfMethods extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Complexity";
    }

    @Override
    public String getAbbreviation() {
        return "NOM";
    }

    @Override
    public String toString() {
        return "Number of Methods";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {
        return cls.getNumberOfMethods();
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
