public class PessoaJuridica extends Cliente{

    private String cnpj;
    private int numFuncionarios;//Número de funcionários da PJ
    private String setor;//Setor de atuação da PJ (financeiro, educação, veículos, etc)

    public PessoaJuridica(String nome,String cnpj){
        this.cnpj="XX.XXX.XXX/0001-XX";
        this.numFuncionarios=0;
        this.setor="INVALIDO";
    }

    public void setCnpj(String cnpj){
        this.cnpj=cnpj;
    }
    public String getCnpj(){
        return this.cnpj;
    }
    public void setNumFuncionarios(int numfuncionarios){
        this.numFuncionarios=numfuncionarios;
    }
    public int getNumFuncionarios(){
        return this.numFuncionarios;
    }
    public void setSetor(String setor){
        this.setor=setor;
    }
    public String getSetor(){
        return this.setor;
    }

    public String toString(){
        String pessoajuridicaStr= "CNPJ:"+this.cnpj+"\n"+
                "Número de funcionários:"+this.numFuncionarios+"\n"+
                "Setor:"+this.setor;
        return pessoajuridicaStr;
    }

    public boolean equals(Object obj){

        if(obj instanceof PessoaJuridica){
            PessoaJuridica objPJ=(PessoaJuridica)obj;
            if(this.cnpj.equals(objPJ.cnpj)){
                return true;
            }
            else{
                return false;
            }
        }else{
            return false;
        }

    }

    public boolean autenticar (String chave){
        if(chave.equals(this.cnpj)){
            return true;
        } else{
            return false;
        }
    }


}
