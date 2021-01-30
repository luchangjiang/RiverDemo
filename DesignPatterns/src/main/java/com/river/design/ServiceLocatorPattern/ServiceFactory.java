package com.river.design.ServiceLocatorPattern;

import com.river.design.ServiceLocatorPattern.service.Service1;
import com.river.design.ServiceLocatorPattern.service.Service2;

public class ServiceFactory {
    public Object build(String jndiName) {
        if (jndiName.equalsIgnoreCase("SERVICE1")) {
            System.out.println("Looking up and creating a new Service1 object");
            return new Service1();
        } else if (jndiName.equalsIgnoreCase("SERVICE2")) {
            System.out.println("Looking up and creating a new Service2 object");
            return new Service2();
        }
        return null;
    }
}