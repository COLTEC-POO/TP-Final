import java.io.File;
import java.util.Scanner;

public class CriarConta {
    Scanner input = new Scanner(System.in);
    int pessoa, tipoconta, aut;
    Conta novac = new ContaCorrente();
    Conta novau = new ContaUniversitaria();
    Conta novap = new ContaPoupanca();
    PessoaJuridica novaJuridica = new PessoaJuridica();
    PessoaFisica novaFisica = new PessoaFisica();
    Conta x = new ContaCorrente();
    Conta adm = new ContaCorrente();
    PessoaAdm admin = new PessoaAdm();


    public void cadastro(){

        /*MENU INICIAL*/
        System.out.println();
        System.out.println();
        System.out.println("---------------Banco Intactoz--------------");
        System.out.println("    Ola,vamos criar sua conta no banco?!");
        System.out.println("-------------------------------------------");
        System.out.println("Qual tipo de pessoa ira usar essa conta?");
        System.out.println("1 - Pessoa Fisica.");
        System.out.println("2 - Pessoa Juridica.");
        System.out.println("-------------------------------------------");
        pessoa = input.nextInt();

        /*CRIAÇÃO DE CONTA PESSOA FISICA*/
        if (pessoa == 1){
            System.out.println("Digite seu nome:");
            novaFisica.nome = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Digite sua idade:");
            novaFisica.idade = input.nextInt();
            System.out.println("-------------------------------------------");
            System.out.println("Digite seu CPF:");
            novaFisica.cpf = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Digite seu Endereço:");
            novaFisica.endereco = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Digite apenas a primeira letra do seu sexo:");
            novaFisica.sexo = input.next().charAt(0);
            System.out.println("-------------------------------------------");
            System.out.println("Digite sua senha:");
            novaFisica.senha = input.next();
            novaFisica.flag = 1;
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println("Escolha qual tipo de Conta deseja criar:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            System.out.println("3 - Conta Universitaria");
            System.out.println("-------------------------------------------");
            tipoconta = input.nextInt();
            /*CRIAÇÃO DO TIPO DE CONTA*/
            if (tipoconta == 1){
                novac.setMemoryCliente(novaFisica);
                novac.setMemoryId(2);
                novac.setLimite(500.0D);
                novac.setMemoryAgencia(1300);
                novac.setA(1);//flag acesso ao painel
                System.out.println();
                System.out.println();
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("           CADASTRADO COM SUCESSO          ");
                System.out.println("-------------------------------------------");
                System.out.println(novac);
                System.out.println("-------------------------------------------");

            }
            if (tipoconta == 2){
                novap.setMemoryCliente(novaFisica);
                novap.setMemoryId(1);
                novap.setLimite(500.0D);
                novap.setMemoryAgencia(1100);
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("           CADASTRADO COM SUCESSO          ");
                System.out.println("-------------------------------------------");
                System.out.println(novap);
                System.out.println("-------------------------------------------");
                novap.setA(2);//flag acesso ao painel

            }
            if (tipoconta == 3){
                novau.setMemoryCliente(novaFisica);
                novau.setMemoryId(1);
                novau.setLimite(500.0D);
                novau.setMemoryAgencia(1100);
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("           CADASTRADO COM SUCESSO          ");
                System.out.println("-------------------------------------------");
                System.out.println(novau);
                System.out.println("-------------------------------------------");
                novau.setA(3); //flag acesso ao painel

            }
            if (tipoconta > 4 || tipoconta <= 0){
                throw new IllegalArgumentException("ERRO: Tipo de conta invalida!!!");
            }

        }
        /*CRIAÇÃO DE CONTA PESSOA JURIDICA*/
        if (pessoa == 2){
            System.out.println("Digite seu CNPJ:");
            novaJuridica.cnpj = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Digite seu Endereço:");
            novaJuridica.endereco = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Quantidade de Funcionários:");
            novaJuridica.numFuncionarios = input.nextInt();
            System.out.println("-------------------------------------------");
            System.out.println("Digite seu setor:");
            novaJuridica.setor = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Digite o nome:");
            novaJuridica.nome = input.next();
            System.out.println("-------------------------------------------");
            System.out.println("Digite sua senha:");
            novaJuridica.senhaa = input.next();
            novaJuridica.flag = 2;
            System.out.println();
            System.out.println();
            System.out.println("-------------------------------------------");
            System.out.println("Escolha qual tipo de Conta deseja criar:");
            System.out.println("1 - Conta Corrente");
            System.out.println("2 - Conta Poupança");
            System.out.println("3 - Conta Universitaria");
            System.out.println("-------------------------------------------");
            tipoconta = input.nextInt();
            /*CRIAÇÃO DO TIPO DE CONTA*/
            if (tipoconta == 1){
                novac.setMemoryCliente(novaJuridica);
                novac.setMemoryId(2);
                novac.setLimite(500.0D);
                novac.setMemoryAgencia(1200);
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("           CADASTRADO COM SUCESSO          ");
                System.out.println("-------------------------------------------");
                System.out.println(novac);
                System.out.println("-------------------------------------------");
                novac.setA(1);

            }
            if (tipoconta == 2){
                novap.setMemoryCliente(novaJuridica);
                novap.setMemoryId(2);
                novap.setLimite(500.0D);
                novap.setMemoryAgencia(1200);
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("           CADASTRADO COM SUCESSO          ");
                System.out.println("-------------------------------------------");
                System.out.println(novap);
                System.out.println("-------------------------------------------");
                novap.setA(2);

            }
            if (tipoconta == 3){
                novau.setMemoryCliente(novaJuridica);
                novau.setMemoryId(2);
                novau.setLimite(500.0D);
                novau.setMemoryAgencia(1200);
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("           CADASTRADO COM SUCESSO          ");
                System.out.println("-------------------------------------------");
                System.out.println(novau);
                System.out.println("-------------------------------------------");
                novau.setA(3);

            }
            if (tipoconta > 4 || tipoconta <= 0){
                throw new IllegalArgumentException("ERRO: Tipo de conta invalida!!!");
            }

        }
        if (pessoa > 2 || pessoa <= 0){
            throw new IllegalArgumentException("ERRO: Tipo de pessoa invalida!!!");
        }

    }
    /*PAINEL DE CONTROLE GERAL*/
    public void painel(){
        //novac.showConta(1100,1);
        byte Menu;
        do {
            System.out.println();
            System.out.println();
            System.out.println("---------------Banco Intactoz--------------");
            System.out.println("              Painel do Cliente            ");
            System.out.println("-------------------------------------------");
            System.out.println("             1- Conta Corrente             ");
            System.out.println("             2- Conta Poupança             ");
            System.out.println("             3- Conta Universitaria        ");
            System.out.println("             4- Exit                       ");
            System.out.println("-------------------------------------------");
            System.out.print("Escolha a conta que deseja acessar: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    if(novac.getA() == 1){
                    painelc();
                    }
                    if(novac.getA() != 1){
                        System.out.println("ERRO: FAVOR ESCOLHER A CONTA CERTA");
                    }
                    break;
                case 2:
                    if(novap.getA() == 2){
                        painelp();
                    }
                    if(novap.getA() != 2){
                        System.out.println("ERRO: FAVOR ESCOLHER A CONTA CERTA");
                    }
                    break;
                case 3:
                    if(novau.getA() == 3){
                        painelu();
                    }
                    if(novau.getA() != 3){
                        System.out.println("ERRO: FAVOR ESCOLHER A CONTA CERTA");
                    }
                    break;
                case 4:
                    System.out.println("Saindo do Banco Intactoz...");
                    System.out.println("Atenção sua conta não sera salva se você não fizer o primeiro acesso!");
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 4);
    }
    /*SUB PAINEL DE CONTROLE C*/
    public void painelc(){

        byte Menu;
        do {
            System.out.println();
            System.out.println();
            System.out.println("---------------Banco Intactoz--------------");
            System.out.println("     Painel do Cliente - Conta Corrente    ");
            System.out.println("-------------------------------------------");
            System.out.println("             1- Seus Dados                 ");
            System.out.println("             2- Depósito                   ");
            System.out.println("             3- Saque                      ");
            System.out.println("             4- Transferência              ");
            System.out.println("             5- Excluir conta              ");
            System.out.println("             6- Exit                       ");
            System.out.println("-------------------------------------------");
            System.out.print("Digite a opção desejada: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    System.out.println("---------------Banco Intactoz--------------");
                    System.out.println(novac);
                    System.out.println("-------------------------------------------");
                    break;
                case 2:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja depositar:");
                        i = input.nextInt();
                        novac.depositar(i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("    Novo Deposito realizado com Sucesso!   ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Novo Saldo: " + novac.getMemorySaldo());
                        System.out.println("-------------------------------------------");
                    } catch (ValorNegativoException var13) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var13.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 3:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja sacar:");
                        i = input.nextInt();
                        novac.sacar(i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("   Saque realizado com Sucesso!  ");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo: " + novac.getMemorySaldo());
                        System.out.println("----------------------------------");
                    } catch (ValorNegativoException var12) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var12.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 4:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja transferir:");
                        i = input.nextInt();
                        novac.transferir(x, i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("Transferência realizado com Sucesso!");;
                        System.out.println("-------------------------------------------");
                        System.out.println("Novo Saldo: " + novac.getMemorySaldo());
                        System.out.println("-------------------------------------------");
                    } catch (ValorNegativoException var11) {
                        System.out.println(var11.getMessage());
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var11.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 5:
                    int i,j;
                    System.out.println("Digite o numero da agência da sua conta:");
                    i = input.nextInt();
                    System.out.println("Digite o numero de Id da sua conta:");
                    j = input.nextInt();
                    novac.deleteConta(i,j);
                    break;
                case 6:
                    System.out.println("Saindo do Banco Intactoz...");
                    novac.saveContas();
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 6);
    }

    public void painelp(){
        byte Menu;
        do {
            System.out.println();
            System.out.println();
            System.out.println("---------------Banco Intactoz--------------");
            System.out.println("     Painel do Cliente - Conta Poupança    ");
            System.out.println("-------------------------------------------");
            System.out.println("             1- Seus Dados                 ");
            System.out.println("             2- Depósito                   ");
            System.out.println("             3- Saque                      ");
            System.out.println("             4- Transferência              ");
            System.out.println("             5- Excluir conta              ");
            System.out.println("             6- Exit                       ");
            System.out.println("-------------------------------------------");
            System.out.print("Digite a opção desejada: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    System.out.println("---------------Banco Intactoz--------------");
                    System.out.println(novap);
                    System.out.println("-------------------------------------------");
                    break;
                case 2:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja depositar:");
                        i = input.nextInt();
                        novap.depositar(i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("    Novo Deposito realizado com Sucesso!   ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Novo Saldo: " + novap.getMemorySaldo());
                        System.out.println("-------------------------------------------");
                    } catch (ValorNegativoException var13) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var13.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 3:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja sacar:");
                        i = input.nextInt();
                        novap.sacar(i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("   Saque realizado com Sucesso!  ");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo: " + novap.getMemorySaldo());
                        System.out.println("----------------------------------");
                    } catch (ValorNegativoException var12) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var12.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 4:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja transferir:");
                        i = input.nextInt();
                        novap.transferir(x, i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("Transferência realizado com Sucesso!");;
                        System.out.println("-------------------------------------------");
                        System.out.println("Novo Saldo: " + novap.getMemorySaldo());
                        System.out.println("-------------------------------------------");
                    } catch (ValorNegativoException var11) {
                        System.out.println(var11.getMessage());
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var11.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 5:
                    int i,j;
                    System.out.println("Digite o numero da agência da sua conta:");
                    i = input.nextInt();
                    System.out.println("Digite o numero de Id da sua conta:");
                    j = input.nextInt();
                    novap.deleteConta(i,j);
                    break;
                case 6:
                    System.out.println("Saindo do Banco Intactoz...");
                    novap.saveContas();
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 6);
    }

    public void painelu(){
        byte Menu;
        do {
            System.out.println();
            System.out.println();
            System.out.println("---------------Banco Intactoz--------------");
            System.out.println("  Painel do Cliente - Conta Universitaria  ");
            System.out.println("-------------------------------------------");
            System.out.println("             1- Seus Dados                 ");
            System.out.println("             2- Depósito                   ");
            System.out.println("             3- Saque                      ");
            System.out.println("             4- Transferência              ");
            System.out.println("             5- Excluir conta              ");
            System.out.println("             6- Exit                       ");
            System.out.println("-------------------------------------------");
            System.out.print("Digite a opção desejada: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    System.out.println("---------------Banco Intactoz--------------");
                    System.out.println(novau);
                    System.out.println("-------------------------------------------");
                    break;
                case 2:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja depositar:");
                        i = input.nextInt();
                        novau.depositar(i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("    Novo Deposito realizado com Sucesso!   ");
                        System.out.println("-------------------------------------------");
                        System.out.println("Novo Saldo: " + novau.getMemorySaldo());
                        System.out.println("-------------------------------------------");
                    } catch (ValorNegativoException var13) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var13.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 3:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja sacar:");
                        i = input.nextInt();
                        novau.sacar(i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("   Saque realizado com Sucesso!  ");
                        System.out.println("----------------------------------");
                        System.out.println("Novo Saldo: " + novau.getMemorySaldo());
                        System.out.println("----------------------------------");
                    } catch (ValorNegativoException var12) {
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var12.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 4:
                    try {
                        int i;
                        System.out.println("Digite o valor que deseja transferir:");
                        i = input.nextInt();
                        novau.transferir(x, i);
                        System.out.println("---------------Banco Intactoz--------------");
                        System.out.println("Transferência realizado com Sucesso!");;
                        System.out.println("-------------------------------------------");
                        System.out.println("Novo Saldo: " + novau.getMemorySaldo());
                        System.out.println("-------------------------------------------");
                    } catch (ValorNegativoException var11) {
                        System.out.println(var11.getMessage());
                        System.out.println("----------Banco Intactoz---------");
                        System.out.println(var11.getMessage());
                        System.out.println("----------------------------------");
                    }
                    break;
                case 5:
                    int i,j;
                    System.out.println("Digite o numero da agência da sua conta:");
                    i = input.nextInt();
                    System.out.println("Digite o numero de Id da sua conta:");
                    j = input.nextInt();
                    novau.deleteConta(i,j);
                    break;
                case 6:
                    System.out.println("Saindo do Banco Intactoz...");
                    novau.saveContas();
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 6);
    }
    void autlogin(){
        String tempcpf, tempsenha;

        /*Autenticar*/
        System.out.println();
        System.out.println();
        System.out.println("---------------Banco Intactoz--------------");
        System.out.println("  Ola,vamos autenticar sua conta no banco?!");
        System.out.println("-------------------------------------------");
        System.out.println("Qual sistema você deseja logar?");
        System.out.println("1 - Pessoa Fisica.");
        System.out.println("2 - Pessoa Juridica.");
        System.out.println("-------------------------------------------");
        aut = input.nextInt();
        if(aut == 1){
            if(novaFisica.flag == 1){
                System.out.println();
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("                   LOGIN                   ");
                System.out.println("-------------------------------------------");
                System.out.println("Digite seu CPF:");
                tempcpf = input.next();
                System.out.println("-------------------------------------------");
                System.out.println("Digite sua SENHA:");
                System.out.println("-------------------------------------------");
                tempsenha = input.next();
                novaFisica.autenticar(tempcpf, tempsenha);
                painel();
            }
            if(novaFisica.flag != 1){
                System.out.println("ERRO: VOCÊ NÃO E UMA PESSOA FISICA");
                System.out.println("FAVOR ESCOLHER A PESSOA CERTA!!!");
            }
        }
        if(aut == 2){
            if(novaJuridica.flag == 2) {
                System.out.println();
                System.out.println("---------------Banco Intactoz--------------");
                System.out.println("                   LOGIN                   ");
                System.out.println("-------------------------------------------");
                System.out.println("Digite seu CNPJ:");
                tempcpf = input.next();
                System.out.println("-------------------------------------------");
                System.out.println("Digite sua SENHA:");
                System.out.println("-------------------------------------------");
                tempsenha = input.next();
                novaJuridica.autenticar(tempcpf, tempsenha);
                painel();
            }
            if(novaJuridica.flag != 2){
                System.out.println("ERRO: VOCÊ NÃO E UMA PESSOA JURIDICA");
                System.out.println("FAVOR ESCOLHER A PESSOA CERTA!!!");
            }
        }
    }
    void criaradm(){
        adm.setMemoryId(999);
        adm.setLimite(0);
        adm.setMemoryAgencia(777);
        adm.setMemoryCliente(admin);
        admin.nome = "Gabriel";
        admin.endereco = "UFMG";
        admin.admidade = 20;
        admin.admsexo = 'M';
        admin.admsenha = "ib2021!";
        admin.login = "admin";
        admin.admflag = 10;
    }
    void loginadm(){
        criaradm();
        String templogin, tempsenha;
        System.out.println();
        System.out.println("---------------Banco Intactoz--------------");
        System.out.println("             LOGIN ADMINISTRADOR           ");
        System.out.println("-------------------------------------------");
        System.out.println("Digite seu LOGIN:");
        templogin = input.next();
        System.out.println("-------------------------------------------");
        System.out.println("Digite sua SENHA:");
        System.out.println("-------------------------------------------");
        tempsenha = input.next();
        admin.autenticar(templogin, tempsenha);
        paineladmin();
    }
    public void paineladmin(){
        byte Menu;
        do {
            System.out.println();
            System.out.println();
            System.out.println("---------------Banco Intactoz--------------");
            System.out.println("            Painel Administrativo          ");
            System.out.println("-------------------------------------------");
            System.out.println("             1- Criar Agência              ");
            System.out.println("             2- Excluir Agência            ");
            System.out.println("             3- Exit                       ");
            System.out.println("-------------------------------------------");
            System.out.print("Digite a opção desejada: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    int i;
                    System.out.println("Digite o numero da nova agência:");
                    i = input.nextInt();
                    adm.setMemoryAgencia(i);
                    adm.saveAgencia();
                    break;
                case 2:
                    int f;
                    System.out.println("Digite qual agência deseja excluir:");
                    f = input.nextInt();
                    adm.deleteAgencia(f);
                    break;
                case 3:
                    adm.saveContas();
                    System.out.println("Saindo do Banco Intactoz...");
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 3);
    }
}
