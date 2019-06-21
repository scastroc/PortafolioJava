/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

/**
 *
 * @author Sebastian
 */
public class Proveedor {
    
    private String proRut;
    private String proDv;
    private String nombre;
    private String email;
    private String telefono;
    private Direccion direccion;
    private Usuario usuId;
    private char estado;

    public Proveedor() {
        
        this.proRut = "";
        this.proDv = "";
        this.nombre = "";
        this.email = "";
        this.telefono = "";        
        this.estado = 0;
        
    }
      

    public String getProRut() {
        return proRut;
    }

    public void setProRut(String proRut) {
        this.proRut = proRut;
    }

    public String getProDv() {
        return proDv;
    }

    public void setProDv(String proDv) {
        this.proDv = proDv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuario usuId) {
        this.usuId = usuId;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char Estado) {
        this.estado = Estado;
    }
    
    
    
    
}
