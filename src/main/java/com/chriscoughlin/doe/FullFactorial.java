package com.chriscoughlin.doe;

import java.util.*;
/**
 * Generates a fully crossed (also known as a full factorial) expansion of
 * an array of conditions
 */
public class FullFactorial {
    private List<Integer> levels; // number of different values each condition takes
    private List<List<Integer>> full; // every permutation of the conditions

    /**
     * Creates a new fully crossed expansion of an array of conditions
     * @param levels number of different values each condition takes
     */
    public FullFactorial(Integer[] levels) {
        this.levels = new ArrayList<>(Arrays.asList(levels));
        generate();
    }

    public FullFactorial(List<Integer> levels) {
        this.levels = new ArrayList<>(levels);
        generate();
    }

    /**
     * Generates the complete list of permutations
     */
    public void generate() {
        // Based on code from https://github.com/tisimst/pyDOE/blob/master/pyDOE/doe_factorial.py
        int numConditions = 1;
        for (int el : levels) {
            numConditions *= el;
        }
        full = new ArrayList<>();
        int levelRepeat = 1;
        int rangeRepeat = numConditions;
        for (Integer level : levels) {
            rangeRepeat /= level;
            ArrayList<Integer> currentLevel = new ArrayList<>();
            for (int j = 0; j < level; j++) {
                for (int k = 0; k < levelRepeat; k++) {
                    currentLevel.add(j);
                }
            }
            ArrayList<Integer> rng = new ArrayList<>();
            for (int m = 0; m < rangeRepeat; m++) {
                rng.addAll(currentLevel);
            }
            levelRepeat *= level;
            full.add(rng);
        }
    }

    /**
     * Returns a specific permutation from the full factorial
     * @param index the index of the specific permutation to retrieve
     * @return list of the condition values for the given permutation
     */
    public List<Integer> getPermutation(int index) {
        ArrayList<Integer> perm = new ArrayList<>();
        for (List<Integer> aFull : full) {
            perm.add(aFull.get(index));
        }
        return perm;
    }

    /**
     * Remove a condition from the list of conditions and regenerate the full
     * factorial expansion
     * @param conditionIndex index of the condition to remove
     */
    public void removeCondition(int conditionIndex) {
        levels.remove(conditionIndex);
        generate();
    }

    /**
     * Adds a new condition and regenerates the full factorial expansion
     * @param conditionIndex index at which to add the condition
     * @param conditionLevels number of values the new condition takes
     */
    public void addCondition(int conditionIndex, int conditionLevels) {
        levels.add(conditionIndex, conditionLevels);
        generate();
    }

    /**
     * Adds a new condition to the end of the list
     * @param conditionLevels number of values the new condition takes
     */
    public void addCondition(int conditionLevels) {
        addCondition(levels.size(), conditionLevels);
    }

    /**
     * Replaces the list of conditions and regenerates the full factorial expansion
     * @param conditions new list of conditions
     */
    public void setConditions(List<Integer> conditions) {
        this.levels = new ArrayList<>(conditions);
        generate();
    }

    /**
     * Replaces the list of conditions and regenerates the full factorial expansion
     * @param conditions array of new condition levels
     */
    public void setConditions(Integer[] conditions) {
        setConditions(Arrays.asList(conditions));
    }

    /**
     * Returns a copy of the complete list of permutations, arranged by condition
     * and the values it takes
     * @return List of each condition and the values it takes
     */
    public List<List<Integer>> getFullFactorial() {
        return new ArrayList<>(full);
    }

    /**
     * Returns a copy of the current list of levels
     * @return list of the levels
     */
    public List<Integer> getLevels() {
        return new ArrayList<>(levels);
    }

    /**
     * Return the current number of permutations
     * @return number of permutations
     */
    public int numPermutations() {
        int combos = 1;
        for (Integer level : levels) {
            combos *= level;
        }
        return combos;
    }

    /**
     * Reformats the full factorial table as a list of permutations
     * @return list of permutations
     */
    public List<List<Integer>> reformat() {
        ArrayList<List<Integer>> ref = new ArrayList<>();
        int numPerms = full.get(0).size();
        for (int j = 0; j < numPerms; j++) {
            ref.add(getPermutation(j));
        }
        return ref;
    }
}
