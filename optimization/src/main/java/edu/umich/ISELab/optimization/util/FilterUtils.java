package edu.umich.ISELab.optimization.util;

import edu.umich.ISELab.optimization.filter.Filter;
import edu.umich.ISELab.optimization.problem.Problem;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.constants.FilterConstants;

import java.util.ArrayList;
import java.util.List;

public class FilterUtils {

	public static List<Solution> filter(Problem problem, List<Solution> solutions, List<Filter> filters) {

		//checkNotNull(solutions, "The solutions cannot be null");
		//checkNotNull(filters, "The filters cannot be null");

		if (filters.isEmpty()) {
			return solutions;
		}

		List<Solution> filtered = new ArrayList<>();

		for (Solution solution : solutions) {

			boolean isValid = true;

			for (Filter filter : filters) {
				isValid &= filter.filter(problem, solution);
			}

			if (isValid) {
				filtered.add(solution);
			}
		}

		return filtered;
	}

	public static String convert(String name) {

		// Solutions
		if (name.equalsIgnoreCase(FilterConstants.CONTAINS)) return FilterConstants.CONTAINS;
		if (name.equalsIgnoreCase(FilterConstants.NOT_CONTAINS)) return FilterConstants.NOT_CONTAINS;

		// Objectives
		if (name.equalsIgnoreCase(FilterConstants.EQUAL_TO)) return FilterConstants.EQUAL_TO;
		if (name.equalsIgnoreCase(FilterConstants.NOT_EQUAL_TO)) return FilterConstants.NOT_EQUAL_TO;
		if (name.equalsIgnoreCase(FilterConstants.GREATER_THAN)) return FilterConstants.GREATER_THAN;
		if (name.equalsIgnoreCase(FilterConstants.LESS_THAN)) return FilterConstants.LESS_THAN;
		if (name.equalsIgnoreCase(FilterConstants.GREATER_THAN_OR_EQUAL_TO))
			return FilterConstants.GREATER_THAN_OR_EQUAL_TO;
		if (name.equalsIgnoreCase(FilterConstants.LESS_THAN_OR_EQUAL_TO)) return FilterConstants.LESS_THAN_OR_EQUAL_TO;

		return "";
	}
}
