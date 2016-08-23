package com.lpf.tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lpf.tools.db.DBManager;
import com.lpf.tools.db.entity.TestUser;

import java.util.List;

public class TestDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBManager dbManager = DBManager.getInstance(TestDBActivity.this);

        //删除表内容
        dbManager.deleteTableUser();

        //添加数据
        for (int i = 10; i < 20; i++) {
            TestUser TestUser = new TestUser();
            TestUser.setId(i);
            TestUser.setAge(i * 3);
            TestUser.setName("第" + i + "用户");
            dbManager.insertUser(TestUser);
        }

        //查询数据
        List<TestUser> TestUserList = dbManager.queryDataList();
        for (TestUser TestUser : TestUserList) {
            Log.e("TAG", "before-->" + TestUser.getId() + "---name:" + TestUser.getName() + "--age:" + TestUser.getAge());
            if (TestUser.getId() == 18) {
//                dbManager.deleteUser(TestUser);
                dbManager.deleteUserById(18);
            }

            //更新数据
            if (TestUser.getId() == 3) {
                TestUser.setAge(10);
                dbManager.updateUser(TestUser);
            }
        }

        //打印列表中数据
        TestUserList = dbManager.queryDataList();
        for (TestUser TestUser : TestUserList) {
            Log.e("TAG", "after--->" + TestUser.getId() + "---name:" + TestUser.getName() + "--age:" + TestUser.getAge());
        }

        //
        List<TestUser> TestUserListWhere = dbManager.queryDataList(50);
        for (TestUser TestUser : TestUserListWhere) {
            Log.e("TAG", "search-->" + TestUser.getId() + "---name:" + TestUser.getName() + "--age:" + TestUser.getAge());
        }
    }
}
