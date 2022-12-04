package org.whiuk.philip.adventOfCode;

import org.whiuk.philip.adventOfCode.twentytwentytwo.Four;
import org.whiuk.philip.adventOfCode.twentytwentytwo.One;
import org.whiuk.philip.adventOfCode.twentytwentytwo.Three;
import org.whiuk.philip.adventOfCode.twentytwentytwo.Two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdventOfCode {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String year = fetchInfo("Year?");
        if ("2022".equals(year)) {
            String day = fetchInfo("Day?");
            if ("1".equals(day)) {
                String part = fetchInfo("Part?");
                switch(part) {
                    case "1": One.partOne(); break;
                    case "2": One.partTwo(); break;
                    default: System.out.println("Unsolved/unknown activity");
                }
            } else if ("2".equals(day)) {
                String part = fetchInfo("Part?");
                switch(part) {
                    case "1": Two.partOne(); break;
                    case "2": Two.partTwo(); break;
                    default: System.out.println("Unsolved/unknown activity");
                }
            } else if ("3".equals(day)) {
                String part = fetchInfo("Part?");
                switch(part) {
                    case "1": Three.partOne(); break;
                    case "2": Three.partTwo(); break;
                    default: System.out.println("Unsolved/unknown activity");
                }
            } else if ("4".equals(day)) {
                String part = fetchInfo("Part?");
                switch(part) {
                    case "1": Four.partOne(); break;
                    case "2": Four.partTwo(); break;
                    default: System.out.println("Unsolved/unknown activity");
                }
            } else{
                System.out.println("Unsolved/unknown activity");
            }
        } else {
            System.out.println("Unsolved/unknown year");
        }
    }

    private static String fetchInfo(String question) throws IOException {
        System.out.println(question);
        return input.readLine();
    }
}
