package thiagodnf.doupr.optimization.operators.selections;

import org.uma.jmetal.operator.impl.selection.TournamentSelection;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import thiagodnf.doupr.optimization.solution.Solution;

/**
 * The Binary Tournament Selection Class. In this selection operator we use
 * the binary one
 *
 * @author Thiago N. Ferreira
 * @version 1.0.0
 * @since 2017-06-09
 */
public class BinaryTournamentSelection extends TournamentSelection<Solution> {

    private static final long serialVersionUID = -2952955830995557298L;

    /**
     * Constructor
     */
    public BinaryTournamentSelection() {
        super(new RankingAndCrowdingDistanceComparator<Solution>(), 2);
    }
}
