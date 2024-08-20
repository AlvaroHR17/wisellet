package com.alvarohdr.gastosapi.domain.dto;

import java.io.Serializable;

public class VariableExpenseTypeDto implements Serializable {
    private static final long serialVersionUID = -3627233698156240005L;

    private long id;
    private String descripcion;

    public VariableExpenseTypeDto() {
    }

    public VariableExpenseTypeDto(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "VariableExpenseTypeDto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
