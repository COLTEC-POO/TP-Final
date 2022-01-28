public class PessoaJuridica extends Cliente{
    String cnpj;
    int numFuncionarios;
    String setor;

    public String toString(){
        return String.format("Nome fantasia: %s \nCNPJ: %s \nNumero de funcionarios: %d \nSetor: %s \n", this.nome, this.cnpj, this.numFuncionarios, this.setor);
    }

    public boolean equals(Object obj){
        if (obj instanceof PessoaFisica) {
            PessoaJuridica that = (PessoaJuridica) obj;
            return (this.cnpj.equals(that.cnpj));
        } else {
            return false;
        }
    }

    public boolean autenticar (String chaveDeIdentificacao){
        if (chaveDeIdentificacao.equals(this.cnpj)){
            return true;
        }else{
            return false;
        }
    }
}
