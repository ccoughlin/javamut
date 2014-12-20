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
        ff.generate();
        List<Integer> condition1 = Arrays.asList(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1);
        List<Integer> condition2 = Arrays.asList(0, 0, 1, 1, 2, 2, 3, 3, 0, 0, 1, 1, 2, 2, 3, 3, 0, 0, 1, 1, 2, 2, 3, 3);
        List<Integer> condition3 = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2);
        expectedFF = new ArrayList<List<Integer>>();
        expectedFF.add(condition1);
        expectedFF.add(condition2);
        expectedFF.add(condition3);
    }

    @Test
    /**
     * Verify the generate method correctly generates the list of permutations
     */
    public void testGenerate() throws Exception {
        List<List<Integer>> returnedFF = ff.getFullFactorial();
        for (int i=0; i<expectedFF.size(); i++) {
            assertEquals(expectedFF.get(i), returnedFF.get(i));
        }
    }

    @Test
    /**
     * Verify the getPermutation method correctly returns a specific permutation
     */
    public void testGetPermutation() throws Exception {
        int numCombinations = 1;
        for (Integer level : levels) {
            numCombinations *= level;
        }
        for (int i = 0; i < numCombinations; i++) {
            ArrayList<Integer> expectedMut = new ArrayList<Integer>();
            expectedMut.add(ff.getFullFactorial().get(0).get(i));
            expectedMut.add(ff.getFullFactorial().get(1).get(i));
            expectedMut.add(ff.getFullFactorial().get(2).get(i));
            List<Integer> returnedMut = ff.getPermutation(i);
            assertEquals(expectedMut, returnedMut);
        }
    }

    
    
    @Test
    public void testBoop() throws Exception {
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

}