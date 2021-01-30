package com.river.design.ObserverPattern.observer;

import com.river.design.ObserverPattern.Subject;

public abstract class Observer {
   protected Subject subject;
   public abstract void update();
}