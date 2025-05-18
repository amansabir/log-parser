package org.logparser.com.model;

public enum LogIndex {
    IP(0), URL(6);

    private final Integer index;
    LogIndex(Integer index) {
        this.index = index;
    }

    public Integer getIndex(){
        return this.index;
    }
}
