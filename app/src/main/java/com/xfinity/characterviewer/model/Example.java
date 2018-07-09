
package com.xfinity.characterviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("RelatedTopics")
    @Expose
    private List<RelatedTopic> relatedTopics = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Example() {
    }


    public Example(List<RelatedTopic> relatedTopics) {
        super();
        this.relatedTopics = relatedTopics;
    }

    public List<RelatedTopic> getRelatedTopics() {
        return relatedTopics;
    }


}
