package org.whiuk.philip.adventOfCode.twentytwentytwo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Two {

    public static final int DRAW = 3;
    public static final int WIN = 6;
    public static final int LOSE = 0;

    public static enum RPS {
        ROCK, PAPER, SCISSORS
    }

    public static void partOne() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/2/input"), Charset.defaultCharset());
        int score = 0;
        for (String line: inputData) {
            String[] responses = extractResponses(line);
            score += roundOneMechanism(responses);
        }
        System.out.println("Expected score:" + score);
    }

    public static void partTwo() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/2/input"), Charset.defaultCharset());
        int score = 0;
        for (String line: inputData) {
            String[] responses = extractResponses(line);
            score += roundTwoMechanism(responses);
        }
        System.out.println("Expected score:" + score);
    }

    private static String[] extractResponses(String round) {
        return round.split(" ");
    }

    private static int roundOneMechanism(String[] responses) {
        RPS opponent = opponentChoice(responses[0]);
        RPS player;
        switch (responses[1]) {
            case "X":
                player = RPS.ROCK;
                break;
            case "Y":
                player = RPS.PAPER;
                break;
            case "Z":
                player = RPS.SCISSORS;
                break;
            default:
                throw new RuntimeException("Bad player");
        }
        return calculateRoundScore(opponent, player);
    }

    private static int roundTwoMechanism(String[] responses) {
        RPS opponent = opponentChoice(responses[0]);
        RPS player = roundTwoPlayerMechanic(opponent, responses[1]);
        return calculateRoundScore(opponent, player);
    }

    private static RPS roundTwoPlayerMechanic(RPS opponent, String strategy) {
        final String LOSE = "X"; final String DRAW = "Y"; final String WIN = "Z";
        switch (strategy) {
            case LOSE:
                switch (opponent) {
                    case ROCK: return RPS.SCISSORS;
                    case PAPER: return RPS.ROCK;
                    case SCISSORS: return RPS.PAPER;
                }
            case DRAW:
                return opponent;
            case WIN:
                switch (opponent) {
                    case ROCK: return RPS.PAPER;
                    case PAPER: return RPS.SCISSORS;
                    case SCISSORS: return RPS.ROCK;
                }
            default:
                throw new RuntimeException("Unexpected strategy");
        }
    }

    private static RPS opponentChoice(String opponentText) {
        switch (opponentText) {
            case "A":
                return RPS.ROCK;
            case "B":
                return RPS.PAPER;
            case "C":
                return RPS.SCISSORS;
            default:
                throw new RuntimeException("Bad opponent");
        }
    }

    private static int calculateRoundScore(RPS opponent, RPS player) {
        return playerChoiceScore(player) + matchupScore(opponent, player);
    }

    private static int playerChoiceScore(RPS player) {
        switch (player) {
            case ROCK:
                return 1;
            case PAPER:
                return 2;
            case SCISSORS:
                return 3;
            default:
                throw new RuntimeException("Unknown response");
        }
    }

    private static int matchupScore(RPS opponent, RPS player) {
        switch (opponent) {
            case ROCK:
                switch (player) {
                    case ROCK:
                        return DRAW;
                    case PAPER:
                        return WIN;
                    case SCISSORS:
                        return LOSE;
                }
                throw new RuntimeException("Unknown response");
            case PAPER:
                switch (player) {
                    case ROCK:
                        return LOSE;
                    case PAPER:
                        return DRAW;
                    case SCISSORS:
                        return WIN;
                }
                throw new RuntimeException("Unknown response");
            case SCISSORS:
                switch (player) {
                    case ROCK:
                        return WIN;
                    case PAPER:
                        return LOSE;
                    case SCISSORS:
                        return DRAW;
                }
                throw new RuntimeException("Unknown response");
        }
        throw new RuntimeException("Unknown response");
    }
}
