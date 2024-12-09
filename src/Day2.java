package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("day2.txt"));

//        part1(bufferedReader);
        part2(bufferedReader);
    }

    private static void part1(BufferedReader bufferedReader) throws IOException {
        String read;
        int safeLevel = 0;
        while ((read = bufferedReader.readLine()) != null) {
            String[] level = read.split(" ");
            boolean increasing = Integer.parseInt(level[0]) - Integer.parseInt(level[1]) > 0;
            boolean unsafe = false;
            for (int i = 0; i < level.length - 1; i++) {
                int levelDiff = Integer.parseInt(level[i]) - Integer.parseInt(level[i + 1]);
                boolean increasingNextLevels = levelDiff > 0;
                if (Math.abs(levelDiff) >= 1 && Math.abs(levelDiff) <= 3 && (increasing == increasingNextLevels)) {
                    unsafe = false;
                }
                else {
                    unsafe = true;
                    break;
                }
            }

            if (!unsafe)
                safeLevel++;
        }

        System.out.println("safe level: " + safeLevel);

    }

    private static void part2(BufferedReader bufferedReader) throws IOException {
        String read;
        int safeLevel = 0;
        while ((read = bufferedReader.readLine()) != null) {
            String[] level = read.split(" ");
            boolean increasing = Integer.parseInt(level[0]) - Integer.parseInt(level[1]) > 0;
            boolean unsafe = false;
            int unsafeCount = 0;
            for (int i = 0; i < level.length - 1; i++) {
                int levelDiff = Integer.parseInt(level[i]) - Integer.parseInt(level[i + 1]);
                boolean increasingNextLevels = levelDiff > 0;
                if (Math.abs(levelDiff) >= 1 && Math.abs(levelDiff) <= 3 && (increasing == increasingNextLevels)) {
                    unsafe = false;
                } else if (unsafeCount < 1) {
                    unsafe = false;
                    unsafeCount++;
                } else {
                    unsafe = true;
                    break;
                }
            }

            if (!unsafe)
                safeLevel++;

        }

        System.out.println("safe level: " + safeLevel);
    }
}