package cn.datamining.dat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.blankj.utilcode.util.LogUtils;

import org.greenrobot.greendao.database.Database;

/**
 * database 升级工具
 * 复制原表至临时表， 然后创建新表 复制数据至新表中删除历史表
 */
public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogUtils.e(  "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
        //操作数据库的更新 有几个表升级都可以传入到下面
        MigrationHelper.getInstance().migrate(db,UserDao.class);
    }

}