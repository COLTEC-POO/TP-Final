import java.io.Serializable;
import java.util.Date;
import java.lang.Comparable;

public abstract class Operacao implements ITaxas, Comparable<Operacao>, Serializable {
    /* Data de realização da operação */
    private Date data;

    /* Tipo da operação */
    private char tipo;

    /* Valor da operação */
    private double valor;

    static int totalOperacoes = 0;

    public Operacao(char tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        data = new Date();
        totalOperacoes++;
    }

//    Getters

    public Date getDataOperacao() {
        return this.data;
    }

    public char getTipo() {
        return this.tipo;
    }

    public double getValor() {
        return this.valor;
    }

//    Setters

    public void setTipo(char tipo){
        if ((tipo == 'd')||(tipo == 's')) {
            this.tipo = tipo;
        }
    }

    public void setValor(double valor){
        this.valor = valor;
    }

//    Métodos

    @Override
    public int compareTo(Operacao o) {
        if (this.tipo < o.getTipo()){
            return -1;
        }else if (this.tipo > o.getTipo()){
            return 1;
        }else {
            return 0;
        }
    }

    public String toString(){
        return String.format("%s \n%c \n %f\n", getDataOperacao(),getTipo(), getValor());
    }
}

