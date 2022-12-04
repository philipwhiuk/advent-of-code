package org.whiuk.philip.adventOfCode.twentytwentytwo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Four {
    public static void partOne() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/4/input"), Charset.defaultCharset());
        int count = 0;
        for (String pairSections : inputData) {
            if (hasFullyContainedRange(pairSections)) {
                count += 1;
            }
        }
        System.out.println("Count: "+ count);
    }

    public static void partTwo() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/4/input"), Charset.defaultCharset());
        int count = 0;
        for (String pairSections : inputData) {
            if (hasOverlappingRange(pairSections)) {
                count += 1;
            }
        }
        System.out.println("Count: "+ count);
    }

    private static boolean hasFullyContainedRange(String pairSections) {
        String[] sectionData = pairSections.split(",");
        int[] sectionRange1 = parseSectionRange(sectionData[0]);
        int[] sectionRange2 = parseSectionRange(sectionData[1]);

        if (sectionRange1[0] <= sectionRange2[0] && sectionRange1[1] >= sectionRange2[1]) {
            return true;
        }
        if (sectionRange2[0] <= sectionRange1[0] && sectionRange2[1] >= sectionRange1[1]) {
            return true;
        }
        return false;
    }

    private static boolean hasOverlappingRange(String pairSections) {
        String[] sectionData = pairSections.split(",");
        int[] sectionRange1 = parseSectionRange(sectionData[0]);
        int[] sectionRange2 = parseSectionRange(sectionData[1]);

        if (sectionRange1[0] <= sectionRange2[0] && sectionRange1[1] >= sectionRange2[0]) {
            //First range starts before or at start of second and doesn't finish before start of range
            //System.out.println("First starts before or equal: " + rangesToString(sectionRange1,sectionRange2));
            return true;
        }
        if (sectionRange1[0] >= sectionRange2[0] && sectionRange1[0] <= sectionRange2[1]) {
            //First range starts after start but before end
            //System.out.println("Second starts mid-range: " + rangesToString(sectionRange1,sectionRange2));
            return true;
        }

        if (sectionRange1[0] <= sectionRange2[0] && sectionRange1[1] >= sectionRange2[1]) {
            //First range starts before or at start of second and doesn't finish before start of range
            return true;
        }

        System.out.println("No overlap: " + rangesToString(sectionRange1, sectionRange2));

        return false;
    }

    private static String rangesToString(int[] range1, int[] range2) {
        return range1[0]+"-"+range1[1]+","+range2[0]+"-"+range2[1];
    }

    private static int[] parseSectionRange(String sectionRange) {
        String[] sectionRangeData = sectionRange.split("-");
        return new int[]{
                Integer.parseInt(sectionRangeData[0]),
                Integer.parseInt(sectionRangeData[1])
        };
    }
}
