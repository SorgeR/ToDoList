package com.app.sorge.todolist.dependencyinjection;

import android.app.Application;

/**
 * Created by stefa on 14/05/2018.
 */

public class PlanApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent=DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .planModule(new PlanModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent(){
        return  applicationComponent;
    }

}
