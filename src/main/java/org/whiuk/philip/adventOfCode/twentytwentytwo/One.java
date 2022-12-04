package org.whiuk.philip.adventOfCode.twentytwentytwo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class One {

    public static void partOne() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/1/input"), Charset.defaultCharset());
        int record = 0;
        int recordIndex = 0;
        int currentElfIndex = 0;
        int currentElfCarry = 0;

        currentElfIndex += 1;
        for (String line: inputData) {
            if (!line.equals("")) {
                currentElfCarry += Integer.parseInt(line);
                if (currentElfCarry > record) {
                    record = currentElfCarry;
                    recordIndex = currentElfIndex;
                }
            } else {
                currentElfCarry = 0;
                currentElfIndex += 1;
            }
        }
        System.out.println("Most calories: "+record + " - Elf: " + recordIndex);
    }

    public static void partTwo() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/1/input"), Charset.defaultCharset());
        List<Integer> elfCarryAmounts = new ArrayList<>();

        int currentElfCarry = 0;
        for (String line: inputData) {
            if (!line.equals("")) {
                currentElfCarry += Integer.parseInt(line);
            } else {
                elfCarryAmounts.add(currentElfCarry);
                currentElfCarry = 0;
            }
        }
        if (currentElfCarry != 0) {
            elfCarryAmounts.add(currentElfCarry);
        }
        elfCarryAmounts.sort(Comparator.reverseOrder());
        int top3 = elfCarryAmounts.get(0) + elfCarryAmounts.get(1) + elfCarryAmounts.get(2);
        System.out.println("Top 3: " + top3);

    }
}
