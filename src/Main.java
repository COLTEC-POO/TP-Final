import java.io.File;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main (String[]args)  {

        Scanner ler = new Scanner(System.in);
        Scanner lerLogin = new Scanner(System.in);
        Scanner lerSenha = new Scanner(System.in);
        Scanner lerAgencia = new Scanner(System.in);
        Scanner lerNumero= new Scanner(System.in);
        Scanner lerNome= new Scanner(System.in);

        //Scanner limpaBuffer = new Scanner(System.in);

        //array list que irá armazenar todas as agências que forem criadas na rede bancaria do BancoIpoo
        ArrayList< Agencia > redeBancoIpoo = new ArrayList< Agencia>();
        ArrayList< PessoaFisica> clientesBancoIpoo = new ArrayList< PessoaFisica>();
        ArrayList< ContaCorrente> contasBancoIpoo = new ArrayList< ContaCorrente>();


        int menu=-1;
        String login;
        String senha;
        String nomeagencia;

        //Falta implementar - mecanismo de carregar a rede com todas as agencias/contas quando entrar no sistema


        do {
            System.out.printf("----Banco POO ----\n");
            System.out.printf("----MENU ----\n");
            System.out.printf("1.Criar nova conta\n");
            System.out.printf("2.Login como cliente\n");
            System.out.printf("3.Login como administrador\n");
            System.out.printf("4.Sair \n");

            menu = ler.nextInt();

            switch (menu){
                case 1:
                    System.out.printf("Opção selecionada:1.Criar nova conta\n");

                    //bloco de comandos
                    //coleta dados
                    System.out.printf("----Dados Cadastrais----\n");
                    System.out.printf("Nome:\n");
                    String nomecliente = lerNome.next();
                    System.out.printf("Infome o seu cpf:\n");
                    login=lerLogin.next();
                    System.out.printf("Cadastre uma senha:\n");
                    senha=lerSenha.next();
                    //cria o cliente - a princípio só pessoa física
                    clientesBancoIpoo.add(new PessoaFisica(nomecliente,login));
                    System.out.printf("Veja a lista de agencias disponíveis:\n");
                    System.out.println(redeBancoIpoo);
                    System.out.printf("Informe o nome da agência no qual você deseja abrir a conta:\n");
                    nomeagencia = lerAgencia.next();
                    //encontra o cliente na lista de clientes, encontra a agencia na lista de agencia e cria a conta com esse parametros
                    if (clientesBancoIpoo != null && !clientesBancoIpoo.isEmpty()) {
                        Cliente last = clientesBancoIpoo.get(clientesBancoIpoo.size()-1);
                        int i= redeBancoIpoo.size();
                        int j=0;
                        for(j=0;j<i;j++){
                            Agencia teste=new Agencia(nomeagencia,0);
                            teste=redeBancoIpoo.get(j);
                            if(teste.getNome()==nomeagencia){
                                redeBancoIpoo.get(j);
                                break;
                            }
                        }
                        //cria a conta
                        contasBancoIpoo.add(new ContaCorrente(last,senha,redeBancoIpoo.get(j)));
                    }
                    //salvar conta criada num arquivo -> dentro da pasta /dbcontas/num-age
                    if (contasBancoIpoo != null && !contasBancoIpoo.isEmpty()) {
                        Conta last = contasBancoIpoo.get(contasBancoIpoo.size()-1);
                        last.salvaconta(last);
                    }

                    menu=-1;
                    break;
                case 2:
                    System.out.printf("Opção selecionada:2.Login como cliente\n");
                    //bloco de comandos
                    int menu2=-1;
                    int menu21=-1;
                    int contador=3;
                    do{
                        //coletando as informações para logar no sistema
                        System.out.printf("Informe o login:\n");
                        login=lerLogin.next();
                        System.out.printf("Informe a senha:\n");
                        senha=lerSenha.next();

                        //procurar na lista de clientes qual cliente esta logando e conferir a senha da conta
                        if (clientesBancoIpoo != null && !clientesBancoIpoo.isEmpty()) {
                            Cliente last = clientesBancoIpoo.get(clientesBancoIpoo.size() - 1);
                            int i = redeBancoIpoo.size();
                            int j = 0;
                            for (j = 0; j < i; j++) {
                                PessoaFisica testecliente = new PessoaFisica("nome", login);
                                testecliente = clientesBancoIpoo.get(j);
                                if (testecliente.getcpf() == login) {
                                    redeBancoIpoo.get(j);
                                    break;
                                }
                            }
                        }
                        //verificar se o login esta correto - adaptar para o cliente
                        if(Objects.equals(login, "admin")){
                            //adaptar para o cliente
                            if(Objects.equals(senha, "ib2021!")){
                                //entrou-bloco de comandos
                                System.out.printf("Tudo certo!Entrou no sistema!\n");
                                do{
                                    System.out.printf("----MENU - Login Cliente ----\n");
                                    System.out.printf("1.Depósito: Depositar um determinado valor na conta\n");
                                    System.out.printf("2.Saque: Retirar um determinado valor da conta\n");
                                    System.out.printf("3.Transferência: Transferir um determinado valor desta conta para outra a ser fornecida.\n");
                                    System.out.printf("4.Excluir conta: Excluir a conta do cliente.\n");
                                    System.out.printf("5.Logout\n");
                                    menu21= ler.nextInt();
                                    switch (menu21) {
                                        case 1:
                                            System.out.printf("1.Depósito:\n");
                                            menu21=-1;
                                            break;
                                        case 2:
                                            System.out.printf("2.Saque:\n");
                                            menu21=-1;
                                            break;
                                        case 3:
                                            System.out.printf("3.Transferência:\n");
                                            menu21=-1;
                                            break;
                                        case 4:
                                            System.out.printf("4.Excluir conta:\n");
                                            menu21=-1;
                                            break;
                                        case 5:
                                            System.out.printf("5.Logout:\n");
                                            menu21=0;
                                            break;
                                        default:
                                            System.out.printf("Opção inválida!Escolha novamente!\n");
                                            menu21=-1;
                                            break;
                                    }
                                }while(menu21!=0);
                                menu2=0;
                            }else{
                                if(contador>0){
                                    System.out.printf("A senha informada está incorreta!Número de tentativas:%d\n",contador);
                                    contador -= 1;
                                    menu2=-1;
                                }else{
                                    System.out.printf("Número de tentativas excedida!\n");
                                    menu2=0;
                                }
                            }
                        }else{
                            if(contador>0){
                                System.out.printf("O login informado está incorreto!Número de tentativas:%d\n",contador);
                                contador -= 1;
                                menu2=-1;
                            }else{
                                System.out.printf("Número de tentativas excedida!\n");
                                menu2=0;
                            }
                        }
                    }while(menu2!=0);
                    menu=-1;
                    break;
                case 3:
                    int menu3,menu31=-1;
                    contador=3;
                    System.out.printf("Opção selecionada:3.Login como administrador\n");
                    do{
                        //coletando as informações para logar no sistema
                        System.out.printf("Informe o login:\n");
                        login=lerLogin.next();
                        System.out.printf("Informe a senha:\n");
                        senha=lerSenha.next();

                        //verificando se o login esta correto
                        if(Objects.equals(login, "admin")){
                            if(Objects.equals(senha, "ib2021!")){
                                //entrou-bloco de comandos
                                System.out.printf("Tudo certo!Entrou no sistema!\n");
                                do{
                                    System.out.printf("----MENU - Area Administrativa ----\n");
                                    System.out.printf("1.Criar nova agência\n");
                                    System.out.printf("2.Excluir agência\n");
                                    System.out.printf("3.Logout\n");
                                    menu31= ler.nextInt();
                                     switch (menu31) {
                                         case 1:
                                             System.out.printf("Opção selecionada:1.Criar nova agência\n");
                                             System.out.printf("Informe o nome da agência:\n");
                                             nomeagencia = lerAgencia.next();
                                             System.out.printf("Informe o número da agência:\n");
                                             int numeroAgencia= lerNumero.nextInt();
                                             redeBancoIpoo.add(new Agencia(nomeagencia,numeroAgencia));
                                             //recupera a ultima agencia inserida e salva dados da agencia num arquivo
                                             //!!!!Atenção!!!--- Não consegui colocar o arquivo na pasta db(->dentro da pasta /dbagenciacontas/num)
                                             if (redeBancoIpoo != null && !redeBancoIpoo.isEmpty()) {
                                                 Agencia last = redeBancoIpoo.get(redeBancoIpoo.size()-1);
                                                 String nome=last.getNome();
                                                 last.salvaAgencia(nome);
                                             }
                                             menu31=-1;
                                             break;
                                         case 2:
                                             System.out.printf("Opção selecionada:2.Excluir agência\n");
                                             //exclui agencia e contas relacionadas a esta agencia
                                             System.out.printf("Informe o nome da agência:\n");
                                             nomeagencia = lerAgencia.next();
                                             System.out.printf("Informe o número da agência:\n");
                                             numeroAgencia= lerNumero.nextInt();
                                             String nomeArquivo="db.agencia-"+nomeagencia+"-"+nomeagencia;
                                             //exclui o arquivo da agencia
                                             File f = new File(nomeArquivo);
                                             f.delete();
                                             //!!!!Atenção!!!--- não esta funcionando
                                             // caso o programa tenha sido encerrado porque falta implementar um mecanismo de persistencia do programa

                                             if (redeBancoIpoo != null && !redeBancoIpoo.isEmpty()) {
                                                 //exclui a agencia no arrayList
                                                 redeBancoIpoo.remove(nomeagencia);
                                             }else{
                                                 System.out.printf("Agência não encontrada!\n");
                                             }
                                             menu31=-1;
                                             break;
                                         case 3:
                                             System.out.printf("Opção selecionada:3.Logout\n");
                                             menu31=0;
                                             break;
                                         default:
                                             System.out.printf("Opção inválida!Escolha novamente!\n");
                                             menu31=-1;
                                             break;
                                     }
                                }while(menu31!=0);
                                menu3=0;
                            }else{
                                if(contador>0){
                                    System.out.printf("A senha informada está incorreta!Número de tentativas:%d\n",contador);
                                    contador -= 1;
                                    menu3=-1;
                                }else{
                                    System.out.printf("Número de tentativas excedida!\n");
                                    menu3=0;
                                }
                            }
                        }else{
                            if(contador>0){
                                System.out.printf("O login informado está incorreto!Número de tentativas:%d\n",contador);
                                contador -= 1;
                                menu3=-1;
                            }else{
                                System.out.printf("Número de tentativas excedida!\n");
                                menu3=0;
                            }
                        }
                    }while(menu3!=0);
                    menu=-1;
                    break;
                case 4:
                    //bloco de comandos
                    System.out.printf("Até a próxima visita!Obrigado por usar o sistema!\n");
                    menu=0;
                    break;

                default:
                    //bloco de comandos
                    System.out.printf("Opção inválida!Escolha novamente!\n");
                    menu=-1;
                    break;
            }

        }while (menu!=0);

    }

}
