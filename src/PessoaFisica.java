public class PessoaFisica extends Cliente{

    String cpf;
    int idade;
    char sexo;
    String senha;
    int flag;

    //-----------------------------------------------------------------------------------------------------//
    //Métodos
    //-----------------------------------------------------------------------------------------------------//
    public String toString(){
        return String.format("Dono da conta: %s \nCPF: %s \nIdade: %d \nSexo: %s \nEndereço: %s \n", this.nome, this.cpf, this.idade, this.sexo, this.endereco);
    }



    public boolean equals(Object obj){
        PessoaFisica that = (PessoaFisica) obj;

        return (this.cpf.equals(that.cpf) || this.senha.equals(that.senha));
    }
    //-----------------------------------------------------------------------------------------------------//
    public boolean autenticar(String chave, String chave1){
        if(chave.equals(this.cpf) && chave1.equals(this.senha)){
            System.out.println("Parabens você foi logado com sucesso!");
            return true;
        }else{
            throw new IllegalArgumentException("ERRO: CPF OU SENHA INCORRETOS!");
        }

    }
    //-----------------------------------------------------------------------------------------------------//
}
