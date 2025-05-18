package org.logparser.com;

import org.logparser.com.service.LogFileService;
import org.logparser.com.service.LogLinesParsingService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the log file path: ");
        String filePath = scanner.nextLine();
        LogFileService logFileService = new LogFileService(Path.of(filePath), new LogLinesParsingService());
        System.out.println(logFileService.extractTopThreeActiveIPs());
        System.out.println(logFileService.extractTopThreeVisitedURLs());
        System.out.println(logFileService.extractUniqueIPs());
    }
}