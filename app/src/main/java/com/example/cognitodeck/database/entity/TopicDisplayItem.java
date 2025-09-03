package com.example.cognitodeck.database.entity;

public class TopicDisplayItem implements LibraryListItem{
    public enum PositionInGroup {
        FIRST, MIDDLE, LAST, SINGLE
    }

    private Topics topic;
    private PositionInGroup position;

    public TopicDisplayItem(Topics topic, PositionInGroup position) {
        this.topic = topic;
        this.position = position;
    }

    public Topics getTopic() {
        return topic;
    }

    public PositionInGroup getPosition() {
        return position;
    }
}
