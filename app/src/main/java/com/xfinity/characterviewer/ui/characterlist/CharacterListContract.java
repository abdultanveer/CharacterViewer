package com.xfinity.characterviewer.ui.characterlist;

import com.xfinity.characterviewer.model.CharacterSet;

/**
 * @CharacterListContract is the interface for View and Presenter
 */
public interface CharacterListContract {
    interface IView{
        void setAdapter(CharacterSet characterData);
    }
    interface IPresenter{
        void requestCharacterData();
    }
}
