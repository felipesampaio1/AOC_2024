package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("day4.txt"));

        part1(bufferedReader);
        part2(bufferedReader);
    }

    private static void part1(BufferedReader bufferedReader) throws IOException {

        String read;

        AtomicInteger count = new AtomicInteger();
        Map<Integer, String> lines = new HashMap<>();
        Map<Integer, String> column = new HashMap<>();
        Map<Integer, String> diags = new HashMap<>();

        int linesNumber = 0;
        while ((read = bufferedReader.readLine()) != null) {
            lines.put(linesNumber++, read);

            for (int i = 0; i < read.length(); i++) {
                column.put(i, column.get(i) == null ? String.valueOf(read.charAt(i)) : column.get(i) + read.charAt(i));
            }
        }

        for (int i = 0; i < lines.get(0).length(); i++) {
            createDiags(diags, lines, i, 0, diags.size() + 1, true);
        }

        for (int j = 1; j < column.get(0).length(); j++) {
            createDiags(diags, lines, 0, j, diags.size() + 1, true);
        }

        for (int i = 0; i < lines.get(0).length(); i++) {
            createDiags(diags, lines, i, lines.get(0).length() - 1, diags.size() + 1, false);
        }

        for (int j = lines.get(0).length() - 2; j >= 0; j--) {
            createDiags(diags, lines, 0, j, diags.size() + 1, false);
        }


        lines.forEach((k, v) -> count.addAndGet(getCount(v)));
        column.forEach((k, v) -> count.addAndGet(getCount(v)));
        diags.forEach((k, v) -> count.addAndGet(getCount(v)));
    }

    private static void createDiags(Map<Integer, String> diags, Map<Integer, String> lines, Integer row, Integer col, int diagCount, boolean principal) {
        int n = lines.get(row).length();

        if (principal) {
            for (int i = 0; i < n; i++) {
                int r = row + i, c = col + i;
                if (r >= 0 && r < n && c >= 0 && c < n) {
                    diags.put(diagCount, diags.get(diagCount) == null ? String.valueOf(lines.get(r).charAt(c)) : diags.get(diagCount) + lines.get(r).charAt(c));
                }
            }

        } else {

            for (int i = 0; i < n; i++) {
                int r = row + i, c = col - i;
                if (r >= 0 && r < n && c >= 0 && c < n) {
                    diags.put(diagCount, diags.get(diagCount) == null ? String.valueOf(lines.get(r).charAt(c)) : diags.get(diagCount) + lines.get(r).charAt(c));
                }
            }
        }
    }

    private static int getCount(String read) {
        int count = 0;
        String xmas = "(?=(XMAS|SAMX))";
        Pattern pattern = Pattern.compile(xmas);
        Matcher matcher = pattern.matcher(read);

        while (matcher.find()) {
            count++;
        }

        return count;
    }

    private static void part2(BufferedReader bufferedReader) throws IOException {
    }
}