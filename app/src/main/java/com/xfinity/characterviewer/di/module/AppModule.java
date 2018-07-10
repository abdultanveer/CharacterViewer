package com.xfinity.characterviewer.di.module;

import android.content.Context;

import com.xfinity.characterviewer.BuildConfig;
import com.xfinity.characterviewer.CharacterViewerApp;
import com.xfinity.characterviewer.di.qualifier.ApplicationContext;
import com.xfinity.characterviewer.di.scope.PerApplication;


import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class AppModule {

    private CharacterViewerApp mApplication;

    public AppModule(CharacterViewerApp mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationContext
    Context providesContext() {
        return mApplication;
    }


    @Provides
    @PerApplication
    Retrofit providesRetrofit() {
        return new Retrofit
                .Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

}
