package thiagodnf.doupr.evaluation.designmetrics;

/**
 * This class implements the <b>Number Of Hierarchies (NOH)</b> design metric.
 * It is the total number of 'root' classes in the design. Design Property:
 * <i>Hierarchies</i>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-16
 */
public class QMOODNumberOfHierarchies extends AbstractDesignMetric {

    @Override
    public String getDesignProperty() {
        return "Hierarchies";
    }

    @Override
    public String getAbbreviation() {
        return "NOH";
    }

    @Override
    public String toString() {
        return "Number Of Hierarchies";
    }

    @Override
    public double calculate(ClassObject cls, ProjectObject project) {

//		int count = 0;
//
//		for (PackageObject pkg : project.getPackages()) {
//
//			for (ClassObject c : pkg.getClasses()) {
//
//				if (c.getSuperClass() == null && ! c.getSubClasses().isEmpty()) {
//					count++;
//				}
//			}
//		}
//
//		return count;

        //TODO changing the definition to have normalized metric  - vahid

        if (cls.getSuperClass() == null && !cls.getSubClasses().isEmpty()) {

            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isValid(ClassObject cls) {
        return true;
    }
}
