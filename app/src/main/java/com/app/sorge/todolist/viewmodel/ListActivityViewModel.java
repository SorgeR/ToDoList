package com.app.sorge.todolist.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.persistence.room.Delete;
import android.os.AsyncTask;

import com.app.sorge.todolist.domain.Plan;
import com.app.sorge.todolist.repository.RepositoryPlan;

import java.util.List;

/**
 * Created by stefa on 14/05/2018.
 */

public class ListActivityViewModel extends ViewModel{

    private RepositoryPlan repositoryPlan;

    public ListActivityViewModel(RepositoryPlan repositoryPlan) {
        this.repositoryPlan = repositoryPlan;
    }

    public LiveData<List<Plan>> getPlans(){
        return this.repositoryPlan.getPlans();
    }

    public void addPlan(Plan plan){
        AddTask addTask=new AddTask();
        addTask.execute(plan);
    }

    public void deletePlan(Plan plan){
        DeleteTask deleteTask=new DeleteTask();
        deleteTask.execute(plan);
    }



    public class AddTask extends AsyncTask<Plan,Void,Void>{

        @Override
        protected Void doInBackground(Plan... plans) {
            repositoryPlan.addPlan(plans[0]);
            return null;
        }
    }

    public class DeleteTask extends AsyncTask<Plan,Void,Void>{

        @Override
        protected Void doInBackground(Plan... plans) {
            repositoryPlan.deletePlan(plans[0]);
            return null;
        }
    }
}
