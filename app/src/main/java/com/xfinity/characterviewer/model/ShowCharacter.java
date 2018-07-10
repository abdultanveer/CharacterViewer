package com.xfinity.characterviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * ShowCharacter gives the information of characters in Simpsons show or the GTO shows
 */
public class ShowCharacter implements Serializable{


    @SerializedName("Text")
    @Expose
    private String text;

    @SerializedName("Icon")
    @Expose
    private Icon icon;

    public boolean isUseGrid() {
        return useGrid;
    }

    public void setUseGrid(boolean useGrid) {
        this.useGrid = useGrid;
    }

    private boolean useGrid = false;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ShowCharacter() {
    }

    /**
     * 
     * @param icon Icon object of the showcharater
     * @param text text contains title and description
     */
    public ShowCharacter(String text, Icon icon) {
        super();
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    /**
     *
     * @param text description contains title and detail
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     *
     * @return an Icon object
     */
    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

}
