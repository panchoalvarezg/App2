// src/models/ElementoAgricola.java
package models;

import java.time.LocalDate;

public abstract class ElementoAgricola {
    protected String nombre;
    protected LocalDate fechaSiembra;
    protected String estado;

    public ElementoAgricola(String nombre, LocalDate fechaSiembra, String estado) {
        this.nombre = nombre;
        this.fechaSiembra = fechaSiembra;
        this.estado = estado;
    }

    public String getNombre() { return nombre; }
    public LocalDate getFechaSiembra() { return fechaSiembra; }
    public String getEstado() { return estado; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setFechaSiembra(LocalDate fechaSiembra) { this.fechaSiembra = fechaSiembra; }
    public void setEstado(String estado) { this.estado = estado; }
}


// src/models/Cultivo.java
package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

