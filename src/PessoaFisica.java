import java.util.Date;

public class PessoaFisica extends Cliente {

    private String cpf;
    private int idade;
    private char genero;

    public PessoaFisica(String nome,String cpf) {

        //this.endereco=endereço;
        setNome(nome);
        this.cpf = cpf;
        this.idade = 0;
        this.genero = 'n';//inicia com o genero null
    }

    public void setcpf(String cpf) {
        this.cpf = cpf;
    }

    public String getcpf() {
        return this.cpf;
    }

    public void setidade(int idade) {
        this.idade = idade;
    }

    public int getidade() {
        return idade;
    }

    public void setGenero(char genero) {
        if ((genero != 'm') && (genero != 'f') && (genero != 'n')) {
            System.out.println("Genero inválido: informe novamente - m:masculinho ou f:feminino");
        }
        this.genero = genero;
    }

    public char getgenero() {
        return this.genero;
    }

    public String toString() {
        String pessoafisica = "CPF:" + this.cpf + "\n" +
                "Idade:" + this.idade + "\n" +
                "Gênero:" + this.genero;
        return pessoafisica;
    }

    public boolean equals(Object obj) {

        if (obj instanceof PessoaFisica) {
            PessoaFisica objPF = (PessoaFisica) obj;
            if (this.cpf.equals(objPF.cpf)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

    public boolean autenticar (String chave){
        if(chave.equals(this.cpf)){
            return true;
        } else{
            return false;
        }
    }
}