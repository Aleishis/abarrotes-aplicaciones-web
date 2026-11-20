package mx.itson.abarrotes.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import mx.itson.abarrotes.entidades.DetalleVenta;
import mx.itson.abarrotes.entidades.Producto;
import mx.itson.abarrotes.entidades.Venta;

/**
 * Todas las llamadas a la API de Django.
 * Los objetos se convierten a JSON (y de JSON a objetos) con Gson.
 */
public class ApiCliente {

    private static final String URL = "http://localhost:8000/api/";

    private static final HttpClient cliente = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    /**
     * Hace la peticion HTTP y regresa el JSON de la respuesta.
     * Si la API responde con error, lanza una excepcion con el mensaje de la API.
     */
    private static String enviar(String metodo, String ruta, Object cuerpo) throws Exception {
        HttpRequest.BodyPublisher body = (cuerpo == null)
                ? HttpRequest.BodyPublishers.noBody()
                : HttpRequest.BodyPublishers.ofString(gson.toJson(cuerpo));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL + ruta))
                .header("Content-Type", "application/json")
                .method(metodo, body)
                .build();

        HttpResponse<String> respuesta;
        try {
            respuesta = cliente.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (ConnectException e) {
            throw new Exception("No se pudo conectar con la API. ¿Esta corriendo Django?");
        }

        // 200 y 201 = todo bien. 400, 404, 500... = error
        if (respuesta.statusCode() >= 400) {
            String mensaje = "Error " + respuesta.statusCode();
            try {
                // La API manda los errores asi: {"error": "mensaje"}
                mensaje = gson.fromJson(respuesta.body(), JsonObject.class).get("error").getAsString();
            } catch (Exception e) {
                // la respuesta no era JSON, se queda el mensaje generico
            }
            throw new Exception(mensaje);
        }

        return respuesta.body();
    }

    // ---------------- Productos ----------------

    public static List<Producto> listarProductos() throws Exception {
        String json = enviar("GET", "productos/", null);
        // TypeToken le dice a Gson que el JSON es una lista de Producto
        return gson.fromJson(json, new TypeToken<List<Producto>>() {}.getType());
    }

    public static void crearProducto(Producto p) throws Exception {
        enviar("POST", "productos/", p);
    }

    public static void actualizarProducto(Producto p) throws Exception {
        enviar("PUT", "productos/" + p.getId() + "/", p);
    }

    public static void eliminarProducto(int id) throws Exception {
        enviar("DELETE", "productos/" + id + "/", null);
    }

    // ---------------- Ventas ----------------

    public static List<Venta> listarVentas() throws Exception {
        String json = enviar("GET", "ventas/", null);
        return gson.fromJson(json, new TypeToken<List<Venta>>() {}.getType());
    }

    public static Venta obtenerVenta(int id) throws Exception {
        String json = enviar("GET", "ventas/" + id + "/", null);
        return gson.fromJson(json, Venta.class);
    }

    /** Manda el carrito como: {"articulos": [ {...}, {...} ]} */
    public static Venta registrarVenta(List<DetalleVenta> carrito) throws Exception {
        String json = enviar("POST", "ventas/", Map.of("articulos", carrito));
        return gson.fromJson(json, Venta.class);
    }
}
