package com.app.sorge.todolist.dependencyinjection;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stefa on 14/05/2018.
 */

@Module
public class ApplicationModule {
    private PlanApplication application;

    public ApplicationModule(PlanApplication application) {
        this.application = application;
    }

    @Provides
    PlanApplication providePlanApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }
}
