import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class ScheduleReader {
    private static final int DAYS_IN_WEEK = 7;
    private static final int HALF_HOURS_IN_DAY = 48;

    private static final List<String> weekdays = List.of(
            "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
    );

    private ScheduleReader() {
    }

    private boolean[][] readFile(String filename) {
        boolean[][] avail = new boolean[HALF_HOURS_IN_DAY][DAYS_IN_WEEK];
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                String weekday = parts[0].trim();
                int col = getWeekdayIndex(weekday);

                String time = parts[1].trim();
                String[] slots = time.split(", ");
                for (String slot : slots) {
                    String[] startEndTimes = slot.split("-");
                    String startTime = startEndTimes[0];
                    String endTime = startEndTimes[1];
                    int start = getTimeIndex(startTime);
                    int end = getTimeIndex(endTime);
                    for (int row = start; row < end; row++) {
                        avail[row][col] = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
        return avail;
    }

    private int getWeekdayIndex(String weekday) {
        return weekdays.indexOf(weekday);
    }

    private int getTimeIndex(String time) {
        String[] hourMin = time.split(":");
        int hour = Integer.parseInt(hourMin[0]) * 2;
        int min = 0;
        if (hourMin.length > 1) {
            String minutes = hourMin[1];
            if (minutes.equals("30")) {
                min = 1;
            }
        }
        return hour + min;
    }

    public List<String> findBestTimes(List<boolean[][]> schedules, int numTimes) {
        List<String> bestTimes = new ArrayList<>();
        int[][] countArray = new int[HALF_HOURS_IN_DAY][DAYS_IN_WEEK];
        for (boolean[][] schedule : schedules) {
            for (int row = 0; row < HALF_HOURS_IN_DAY; row++) {
                for (int col = 0; col < DAYS_IN_WEEK; col++) {
                    if (schedule[row][col]) {
                        countArray[row][col]++;
                    }
                }
            }
        }

        List<TimeWithCount> timeWithCountsList = new ArrayList<>();
        for (int row = 0; row < HALF_HOURS_IN_DAY; row++) {
            for (int col = 0; col < DAYS_IN_WEEK; col++) {
                timeWithCountsList.add(new TimeWithCount(row, col, countArray[row][col]));
            }
        }

        Collections.sort(timeWithCountsList);
        Collections.reverse(timeWithCountsList);

        List<TimeWithCount> bestTimesWithCounts = timeWithCountsList.subList(0, numTimes);

        for (TimeWithCount bestTime : bestTimesWithCounts) {
            bestTimes.add(bestTime.toString());
        }

        System.out.println(bestTimes);
        return bestTimes;
    }

    class TimeWithCount implements Comparable<TimeWithCount> {
        private int col;
        private int row;
        private int count;

        public TimeWithCount(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            String weekday = weekdays.get(col);
            int hour = row / 2;
            int min = (row % 2) == 0 ? 0 : 30;
            return String.format("%s %02d:%02d", weekday, hour, min);
        }

        @Override
        public int compareTo(TimeWithCount o) {
            return getCount() - o.getCount();
        }
    }

    public static void main(String[] args) {
        String filename = "resources/hui_wang.txt";
        ScheduleReader scheduleReader = new ScheduleReader();
        boolean[][] avail = scheduleReader.readFile(filename);

        List<boolean[][]> schedules = new ArrayList<>();
        schedules.add(avail);
        scheduleReader.findBestTimes(schedules, 2);
    }
}