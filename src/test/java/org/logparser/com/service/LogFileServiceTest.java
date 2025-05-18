package org.logparser.com.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LogFileServiceTest {

    private Path logPath;

    private Stream<String> mockLines;

    @Mock
    private LogLinesParsingService mockLogLinesParsingService;

    private LogFileService logFileService;

    @BeforeEach
    void setUp() {
        logPath = Paths.get("test.txt");
        mockLines = Stream.of("line1", "line2", "line3");
        logFileService = new LogFileService(logPath, mockLogLinesParsingService);
    }

    @Test
    void givenLogPathShouldReturnUniqueIPs() throws IOException {
        HashSet<String> expectedSet = new HashSet<>(List.of("177.71.128.21", "168.41.191.40", "168.41.191.41", "177.71.128.21", "168.41.191.9"));

        try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
            when(Files.lines(logPath)).thenReturn(mockLines);

            when(mockLogLinesParsingService.extractUniqueIPs(Files.lines(logPath))).thenReturn(expectedSet);

            assertEquals(expectedSet, logFileService.extractUniqueIPs());
        }
    }

    @Test
    void givenLogPathShouldReturnThreeMostVisitedUrls() throws IOException {
        List<String> expectedList = Arrays.asList("/intranet-analytics/", "http://example.net/blog/category/meta/", "http://example.net/faq/");

        try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
            when(Files.lines(logPath)).thenReturn(mockLines);

            when(mockLogLinesParsingService.extractTopThreeVisitedURLs(Files.lines(logPath))).thenReturn(expectedList);

            assertEquals(expectedList, logFileService.extractTopThreeVisitedURLs());
        }
    }

    @Test
    void givenLogPathShouldReturnThreeMostActiveIPs() throws IOException {
        List<String> expectedList = Arrays.asList("168.41.191.40", "168.41.191.41", "177.71.128.21");

        try (MockedStatic<Files> filesMock = mockStatic(Files.class)) {
            when(Files.lines(logPath)).thenReturn(mockLines);

            when(mockLogLinesParsingService.extractTopThreeActiveIPs(Files.lines(logPath))).thenReturn(expectedList);

            assertEquals(expectedList, logFileService.extractTopThreeActiveIPs());
        }
    }
}