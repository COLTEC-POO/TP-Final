import java.io.Serializable;
import java.util.Date;

/**
 * Classe responsável por registrar operações de saque e depósitos realizados em contas correntes.
 */
public abstract class Operacao implements Comparable<Operacao>, Serializable {

    /* Valor da operação */
    private double valor;

    /* Data de realização da operação */
    private Date data;

    private char tipo;

    public Operacao() {
        this.valor = valor;
        this.tipo= 'n';
        data = new Date();
    }

    //metodos
    //-getters e setters:
    public Date getData(){
        return this.data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor){
        this.valor=valor;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public int compareTo(Operacao o) {
        if(this.tipo < o.getTipo()) {
            return -1;
        }
        if(this.tipo > o.getTipo()) {
            return 1;
        }
        return 0;
    }

}
