package thiagodnf.doupr.evaluation.antipatterns;

import thiagodnf.doupr.core.base.ProjectObject;
import thiagodnf.doupr.evaluation.Objective;

/**
 * Blob
 * <p>Rule # 1 - BLOB : (NOM greater 12) (NOA greater 10) (LCOM greater 200)</p>
 * <ul>
 * <li>NOM  = number of methods</li>
 * <li>NOA  = number of attributes</li>
 * <li>LCOM = number of line codes</li>
 * </ul>
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2018-02-07
 */
public class Blob extends AbstractAntiPattern {

	public Blob() {

	}

	public Blob(Blob cohesion) {
		super(cohesion);
	}

	@Override
	public double getDiff(ProjectObject project, ProjectObject refactored) {
		return getRate(project, refactored, "Blob");
	}

	@Override
	public double getValue(ProjectObject project) {

		double value = 0;


		return value;
	}

	@Override
	public String toString() {
		return "Blob";
	}

	@Override
	public boolean isMinimize() {
		return false;
	}

	@Override
	public Objective copy() {
		return new Blob(this);
	}
}
