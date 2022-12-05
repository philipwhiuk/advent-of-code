package org.whiuk.philip.adventOfCode.twentytwentytwo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Function;

public class Five {

    @FunctionalInterface
    interface Function4<One, Two, Three, Four> {
        public void apply(One one, Two two, Three three, Four four);
    }
    
    private static int calculateStackCount(List<String> inputData) {
        for (String line : inputData) {
            if (line.trim().startsWith("1")) {
                return line.trim().split("\\s+").length;
            }
        }
        return 0;
    }
    
    private static void processInitialConfigurationLine(String line, List<List<Character>> stackData) {
        char[] stackLine = line.toCharArray();
        for (int i = 0; i < stackLine.length; i += 4) {
            int stack = (i/4);
            if (stackLine[i+1] != ' ') {
                stackData.get(stack).add(stackLine[i+1]);
            }
        }
    }
    
    public static void partOne() throws IOException {
        processData(Five::singleMovement);
    }
    public static void partTwo() throws IOException {
        processData(Five::bulkMovement);
    }
    
    private static void processData(Function4<List<Stack<Character>>, Integer, Integer, Integer> processingMethod) throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/5/input"), Charset.defaultCharset());
        int stackCount = calculateStackCount(inputData);
        
        
        boolean processingConfig = true;
        List<Stack<Character>> stacks = null;
        List<List<Character>> stackData = new ArrayList<>();
        for (int i = 0; i < stackCount ; i++) {
            stackData.add(new ArrayList<>());
        }
        for (String line : inputData) {
            if (line.length() == 0) {
                processingConfig = false;
                stacks = transferToStacks(stackData);
            } else if (processingConfig) {
                processInitialConfigurationLine(line, stackData);
            } else {
                processMovement(line, stacks, processingMethod);
            }
        }
        String output = "";
        for (int s = 0; s < stacks.size(); s++) {
            output += stacks.get(s).peek();
        }
        System.out.println(output);
    }
    
    private static void processMovement(String line, List<Stack<Character>> stacks, Function4<List<Stack<Character>>, Integer, Integer, Integer> process) {
        String[] parts = line.split(" ");
        int a = Integer.parseInt(parts[1]);
        int b = Integer.parseInt(parts[3]);
        int c = Integer.parseInt(parts[5]);
        process.apply(stacks, a, b, c);
    }
    
    private static void singleMovement(List<Stack<Character>> stacks, int a, int b, int c) {
        for (int i = 0; i < a; i++) {
            char toMove = stacks.get(b - 1).pop();
            stacks.get(c-1).push(toMove);
        }
    }

    private static void bulkMovement(List<Stack<Character>> stacks, int a, int b, int c) {
        Stack<Character> carryQueue = new Stack<>();
        for (int i = 0; i < a; i++) {
            char toRemove = stacks.get(b - 1).pop();
            carryQueue.push(toRemove);
        }
        for (int i = 0; i < a; i++) {
            char toPut = carryQueue.pop();
            stacks.get(c-1).push(toPut);
        }
    }

    private static List<Stack<Character>> transferToStacks(List<List<Character>> stackData) {
        List<Stack<Character>> stacks = new ArrayList<>();
        for (List<Character> stackList : stackData) {
            Stack<Character> stack = new Stack<>();
            for (int i = stackList.size()-1; i >= 0; i-- ) {
                stack.add(stackList.get(i));
            }
            stacks.add(stack);
        }
        return stacks;
    }
}
