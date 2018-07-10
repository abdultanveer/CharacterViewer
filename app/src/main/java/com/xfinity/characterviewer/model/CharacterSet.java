
package com.xfinity.characterviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CharacterSet contains all the character information from server.
 */
public class CharacterSet {
    @SerializedName("RelatedTopics")
    @Expose
    private List<ShowCharacter> mShowCharacters = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CharacterSet() {
    }


    /**
     * this is a constructor
     * @param showCharacters character list
     */
    public CharacterSet(List<ShowCharacter> showCharacters) {
        super();
        this.mShowCharacters = showCharacters;
    }

    /**
     *
     * @return a list of show characters
     */
    public List<ShowCharacter> getShowCharacters() {
        return mShowCharacters;
    }


}
