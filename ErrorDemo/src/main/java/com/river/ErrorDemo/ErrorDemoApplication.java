package com.river.ErrorDemo;

import com.river.ErrorDemo.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: RiverDemo
 * @description:  模拟 NoClassDefFoundError
 * @author: River
 * @create: 2019-10-20 21:17
 **/
public class ErrorDemoApplication {
    public static void main(String args[]){

        List<User> users = new ArrayList<User>(2);

        for(int i=0; i<2; i++){
            try{
                users.add(new User(String.valueOf(i))); //will throw NoClassDefFoundError
                System.out.println("user added");
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
}
