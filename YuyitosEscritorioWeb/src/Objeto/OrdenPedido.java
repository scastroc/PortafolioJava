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
public class OrdenPedido {
    
    private int id;
    private Proveedor proveedor;
    private Trabajador trabajador;
    private Date fechaOrden;
    private Date fechaRecep;

    public OrdenPedido() {
        
        this.id = 0;
        this.proveedor = new Proveedor();
        this.trabajador = new Trabajador();
        this.fechaOrden = new Date(2000, 01,01);
        this.fechaRecep = new Date(2000, 01,01);
        
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaRecep() {
        return fechaRecep;
    }

    public void setFechaRecep(Date fechaRecep) {
        this.fechaRecep = fechaRecep;
    }
    
    
}
