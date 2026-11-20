package mx.itson.abarrotes.entidades;


public class Producto {

    private int id;
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    /** Es lo que se muestra en el combo del punto de venta. */
    @Override
    public String toString() {
        return nombre + "  -  $" + precio + "  (stock: " + stock + ")";
    }
}
