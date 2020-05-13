package com.liuchuanzheng.study_room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * @author 刘传政
 * @date 2020/5/13 15:23
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}