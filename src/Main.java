import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CriarConta configConta = new CriarConta();

        byte Menu;
        do {
            System.out.println();
            System.out.println("---------------Banco Intactoz--------------");
            System.out.println("          BEM VINDO AO NOSSO BANCO!!!");
            System.out.println("-------------------------------------------");
            System.out.println("1- Cadastro de conta");
            System.out.println("2- Login");
            System.out.println("3- Somente acesso autorizado!");
            System.out.println("4- Exit");
            System.out.println("-------------------------------------------");
            System.out.print("Digite a opção desejada: ");
            Menu = input.nextByte();
            System.out.println();
            switch(Menu) {
                case 0:
                    break;
                case 1:
                    configConta.cadastro();
                    break;
                case 2:
                    configConta.autlogin();
                    break;
                case 3:
                    configConta.loginadm();
                    break;
                case 4:
                    System.out.println("Saindo do Banco Intactoz...");
                    System.exit(0);
                default:
                    System.out.println("Error: Você escolheu uma opção invalida...");
            }
        } while(Menu != 4);

    }
}

