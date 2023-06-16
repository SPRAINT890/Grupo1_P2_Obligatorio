package Entities;

public class Driver {
    String name;
    String lastName;
    Integer menciones = 0;

    public Driver(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getMenciones() {
        return menciones;
    }

    public void setMenciones(Integer menciones) {
        this.menciones = menciones;
    }
}
