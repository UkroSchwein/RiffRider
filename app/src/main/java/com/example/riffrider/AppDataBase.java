package com.example.riffrider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Song.class, FavoriteSong.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract SongDao getSongDao();


    public abstract FavoriteDao getFavoriteDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase buildDatabase(Context context) {
        return Room.databaseBuilder(context, AppDataBase.class, "/database/file.db")
                .build();
    }


}


