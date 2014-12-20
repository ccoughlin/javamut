package com.chriscoughlin.doe;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Permutations class
 */
public class PermutationsTest {

    /**
     * Verify the generate method correctly generates the full factorial table
     * @throws Exception
     */
    @Test
    public void testGenerate() throws Exception {
        List<String> words = new ArrayList<String>();
        words.add("Alpha");
        words.add("Beta");
        words.add("Gamma");
        List<Double> numbers = new ArrayList<Double>();
        numbers.add(-1.);
        numbers.add(2.2);
        numbers.add(3.141592654);
        Permutations perms = new Permutations(words, numbers);
        List<List<?>> permTable = perms.getPermutations();
        FullFactorial ff = new FullFactorial(new Integer[]{words.size(),
                numbers.size()});
        // Check the table row by row
        List<List<Integer>> levels = ff.getFullFactorial();
        for (int j = 0; j < permTable.get(0).size(); j++) {
            assertEquals(words.get(levels.get(0).get(j)), permTable.get(0).get(j));
        }
        for (int j = 0; j < permTable.get(1).size(); j++) {
            assertEquals(numbers.get(levels.get(1).get(j)), permTable.get(1).get(j));
        }
    }
}