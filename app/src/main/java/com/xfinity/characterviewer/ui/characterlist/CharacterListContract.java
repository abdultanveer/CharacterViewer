package com.xfinity.characterviewer.ui.characterlist;

import com.xfinity.characterviewer.model.CharacterSet;

/**
 * CharacterListContract is the interface for View and Presenter
 */
public interface CharacterListContract {
    interface IView{
        void setCharacterAdapter(CharacterSet characterData);
    }
    interface IPresenter{
        void requestCharacterData();
    }
}
