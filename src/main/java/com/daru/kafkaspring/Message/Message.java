package com.daru.kafkaspring.Message;

public class Message {

    String header;
    String body;
    long timestamp;

    public Message(String header, String body, long timestamp) {
        this.header = header;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public long getTimestamp() {
        return timestamp;
    }

}
