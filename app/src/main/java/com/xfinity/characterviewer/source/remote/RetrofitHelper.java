package com.xfinity.characterviewer.source.remote;


import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

/**
 * RetrofitHelper is a singleton class provides ShowCharacter Service instance
 */

@Singleton
public class RetrofitHelper {
    private Retrofit retrofit;


    @Inject
    public RetrofitHelper(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    /**
     *
     * @return an instance of CharacterService
     */
    public CharacterService getCharacterService() {
        return retrofit.create(CharacterService.class);
    }
}
