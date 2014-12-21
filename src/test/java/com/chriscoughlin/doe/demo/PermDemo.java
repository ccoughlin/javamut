package com.chriscoughlin.doe.demo;

import com.chriscoughlin.doe.Permutations;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

/**
 * Simple demo for the Permutations implementation
 */
public class PermDemo {

    private List<Condition> conditions;

    /**
     * Demonstrates the Permutations class by reading a CSV of conditions
     * @param conditionsCSV full path and filename to the conditions CSV
     */
    public PermDemo(Path conditionsCSV) {
        parseFile(conditionsCSV);
    }

    /**
     * Reads a comma-separated variable (CSV) file and builds a list of conditions
     * @param inputFile full path and filename to the CSV
     */
    public void parseFile(Path inputFile) {
        try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
            String currentLine;
            conditions = new ArrayList<>();
            while ((currentLine = reader.readLine()) != null) {
                if (!currentLine.startsWith("#")) {
                    conditions.add(new Condition(currentLine));
                }
            }
        } catch (IOException ioe) {
            System.out.println("Unable to read file, error was: " + ioe.getLocalizedMessage());
        } catch (SecurityException se) {
            System.out.println("Insufficient permission to access file, error was: " + se.getLocalizedMessage());
        }
    }

    /**
     * Returns the list of permutations
     * @return list of permutations
     */
    public List<List<?>> getPermutations() {
        List<List<?>> perms = null;
        if (conditions != null) {
            List<?>[] conditionValues = new List[conditions.size()];
            for (int i = 0; i < conditions.size(); i++) {
                conditionValues[i] = conditions.get(i).values;
            }
            Permutations perm = new Permutations(conditionValues);
            perms = perm.getTable();
        }
        return perms;
    }

    /**
     * Simple wrapper for the condition name and its values
     */
    public static class Condition {
        String name;
        List<String> values;

        public Condition(String configString) {
            populate(configString);
        }

        /**
         * Builds the Condition from a String
         * @param configStr comma-delimited String to parse
         */
        public void populate(String configStr) {
            values = new ArrayList<>();
            String[] tokens = configStr.trim().split(",");
            name = tokens[0].trim();
            for (int i = 1; i < tokens.length; i++) {
                values.add(tokens[i].trim());
            }
        }
    }

    /**
     * Simple demo - prints the list of conditions from an input file and the full list of permutations
     * @param args
     */
    public static void main(String[] args) {
        URL url = PermDemo.class.getResource("/sample.csv");
        PermDemo demo = new PermDemo(new File(url.getPath()).toPath());
        List<List<?>> permutations = demo.getPermutations();
        System.out.println("Found " + demo.conditions.size() + " conditions in " + url.getPath() + ":\n");
        for (Condition condition : demo.conditions) {
            System.out.println("\tCondition: " + condition.name);
            System.out.println("\tValues: " + condition.values + "\n");
        }
        System.out.println("\nTotal of " + permutations.size() + " permutations:\n");
        for (List<?> permutation : permutations) {
            System.out.println(permutation);
        }
    }
}
