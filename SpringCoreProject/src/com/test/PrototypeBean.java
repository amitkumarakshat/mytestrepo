package com.test;

public class PrototypeBean {
    private String message;
    public PrototypeBean()
    {
           System.out.println("Prototype Bean Instantiated !!");
    }      
    public void setMessage(String message){
           this.message  = message;
    }
    public String  getMessage(){
           return this.message;
    }      
    public PrototypeBean getPrototypeBean()
    {
           return this;
    }
}
