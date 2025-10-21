/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo.práctico.pkg5;

public class CuentaBancaria {
    private String cbu;
    private double saldo;
    private ClaveSeguridad clave;
    private Titular1 titular;

    public CuentaBancaria(String cbu, double saldo, String codigoClave, String ultimaModificacion) {
        this.cbu = cbu;
        this.saldo = saldo;
        this.clave = new ClaveSeguridad(codigoClave, ultimaModificacion);
    }

    public void setTitular(Titular1 titular) {
        this.titular = titular;
        titular.setCuenta(this); 
    }

    public String getCbu() { return cbu; }
    public double getSaldo() { return saldo; }
    public ClaveSeguridad getClave() { return clave; }
    public Titular1 getTitular() { return titular; }

    @Override
    public String toString() {
        return "CuentaBancaria [cbu=" + cbu + ", saldo=" + saldo + ", clave=" + clave + ", titular=" + titular + "]";
    }    
}
