package com.app.sorge.todolist.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;

import com.app.sorge.todolist.dao.PlanDao;
import com.app.sorge.todolist.domain.Plan;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by stefa on 14/05/2018.
 */

public class RepositoryPlan {
    private PlanDao planDao;

    @Inject
    public RepositoryPlan(PlanDao planDao) {
        this.planDao = planDao;
    }

    public void addPlan(Plan plan){
        this.planDao.addPlan(plan);
    }

    public void deletePlan(Plan plan){
        this.planDao.deletePlan((plan));
    }

    public LiveData<List<Plan>> getPlans(){
        return this.planDao.getPlans();
    }
}
