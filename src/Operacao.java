import java.util.Date;
import java.lang.Comparable;
import java.io.Serializable;

public abstract class Operacao implements ITaxas, Comparable<Operacao>, Serializable {

    private Date data;
    private char tipo;
    private int b;
    private double valor;
    private double taxas;
    static int totalOperacoes = 0;
    //-----------------------------------------------------------------------------------------------------//


    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        data = new Date();
        totalOperacoes++;
    }
    //-----------------------------------------------------------------------------------------------------//
    //Getters
    //-----------------------------------------------------------------------------------------------------//
    public Date getDataOperacao() {
        return this.data;
    }

    public char getTipo() {
        return this.tipo;
    }

    public double getValor() {
        return this.valor;
    }

    public double getTaxas() {return this.taxas;}

    //-----------------------------------------------------------------------------------------------------//
    //Setters
    //-----------------------------------------------------------------------------------------------------//
    public void setTipo(char tipo){
        if ((tipo == 'd')||(tipo == 's')) {
            this.tipo = tipo;
        }
    }

    public void setValor(double valor){
        this.valor = valor;
    }
    public void setTaxas(double taxas){
        this.valor = taxas;
    }
    //-----------------------------------------------------------------------------------------------------//

    //Metodos
    public String toString(){
        return String.format("%s %c  %f", getDataOperacao(),getTipo(), getValor());
    }

    @Override
    public int compareTo(Operacao o) {
        if(this.tipo < o.getTipo()) {
            return -1;
        }
        if(this.tipo > o.getTipo()) {
            return 1;
        }
        return 0;
    }


    //-----------------------------------------------------------------------------------------------------//
}
