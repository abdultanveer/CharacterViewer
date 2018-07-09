
package com.xfinity.characterviewer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Icon implements Serializable{


    @SerializedName("URL")
    @Expose
    private String uRL;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Icon() {
    }

    /**
     * 
     * @param uRL
     */
    public Icon(String uRL) {
        super();
        this.uRL = uRL;
    }

    public String getURL() {
        return uRL;
    }


}
