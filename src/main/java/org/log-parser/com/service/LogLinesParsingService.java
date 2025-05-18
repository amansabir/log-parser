package org.iptracker.com.service;

import org.iptracker.com.model.LogIndex;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.iptracker.com.model.LogIndex.IP;
import static org.iptracker.com.model.LogIndex.URL;

public class LogLinesParsingService {
    public Set<String> extractUniqueIPs(Stream<String> logLinesAsStream){
        return extractLogIndexAsStream(logLinesAsStream, IP).collect(Collectors.toSet());
    }

    public List<String> extractTopThreeVisitedURLs(Stream<String> logLinesAsStream) {
        return getTopMostFrequentElements(extractLogIndexAsStream(logLinesAsStream, URL), 3);
    }

    public List<String> extractTopThreeActiveIPs(Stream<String> logLinesAsStream) {
        return getTopMostFrequentElements(extractLogIndexAsStream(logLinesAsStream, IP),3);
    }

    private List<String> getTopMostFrequentElements(Stream<String> elementsStream, int frequency) {
        return elementsStream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(frequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private  Stream<String> extractLogIndexAsStream(Stream<String> logLinesAsStream, LogIndex logIndex)  {
        return logLinesAsStream.map(line -> line.split(" ")[logIndex.getIndex()]);
    }
}
