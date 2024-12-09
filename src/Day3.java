package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("day3.txt"));

//        part1(bufferedReader);
        part2(bufferedReader);
    }

    private static void part1(BufferedReader bufferedReader) throws IOException {
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)";
        String read;
        Long value = 0L;
        while ((read = bufferedReader.readLine()) != null) {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(read);

            while (matcher.find()) {
                String[] mul = matcher.group().substring(4, matcher.group().length() - 1).split(",");
                value += Long.parseLong(mul[0]) * Long.parseLong(mul[1]);

            }
                System.out.println(value);
        }
    }

    private static void part2(BufferedReader bufferedReader) throws IOException {
        String regex = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)";
        String read;
        Long value = 0L;
        boolean nextDisabled = false;
        while ((read = bufferedReader.readLine()) != null) {

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(read);

            while (matcher.find()) {
                String text = matcher.group();
                if (text.equals("don't()")) {
                    nextDisabled = true;
                    continue;
                } else if (text.equals("do()")){
                    nextDisabled = false;
                    continue;
                }

                if (!nextDisabled) {
                    String[] mul = matcher.group().substring(4, matcher.group().length() - 1).split(",");
                    value += Long.parseLong(mul[0]) * Long.parseLong(mul[1]);
                }

            }
        }
        System.out.println(value);
    }
}