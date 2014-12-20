package com.chriscoughlin.doe;
import java.util.*;

/**
 * Generates every possible combination of a list of conditions
 */
public class Permutations {
    private List<?>[] conditions;  // List of conditions and their values
    private List<List<?>> permutations; // List of the permutations of the conditions

    /**
     * Creates a list of every possible combination of the specified conditions and
     * the value(s) each takes
     * @param conditions lists of conditions and the values taken
     */
    public Permutations(List<?>... conditions) {
        this.conditions = conditions;
        generate();
    }

    /**
     * Creates the fully crossed (AKA full factorial) list
     */
    public void generate() {
        List<Integer> conditionLevels = new ArrayList<Integer>();
        for (List<?> condition1 : conditions) {
            conditionLevels.add(condition1.size());
        }
        FullFactorial ff = new FullFactorial(conditionLevels);
        permutations = new ArrayList<List<?>>();
        List<List<Integer>> levels = ff.getFullFactorial();
        for (int i = 0; i < levels.size(); i++) {
            ArrayList<Object> condition = new ArrayList<Object>();
            for (int j = 0; j < levels.get(i).size(); j++) {
                condition.add(conditions[i].get(levels.get(i).get(j)));
            }
            permutations.add(condition);
        }
    }

    /**
     * Returns a copy of the current permutations list
     * @return List of permutations
     */
    public List<List<?>> getPermutations() {
        return new ArrayList<List<?>>(permutations);
    }
}
