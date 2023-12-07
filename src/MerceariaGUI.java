import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class MerceariaGUI {

    public static void main(String[] args) {
        int op = -1;

        JOptionPane.showMessageDialog(null, "================================\nBem vindo(a) ao Gerenciador de Mercearia!\n================================");

        do {
            String[] options = {"Cadastrar Usuário", "Entrar na conta", "Alterar senha", "Sair"};
            op = JOptionPane.showOptionDialog(null, "Digite a opção desejada", "Menu Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            switch (op) {
                case 0:
                    cadastrarUsuario();
                    break;
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    alterarSenha();
                    break;
                default:
                    op = -1;
                    break;
            }
        } while (op != -1);

        JOptionPane.showMessageDialog(null, "Até logo! "+ "\n" +  "Volte sempre!");
    }

    private static void loginCliente(Cliente cliente) {
        boolean exit = false;

        do {
            String[] options = {"Tipo", "Marca", "Listar Todos"};
            int opcao = JOptionPane.showOptionDialog(null, "Bem-vindo(a) " + cliente.getNome() + "!\nEscolha como deseja procurar por um produto:", "Menu Cliente",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            ProdutoDAO produtoDAO = new ProdutoDAO();
            List<Produto> produtos = new ArrayList<>();

            switch (opcao) {
                case 0:
                    String[] tipos = {"Cosmeticos", "Padaria", "Mercado"};
                    int tipoOpcao = JOptionPane.showOptionDialog(null, "Selecione o tipo desejado:", "Seleção de Tipo",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tipos, tipos[0]);

                    switch (tipoOpcao) {
                        case 0:
                            produtos = produtoDAO.findAllTipo("Cosmeticos");
                            break;
                        case 1:
                            produtos = produtoDAO.findAllTipo("Padaria");
                            break;
                        case 2:
                            produtos = produtoDAO.findAllTipo("Mercado");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }
                    break;
                case 1:
                    String marca = JOptionPane.showInputDialog("Digite a marca desejada:");
                    produtos = produtoDAO.findAllMarca(marca);
                    break;
                case 2:
                    produtos = produtoDAO.findAll();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }

            StringBuilder result = new StringBuilder();
            for (Produto prod : produtos) {
                result.append(produtoDAO.toString(prod)).append("\n");
            }
            JOptionPane.showMessageDialog(null, result.toString(), "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);

            int confirmExit = JOptionPane.showConfirmDialog(null, "Deseja sair?", "Sair", JOptionPane.YES_NO_OPTION);
            if (confirmExit == JOptionPane.YES_OPTION) {
                exit = true;
            }
        } while (!exit);
    }


    private static void loginGerente(Gerente gerente) {
        boolean exit = false;

        do {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            RelatorioDAO relatorioDAO = new RelatorioDAO();

            String nome;
            String marca;
            int quantidade = 0;
            double preco = 0;
            int idUsuario;
            int idProduto;

            String[] options = {
                    "Cadastrar Produtos",
                    "Ver Estoque",
                    "Modificar Estoque",
                    "Deletar Produto",
                    "Listar Usuários",
                    "Deletar Empregado",
                    "Gerar Relatorio",
                    "Sair"
            };

            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Bem-vindo " + gerente.getNome() + "!",
                    "Menu Gerente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (opcao) {
                case 0:
                    String[] tipoProdutos = {"Cosméticos", "Padaria", "Mercado"};
                    int opcaoTipoProduto = JOptionPane.showOptionDialog(
                            null,
                            "Escolha o tipo de produto que deseja cadastrar:",
                            "Cadastro de Produto",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            tipoProdutos,
                            tipoProdutos[0]
                    );

                    nome = JOptionPane.showInputDialog("Digite o nome do produto:");
                    marca = JOptionPane.showInputDialog("Digite a marca do produto:");
                    quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto:"));
                    preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do produto:"));

                    Produto produtoNovo = null;

                    switch (opcaoTipoProduto) {
                        case 0:
                            produtoNovo = new Cosmeticos(nome, marca, quantidade, preco);
                            break;
                        case 1:
                            produtoNovo = new Padaria(nome, marca, quantidade, preco);
                            break;
                        case 2:
                            produtoNovo = new Mercado(nome, marca, quantidade, preco);
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }

                    if (produtoNovo != null) {
                        produtoDAO.insert(produtoNovo);
                        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                    }
                    break;
                case 1:
                    String[] produtoOptions = {"Tipo", "Marca", "Listar Todos"};
                    int opcaoProduto = JOptionPane.showOptionDialog(
                            null,
                            "Escolha como deseja procurar por um produto:",
                            "Ver estoque",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            produtoOptions,
                            produtoOptions[0]
                    );

                    List<Produto> produtos = new ArrayList<>();

                    switch (opcaoProduto) {
                        case 0:
                            String[] tipos = {"Cosméticos", "Padaria", "Mercado"};
                            int tipoOpcao = JOptionPane.showOptionDialog(
                                    null,
                                    "Selecione o tipo desejado:",
                                    "Seleção de Tipo",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    tipos,
                                    tipos[0]
                            );

                            switch (tipoOpcao) {
                                case 0:
                                    produtos = produtoDAO.findAllTipo("Cosmeticos");
                                    break;
                                case 1:
                                    produtos = produtoDAO.findAllTipo("Padaria");
                                    break;
                                case 2:
                                    produtos = produtoDAO.findAllTipo("Mercado");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                                    break;
                            }
                            break;
                        case 1:
                            marca = JOptionPane.showInputDialog("Digite a marca desejada:");
                            produtos = produtoDAO.findAllMarca(marca);
                            break;
                        case 2:
                            produtos = produtoDAO.findAll();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }

                    StringBuilder result = new StringBuilder();
                    for (Produto prod : produtos) {
                        result.append(produtoDAO.toString(prod)).append("\n");
                    }

                    JOptionPane.showMessageDialog(null, result.toString(), "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);

                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    deletarProduto();
                    break;
                case 4:
                    List<Usuario> usuarios = usuarioDAO.findAll();
                    StringBuilder userList = new StringBuilder();
                    for (Usuario user : usuarios) {
                        userList.append(usuarioDAO.toString(user)).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, userList.toString(), "Lista de Usuários", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 5:
                    deletarUsuario();
                    break;
                case 6:
                    String[] listarOpcoes = {"Pelo idProduto", "Pelo idUsuario", "Todos"};
                    int listar = JOptionPane.showOptionDialog(
                            null,
                            "Escolha a forma que você quer listar o relatório",
                            "Listar Relatório",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            listarOpcoes,
                            listarOpcoes[0]
                    );

                    List<Relatorio> rel = new ArrayList<>();

                    switch (listar) {
                        case 0:
                            idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o idProduto:"));
                            rel = relatorioDAO.findAllIdProduto(idProduto);
                            break;
                        case 1:
                            idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite o idFuncionario:"));
                            rel = relatorioDAO.findAllIdUsuario(idUsuario);
                            break;
                        case 2:
                            rel = relatorioDAO.findAll();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }

                    StringBuilder resultRelatorio = new StringBuilder();
                    for (Relatorio relatorio1 : rel) {
                        resultRelatorio.append(relatorioDAO.toString(relatorio1)).append("\n");
                    }

                    JOptionPane.showMessageDialog(null, resultRelatorio.toString(), "Relatório", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 7:
                    int confirmExit = JOptionPane.showConfirmDialog(
                            null,
                            "Deseja sair?",
                            "Sair",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirmExit == JOptionPane.YES_OPTION) {
                        exit = true;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (!exit);
    }


    private static void loginAtendente(Atendente atendente) {
        boolean exit = false;

        do {
            ProdutoDAO produtoDAO = new ProdutoDAO();

            String[] options = {"Ver Estoque", "Modificar Estoque", "Sair"};
            int opcao = JOptionPane.showOptionDialog(
                    null,
                    "Bem-vindo " + atendente.getNome() + "!\nEscolha a opção desejada:",
                    "Menu Atendente",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (opcao) {
                case 0:
                    String[] listarOptions = {"Por Tipo", "Por Marca", "Todos"};
                    int listarOpcao = JOptionPane.showOptionDialog(
                            null,
                            "Escolha como deseja listar os produtos:",
                            "Listar Produtos",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            listarOptions,
                            listarOptions[0]
                    );

                    List<Produto> produtos = new ArrayList<>();
                    StringBuilder result = new StringBuilder();

                    switch (listarOpcao) {
                        case 0:
                            String[] tipos = {"Cosmeticos", "Padaria", "Mercado"};
                            int tipoOpcao = JOptionPane.showOptionDialog(
                                    null,
                                    "Selecione o tipo desejado:",
                                    "Seleção de Tipo",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    tipos,
                                    tipos[0]
                            );
                            switch (tipoOpcao) {
                                case 0:
                                    produtos = produtoDAO.findAllTipo("Cosmeticos");
                                    break;
                                case 1:
                                    produtos = produtoDAO.findAllTipo("Padaria");
                                    break;
                                case 2:
                                    produtos = produtoDAO.findAllTipo("Mercado");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                                    break;
                            }
                            break;

                        case 1:
                            String marca = JOptionPane.showInputDialog("Digite a marca desejada:");
                            produtos = produtoDAO.findAllMarca(marca);
                            break;

                        case 2:
                            produtos = produtoDAO.findAll();
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }

                    for (Produto prod : produtos) {
                        result.append(produtoDAO.toString(prod)).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, result.toString(), "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 1:
                    alterarProduto();
                    break;
                case 2:

                    int confirmExit = JOptionPane.showConfirmDialog(
                            null,
                            "Deseja sair?",
                            "Sair",
                            JOptionPane.YES_NO_OPTION
                    );
                    if (confirmExit == JOptionPane.YES_OPTION) {
                        exit = true;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (!exit);
    }

    private static void fazerLogin() {
        UsuarioDAO user = new UsuarioDAO();

        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite seu ID:"));
        String senha = JOptionPane.showInputDialog("Digite sua senha:");

        if (user.confereCadastro(id, senha)) {
            String conferenciaCargo = user.findByCargo(id);
            switch (conferenciaCargo) {
                case "Cliente":
                    Cliente cliente = new Cliente(id, nome, senha);
                    loginCliente(cliente);
                    break;
                case "Atendente":
                    Atendente atendente = new Atendente(id, nome, senha);
                    loginAtendente(atendente);
                    break;
                case "Gerente":
                    Gerente gerente = new Gerente(id, nome, senha);
                    loginGerente(gerente);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro não encontrado!");
        }
    }

    private static void alterarSenha() {
        UsuarioDAO user = new UsuarioDAO();

        String nome = JOptionPane.showInputDialog("Digite seu nome:");
        String senha = JOptionPane.showInputDialog("Digite uma nova senha:");
        int id = Integer.parseInt(JOptionPane.showInputDialog("Digite seu ID:"));
        String opcaoCargo = user.findByCargo(id);

        switch (opcaoCargo) {
            case "Cliente":
                Cliente cliente = new Cliente(id, nome, senha);
                user.update(cliente);
                break;
            case "Atendente":
                Atendente atendente = new Atendente(id, nome, senha);
                user.update(atendente);
                break;
            case "Gerente":
                Gerente gerente = new Gerente(id, nome, senha);
                user.update(gerente);
                break;
        }

        JOptionPane.showMessageDialog(null, "Senha alterada com sucesso");
    }

    private static void cadastrarUsuario() {
        String nome = JOptionPane.showInputDialog("Digite o seu nome:");
        String senha = JOptionPane.showInputDialog("Digite uma senha:");
        String[] opcoesCargo = {"Cliente", "Atendente", "Gerente"};
        int opcaoCargo = JOptionPane.showOptionDialog(null, "Escolha o cargo:", "Cadastro de Usuário",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opcoesCargo, opcoesCargo[0]);

        UsuarioDAO user = new UsuarioDAO();
        Usuario usuario = null;

        switch (opcaoCargo) {
            case 0:
                Cliente cliente = new Cliente(nome, senha);
                usuario = user.insert(cliente);
                break;
            case 1:
                Atendente atendente = new Atendente(nome, senha);
                usuario = user.insert(atendente);
                break;
            case 2:
                Gerente gerente = new Gerente(nome, senha);
                usuario = user.insert(gerente);
                break;
        }

        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!\n" + user.toString(usuario));
    }

    private static void alterarProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        RelatorioDAO relatorioDAO = new RelatorioDAO();
        Relatorio relatorio = new Relatorio();

        int quantidade = 0;
        String tipoAlteracao = "";
        int idUsuario;
        boolean possivel = true;

        int idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto que deseja modificar:"));

        Produto produtoNovo = produtoDAO.findById(idProduto);

        int op = JOptionPane.showOptionDialog(
                null,
                "Escolha o tipo de movimentação de estoque:",
                "Tipo de Alteração",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                new String[]{"Entrada de produtos", "Saída de produtos"},
                "Entrada de produtos"
        );

        if (op == 0) {
            int alteracao = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de entrada:"));

            tipoAlteracao = "Entrada";
            quantidade = produtoNovo.getQuantidade() + alteracao;
        } else if (op == 1) {
            int alteracao = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de saída:"));

            tipoAlteracao = "Saída";

            if (produtoNovo.getQuantidade() > alteracao) {
                quantidade = produtoNovo.getQuantidade() - alteracao;
            } else {
                JOptionPane.showMessageDialog(null, "Estoque menor que o solicitado!");
                possivel = false; //atualiza a variável para falso, não é possível fazer a alteração
            }
        }

        if (possivel) {
            idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite seu ID para confirmar:"));
            String senha = JOptionPane.showInputDialog("Digite sua senha para confirmar:");

            if (conferePermissaoMain(idUsuario, senha, "Gerente")) {
                produtoNovo.setQuantidade(quantidade);

                if (produtoDAO.update(produtoNovo)) {
                    double receita = relatorio.geradorReceita(quantidade, produtoNovo.getPreco());
                    relatorio = new Relatorio(produtoNovo.getId(), idUsuario, tipoAlteracao, quantidade, receita);
                    relatorioDAO.insert(relatorio);
                    JOptionPane.showMessageDialog(null, "Produto alterado com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Permissão negada!");
            }
        }
    }

    private static void deletarProduto() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        int idProduto   ;
        int idUsuario;

        idProduto = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do produto que deseja apagar:"));

        Produto produtoNovo = produtoDAO.findById(idProduto);

        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite seu ID para confirmar:"));
        String senha = JOptionPane.showInputDialog("Digite sua senha para confirmar:");

        if (conferePermissaoMain(idUsuario, senha, "Gerente")) {
            int confirmacao = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza que deseja deletar o produto?\n" + produtoNovo.getDescricao(),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                produtoDAO.delete(produtoNovo);
                JOptionPane.showMessageDialog(null, "Produto deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Permissão negada!");
        }
    }

    private static void deletarUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int idUsuario;
        int idGerente;

        idUsuario = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do usuário que deseja apagar:"));

        Usuario usuario = usuarioDAO.findById(idUsuario);

        idGerente = Integer.parseInt(JOptionPane.showInputDialog("Digite seu ID para confirmar:"));
        String senha = JOptionPane.showInputDialog("Digite sua senha para confirmar:");

        if (conferePermissaoMain(idGerente, senha, "Gerente")) {
            int confirmacao = JOptionPane.showConfirmDialog(
                    null,
                    "Tem certeza que deseja deletar o usuário?\n" + usuario.getNome(),
                    "Confirmação",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacao == JOptionPane.YES_OPTION) {
                usuarioDAO.delete(usuario);
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Operação cancelada.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Permissão negada!");
        }
    }

    private static boolean conferePermissaoMain(int idUsuario, String senha, String cargo) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        if(usuarioDAO.conferePermissao(idUsuario, senha, cargo)) {
            return true;
        }
        return false;
    }

}