public class PessoaFisica extends Cliente{
    String CPF;
    int idade;
    String sexo;

    public String toString(){
        return String.format("Dono da conta: %s \nCPF: %s \nIdade: %d \nSexo: %s \nEndereço: %s \n", this.nome, this.CPF, this.idade, this.sexo, this.endereco);
    }

    public boolean equals(Object obj) {
        if (obj instanceof PessoaFisica) {
            PessoaFisica that = (PessoaFisica) obj;
            return (this.CPF.equals(that.CPF));
        } else {
            return false;
        }
    }

    public boolean autenticar (String chaveDeIdentificacao){
        if (chaveDeIdentificacao.equals(this.CPF)){
            return true;
        }else{
            return false;
        }
    }
}
