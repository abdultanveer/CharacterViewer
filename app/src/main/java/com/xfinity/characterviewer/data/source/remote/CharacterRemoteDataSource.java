package com.xfinity.characterviewer.data.source.remote;

import com.xfinity.characterviewer.data.source.CharacterDataSource;

/**
 * @CharacerRemoteDataSource provides CharacterService by accessing RetrofitHelper
 */
public class CharacterRemoteDataSource implements CharacterDataSource.RemoteCharacterCallback {
    private RetrofitHelper mRetrofitHelper;
    public CharacterRemoteDataSource(RetrofitHelper retrofitHelper){
        this.mRetrofitHelper = retrofitHelper;
    }

    /**
     *
     * @return an instance of CharacterService
     */
    public CharacterService getCharacterService(){
        return mRetrofitHelper.getCharacterService();
    }

}
