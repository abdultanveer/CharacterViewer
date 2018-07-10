package com.xfinity.characterviewer.ui.characterlist;

import com.xfinity.characterviewer.data.source.CharacterRepository;
import com.xfinity.characterviewer.model.CharacterSet;

/**
 * CharacterListPresenter is the presenter responsible for character list fragment.
 */
public class CharacterListPresenter implements CharacterListContract.IPresenter, CharacterRepository.CharacterDataListener{
    private CharacterListFragment listFragmentRef;
    private CharacterRepository mCharacterRepository;

    /**
     *
     * @param listFragmentRef a reference to ListFragment
     * @param characterRepository an instance of CharacterRepository
     */
    public CharacterListPresenter(CharacterListFragment listFragmentRef, CharacterRepository characterRepository) {
        this.listFragmentRef = listFragmentRef;
        mCharacterRepository = characterRepository;
    }

    @Override
    public void requestCharacterData() {
        mCharacterRepository.requestCharacterData(this);

    }

    @Override
    public void handleCharacterResponse(CharacterSet characterData) {
        listFragmentRef.setAdapter(characterData);
        this.listFragmentRef.setAdapter(characterData);
    }

    @Override
    public void handleError() {

    }

}
