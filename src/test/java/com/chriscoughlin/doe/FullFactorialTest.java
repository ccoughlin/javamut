package com.chriscoughlin.doe;

import java.util.*;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the FullFactorial implementation
 */
public class FullFactorialTest extends TestCase {
    private FullFactorial ff;
    private Integer[] levels = {2, 4, 3};
    ArrayList<List<Integer>> expectedFF;

    @Before
    public void setUp() throws Exception {
        this.ff = new FullFactorial(levels);
        List<Integer> condition1 = Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1);
        List<Integer> condition2 = Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 0, 0, 1, 1, 2, 2, 3, 3, 0, 0, 1, 1, 2, 2, 3, 3);
        List<Integer> condition3 = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2);
        expectedFF = new ArrayList<>();
        expectedFF.add(condition1);
        expectedFF.add(condition2);
        expectedFF.add(condition3);
    }

    /**
     * Verify the generate method correctly generates the list of permutations
     */
    @Test
    public void testGenerate() throws Exception {
        List<List<Integer>> returnedFF = ff.getFullFactorial();
        for (int i=0; i<expectedFF.size(); i++) {
            assertEquals(expectedFF.get(i), returnedFF.get(i));
        }
    }

    /**
     * Verify the getPermutation method correctly returns a specific permutation
     */
    @Test
    public void testGetPermutation() throws Exception {
        int numCombinations = 1;
        for (Integer level : levels) {
            numCombinations *= level;
        }
        for (int i = 0; i < numCombinations; i++) {
            ArrayList<Integer> expectedMut = new ArrayList<>();
            expectedMut.add(ff.getFullFactorial().get(0).get(i));
            expectedMut.add(ff.getFullFactorial().get(1).get(i));
            expectedMut.add(ff.getFullFactorial().get(2).get(i));
            List<Integer> returnedMut = ff.getPermutation(i);
            assertEquals(expectedMut, returnedMut);
        }
    }

    /**
     * Verify returning the full factorial as a list of permutations
     */
    @Test
    public void testReformat() throws Exception {
        Integer[][] expected = {{ 0, 0, 0},
                                { 1, 0, 0},
                                { 0, 1, 0},
                                { 1, 1, 0},
                                { 0, 2, 0},
                                { 1, 2, 0},
                                { 0, 3, 0},
                                { 1, 3, 0},
                                { 0, 0, 1},
                                { 1, 0, 1},
                                { 0, 1, 1},
                                { 1, 1, 1},
                                { 0, 2, 1},
                                { 1, 2, 1},
                                { 0, 3, 1},
                                { 1, 3, 1},
                                { 0, 0, 2},
                                { 1, 0, 2},
                                { 0, 1, 2},
                                { 1, 1, 2},
                                { 0, 2, 2},
                                { 1, 2, 2},
                                { 0, 3, 2},
                                { 1, 3, 2}};
        List<List<Integer>> returned = ff.reformat();
        for (int i = 0; i < expected.length; i++) {
            List<Integer> expectedPermutation = Arrays.asList(expected[i]);
            assertEquals(expectedPermutation, returned.get(i));
        }
    }

    /**
     * Verify returning a randomized design matrix
     * @throws Exception
     */
    @Test 
    public void testRandomized() throws Exception {
        List<List<Integer>> expected = ff.reformat();
        List<List<Integer>> returned = ff.randomized();
        assertTrue(returned.size() == expected.size() + 3);
        // Verify the returned design matrix includes center point conditions at beginning, middle, and end
        Integer[] midVals = new Integer[]{0, 0, 0};
        List<Integer> centrePoint = Arrays.asList(midVals);
        assertEquals(returned.get(0), centrePoint);
        assertEquals(returned.get(returned.size() - 1), centrePoint);
        int midIndex = expected.size() == 0 ? -1 : expected.size() / 2;
        assertEquals(returned.get(midIndex), centrePoint);
        // Verify every expected design is in the randomized matrix
        for (List<Integer> condition : expected) {
            assertTrue(returned.contains(condition));
        }
    }

    /**
     * Verify returning the number of permutations
     */
    @Test
    public void testGetNumPermutations() throws Exception {
        int expected = 1;
        for (int i : levels) {
            expected *= i;
        }
        assertEquals(expected, ff.numPermutations());
    }

    /**
     * Verify removing a condition
     */
    @Test
    public void testRemoveCondition() throws Exception {
        Integer[] conditions = {2, 4};
        FullFactorial small = new FullFactorial(conditions);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        small.removeCondition(0);
        assertTrue(small.numPermutations() == conditions[1]);
        assertEquals(expected, small.getFullFactorial().get(0));
    }

    /**
     * Verify adding a condition
     */
    @Test
    public void testAddCondition() throws Exception {
        Integer[] conditions = {4};
        FullFactorial tiny = new FullFactorial(conditions);
        tiny.addCondition(0, 2);
        tiny.addCondition(3);
        List<List<Integer>> returnedFF = tiny.getFullFactorial();
        for (int i=0; i<expectedFF.size(); i++) {
            assertEquals(expectedFF.get(i), returnedFF.get(i));
        }
    }

    /**
     * Verify setting the conditions
     */
    @Test
    public void testSetConditions() throws Exception {
        FullFactorial dummy = new FullFactorial(new Integer[]{1});
        dummy.setConditions(levels);
        List<List<Integer>> returnedFF = dummy.getFullFactorial();
        for (int i=0; i<expectedFF.size(); i++) {
            assertEquals(expectedFF.get(i), returnedFF.get(i));
        }
    }
}