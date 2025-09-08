package com.example.cognitodeck.database.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TopicDisplayItem that = (TopicDisplayItem) o;
        return Objects.equals(topic, that.topic) && position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, position);
    }
}
