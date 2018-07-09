package com.xfinity.characterviewer.data.source.remote;


import com.xfinity.characterviewer.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @RetrofitHelper is a singleton class provides ShowCharacter Service instance
 */
public class RetrofitHelper {
    private static RetrofitHelper retrofitHelper = null;

    public static RetrofitHelper getRetrofitHelper(){
        if(retrofitHelper==null)
            retrofitHelper = new RetrofitHelper();
        return retrofitHelper;
    }

    /**
     *
     * @return an instance of CharacterService
     */
    public CharacterService getCharacterService() {
        Retrofit retrofit = new retrofit2.Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(CharacterService.class);
    }
}
