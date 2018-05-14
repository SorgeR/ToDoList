package com.app.sorge.todolist.dependencyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;

import com.app.sorge.todolist.dao.PlanDao;
import com.app.sorge.todolist.database.DatabasePlan;
import com.app.sorge.todolist.repository.RepositoryPlan;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by stefa on 14/05/2018.
 */
@Module
public class PlanModule {
    private final DatabasePlan databasePlan;

    public PlanModule(Application application) {
        this.databasePlan = DatabasePlan.getDatabase(application);
    }

    @Provides
    @Singleton
    DatabasePlan provideDatabasePlan(Application application){
        return this.databasePlan;
    }

    @Provides
    @Singleton
    PlanDao  providePlanDao(DatabasePlan databasePlan){
        return databasePlan.planDao();
    }

    @Provides
    @Singleton
    RepositoryPlan provideRepositoryPlan(PlanDao planDao){
        return new RepositoryPlan(planDao);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(RepositoryPlan repositoryPlan){
        return new ViewModelFactory(repositoryPlan);
    }

}
