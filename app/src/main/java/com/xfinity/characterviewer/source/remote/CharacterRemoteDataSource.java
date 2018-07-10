package com.xfinity.characterviewer.source.remote;


import com.xfinity.characterviewer.source.CharacterDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * CharacterRemoteDataSource provides CharacterService by accessing RetrofitHelper
 */

@Singleton
public class CharacterRemoteDataSource implements CharacterDataSource.RemoteCharacterCallback {
    private RetrofitHelper mRetrofitHelper;

    @Inject
    public CharacterRemoteDataSource(RetrofitHelper retrofitHelper) {
        this.mRetrofitHelper = retrofitHelper;
    }

    /**
     * @return an instance of CharacterService
     */
    public CharacterService getCharacterService() {
        return mRetrofitHelper.getCharacterService();
    }

}
