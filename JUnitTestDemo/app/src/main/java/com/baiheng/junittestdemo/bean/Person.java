package com.baiheng.junittestdemo.bean;

public class Person {
    private String name;
    private int sex;
    private int age;
    private String food;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge(){
        return 11;
    }

    public String getFood(String food) {
        return food;
    }
}
