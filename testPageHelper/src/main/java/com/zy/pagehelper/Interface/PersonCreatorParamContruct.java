package com.zy.pagehelper.Interface;

import com.zy.pagehelper.model.Person;

public interface PersonCreatorParamContruct {
    /*
     *有参构造器
     */
    Person getPerson(String firstName, String lastName, String job,
                   String gender, int age, int salary);
}
