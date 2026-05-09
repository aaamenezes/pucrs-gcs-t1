import java.util.Scanner;

public class App {
    Usuario usuarioLogado;
    private Scanner scanner = new Scanner(System.in);
    private BancoUsuarios usuarios = new BancoUsuarios();
    private BancoAutorizacaoExames autorizacaoExames = new BancoAutorizacaoExames();

    public App() {
        login();
    }

    public void executar() {
        if (this.usuarioLogado != null) {
            mostrarMenu();
        } else {
            System.out.println("Nenhum usuário logado. Faça login para acessar o sistema.");
            login();
        }
    }

    /*------|   MÉTODO GERENCIADOR DE LOGIN ATIVO  |------*/
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

        System.out.println("Usuário logado: " + this.usuarioLogado.getNome());
        System.out.println("Iniciais do usuário: " + this.usuarioLogado.getIniciais());
    }

    public void mostrarMenu() {
        if (this.usuarioLogado instanceof Paciente) {
            showMenuPaciente();
        } else if (this.usuarioLogado instanceof Medico) {
            showMenuMedico();
        } else if (this.usuarioLogado instanceof Administrador) {
            showMenuAdministrador();
        } else {
            System.out.println("Tipo de usuário desconhecido.");
        }
    }

    private void showMenuPaciente() {
    }

    private void showMenuMedico() {
    }

    private void showMenuAdministrador() {
        System.out.println("Seja bem vindo, adm. " + this.usuarioLogado.getNome() + "!");

        int opcao = 0;

        while (opcao <= 0 || opcao >= 5) {
            System.out.println("Escolha uma das opções");
            System.out.println("1 - Ver nome do usuário");
            System.out.println("2 - Ver iniciais do usuário");
            System.out.println("3 - Incluir usuário");
            System.out.println("4 - Sair");
            opcao = this.scanner.nextInt();
            this.scanner.nextLine();
        }

        switch (opcao) {
            case 1:
                verNomeUsuario();
                showMenuAdministrador();
                break;
            case 2:
                verIniciaisUsuario();
                showMenuAdministrador();
                break;
            case 3:
                incluirUsuario();
                showMenuAdministrador();
                break;
            case 4:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                showMenuAdministrador();
                break;
        }
    }

    private void verNomeUsuario() {
        System.out.println(this.usuarioLogado.getNome());
    }

    private void verIniciaisUsuario() {
        System.out.println(this.usuarioLogado.getIniciais());
    }

    private void incluirUsuario() {
        int opcao = 0;
        String nome;

        while (opcao <= 0 || opcao >= 4) {
            System.out.println("Qual o tipo de usuário a ser incluído?");
            System.out.println("1 - Paciente");
            System.out.println("2 - Medico");
            System.out.println("3 - Administrador");
            opcao = this.scanner.nextInt();
            this.scanner.nextLine();
        }

        System.out.println("Digite o nome do novo usuário:");
        nome = this.scanner.nextLine();
        Usuario novoUsuario = null;

        if (opcao == 1) {
            novoUsuario = new Paciente(nome);
        } else if (opcao == 2) {
            novoUsuario = new Medico(nome);
        } else if (opcao == 3) {
            novoUsuario = new Administrador(nome);
        }

        this.usuarios.adicionarUsuario(novoUsuario);
        System.out.println("Usuário " + nome + " incluído com sucesso!");
    }

    /*--------|   INCLUSÃO DE DADOS   |--------*/
    public void inicializarDados() {
        // ADICIONANDO 5 MÉDICOS
        this.usuarios.incluirMedico("Ana Silva");
        this.usuarios.incluirMedico("Bruno Costa");
        this.usuarios.incluirMedico("Carla Souza");
        this.usuarios.incluirMedico("Diego Lima");
        this.usuarios.incluirMedico("Elena Pires");

        // ADICIONANDO 12 PACIENTES
        this.usuarios.incluirPaciente("Fabio Santos");
        this.usuarios.incluirPaciente("Gisele Reis");
        this.usuarios.incluirPaciente("Helio Junior");
        this.usuarios.incluirPaciente("Iara Costa");
        this.usuarios.incluirPaciente("Joao Mello");
        this.usuarios.incluirPaciente("Kelly Rocha");
        this.usuarios.incluirPaciente("Leonardo Paz");
        this.usuarios.incluirPaciente("Maria Alice");
        this.usuarios.incluirPaciente("Natan Silva");
        this.usuarios.incluirPaciente("Olivia Ramos");
        this.usuarios.incluirPaciente("Paulo Lopes");
        this.usuarios.incluirPaciente("Rosa Maria");

        // ADICIONANDO 3 ADMINISTRADORES
        this.usuarios.incluirAdministrador("Admin Geral");
        this.usuarios.incluirAdministrador("Supervisor TI");
        this.usuarios.incluirAdministrador("Diretoria");

        // BUSCANDO MEDICOS E PACIENTES PARA CRIAR AUTORIZACAO DE EXAME
        Medico med1 = this.usuarios.buscarMedicoPeloNome("Ana Silva");
        Medico med2 = this.usuarios.buscarMedicoPeloNome("Bruno Costa");
        Paciente pac1 = this.usuarios.buscarPacientePeloNome("Fabio Santos");
        Paciente pac2 = this.usuarios.buscarPacientePeloNome("Gisele Reis");

        // CRIANDO EXAMES E AUTORIZAÇÕES
        Exame e1 = new Exame(TipoExame.HEMOGRAMA);
        AutorizacaoExame aut1 = new AutorizacaoExame(med1, pac1, e1);
        this.autorizacaoExames.adicionarAutorizacao(aut1);

        // Exame 2
        Exame e2 = new Exame(TipoExame.RAIO_X);
        AutorizacaoExame aut2 = new AutorizacaoExame(med1, pac2, e2);
        this.autorizacaoExames.adicionarAutorizacao(aut2);

        // Exame 3
        Exame e3 = new Exame(TipoExame.ULTRASSOM);
        AutorizacaoExame aut3 = new AutorizacaoExame(med2, pac1, e3);
        this.autorizacaoExames.adicionarAutorizacao(aut3);

        // Exame 4
        Exame e4 = new Exame(TipoExame.RESSONANCIA_MAGNETICA);
        AutorizacaoExame aut4 = new AutorizacaoExame(med2, pac2, e4);
        this.autorizacaoExames.adicionarAutorizacao(aut4);

        // Exame 5
        Exame e5 = new Exame(TipoExame.TOMOGRAFIA);
        AutorizacaoExame aut5 = new AutorizacaoExame(med1, pac1, e5);
        this.autorizacaoExames.adicionarAutorizacao(aut5);

        // Exame 6
        Exame e6 = new Exame(TipoExame.ELETROCARDIOGRAMA);
        AutorizacaoExame aut6 = new AutorizacaoExame(med1, pac2, e6);
        this.autorizacaoExames.adicionarAutorizacao(aut6);

        // Exame 7
        Exame e7 = new Exame(TipoExame.EXAME_SANGUE);
        AutorizacaoExame aut7 = new AutorizacaoExame(med2, pac1, e7);
        this.autorizacaoExames.adicionarAutorizacao(aut7);

        // Exame 8
        Exame e8 = new Exame(TipoExame.PRESSAO_ARTERIAL);
        AutorizacaoExame aut8 = new AutorizacaoExame(med2, pac2, e8);
        this.autorizacaoExames.adicionarAutorizacao(aut8);

        // Exame 9
        Exame e9 = new Exame(TipoExame.GLICEMIA_JEJUM);
        AutorizacaoExame aut9 = new AutorizacaoExame(med1, pac1, e9);
        this.autorizacaoExames.adicionarAutorizacao(aut9);

        // Exame 10
        Exame e10 = new Exame(TipoExame.COLESTEROL_TOTAL);
        AutorizacaoExame aut10 = new AutorizacaoExame(med1, pac2, e10);
        this.autorizacaoExames.adicionarAutorizacao(aut10);
    }
}
