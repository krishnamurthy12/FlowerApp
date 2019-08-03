package com.krish.flowerapp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Customers.class,Vendors.class}, version = 1)
public abstract class FlowerRoomDatabase extends RoomDatabase {

    public abstract CustomersDAO customersDAO();

    private static volatile FlowerRoomDatabase flowerRoomDatabase;

    public static FlowerRoomDatabase getDatabase(final Context context) {
        if (flowerRoomDatabase == null) {
            synchronized (FlowerRoomDatabase.class) {
                if (flowerRoomDatabase == null) {
                    flowerRoomDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            FlowerRoomDatabase.class, "flower_database")
                           /*
                           //Enable this block only when database version changes
                           //1,2 means changed version 1 from 2

                           .addMigrations(new Migration(1,2) {
                                @Override
                                public void migrate(@NonNull SupportSQLiteDatabase database) {

                                }
                            })*/
                            .build();
                }
            }
        }
        return flowerRoomDatabase;
    }
}
