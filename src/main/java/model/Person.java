package model;

import serial.Database;

public class Person {
    int id;
    String name;
    String wohnort;

    public Person(int id, String name, String wohnort) {
        this.id = id;
        this.name = name;
        this.wohnort = wohnort;
    }

    public void save() throws PersonException{
        checkInput();
        Database.getInstance().save(this);
    }

    private void checkInput() throws PersonException{
        if(name==null){
            name="";
        }
        if(name.length()<5){
            throw new PersonException("Name Eingabe unzulässig!");
        }
        if (wohnort == null) {
            wohnort="";
        }
        if(wohnort.length()<5){
            throw new PersonException("Wohnort Eingabe unzulässig!");
        }
    }

    @Override
    public String toString() {
        return "ID: "+this.id+"   Name: "+this.name+"   Wohnort: "+this.wohnort;
    }
}
