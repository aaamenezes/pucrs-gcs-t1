import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    Usuario usuarioLogado;
    private Scanner scanner = new Scanner(System.in);
    private BancoUsuarios usuarios = new BancoUsuarios();
    private BancoAutorizacaoExames autorizacaoExames = new BancoAutorizacaoExames();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public App() {
        login();
        inicializarDados();
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
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Paciente");
        System.out.println("2 - Medico");
        System.out.println("3 - Administrador");
        int opcao = validaOp(1, 3);

        System.out.println("Digite o nome do usuário:");
        String nome = this.scanner.nextLine();

        switch (opcao) {
            case 1:
                this.usuarioLogado = new Paciente(nome);
                break;
            case 2:
                this.usuarioLogado = new Medico(nome);
                break;
            case 3:
                this.usuarioLogado = new Administrador(nome);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                login();
                return;
        }

        System.out.println("Usuário logado: " + this.usuarioLogado.getNome());
        System.out.println("Iniciais do usuário: " + this.usuarioLogado.getIniciais());
        System.out.println("Boas vindas, " + this.usuarioLogado.getNome() + "!");
    }

    /* -------| MENUS |------- */
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
        System.out.println("Escolha uma das opções");
        System.out.println("1 - Listar minhas autorizações de exame");
        System.out.println("2 - Sair");
        int opcao = validaOp(1, 2);

        switch (opcao) {
            case 1:
                ArrayList<AutorizacaoExame> lista = ((Paciente) this.usuarioLogado)
                        .listarAutorizacaoExames(this.autorizacaoExames);
                if (lista.isEmpty()) {
                    System.out.println("Nenhuma autorização encontrada.");
                } else {
                    for (AutorizacaoExame a : lista) {
                        System.out.println(a);
                    }
                }
                showMenuPaciente();
                break;
            case 2:
                System.out.println("Saindo...");
                break;
        }
    }

    private void showMenuMedico() {
        int op = 0;
        do {
            System.out.println("\n|-----| [1] Incluir autorização de exame");
            System.out.println("|-----| [2] Listar autorizações");
            System.out.println("|-----| [0] Voltar");
            op = validaOp(0, 2);

            switch (op) {
                case 1:
                    incluirAutorizacao(usuarioLogado);
                    break;
                case 2:
                    listarAutorizacaoExames();
                    break;
                case 0:
                    break;
                default:
                    break;
            }
        } while (op != 0);
    }

    private void showMenuAdministrador() {
        int opcao = 0;

        while (opcao <= 0 || opcao >= 8) {
            System.out.println("Escolha uma das opções");
            System.out.println("1 - Ver nome do usuário");
            System.out.println("2 - Ver iniciais do usuário");
            System.out.println("3 - Incluir usuário");
            System.out.println("4 - Listar autorizações de exames de paciente");
            System.out.println("5 - Listar autorizações de exames de médico");
            System.out.println("6 - Trocar usuário logado");
            System.out.println("7 - Sair");
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
                listarAutorizacaoExamesPorParteNomePaciente();
                showMenuAdministrador();
                break;
            case 5:
                listarAutorizacaoExamesPorParteNomeMedico();
                showMenuAdministrador();
                break;
            case 6:
                login();
                mostrarMenu();
                break;
            case 7:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                showMenuAdministrador();
                break;
        }
    }

    /* ------| OUTROS MÉTODOS |------ */
    private void verNomeUsuario() {
        System.out.println(this.usuarioLogado.getNome());
    }

    private void verIniciaisUsuario() {
        System.out.println(this.usuarioLogado.getIniciais());
    }

    private void marcarExameComoRealizadoPaciente() {
        System.out.println("Para marcar como realizado o exame, insira a data do exame no formato dd/MM/yyyy: ");
        String dataInserida = scanner.nextLine();

        LocalDate dataRealizada = LocalDate.parse(dataInserida, formatter);

        autorizacaoExames.marcarAutorizacaoExameComoRealizado(null, dataRealizada, null);
    }

    private void listarAutorizacaoExamesPorParteNomePaciente() {
        System.out.println("Digite a parte do nome do paciente:");
        String parteNome = scanner.nextLine();
        Paciente paciente = this.usuarios.buscarPacientePeloNome(parteNome);

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        for (AutorizacaoExame autorizacaoExame : autorizacaoExames.listarAutorizacaoExamesPorPaciente(paciente)) {
            System.out.println(autorizacaoExame);
        }
    }

    private void listarAutorizacaoExamesPorParteNomeMedico() {
        System.out.println("Digite a parte do nome do médico:");
        String parteNome = scanner.nextLine();
        Medico medico = this.usuarios.buscarMedicoPeloNome(parteNome);

        if (medico == null) {
            System.out.println("Médico não encontrado.");
            return;
        }

        for (AutorizacaoExame autorizacaoExame : autorizacaoExames.listarAutorizacaoExamesPorMedico(medico)) {
            System.out.println(autorizacaoExame);
        }
    }

    /*--------|   INCLUSÃO DE DADOS   |--------*/
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

    public TipoExame selecionaTipoExame() {
        TipoExame tipo;
        for (int i = 0; i < TipoExame.values().length; i++) {
            System.out.println("|-----| [" + i + "] " + TipoExame.values()[i].getDescricao());
        }

        int exame = validaOp(0, 19);

        tipo = TipoExame.values()[exame];

        return tipo;
    }

    public int validaOp(int min, int max) {
        int op = this.scanner.nextInt();
        this.scanner.nextLine();

        while (op < min || op > max) {
            System.out.print("Opção inválida. Tente novamente: ");
            op = this.scanner.nextInt();
            this.scanner.nextLine();
        }

        return op;
    }

    /* ------| FUNCIONALIDADES MÉDICO |------ */
    public void incluirAutorizacao(Usuario user) {
        System.out.print("Digite o nome do paciente: ");
        this.scanner.nextLine();
        String pac = this.scanner.nextLine();
        Usuario u = usuarios.buscarPacientePeloNome(pac);
        if (u == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }
        Paciente paciente = (Paciente) u;

        System.out.println("Selecione o tipo de exame: ");
        TipoExame tipo = selecionaTipoExame();
        Exame exame = new Exame(tipo);

        Medico medico = (Medico) user;
        AutorizacaoExame autorizacao = new AutorizacaoExame(medico, paciente, exame);

        autorizacaoExames.adicionarAutorizacao(autorizacao);
        System.out.println("Autorização de Exame criada.");
        System.out.println(autorizacao.toString());
    }

    public void listarAutorizacaoExames() {
        System.out.println("\nDeseja listar por:");
        System.out.println("[1] Paciente\n[2] Tipo de Exame");
        int op = validaOp(1, 2);
        ArrayList<AutorizacaoExame> lista;
        if (op == 1) {
            System.out.print("Digite o nome do paciente: ");
            this.scanner.nextLine();
            String pac = this.scanner.nextLine();
            Paciente paciente = usuarios.buscarPacientePeloNome(pac);
            if (paciente == null) {
                System.out.println("Paciente não encontrado.");
                return;
            }
            lista = autorizacaoExames.listarAutorizacaoExamesPorPaciente(paciente);
        } else {
            System.out.println("Selecione o Tipo de Exame: ");
            TipoExame tipo = selecionaTipoExame();
            lista = autorizacaoExames.listarAutorizacaoExamesPorTipo(tipo);
        }

        lista.sort(Comparator.comparing(AutorizacaoExame::getDataCadastro));

        for (AutorizacaoExame a : lista) {
            System.out.println(a.toString());
        }
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
        aut1.setDataCadastro(LocalDate.of(2026, 2, 18));

        // Exame 2
        Exame e2 = new Exame(TipoExame.RAIO_X);
        AutorizacaoExame aut2 = new AutorizacaoExame(med1, pac2, e2);
        this.autorizacaoExames.adicionarAutorizacao(aut2);

        // Exame 3
        Exame e3 = new Exame(TipoExame.ULTRASSOM);
        AutorizacaoExame aut3 = new AutorizacaoExame(med2, pac1, e3);
        this.autorizacaoExames.adicionarAutorizacao(aut3);
        aut3.setDataCadastro(LocalDate.of(2026, 2, 25));

        // Exame 4
        Exame e4 = new Exame(TipoExame.RESSONANCIA_MAGNETICA);
        AutorizacaoExame aut4 = new AutorizacaoExame(med2, pac2, e4);
        this.autorizacaoExames.adicionarAutorizacao(aut4);

        // Exame 5
        Exame e5 = new Exame(TipoExame.TOMOGRAFIA);
        AutorizacaoExame aut5 = new AutorizacaoExame(med1, pac1, e5);
        this.autorizacaoExames.adicionarAutorizacao(aut5);
        aut5.setDataCadastro(LocalDate.of(2026, 3, 14));

        // Exame 6
        Exame e6 = new Exame(TipoExame.ELETROCARDIOGRAMA);
        AutorizacaoExame aut6 = new AutorizacaoExame(med1, pac2, e6);
        this.autorizacaoExames.adicionarAutorizacao(aut6);

        // Exame 7
        Exame e7 = new Exame(TipoExame.EXAME_SANGUE);
        AutorizacaoExame aut7 = new AutorizacaoExame(med2, pac1, e7);
        this.autorizacaoExames.adicionarAutorizacao(aut7);
        aut7.setDataCadastro(LocalDate.of(2026, 4, 11));

        // Exame 8
        Exame e8 = new Exame(TipoExame.PRESSAO_ARTERIAL);
        AutorizacaoExame aut8 = new AutorizacaoExame(med2, pac2, e8);
        this.autorizacaoExames.adicionarAutorizacao(aut8);

        // Exame 9
        Exame e9 = new Exame(TipoExame.GLICEMIA_JEJUM);
        AutorizacaoExame aut9 = new AutorizacaoExame(med1, pac1, e9);
        this.autorizacaoExames.adicionarAutorizacao(aut9);
        aut9.setDataCadastro(LocalDate.of(2026, 4, 23));

        // Exame 10
        Exame e10 = new Exame(TipoExame.COLESTEROL_TOTAL);
        AutorizacaoExame aut10 = new AutorizacaoExame(med1, pac2, e10);
        this.autorizacaoExames.adicionarAutorizacao(aut10);
    }
}
