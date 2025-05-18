package org.iptracker.com;

import org.iptracker.com.model.LogIndex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogIndexTest {

    @Test
    void getIndex() {
        assertEquals(6, LogIndex.URL.getIndex());
        assertEquals(0, LogIndex.IP.getIndex());
    }
}