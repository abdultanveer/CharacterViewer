package com.xfinity.characterviewer.ui.characterlist;

import com.xfinity.characterviewer.model.CharacterSet;
import com.xfinity.characterviewer.source.CharacterRepository;

import javax.inject.Inject;

/**
 * CharacterListPresenter is the presenter responsible for character list fragment.
 */
public class CharacterListPresenter implements CharacterListContract.IPresenter, CharacterRepository.CharacterDataListener{
    private CharacterListContract.IView listFragmentRef;
    private CharacterRepository mCharacterRepository;

    /**
     *
     * @param listFragmentRef a reference to ListFragment
     * @param characterRepository an instance of CharacterRepository
     */
    @Inject
    public CharacterListPresenter(CharacterListContract.IView listFragmentRef, CharacterRepository characterRepository) {
        this.listFragmentRef = listFragmentRef;
        mCharacterRepository = characterRepository;
    }

    public CharacterListPresenter(CharacterListFragment listFragmentRef) {
        this.listFragmentRef = listFragmentRef;
    }

    @Override
    public void requestCharacterData() {
        mCharacterRepository.requestCharacterData(this);

    }

    @Override
    public void handleCharacterResponse(CharacterSet characterData) {
        this.listFragmentRef.setCharacterAdapter(characterData);
    }

    @Override
    public void handleError() {

    }

}
