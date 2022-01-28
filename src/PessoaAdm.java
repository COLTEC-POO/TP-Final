public class PessoaAdm  extends Cliente{

    String login;
    int admidade;
    int admflag;
    char admsexo;
    String admsenha;


    public boolean equals(Object obj){
        PessoaAdm that = (PessoaAdm) obj;

        return (this.login.equals(that.login) || this.admsenha.equals(that.admsenha));
    }
    //-----------------------------------------------------------------------------------------------------//
    public boolean autenticar(String chave, String chave1){
        if(chave.equals(this.login) && chave1.equals(this.admsenha)){
            System.out.println("Parabens admin você foi logado com sucesso!");
            return true;
        }else{
            throw new IllegalArgumentException("ERRO: SOMENTE ACESSO AUTORIZADO");
        }

    }
}
