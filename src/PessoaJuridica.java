public class PessoaJuridica extends Cliente{

    String cnpj;
    int numFuncionarios;
    String setor;
    String senhaa;
    int flag;

    //-----------------------------------------------------------------------------------------------------//
    //Métodos
    //-----------------------------------------------------------------------------------------------------//
    public String toString(){
        return String.format("Nome fantasia: %s \nCNPJ: %s \nNumero de funcionarios: %d \nSetor: %s \n", this.nome, this.cnpj, this.numFuncionarios, this.setor);
    }

    public boolean equals(Object obj){
        PessoaJuridica that = (PessoaJuridica) obj;

        return (this.cnpj.equals(that.cnpj) || this.senhaa.equals(that.senhaa));
    }
    //-----------------------------------------------------------------------------------------------------//
    public boolean autenticar(String chave, String chave1){
        if(chave.equals(this.cnpj) && chave1.equals(this.senhaa)){
            System.out.println("Parabens você foi logado com sucesso!");
            return true;
        }else{
            throw new IllegalArgumentException("ERRO: CNPJ OU SENHA INCORRETOS!");
        }

    }
}
