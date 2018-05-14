package com.app.sorge.todolist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.ContactsContract;

import com.app.sorge.todolist.dao.PlanDao;
import com.app.sorge.todolist.domain.Plan;

/**
 * Created by stefa on 14/05/2018.
 */

@Database(entities={Plan.class},version=1)
public abstract class DatabasePlan extends RoomDatabase{
    private static  DatabasePlan INSTANCE;

    public abstract PlanDao planDao();

    public static DatabasePlan getDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(context,DatabasePlan.class,"plan.db").build();
        }
        return INSTANCE;
    }
}
