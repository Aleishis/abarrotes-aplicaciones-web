package mx.itson.abarrotes.entidades;

import com.google.gson.annotations.SerializedName;

public class DetalleVenta {

    //@SerializedName indica el nombre que tiene el campo en el JSON
    @SerializedName("producto_id")
    private int productoId;

    private String nombre;
    private int cantidad;

    @SerializedName("precio_unitario")
    private double precioUnitario;

    private double subtotal;

    public DetalleVenta(int productoId, String nombre, int cantidad, double precioUnitario) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario;
    }

    public int getProductoId() { return productoId; }
    public String getNombre() { return nombre; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }

    public int getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = cantidad * precioUnitario;
    }
}
