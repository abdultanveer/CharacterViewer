
package com.xfinity.characterviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RelatedTopic implements Serializable{


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
    public RelatedTopic() {
    }

    /**
     * 
     * @param icon
     * @param text
     */
    public RelatedTopic(String text,  Icon icon) {
        super();
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

}
