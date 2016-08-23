package com.lpf.tools.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.lpf.tools.db.autogenerate.DaoMaster;
import com.lpf.tools.db.autogenerate.DaoSession;
import com.lpf.tools.db.autogenerate.TestUserDao;
import com.lpf.tools.db.entity.TestUser;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/23.
 * Description:
 */
public class DBManager {

    private final String dbName = "test_db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context mContext;

    public DBManager(Context context) {
        this.mContext = context;
        openHelper = new DaoMaster.DevOpenHelper(mContext,dbName,null);
    }


    /**
     * 获取单例应用
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context){
        if(mInstance == null){
            synchronized (DBManager.class){
                if(mInstance == null){
                    mInstance = new DBManager(context);

                }
            }
        }
        return mInstance;
    }


    /**
     * 获取可读数据库
     * @return
     */
    private SQLiteDatabase getReadableDatabase(){
        if (openHelper == null){
            openHelper = new DaoMaster.DevOpenHelper(mContext,dbName,null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     * @return
     */
    private SQLiteDatabase getWritableDatabase(){
        if(openHelper == null){
            openHelper = new DaoMaster.DevOpenHelper(mContext,dbName,null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    //TestUser表 start
    /**
     * 添加一个新用户
     * @param user
     */
    public void insertUser(TestUser user){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession= daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();
        userDao.insert(user);
    }

    /**
     * 添加多个新用户
     * @param users
     */
    public void insertUserList(List<TestUser> users){
        if(users == null || users.isEmpty()){
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();
        userDao.insertInTx(users);
    }

    /**
     * 删除一个用户
     * @param user
     */
    public void deleteUser(TestUser user){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();
        userDao.delete(user);
    }

    public void deleteUserById(long id){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();
        userDao.deleteByKey(id);
    }

    /**
     * 更新一条数据
     * @param user
     */
    public void updateUser(TestUser user){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();
        userDao.update(user);
    }

    /**
     * 查询数据列表
     */
    public List<TestUser> queryDataList(){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();

        QueryBuilder<TestUser> qb = userDao.queryBuilder();
        List<TestUser> list = qb.list();
        return list;
    }

    /**
     * 查询数据列表
     * @param age 参数表
     * @return
     */
    public List<TestUser> queryDataList(int age){
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();

        QueryBuilder<TestUser> qb = userDao.queryBuilder();
        qb.where(
                TestUserDao.Properties.Age.gt(age))
                .orderAsc(TestUserDao.Properties.Age);
        List<TestUser> list = qb.list();
        return list;
    }

    /**
     * 删除User表中的数据
     * @return
     */
    public void deleteTableUser(){
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession= daoMaster.newSession();
        TestUserDao userDao = daoSession.getTestUserDao();
        userDao.deleteAll();
    }

    //TestUser表 end
}
