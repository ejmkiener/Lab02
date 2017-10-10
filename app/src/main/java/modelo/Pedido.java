package modelo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Eric on 28/09/2017.
 */

public class Pedido implements Serializable {
    private String nombreCliente;
    private String email;
    private String nombre;
    private Double costo;
    private Boolean esDelivery;
    private String horaEntrega;
    private ArrayList<Utils.ElementoMenu> bebida;
    private ArrayList<Utils.ElementoMenu> plato;
    private ArrayList<Utils.ElementoMenu> postre;

    public Pedido(){
        bebida=new ArrayList<Utils.ElementoMenu>();
        plato=new ArrayList<Utils.ElementoMenu>();
        postre=new ArrayList<Utils.ElementoMenu>();
        costo=0d;
    }

    public Pedido(String nombreCliente, String email, String nombre, Double costo, Boolean esDelivery, String horaEntrega) {
        this.nombreCliente = nombreCliente;
        this.email = email;
        this.nombre = nombre;
        this.costo = costo;
        this.esDelivery = esDelivery;
        this.horaEntrega = horaEntrega;
        bebida=new ArrayList<Utils.ElementoMenu>();
        plato=new ArrayList<Utils.ElementoMenu>();
        postre=new ArrayList<Utils.ElementoMenu>();

    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setEsDelivery(Boolean esDelivery) {
        this.esDelivery = esDelivery;
    }

    public void setHoraEntrega(String horaEntrega) {
        this.horaEntrega = horaEntrega;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public Boolean getEsDelivery() {
        return esDelivery;
    }

    public String getHoraEntrega() {
        return horaEntrega;
    }

    public void AddBebida(Utils.ElementoMenu b){
        bebida.add(b);
    }

    public void AddPlato(Utils.ElementoMenu p){
        plato.add(p);
    }

    public void AddPostre(Utils.ElementoMenu p){
        postre.add(p);
    }

    public ArrayList<Utils.ElementoMenu> getBebida() {
        return bebida;
    }

    public void setBebida(ArrayList<Utils.ElementoMenu> bebida) {
        this.bebida = bebida;
    }

    public ArrayList<Utils.ElementoMenu> getPostre() {
        return postre;
    }

    public void setPostre(ArrayList<Utils.ElementoMenu> postre) {
        this.postre = postre;
    }

    public ArrayList<Utils.ElementoMenu> getPlato() {
        return plato;
    }

    public void setPlato(ArrayList<Utils.ElementoMenu> plato) {
        this.plato = plato;
    }

    public Double CalcularCosto(){
        costo=0d;
        for(int i=0;i<plato.size();i++)
            costo+=plato.get(i).getPrecio();

        for(int i=0;i<bebida.size();i++)
            costo+=bebida.get(i).getPrecio();

        for(int i=0;i<postre.size();i++)
            costo+=postre.get(i).getPrecio();

        return costo;
    }

    @Override
    public String toString() {
        return "Informacion Pedido\n" +
                "nombre:'" + nombreCliente + '\n' +
                ", email: " + email + '\n' +
                ", costo: " + costo + '\n' +
                ", Delivery: " + esDelivery +'\n'+
                ", Hora Entrega: " + horaEntrega;
    }
}
