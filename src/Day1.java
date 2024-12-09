package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("day1.txt"));

//        part1(bufferedReader);
        part2(bufferedReader);
    }

    private static void part1(BufferedReader bufferedReader) throws IOException {
        String read;
        List<Long> leftList = new ArrayList<>();
        List<Long> rightList = new ArrayList<>();
        while ((read = bufferedReader.readLine()) != null) {
            String[] options = read.split("   ");
            leftList.add(Long.parseLong(options[0]));
            rightList.add(Long.parseLong(options[1]));
        }

        Collections.sort(leftList);
        Collections.sort(rightList);

        List<Long> distance = new ArrayList<>();

        for (int i = 0; i < leftList.size(); i++) {
            distance.add(Math.abs(leftList.get(i) - rightList.get(i)));
        }

        System.out.println("Distance " + distance.stream().mapToLong(Long::longValue).sum());
    }

    private static void part2(BufferedReader bufferedReader) throws IOException {
        String read;
        List<Long> leftList = new ArrayList<>();
        List<Long> rightList = new ArrayList<>();

        while ((read = bufferedReader.readLine()) != null) {
            String[] options = read.split("   ");
            leftList.add(Long.parseLong(options[0]));
            rightList.add(Long.parseLong(options[1]));
        }

        Map<Long, Integer> qntsRightList = new HashMap<>();
        for (Long aLong : rightList)
            qntsRightList.compute(aLong, (k, qnt) -> (qnt == null ? 1 : qnt + 1));

        List<Long> distance = new ArrayList<>();
        for (Long aLong : leftList) {
            Integer value = qntsRightList.get(aLong);
            distance.add(aLong * (value == null ? 0 : value));
        }

        System.out.println("Distance " + distance.stream().mapToLong(Long::longValue).sum());
    }


}