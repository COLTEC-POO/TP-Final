import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Agencia> agencias = new ArrayList<>();
    private static List<Conta> contas = new ArrayList<>();


    public static void CriarConta ()throws AgenciaInexistenteException, ContaExistenteException{

        Scanner sc = new Scanner(System.in);

        System.out.println("Conta para:");
        System.out.println("(1) Pessoa física.");
        System.out.println("(2) Pessoa jurídica.");
        int opcao = sc.nextInt();

        if ((opcao == 1)||(opcao == 2)){
            switch (opcao) {
                case 1:
                    System.out.println("Digite qual das modalidades de conta você deseja criar:");
                    System.out.println("(1) Conta corrente.");
                    System.out.println("(2) Conta poupança.");
                    System.out.println("(3) Conta universitária.");
                    int opcao2 = sc.nextInt();

                    if ((opcao2 == 1) || (opcao2 == 2) || (opcao2 == 3)) {
                        switch (opcao2) {
                            case 1:

                                PessoaFisica clienteCC = new PessoaFisica();
                                ContaCorrente contaC = new ContaCorrente();

                                contaC.setDono(clienteCC);

                                System.out.println("Digite seu nome:");
                                sc.nextLine();
                                String nome = sc.nextLine();
                                clienteCC.nome = nome;

                                System.out.println("Digite a sua idade:");
                                int idade = sc.nextInt();
                                clienteCC.idade = idade;

                                System.out.println("Digite seu sexo (Feminino/Masculino):");
                                sc.nextLine();
                                String sexo = sc.nextLine();
                                clienteCC.sexo = sexo;

                                System.out.println("Digite seu CPF:");
                                String cpf = sc.nextLine();
                                clienteCC.CPF = cpf;

                                System.out.println("Digite seu endereço:");
                                String endereco = sc.nextLine();
                                clienteCC.endereco = endereco;

                                System.out.println("Digite o limite que deseja para conta " +
                                        "(para essa modalidade de conta o limite deve ser superior a R$ -100.00):");
                                float limite = sc.nextFloat();
                                try {
                                    contaC.setLimite(limite);
                                }catch (IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("Digite o numero da agência");
                                int agencia = sc.nextInt();

                                if (!Agencia.existe(agencia, agencias)){
                                    throw new AgenciaInexistenteException("Agência inexistente.");
                                }

                                for (Agencia atual: agencias){
                                    if (atual.getNumeroDaAgencia() == agencia){
                                        contaC.setAgencia(atual);
                                    }
                                }

                                System.out.println("Digite o numero da conta");
                                int conta = sc.nextInt();
                                if (Conta.existe(conta, contas)){
                                    throw new ContaExistenteException("Conta com essa número já existente.");
                                }else {
                                    contaC.setNumeroDaConta(conta);
                                }

                                System.out.println("Defina uma senha numerica de 6 dígitos:");
                                int senha = sc.nextInt();
                                contaC.setSenha(senha);

                                contaC.getAgencia().contasPertencentes.add(contaC);
                                contas.add(contaC);

                                break;

                            case 2:

                                PessoaFisica clienteCP = new PessoaFisica();
                                ContaPoupanca contaP = new ContaPoupanca();

                                contaP.setDono(clienteCP);

                                System.out.println("Digite seu nome:");
                                sc.nextLine();
                                nome = sc.nextLine();
                                clienteCP.nome = nome;

                                System.out.println("Digite a sua idade:");
                                idade = sc.nextInt();
                                clienteCP.idade = idade;

                                System.out.println("Digite seu sexo (Feminino/Masculino):");
                                sc.nextLine();
                                sexo = sc.nextLine();
                                clienteCP.sexo = sexo;

                                System.out.println("Digite seu CPF:");
                                cpf = sc.nextLine();
                                clienteCP.CPF = cpf;

                                System.out.println("Digite seu endereço:");
                                endereco = sc.nextLine();
                                clienteCP.endereco = endereco;

                                System.out.println("Digite o limite que deseja para conta " +
                                        "(para essa modalidade de conta o limite mínimo é de R$ 100,00 e de R$ 1000,00 o máximo):");
                                limite = sc.nextFloat();
                                try {
                                    contaP.setLimite(limite);
                                }catch (IllegalArgumentException e){
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("Digite o numero da agência");
                                agencia = sc.nextInt();

                                if (!Agencia.existe(agencia, agencias)){
                                    throw new AgenciaInexistenteException("Agência inexistente.");
                                }

                                for (Agencia atual: agencias){
                                    if (atual.getNumeroDaAgencia() == agencia){
                                        contaP.setAgencia(atual);
                                    }
                                }

                                System.out.println("Digite o numero da conta");
                                conta = sc.nextInt();
                                if (Conta.existe(conta, contas)){
                                    throw new ContaExistenteException("Conta com essa número já existente.");
                                }else {
                                    contaP.setNumeroDaConta(conta);
                                }

                                System.out.println("Defina uma senha numerica de 6 dígitos:");
                                senha = sc.nextInt();
                                contaP.setSenha(senha);

                                contas.add(contaP);
                                contaP.getAgencia().contasPertencentes.add(contaP);

                                break;

                            case 3:

                                PessoaFisica clienteCUni = new PessoaFisica();
                                ContaUniversitaria contaUni = new ContaUniversitaria();

                                contaUni.setDono(clienteCUni);

                                System.out.println("Digite seu nome:");
                                sc.nextLine();
                                nome = sc.nextLine();
                                clienteCUni.nome = nome;

                                System.out.println("Digite a sua idade:");
                                idade = sc.nextInt();
                                clienteCUni.idade = idade;

                                System.out.println("Digite seu sexo (Feminino/Masculino):");
                                sc.nextLine();
                                sexo = sc.nextLine();
                                clienteCUni.sexo = sexo;

                                System.out.println("Digite seu CPF:");
                                cpf = sc.nextLine();
                                clienteCUni.CPF = cpf;

                                System.out.println("Digite seu endereço:");
                                endereco = sc.nextLine();
                                clienteCUni.endereco = endereco;

                                System.out.println("Digite o limite que deseja para conta " +
                                        "(para essa modalidade de conta o limite mínimo é de R$ 0,00 e de R$ 500,00 o máximo):");
                                limite = sc.nextFloat();
                                try {
                                    contaUni.setLimite(limite);
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("Digite o numero da agência");
                                agencia = sc.nextInt();
                                if (!Agencia.existe(agencia, agencias)){
                                    throw new AgenciaInexistenteException("Agência inexistente.");
                                }

                                for (Agencia atual: agencias){
                                    if (atual.getNumeroDaAgencia() == agencia){
                                        contaUni.setAgencia(atual);
                                    }
                                }

                                System.out.println("Digite o numero da conta");
                                conta = sc.nextInt();
                                if (Conta.existe(conta, contas)){
                                    throw new ContaExistenteException("Conta com essa número já existente.");
                                }else {
                                    contaUni.setNumeroDaConta(conta);
                                }

                                System.out.println("Defina uma senha numerica de 6 dígitos:");
                                senha = sc.nextInt();
                                contaUni.setSenha(senha);

                                contas.add(contaUni);
                                contaUni.getAgencia().contasPertencentes.add(contaUni);

                                break;
                        }
                    } else {
                        System.out.println("A opção digitada é inválida!\n");
                    }
                    break;

                case 2:
                    System.out.println("Digite qual das modalidades de conta você deseja criar:");
                    System.out.println("(1) Conta corrente.");
                    System.out.println("(2) Conta poupança.");
                    int opcao3 = sc.nextInt();

                    if ((opcao3 == 1) || (opcao3 == 2)) {
                        switch (opcao3) {
                            case 1:

                                PessoaJuridica empresaCC = new PessoaJuridica();
                                ContaCorrente contaC = new ContaCorrente();

                                contaC.setDono(empresaCC);

                                System.out.println("Digite o nome da empresa:");
                                sc.nextLine();
                                String nome = sc.nextLine();
                                empresaCC.nome = nome;

                                System.out.println("Digite o setor da empresa:");
                                String setor = sc.nextLine();
                                empresaCC.setor = setor;

                                System.out.println("Digite o CNPJ:");
                                String cnpj = sc.nextLine();
                                empresaCC.nome = cnpj;

                                System.out.println("Digite o numero de funcionários:");
                                int numFuncionarios = sc.nextInt();
                                empresaCC.numFuncionarios = numFuncionarios;

                                System.out.println("Digite seu endereço da empresa:");
                                sc.nextLine();
                                String endereco = sc.nextLine();
                                empresaCC.endereco = endereco;

                                System.out.println("\"Digite o limite que deseja para conta " +
                                        "(para essa modalidade de conta o limite deve ser superior a R$ -100.00)");
                                float limite = sc.nextFloat();
                                try {
                                    contaC.setLimite(limite);
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("Digite o numero da agência");
                                int agencia = sc.nextInt();
                                if (!Agencia.existe(agencia, agencias)){
                                    throw new AgenciaInexistenteException("Agência inexistente.");
                                }

                                for (Agencia atual: agencias){
                                    if (atual.getNumeroDaAgencia() == agencia){
                                        contaC.setAgencia(atual);
                                    }
                                }

                                System.out.println("Digite o numero da conta");
                                int conta = sc.nextInt();
                                if (Conta.existe(conta, contas)){
                                    throw new ContaExistenteException("Conta com essa número já existente.");
                                }else {
                                    contaC.setNumeroDaConta(conta);
                                }

                                System.out.println("Defina uma senha numerica de 6 dígitos:");
                                int senha = sc.nextInt();
                                contaC.setSenha(senha);

                                contas.add(contaC);
                                contaC.getAgencia().contasPertencentes.add(contaC);

                                break;
                            case 2:

                                PessoaJuridica empresaCP = new PessoaJuridica();
                                ContaPoupanca contaP = new ContaPoupanca();

                                contaP.setDono(empresaCP);

                                System.out.println("Digite o nome da empresa:");
                                sc.nextLine();
                                nome = sc.nextLine();
                                empresaCP.nome = nome;

                                System.out.println("Digite o setor da empresa:");
                                setor = sc.nextLine();
                                empresaCP.setor = setor;

                                System.out.println("Digite o CNPJ:");
                                cnpj = sc.nextLine();
                                empresaCP.nome = cnpj;

                                System.out.println("Digite o numero de funcionários:");
                                numFuncionarios = sc.nextInt();
                                empresaCP.numFuncionarios = numFuncionarios;

                                System.out.println("Digite seu endereço da empresa:");
                                sc.nextLine();
                                endereco = sc.nextLine();
                                empresaCP.endereco = endereco;

                                System.out.println("Digite o limite que deseja para conta " +
                                        "(para essa modalidade de conta o limite mínimo é de R$ 100,00 e de R$ 1000,00 o máximo):");
                                limite = sc.nextFloat();
                                try {
                                    contaP.setLimite(limite);
                                }catch (IllegalArgumentException e) {
                                    System.out.println(e.getMessage());
                                }

                                System.out.println("Digite o numero da agência");
                                agencia = sc.nextInt();
                                if (!Agencia.existe(agencia, agencias)){
                                    throw new AgenciaInexistenteException("Agência inexistente.");
                                }

                                for (Agencia atual: agencias){
                                    if (atual.getNumeroDaAgencia() == agencia){
                                        contaP.setAgencia(atual);
                                    }
                                }

                                System.out.println("Digite o numero da conta");
                                conta = sc.nextInt();
                                if (Conta.existe(conta, contas)){
                                    throw new ContaExistenteException("Conta com essa número já existente.");
                                }else {
                                    contaP.setNumeroDaConta(conta);
                                }

                                System.out.println("Defina uma senha numerica de 6 dígitos:");
                                senha = sc.nextInt();
                                contaP.setSenha(senha);

                                contas.add(contaP);
                                contaP.getAgencia().contasPertencentes.add(contaP);

                                break;
                        }
                    } else {
                        System.out.println("A opção digitada é inválida!\n");
                    }
                    break;
            }

        }else{
            System.out.println("A opção digitada é inválida!\n");
        }

    }

    public static void loginCliente () throws ContaInexistenteException{

        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o numero da agência:");
        int numeroDaAgencia = sc.nextInt();
        if (Agencia.existe(numeroDaAgencia, agencias)){
            System.out.println("Digite o numero da conta:");
            int numeroDaConta = sc.nextInt();
            if (Conta.existe(numeroDaConta, contas)){
                Conta contaAtual = Conta.retornaContaIgual(numeroDaConta, contas);
                    System.out.println("Digite a senha numerica de 6 dígitos");
                    int senha = sc.nextInt();
                    if (senha == contaAtual.getSenha()) {
                        int opcao = 0;
                        do {
                            System.out.println("Digite qual ação deseja realizar:");
                            System.out.println("(1) Depósito.");
                            System.out.println("(2) Saque.");
                            System.out.println("(3) Transferência.");
                            System.out.println("(4) Excluir Conta.");
                            System.out.println("(5) Logout.");
                            opcao = sc.nextInt();
                            if ((opcao == 1) || (opcao == 2) || (opcao == 3) || (opcao == 4) || (opcao == 5)) {
                                switch (opcao) {
                                    case 1:
                                        System.out.println("Digite o valor do depósito:");
                                        double valor = sc.nextDouble();
                                        try {
                                            contaAtual.depositar(valor);
                                        } catch (ValorNegativoException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println("Saldo: R$ " + contaAtual.getSaldo());
                                        break;
                                    case 2:
                                        System.out.println("Digite o valor do saque:");
                                        valor = sc.nextDouble();
                                        try {
                                            contaAtual.sacar(valor);
                                        } catch (ValorNegativoException e) {
                                            System.out.println(e.getMessage());
                                        } catch (SemLimiteException e) {
                                            System.out.println(e.getMessage());
                                        }
                                        System.out.println("Saldo: R$ " + contaAtual.getSaldo());
                                        break;
                                    case 3:
                                        System.out.println("Digite o numero da conta de destino.");
                                        int contaDestino = sc.nextInt();
                                        if (Conta.existe(contaDestino, contas)) {
                                            for (Conta atualDestino : contas) {
                                                if (atualDestino.getNumeroDaConta() == contaDestino) {
                                                    System.out.println("Digite o valor do transferencia:");
                                                    sc.nextLine();
                                                    valor = sc.nextDouble();
                                                    try {
                                                        contaAtual.transferir(atualDestino, valor);

                                                    } catch (ValorNegativoException e) {
                                                        System.out.println(e.getMessage());
                                                    } catch (SemLimiteException e) {
                                                        System.out.println(e.getMessage());
                                                    }
                                                    System.out.println("Saldo: R$ " + contaAtual.getSaldo());
                                                }
                                            }
                                        } else {
                                            throw new ContaInexistenteException("A conta digitada não existe.");
                                        }
                                        break;
                                    case 4:
                                        contaAtual.exluirConta(contaAtual, contas, contaAtual.getAgencia());
                                        System.out.println("Conta removida com sucesso.");
                                        opcao = 5;
                                        break;
                                    case 5:
                                        break;
                                }

                            } else {
                                System.out.println("A opção digitada é inválida!\n");
                            }
                        } while (opcao != 5);

                    } else {
                        System.out.println("Senha incorreta.");
                    }
            }else{
                System.out.println("Conta digitada não existe.");
            }
        }else{
            System.out.println("Agência digitada não existe.");
        }
    }

    public static void loginAdmin() throws AgenciaExistenteException, AgenciaInexistenteException{
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o nome de login:");
        String login = sc.nextLine();
        if (login.equals("admin")){
            System.out.println("Digite a senha");
            String senha = sc.nextLine();
            if(senha.equals("ib2021")){
                int opcao = 0;
                do {
                    System.out.println("Digite qual ação deseja realizar:");
                    System.out.println("(1) Criar uma nova agência.");
                    System.out.println("(2) Excluir agência.");
                    System.out.println("(3) Logout.");
                    opcao = sc.nextInt();
                    if ((opcao == 1) || (opcao == 2) || (opcao == 3)) {
                        switch (opcao) {
                            case 1:
                                System.out.println("Digite qual será o número da agência:");
                                int novaAgencia = sc.nextInt();

                                if (Agencia.existe(novaAgencia, agencias)){
                                    throw new AgenciaExistenteException("Número de agência existente.");
                                } else{
                                    Agencia agencia = new Agencia(novaAgencia);
                                    agencias.add(agencia);
                                    System.out.println("Agência criada com sucesso.");
                                }
                                break;

                            case 2:
                                System.out.println("Digite o numero da agência que deseja excluir:");
                                int deletarAgencia = sc.nextInt();


                                if (!Agencia.existe(deletarAgencia, agencias)){
                                    throw new AgenciaInexistenteException("Agência inexistente.");
                                }else {
                                    Agencia agenciaADeletar = Agencia.retornaAgenciaIgual(deletarAgencia,agencias);
                                    agenciaADeletar.excluirAgencia(agenciaADeletar, agencias, contas);
                                }
                                break;

                            case 3:
                                break;
                        }
                    } else {
                        System.out.println("A opção digitada é inválida!\n");
                    }
                }while(opcao != 3);
            }else{
                System.out.println("Senha incorreta!");
            }
        }else{
            System.out.println("Nome incorreto!");
        }
    }

    public static void main (String [] args) {
        int opcao = 0;
        Scanner sc = new Scanner(System.in);
        contas = Conta.carregaContas("./contas");
        agencias = Agencia.carregaAgencias("./agencias");

        do {
            System.out.println("Bem vindo ao nosso sistema bancário!");
            System.out.println("Digite qual das opções você deseja:");
            System.out.println("(1) Criar uma conta.");
            System.out.println("(2) Fazer login como cliente.");
            System.out.println("(3) Fazer login como admin.");
            System.out.println("(4) Sair.");
            opcao = sc.nextInt();

            if ((opcao == 1) || (opcao == 2) || (opcao == 3) || (opcao == 4)) {
                switch (opcao) {
                    case 1:
                        try {
                            CriarConta();
                        } catch (AgenciaInexistenteException e) {
                            System.out.println(e.getMessage());
                        } catch (ContaExistenteException e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case 2:
                        try{
                            loginCliente();
                        }catch (ContaInexistenteException e ){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            loginAdmin();
                        }catch (AgenciaExistenteException e){
                            System.out.println(e.getMessage());
                        }catch (AgenciaInexistenteException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        break;
                }
            } else {
                System.out.println("A opção digitada é inválida!\n");
            }

        }while (opcao != 4);

        System.out.println("\nObrigada por usar os nossos serviços");

        Conta.salvarContas("./contas", contas);
        Agencia.salvarAgencias("./agencias", agencias);

    }
}

