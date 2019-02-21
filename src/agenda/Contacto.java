/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agenda;

/**
 *
 * @author cinthya
 */
public class Contacto {
    
    String nombre;
    String número;
    String email;

    public Contacto(String nombre, String número, String email) {
        this.nombre = nombre;
        this.número = número;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNúmero() {
        return número;
    }

    public void setNúmero(String número) {
        this.número = número;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
