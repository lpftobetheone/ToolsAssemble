package com.lpf.tools.db.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * liupf1213@gmail.com
 * Created by liupf5 on 16/8/23.
 * Description:
 */
@Entity
public class TestUser {
    @Id
    private long id;
    private String name;
    private int age;

    @Generated(hash = 838720705)
    public TestUser(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 925009630)
    public TestUser() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
