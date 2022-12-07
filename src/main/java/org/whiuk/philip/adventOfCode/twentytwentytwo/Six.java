package org.whiuk.philip.adventOfCode.twentytwentytwo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Six {
    public static void partOne() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/6/input"), Charset.defaultCharset());
        String data = inputData.get(0);
        char[] dataBuffer = data.toCharArray();
        int i = findIndex(dataBuffer, 4);
        System.out.println(i+1);
    }

    public static void partTwo() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/6/input"), Charset.defaultCharset());
        String data = inputData.get(0);
        char[] dataBuffer = data.toCharArray();
        int i = findIndex(dataBuffer, 14);
        System.out.println(i+1);
    }
    
    private static int findIndex(char[] dataBuffer, int uniqueCount) {
        Set<Character> uniqueCharacters = new HashSet<>();
        int firstUniqueCharacter = 0;
        for (int i = 0; i < dataBuffer.length; i++) {
            if (uniqueCharacters.contains(dataBuffer[i])) {
                uniqueCharacters.clear();
                i = firstUniqueCharacter;
            } else {
                if (uniqueCharacters.isEmpty()) {
                    firstUniqueCharacter = i;
                }
                uniqueCharacters.add(dataBuffer[i]);
                if (uniqueCharacters.size() == uniqueCount) {
                    return i;
                }
            }
        }
        return -1;
    }
}
