package ru.progwards.java2.lessons.graph;

import java.awt.*;

public enum NodeStatus {
    UNKNOWN(Color.RED), DISCOVERED(Color.GRAY), PROCESSED(new Color(245, 245, 245));

    public Color color;

    NodeStatus(Color color) {
        this.color = color;
    }
}

