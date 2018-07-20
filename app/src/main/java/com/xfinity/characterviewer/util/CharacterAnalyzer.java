package com.xfinity.characterviewer.util;

import java.util.ArrayList;
import java.util.List;

public class CharacterAnalyzer {
    /**
     *
     * @param inputTx returned text containing character title and detail, separated with " - ", some input does not
     *                have a separated -, treat the whole thing as title and description here
     * @return a list with title and description contained
     */
    public static List<String> findTitleDes(String inputTx) {
        List<String> titleDes = new ArrayList<>();
        int quoteIdx = inputTx.indexOf(" - ");
        // no title found
        if (quoteIdx < 0) {
            titleDes.add(inputTx);
            titleDes.add(inputTx);
        } else {
            titleDes.add(inputTx.substring(0, quoteIdx));
            titleDes.add(inputTx.substring(quoteIdx + 3));
        }
        return titleDes;
    }
}
