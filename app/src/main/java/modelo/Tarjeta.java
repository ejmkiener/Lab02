package modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Eric on 28/09/2017.
 */

public class Tarjeta implements Serializable {
    private String nombre;
    private Integer numero;
    private Integer seguridad;
    private Date fechaVencimiento;

    public Tarjeta(){

    }

    public Tarjeta(String nombre, Integer numero, Integer seguridad, Date fechaVencimiento) {
        this.nombre = nombre;
        this.numero = numero;
        this.seguridad = seguridad;
        this.fechaVencimiento = fechaVencimiento;

    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getSeguridad() {
        return seguridad;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setSeguridad(Integer seguridad) {
        this.seguridad = seguridad;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
    @Override
    public String toString() {
        return "Tarjeta{" +
                "nombre='" + nombre + '\'' +
                ", numero=" + numero +
                ", seguridad=" + seguridad +
                ", fechaVencimiento=" + fechaVencimiento +
                '}';
    }

}
