package com.app.sorge.todolist.dependencyinjection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Insert;

import com.app.sorge.todolist.repository.RepositoryPlan;
import com.app.sorge.todolist.viewmodel.ListActivityViewModel;

import javax.inject.Inject;

/**
 * Created by stefa on 14/05/2018.
 */

public class ViewModelFactory implements ViewModelProvider.Factory {
    private RepositoryPlan repositoryPlan;

    @Inject
    public ViewModelFactory(RepositoryPlan repositoryPlan) {
        this.repositoryPlan = repositoryPlan;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if(modelClass.isAssignableFrom(ListActivityViewModel.class)){
            return (T) new ListActivityViewModel(repositoryPlan);
        }
        return null;
    }
}
