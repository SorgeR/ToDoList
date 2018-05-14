package com.app.sorge.todolist.dependencyinjection;

import com.app.sorge.todolist.view.ListActivity;
import com.app.sorge.todolist.viewmodel.ListActivityViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by stefa on 14/05/2018.
 */
@Singleton
@Component(modules = {ApplicationModule.class,PlanModule.class})
public interface ApplicationComponent {

    void inject(PlanApplication exp);
    void inject(ListActivity exp);
}
