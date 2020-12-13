package com.search;

import java.util.Objects;


public class Hit {

    private float score;

    private Document doc;

    public Hit(float score, Document doc) {
        this.doc = Objects.requireNonNull(doc);
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public Document getDoc() {
        return doc;
    }

    @Override
    public String toString() {
        return "com.search.Hit{" +
            "score=" + score +
            ", doc=" + doc +
            '}';
    }
}
