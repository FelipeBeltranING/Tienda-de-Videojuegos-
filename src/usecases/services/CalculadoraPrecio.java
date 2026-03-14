package usecases.services;
public class CalculadoraPrecio {
    public double calcularPrecioVenta(double precioUnitario, double iva){
        return precioUnitario + precioUnitario*iva;
    }
}
