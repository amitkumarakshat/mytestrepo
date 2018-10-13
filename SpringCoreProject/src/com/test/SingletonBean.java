package com.test;

public abstract class SingletonBean {
    public SingletonBean()
    {
           System.out.println("Singleton Bean Instantiated !!");
    }
    public abstract PrototypeBean getPrototypeBean();      
}
