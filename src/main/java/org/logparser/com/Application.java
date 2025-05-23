package org.logparser.com;

import org.logparser.com.service.LogFileService;
import org.logparser.com.service.LogLinesParsingService;

import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Logger;


public class Application {

    private static final Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the log file path: ");
        String filePath = scanner.nextLine();
        LogFileService logFileService = new LogFileService(Path.of(filePath), new LogLinesParsingService());

        System.out.println("The number of unique IP addresses: " + logFileService.extractUniqueIPs().size());
        System.out.println("The top 3 most visited URLs: " + logFileService.extractTopThreeVisitedURLs());
        System.out.println("The top 3 most active IP addresses: " + logFileService.extractTopThreeActiveIPs());
    }
}