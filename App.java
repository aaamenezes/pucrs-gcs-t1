import java.util.Scanner;

public class App {
    Usuario usuarioLogado;
    static Scanner scanner = new Scanner(System.in);

    public App() {
        login();
    }

    public void executar() {

    }

    private void login() {
        String nome;
        int opcao = 0;

        while (opcao <= 0 || opcao >= 4) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Paciente");
            System.out.println("2 - Medico");
            System.out.println("3 - Administrador");
            opcao = this.scanner.nextInt();
            this.scanner.nextLine();
        }

        System.out.println("Digite o nome do usuário:");
        nome = this.scanner.nextLine();

        if (opcao == 1) {
            this.usuarioLogado = new Paciente(nome);
        } else if (opcao == 2) {
            this.usuarioLogado = new Medico(nome);
        } else if (opcao == 3) {
            this.usuarioLogado = new Administrador(nome);
        }
    }

    private void verNomeUsuario() {
        System.out.println(this.usuarioLogado.getNome());
    }

    private void verIniciaisUsuario() {
        System.out.println(this.usuarioLogado.getIniciais());
    }
}
