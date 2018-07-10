package com.xfinity.characterviewer;

import android.app.Application;
import android.content.Context;

import com.xfinity.characterviewer.di.component.AppComponent;
import com.xfinity.characterviewer.di.component.DaggerAppComponent;
import com.xfinity.characterviewer.di.module.AppModule;


public class CharacterViewerApp extends Application{

    private AppComponent appComponent;

    public static CharacterViewerApp get(Context context){
        return (CharacterViewerApp)context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


}
