package test.java.test.thiagodnf.doupr.optimization.utils;

import org.junit.Test;
import edu.umich.ISELab.optimization.solution.Solution;
import edu.umich.ISELab.optimization.util.SolutionListUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSolutionListUtils {

    protected Solution getSolution(double... values) {

        Solution solution = mock(Solution.class);

        when(solution.getNumberOfObjectives()).thenReturn(values.length);
        when(solution.copy()).thenReturn(mock(Solution.class));
        when(solution.getAttributes()).thenReturn(new HashMap<>());

        for (int i = 0; i < values.length; i++) {
            when(solution.getObjective(i)).thenReturn(values[i]);
        }

        return solution;
    }

    @Test
    public void shouldReturnTrueBecauseTheSolutionsAreEquals() {
        assertTrue(SolutionListUtils.equals(getSolution(0.1, 0.2), getSolution(0.1, 0.2)));
    }

    @Test
    public void shouldReturnFalseBecauseTheNumberOfObjectivesAreDifferent() {
        assertFalse(SolutionListUtils.equals(getSolution(0.1, 0.2, 0.3), getSolution(0.1, 0.2)));
    }

    @Test
    public void shouldReturnFalseBecauseTheObjectiveValuesAreDifferent() {
        assertFalse(SolutionListUtils.equals(getSolution(1, 2), getSolution(1, 3)));
    }

    @Test
    public void shouldReturnNotContainsBecauseTheItemListIsEmpty() {
        assertFalse(SolutionListUtils.contains(new ArrayList<>(), getSolution(0.1, 0.2)));
    }

    @Test
    public void shouldReturnNotContainsBecauseTheListHasDifferentObjectiveValues() {
        assertFalse(SolutionListUtils.contains(Arrays.asList(getSolution(0.1, 0.2)), getSolution(0.1, 0.3)));
    }

    @Test
    public void shouldReturnContainsBecauseTheItemIsIn() {
        assertTrue(SolutionListUtils.contains(Arrays.asList(getSolution(0.1, 0.2)), getSolution(0.1, 0.2)));
    }

    @Test
    public void shouldRemoveTheRepeatedSolutionsWithTheSameObjectiveValues() {

        Solution s1 = getSolution(0.1, 0.2);
        Solution s2 = getSolution(0.2, 0.3);
        Solution s3 = getSolution(0.1, 0.2);
        Solution s4 = getSolution(0.1, 0.2);

        List<Solution> nonRepeated = SolutionListUtils.removeRepeated(Arrays.asList(s1, s2, s3, s4));

        assertEquals(2, nonRepeated.size());
        assertEquals(s1, nonRepeated.get(0));
        assertEquals(s2, nonRepeated.get(1));
    }

    @Test
    public void shouldClearAllAttributes() {

        Solution solution = getSolution(0.2, 0.3);

        solution.setAttribute("ATTR_1", true);
        solution.setAttribute("ATTR_2", 400);
        solution.setAttribute("ATTR_3", 10.2);

        List<Solution> items = Arrays.asList(solution);

        SolutionListUtils.clearAttributes(items);

        assertTrue(solution.getAttributes().isEmpty());
    }

    @Test
    public void shouldCopyAListOfSolutions() {

        List<Solution> items = Arrays.asList(getSolution(1, 1), getSolution(2, 2));

        List<Solution> copy = SolutionListUtils.copy(items);

        assertEquals(items.size(), copy.size());
        assertNotEquals(items.get(0), copy.get(0));
        assertNotEquals(items.get(1), copy.get(1));
    }

    @Test
    public void shouldNotInstatiateThisClass() throws Exception {
        Constructor<SolutionListUtils> constructor = SolutionListUtils.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

//	@Test(expected=IllegalArgumentException.class)
//	public void shouldThrowExceptionWhenGetMinimumValuesWithEmptyArray() {
//		SolutionListUtils.getMinimumValues(new ArrayList<>());
//	}
//	
//	@Test
//	public void shouldReturnTheCorrectMinimumValues() {
//		
//		List<Solution> solutions = Arrays.asList(getSolution(1, 0), getSolution(2, 1), getSolution(5, 3));
//
//		double[] minValues = SolutionListUtils.getMinimumValues(solutions);
//
//		assertEquals(minValues.length, 2);
//		assertEquals(minValues[0], 1, 0);
//		assertEquals(minValues[1], 0, 0);
//	}
//	
//	@Test(expected=IllegalArgumentException.class)
//	public void shouldThrowExceptionWhenGetMaximumValuesWithEmptyArray() {
//		SolutionListUtils.getMaximumValues(new ArrayList<>());
//	}
//	
//	@Test
//	public void shouldReturnTheCorrectMaximumValues() {
//		
//		List<Solution> solutions = Arrays.asList(getSolution(1, 0), getSolution(2, 1), getSolution(5, 3));
//
//		double[] minValues = SolutionListUtils.getMaximumValues(solutions);
//
//		assertEquals(minValues.length, 2);
//		assertEquals(5, minValues[0], 0);
//		assertEquals(3, minValues[1], 0);
//	}
//	
//	@Test
//	public void shouldReturnTheSelectedSolutions() {
//
//		Solution s1 = getSolution(0.2, 0.3);
//		Solution s2 = getSolution(0.1, 0.3);
//		
//		when(s1.getUserSelection()).thenReturn(true);
//		when(s2.getUserSelection()).thenReturn(false);
//		
//		List<Solution> selected = SolutionListUtils.getSolutionsSelectedByTheUser(Arrays.asList(s1,s2));
//		
//		assertEquals(1, selected.size());
//		assertEquals(s1, selected.get(0));
//	}
//	
//	@Test(expected=IllegalArgumentException.class)
//	public void shouldThrowExceptionWhenNormalizeWithEmptyArray() {
//		SolutionListUtils.normalize(new ArrayList<>());
//	}
//	
//	@Test
//	public void shouldNormalizeTheSolutions() {
//
//		Problem problem = mock(Problem.class);
//		
//		when(problem.getNumberOfObjectives()).thenReturn(2);
//		when(problem.getNumberOfVariables()).thenReturn(1);
//		
//		RefactoringSolution s1 = new RefactoringSolution(problem);
//		RefactoringSolution s2 = new RefactoringSolution(problem);
//		
//		s1.setVariableValue(0, new RefactoringVariable());
//		s2.setVariableValue(0, new RefactoringVariable());
//		
//		s1.setObjective(0, 1);
//		s1.setObjective(1, 2);
//		s2.setObjective(0, 3);
//		s2.setObjective(1, 4);
//		
//		List<Solution> solutions = Arrays.asList(s1,s2);
//		
//		List<Solution> normalized = SolutionListUtils.normalize(solutions);
//		
//		assertEquals(solutions.size(), normalized.size());
//		assertEquals((1-1)/(3-1), normalized.get(0).getObjective(0), 0);
//		assertEquals((2-1)/(3-1), normalized.get(0).getObjective(1), 0);
//		assertEquals((3-1)/(3-1), normalized.get(1).getObjective(0), 0);
//		assertEquals((4-2)/(4-2), normalized.get(1).getObjective(1), 0);
//	}
}
