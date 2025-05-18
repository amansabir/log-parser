package org.iptracker.com.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class LogFileService {

    private static final Logger logger = Logger.getLogger(LogFileService.class.getName());

    private final Path logPath;

    private final org.iptracker.com.service.LogLinesParsingService logLinesParsingService;


    public LogFileService(Path logPath, org.iptracker.com.service.LogLinesParsingService logLinesParsingService) {
        this.logPath = logPath;
        this.logLinesParsingService = logLinesParsingService;
    }

    public Set<String> extractUniqueIPs()  {
        try {
            return logLinesParsingService.extractUniqueIPs(Files.lines(this.logPath));
        } catch (IOException e) {
            logger.warning("Error accessing log file");
            throw new RuntimeException(e);
        }
    }

    public List<String> extractTopThreeVisitedURLs()  {
        try {
            return logLinesParsingService.extractTopThreeVisitedURLs(Files.lines(this.logPath));
        } catch (IOException e) {
            logger.warning("Error accessing log file");
            throw new RuntimeException(e);
        }
    }

    public List<String> extractTopThreeActiveIPs() {
        try {
            return logLinesParsingService.extractTopThreeActiveIPs(Files.lines(this.logPath));
        } catch (IOException e) {
            logger.warning("Error accessing log file");
            throw new RuntimeException(e);
        }
    }

}
