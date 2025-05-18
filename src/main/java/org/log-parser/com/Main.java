package org.iptracker.com;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            print(extractIPsAsStream());
            print(extractUrlsAsStream());
            extractUniqueIPs();
            extractUniqueUrls();
            getTopNElements(extractIPsAsStream(), 3);
            getTopNElements(extractUrlsAsStream(), 3);



        } catch (IOException e) {
            logger.info("Error reading file" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static List<String> getTopNElements(Stream<String> stringStream, int n) throws IOException {
        List<String> topNElements = stringStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        logger.info("Top N elements" +topNElements);

        return null;
    }


    private static Stream<String> extractUniqueUrls() throws IOException {
        logger.info("Unique URLS");
        logger.info(extractUrlsAsStream().collect(Collectors.toSet()).toString());
        return null;
    }
    private static Stream<String> extractUniqueIPs() throws IOException {
        logger.info("Unique IPs");
        logger.info(extractIPsAsStream().collect(Collectors.toSet()).toString());
        return null;
    }

    private static void print(Stream<String> stringStream) throws IOException {
        logger.info("List");
        logger.info(stringStream.collect(Collectors.toList()).toString());
    }

    private static Stream<String> extractUrlsAsStream() throws IOException {
        Path logPath = Path.of("/Users/amansabir/Desktop/projects/personal-workspace/IPTracker/src/main/resources/programming-task-example-data.log");
        return Files.lines(logPath)
                .map(line -> line.split(" ")[6]);
    }
    private static Stream<String> extractIPsAsStream() throws IOException {
        return Files.lines(Path.of("/Users/amansabir/Desktop/projects/personal-workspace/IPTracker/src/main/resources/programming-task-example-data.log"))
                .map(line -> line.split(" ")[0]);
    }
}