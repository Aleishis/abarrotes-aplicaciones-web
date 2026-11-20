package mx.itson.abarrotes.entidades;

import java.util.List;


public class Venta {

    private int id;
    private String fecha;
    private double total;
    private List<DetalleVenta> detalles;

    public int getId() { return id; }
    public String getFecha() { return fecha; }
    public double getTotal() { return total; }
    public List<DetalleVenta> getDetalles() { return detalles; }
}
