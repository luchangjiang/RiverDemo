package com.river.ErrorDemo;

import com.river.ErrorDemo.model.User;

<<<<<<< HEAD
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
=======
import java.util.ArrayList;
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
import java.util.List;

/**
 * @program: RiverDemo
 * @description:  模拟 NoClassDefFoundError
 * @author: River
 * @create: 2019-10-20 21:17
 **/
public class ErrorDemoApplication {
<<<<<<< HEAD
    public static void main(String args[]) throws ParseException {

        /*List<User> users = new ArrayList<User>(2);
=======
    public static void main(String args[]){

        List<User> users = new ArrayList<User>(2);
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c

        for(int i=0; i<2; i++){
            try{
                users.add(new User(String.valueOf(i))); //will throw NoClassDefFoundError
                System.out.println("user added");
            }catch(Throwable t){
                t.printStackTrace();
            }
<<<<<<< HEAD
        }*/

        compareDate();
    }

    private static void compareDate() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date start = df.parse("2020-11-24 22:59:36");
        int i = start.compareTo(new Date());
        System.out.println(i);
=======
        }
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c
    }
}
