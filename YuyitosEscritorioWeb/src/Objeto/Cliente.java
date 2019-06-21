/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objeto;

import java.sql.Date;

/**
 *
 * @author Sebastian
 */
public class Cliente {
    
    private String cliRut;
    private String cliDv;
    private String nombre;
    private String apePat;
    private String apeMat;
    private String email;
    private String telefono;
    private Date fechaNacimiento;
    private Direccion direccion;
    private char estado;

    public Cliente() {
        
        this.cliRut = "";
        this.cliDv = "";
        this.nombre = "";
        this.apePat = "";
        this.apeMat = "";
        this.email = "";
        this.telefono = "";        
        this.estado = 0;
    }
    
    

    public String getCliRut() {
        return cliRut;
    }

    public void setCliRut(String cliRut) {
        this.cliRut = cliRut;
    }

    public String getCliDv() {
        return cliDv;
    }

    public void setCliDv(String cliDv) {
        this.cliDv = cliDv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApePat() {
        return apePat;
    }

    public void setApePat(String apePat) {
        this.apePat = apePat;
    }

    public String getApeMat() {
        return apeMat;
    }

    public void setApeMat(String apeMat) {
        this.apeMat = apeMat;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }
    
    
    
}
