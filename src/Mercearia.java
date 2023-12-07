import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mercearia {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op = -1;

        System.out.println("=================================");
        System.out.println("Bem vindo ao Sistema Gerenciador!");
        System.out.println("=================================");

        do {
            System.out.println("Digite a opção desejada");
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Entrar na conta");
            System.out.println("3 - Alterar senha");
            System.out.println("Pressione 0 para sair");

            op = entrada.nextInt();
            switch (op) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    fazerLogin();
                    break;
                case 3:
                    alterarSenha();
                    break;
                default:
                    op = -1;
            }
        } while (op != -1);

        System.out.println("Volte sempre...");
    }
    private static void loginCliente(Cliente cliente){
        Scanner entrada = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        System.out.println("Bem vindo(a) " + cliente.getNome() + "!!!!!!");
        List<Produto> produtos = new ArrayList<>();
        menuListar();
        int opcao = entrada.nextInt();
        entrada.nextLine();
        if(opcao == 1) {
            System.out.println("Digite o tipo desejado: ");
            String tipo = entrada.nextLine();
            produtos=produtoDAO.findAllTipo(tipo);
            for (Produto prod: produtos) {
                System.out.println(produtoDAO.toString(prod));
            }
        } else if (opcao == 2) {
            System.out.println("Digite a marca desejada: ");
            String marca = entrada.nextLine();
            produtos=produtoDAO.findAllMarca(marca);
            for (Produto prod: produtos) {
                System.out.println(produtoDAO.toString(prod));
            }
        } else if (opcao == 3) {
            produtos=produtoDAO.findAll();
            for (Produto prod: produtos) {
                System.out.println(produtoDAO.toString(prod));
            }
        } else {
            System.out.println("Opcao inválida!");
        }

    }
    private static void loginGerente(Gerente gerente){
        Scanner entrada = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = null;
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        Relatorio relatorio = new Relatorio();

        String nome;
        String marca;
        int quantidade = 0;
        double preco = 0;
        String tipoProduto;
        String tipoAlteracao = "";
        int idUsuario;
        int idProduto;
        boolean possivel = true;

        System.out.println("Bem vindo " + gerente.getNome());

        System.out.println("Digite a opção desejada");
        System.out.println("1 - Cadastrar Produtos");
        System.out.println("2 - Ver Estoque");
        System.out.println("3 - Modificar Estoque");
        System.out.println("4 - Deletar Produto");
        System.out.println("5 - Listar Empregados");
        System.out.println("6 - Deletar Empregado");
        System.out.println("7 - Gerar Relatorio");
        System.out.println("Pressione 0 para sair");
        int opcao = entrada.nextInt();
        switch (opcao){
            case 1:
                entrada.nextLine();
                System.out.println("Digite o tipo do produto desejado: (cosmetico, mercado, padaria)");
                tipoProduto = entrada.nextLine();

                Produto produtoNovo = null;

                System.out.println("Digite o nome: ");
                nome = entrada.nextLine();
                System.out.println("Digite a marca: ");
                marca = entrada.nextLine();
                System.out.println("Digite a quantidade: ");
                quantidade = entrada.nextInt();
                System.out.println("Digite o preço: ");
                preco = entrada.nextDouble();

                if (tipoProduto.equals("mercado")){
                    produtoNovo = new Mercado(nome, marca, quantidade, preco);
                } else if (tipoProduto.equals("cosmetico")) {
                    produtoNovo = new Cosmeticos(nome, marca, quantidade, preco);
                }else if (tipoProduto.equals("padaria")) {
                    produtoNovo = new Padaria(nome, marca, quantidade, preco);
                }

                produtoDAO.insert(produtoNovo);
                break;
            case 2:
                menuListar();
                int listar = entrada.nextInt();
                List<Produto> produtos = new ArrayList<>();
                if(listar == 1) {
                    entrada.nextLine();
                    System.out.println("Digite o tipo desejado: ");
                    tipoProduto = entrada.nextLine();
                    produtos=produtoDAO.findAllTipo(tipoProduto);
                    for (Produto prod: produtos) {
                        System.out.println(produtoDAO.toString(prod));
                    }
                } else if (listar == 2) {
                    entrada.nextLine();
                    System.out.println("Digite a marca desejada");
                    marca = entrada.nextLine();
                    produtos=produtoDAO.findAllMarca(marca);
                    for (Produto prod: produtos) {
                        System.out.println(produtoDAO.toString(prod));
                    }
                } else if (listar == 3) {
                    entrada.nextLine();
                    produtos=produtoDAO.findAll();
                    for (Produto prod: produtos) {
                        System.out.println(produtoDAO.toString(prod));
                    }
                } else {
                    System.out.println("Opção inválida!");
                }
                break;
            case 3:
                alterarProduto();
                break;
            case 4:
                //pega o id do produto a ser deletado
                System.out.println("Digite o ID do produto que deseja apagar: ");
                idProduto = entrada.nextInt();

                //acha o produto
                produtoNovo = produtoDAO.findById(idProduto);

                //confere se tem permissao
                System.out.println("Digite seu ID e sua senha para confirmar");
                System.out.println("ID: ");
                idUsuario = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Senha: ");
                String senha = entrada.nextLine();

                if (conferePermissaoMain(idUsuario,senha,"Gerente")){
                    produtoDAO.delete(produtoNovo); //deleta
                }else {
                    System.out.println("Permissão negada!");
                }
                break;
            case 5:
                List<Usuario> usuarios = new ArrayList<>();
                usuarios = usuarioDAO.findAll();
                for (Usuario user: usuarios) {
                    System.out.println(usuarioDAO.toString(user));
                }
                break;
            case 6:
                System.out.println("Digite o ID do Usuario que deseja apagar: ");
                int userUser = entrada.nextInt();
                //acha o usuario
                Usuario usuario2;
                usuario2 = usuarioDAO.findById(userUser);

                //confere se tem permissao
                System.out.println("Digite seu ID e sua senha para confirmar");
                System.out.println("ID: ");
                idUsuario = entrada.nextInt();
                entrada.nextLine();
                System.out.println("Senha: ");
                senha = entrada.nextLine();

                if (conferePermissaoMain(idUsuario,senha,"Gerente")){
                    usuarioDAO.delete(usuario2); //deleta
                }else {
                    System.out.println("Permissão negada!");
                }
                break;
            case 7:
                List<Relatorio> rel;
                //nome, tudo, idProduto, idFuncionario
                menuListarRelatorio();
                listar = entrada.nextInt();
                if (listar == 1) {
                    System.out.println("Digite o idProduto");
                    idProduto = entrada.nextInt();
                    rel=relatorioDAO.findAllIdProduto(idProduto);
                    for (Relatorio relatorio1: rel) {
                        System.out.println(relatorioDAO.toString(relatorio1));
                    }
                } else if (listar == 2) {
                    System.out.println("Digite o idFuncionario");
                    idUsuario = entrada.nextInt();
                    rel=relatorioDAO.findAllIdUsuario(idUsuario);
                    for (Relatorio relatorio1: rel) {
                        System.out.println(relatorioDAO.toString(relatorio1));
                    }
                }else if (listar == 3) {
                    rel = relatorioDAO.findAll();
                    for (Relatorio relatorio1: rel) {
                        System.out.println(relatorioDAO.toString(relatorio1));
                    }
                } else {
                    System.out.println("Opcão inválida!");
                }
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
    private static void loginAtendente(Atendente atendente){
        Scanner entrada = new Scanner(System.in);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario;

        System.out.println("Bem vindo " + atendente.getNome());
        System.out.println("Digite a opção desejada");
        System.out.println("1 - Ver Estoque");
        System.out.println("2 - Modificar Estoque");
        System.out.println("Pressione outro número para sair");
        int opcao = entrada.nextInt();
        switch (opcao){
            case 1:
                menuListar();
                int listar = entrada.nextInt();
                List<Produto> produtos = new ArrayList<>();
                entrada.nextLine();
                if(listar==1) {
                    System.out.println("Digite o tipo desejado: ");
                    String tipoProduto = entrada.nextLine();
                    produtos=produtoDAO.findAllTipo(tipoProduto);
                    for (Produto prod: produtos) {
                        System.out.println(produtoDAO.toString(prod));
                    }
                } else if (listar==2) {
                    System.out.println("Digite a marca desejada");
                    String marca = entrada.nextLine();
                    produtos=produtoDAO.findAllMarca(marca);
                    for (Produto prod: produtos) {
                        System.out.println(produtoDAO.toString(prod));
                    }
                } else if (listar == 3) {
                    produtos=produtoDAO.findAll();
                    for (Produto prod: produtos) {
                        System.out.println(produtoDAO.toString(prod));
                    }
                } else {
                    System.out.println("Opcao inválida!");
                }
                break;
            case 2:
                alterarProduto();
                break;
            default:
                System.out.println("Opcao inválida!");
                break;
        }
    }

    private static void fazerLogin() {
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO user = new UsuarioDAO();

        int id;
        String senha;
        String nome;
        System.out.println("Digite seu nome: ");
        nome = entrada.nextLine();
        System.out.println("Digite seu id: ");
        id = entrada.nextInt();
        entrada.nextLine();
        System.out.println("Digite sua senha: ");
        senha = entrada.nextLine();
        if (user.confereCadastro(id, senha)) {
            String conferenciaCargo = user.findByCargo(id);
            if (conferenciaCargo.equals("Cliente")){
                Cliente cliente = new Cliente(id, nome, senha);
                loginCliente(cliente);
            } else if (conferenciaCargo.equals("Atendente")) {
                Atendente atendente = new Atendente(id, nome, senha);
                loginAtendente(atendente);
            } else if (conferenciaCargo.equals("Gerente")) {
                Gerente gerente = new Gerente(id, nome, senha);
                loginGerente(gerente);
            }

        }else{
            System.out.println("Cadastro não encontrado!");
        }
    }

    private static void alterarSenha() {
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO user = new UsuarioDAO();
        String nome;
        String senha;
        int id;

        System.out.println("Digite o seu nome: ");
        nome = entrada.nextLine();
        System.out.println("Digite uma nova senha: ");
        senha = entrada.nextLine();
        System.out.println("Digite seu ID");
        id = entrada.nextInt();
        String opcaoCargo = user.findByCargo(id);

        switch (opcaoCargo){
            case ("Cliente"):
                Cliente cliente = new Cliente(id, nome, senha);
                user.update(cliente);
                break;
            case ("Atendente"):
                Atendente atendente = new Atendente(id, nome, senha);
                user.update(atendente);
                break;
            case ("Gerente"):
                Gerente gerente = new Gerente(id, nome, senha);
                user.update(gerente);
                break;
        }

        System.out.println("Senha alterada com sucesso");
    }

    private static void cadastrarUsuario() {
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO user = new UsuarioDAO();
        Usuario usuario=null;
        String nome;
        String senha;
        int opcaoCargo=0;

        System.out.println("Digite o seu nome: ");
        nome = entrada.nextLine();
        System.out.println("Digite uma senha: ");
        senha = entrada.nextLine();
        System.out.println("Digite 1- Cliente | 2- Atendente | 3- Gerente: ");
        opcaoCargo = entrada.nextInt();

        switch (opcaoCargo){
            case 1:
                Cliente cliente = new Cliente(nome, senha);
                usuario = user.insert(cliente);
                break;
            case 2:
                Atendente atendente = new Atendente(nome, senha);
                usuario =user.insert(atendente);
                break;
            case 3:
                Gerente gerente = new Gerente(nome, senha);
                usuario = user.insert(gerente);
                break;
        }
        System.out.println("Usuário cadastrado com sucesso!" );
        user.toString(usuario);

    }

    private static void alterarProduto(){
        Scanner entrada = new Scanner(System.in);

        ProdutoDAO produtoDAO = new ProdutoDAO();
        UsuarioDAO usuarioDAO = null;
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        Relatorio relatorio = new Relatorio();
        Produto produtoNovo;


        int quantidade = 0;
        double preco = 0;
        String tipoProduto;
        String tipoAlteracao = "";
        int idUsuario;
        int idProduto;
        boolean possivel = true;

        //pega o id do produto a ser modificado
        System.out.println("Digite o id do produto que deseja modificar: ");
        idProduto = entrada.nextInt();

        //acha o produto
        produtoNovo = produtoDAO.findById(idProduto);

        //verifica o tipo de alteracao
        System.out.println("Para qual tipo de movimentação a alteração se refere?");
        System.out.println("1- Entrada de produtos | 2- Saída de produtos ");
        int op = entrada.nextInt();

        if (op == 1){
            System.out.println("Digite a quantidade de entrada: ");
            int alteracao = entrada.nextInt();

            tipoAlteracao = "Entrada";  //salva o tipo de alteração

            //faz a conta da nova quantidade e salva em quantidade
            quantidade = produtoNovo.getQuantidade() + alteracao;
        } else if (op == 2) {
            System.out.println("Digite a quantidade de saída: ");
            int alteracao = entrada.nextInt();

            tipoAlteracao = "Saída";    //salva o tipo de alteração

            //confere se tem estoque disponivel
            if (produtoNovo.getQuantidade() > alteracao) {
                //faz a conta da nova quantidade e salva em quantidade
                quantidade = produtoNovo.getQuantidade() - alteracao;
            }else{
                System.out.println("Estoque menor que o solicitado!");
                possivel = false; //atualiza a variavel para falso, nao e possivel fazer a alteracao
            }
        }

        if (possivel) {

            //confere se tem permissao
            System.out.println("Digite seu id e sua senha para confirmar");
            System.out.println("ID: ");
            idUsuario = entrada.nextInt();
            entrada.nextLine();
            System.out.println("Senha: ");
            String senha = entrada.nextLine();

            if(conferePermissaoMain(idUsuario, senha, "Gerente")) {
                //atualiza a quantidade no objeto
                produtoNovo.setQuantidade(quantidade);

                //atualiza no banco
                if (produtoDAO.update(produtoNovo)) {
                    double receita = relatorio.geradorReceita(quantidade, produtoNovo.getPreco());
                    relatorio = new Relatorio(produtoNovo.getId(), idUsuario, tipoAlteracao, quantidade, receita);
                    relatorioDAO.insert(relatorio);
                    System.out.println("Produto alterado!");
                }
            } else {
                System.out.println("Permissão negada!");
            }
        }
    }
    private static boolean conferePermissaoMain(int idUsuario, String senha, String cargo){
        Scanner entrada = new Scanner(System.in);

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if(usuarioDAO.conferePermissao(idUsuario, senha, cargo)) {
            return true;
        }
        return false;
    }
    private static void cadastrarProduto() {
        Scanner entrada = new Scanner(System.in);
        UsuarioDAO user = new UsuarioDAO();
        String nome;
        String senha;
        int opcaoCargo = 0;

        System.out.println("Digite o seu nome: ");
        nome = entrada.nextLine();
        System.out.println("Digite uma senha: ");
        senha = entrada.nextLine();
        System.out.println("Digite 1- Cliente | 2- Atendente | 3- Gerente: ");
        opcaoCargo = entrada.nextInt();

        switch (opcaoCargo){
            case 1:
                Cliente cliente = new Cliente(nome, senha);
                user.insert(cliente);
                break;
            case 2:
                Atendente atendente = new Atendente(nome, senha);
                user.insert(atendente);
                break;
            case 3:
                Gerente gerente = new Gerente(nome, senha);
                user.insert(gerente);
                break;
        }

        System.out.println("Usuario cadastrado: " + user);
    }

    private static void menuListar(){
        System.out.println("Escolha a forma que você quer listar os produtos: ");
        System.out.println("1 - Por Tipo");
        System.out.println("2 - Por Marca");
        System.out.println("3 - Todos");
    }
    private static void menuListarRelatorio(){
        System.out.println("Escolha a forma que você quer listar o relatório");
        System.out.println("1 - Pelo idProduto");
        System.out.println("2 - Pelo idFuncionario");
        System.out.println("3 - Todos");
    }
}