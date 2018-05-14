package com.app.sorge.todolist.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.app.sorge.todolist.domain.Plan;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by stefa on 14/05/2018.
 */

@Dao
public interface PlanDao {

    @Query(value = "SELECT * FROM `plan`")
    LiveData<List<Plan>> getPlans();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPlan(Plan plan);

    @Delete
    void deletePlan(Plan plan);
}
