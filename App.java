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
        inicializarDados();
    }

    public void executar() {
        login();
    }

    /*------|   MÉTODO GERENCIADOR DE LOGIN ATIVO  |------*/
    private void login() {
        System.out.println("\nEscolha uma opção:\n");
        System.out.println("1 - Paciente");
        System.out.println("2 - Medico");
        System.out.println("3 - Administrador");
        int opcao = validaOp(1, 3);

        switch (opcao) {
            case 1:
                this.usuarioLogado = selecionarPaciente();
                boasVindas();
                mostrarMenuPaciente();
                break;
            case 2:
                this.usuarioLogado = selecionarMedico();
                boasVindas();
                mostrarMenuMedico();
                break;
            case 3:
                this.usuarioLogado = selecionarAdministrador();
                boasVindas();
                mostrarMenuAdministrador();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                login();
                return;
        }
    }

    private Paciente selecionarPaciente() {
        System.out.println("\nEscolha o paciente:\n");
        ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

        for (Usuario usuario : this.usuarios.listar()) {
            if (usuario instanceof Paciente) {
                pacientes.add((Paciente) usuario);
                System.out.println(pacientes.size() + " - " + usuario.getNome());
            }
        }
        int opcao = validaOp(1, pacientes.size());
        return pacientes.get(opcao - 1);
    }

    private void mostrarMenuPaciente() {
        System.out.println("\nO que você deseja fazer?\n");
        System.out.println("1 - Listar minhas autorizações de exame");
        System.out.println("2 - Trocar usuário");
        System.out.println("3 - Sair");
        int opcao = validaOp(1, 3);

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
                mostrarMenuPaciente();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Saindo...");
                break;
        }
    }

    private Medico selecionarMedico() {
        System.out.println("\nEscolha o médico:\n");
        ArrayList<Medico> medicos = new ArrayList<Medico>();

        for (Usuario usuario : this.usuarios.listar()) {
            if (usuario instanceof Medico) {
                medicos.add((Medico) usuario);
                System.out.println(medicos.size() + " - " + usuario.getNome());
            }
        }
        int opcao = validaOp(1, medicos.size());
        return medicos.get(opcao - 1);
    }

    private void mostrarMenuMedico() {
        System.out.println("\nO que você deseja fazer?\n");
        System.out.println("1 - Incluir autorização de exame");
        System.out.println("2 - Listar autorizações");
        System.out.println("3 - Trocar usuário");
        System.out.println("4 - Sair");
        int op = validaOp(1, 4);

        switch (op) {
            case 1:
                incluirAutorizacao((Medico) usuarioLogado);
                break;
            case 2:
                listarAutorizacaoExames();
                break;
            case 3:
                login();
                break;
            case 4:
                break;
            default:
                break;
        }
    }

    public TipoExame selecionarTipoExame() {
        System.out.println("\nSelecione o tipo de exame:\n");

        TipoExame tipo;
        for (int i = 0; i < TipoExame.values().length; i++) {
            System.out.println(i + 1 + " - " + TipoExame.values()[i].getDescricao());
        }

        int exame = validaOp(1, TipoExame.values().length) - 1;

        tipo = TipoExame.values()[exame];

        return tipo;
    }

    private Administrador selecionarAdministrador() {
        System.out.println("\nEscolha o administrador:\n");
        ArrayList<Administrador> administradores = new ArrayList<Administrador>();

        for (Usuario usuario : this.usuarios.listar()) {
            if (usuario instanceof Administrador) {
                administradores.add((Administrador) usuario);
                System.out.println(administradores.size() + " - " + usuario.getNome());
            }
        }
        int opcao = validaOp(1, administradores.size());
        return administradores.get(opcao - 1);
    }

    private void mostrarMenuAdministrador() {
        System.out.println("\nO que você deseja fazer?\n");
        System.out.println("1 - Ver nome do usuário");
        System.out.println("2 - Ver iniciais do usuário");
        System.out.println("3 - Incluir usuário");
        System.out.println("4 - Listar autorizações de exames de paciente");
        System.out.println("5 - Listar autorizações de exames de médico");
        System.out.println("6 - Trocar usuário logado");
        System.out.println("7 - Sair");
        int opcao = validaOp(1, 7);

        switch (opcao) {
            case 1:
                verNomeUsuario();
                mostrarMenuAdministrador();
                break;
            case 2:
                verIniciaisUsuario();
                mostrarMenuAdministrador();
                break;
            case 3:
                incluirUsuario();
                mostrarMenuAdministrador();
                break;
            case 4:
                listarAutorizacaoExamesPorParteNomePaciente();
                mostrarMenuAdministrador();
                break;
            case 5:
                listarAutorizacaoExamesPorParteNomeMedico();
                mostrarMenuAdministrador();
                break;
            case 6:
                login();
                break;
            case 7:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                mostrarMenuAdministrador();
                break;
        }
    }

    private void boasVindas() {
        System.out.println("Usuário logado: " + this.usuarioLogado.getNome());
        System.out.println("Iniciais do usuário: " + this.usuarioLogado.getIniciais());
        System.out.println("Boas vindas, " + this.usuarioLogado.getNome() + "!");
    }

    /* ------| OUTROS MÉTODOS |------ */
    private void verNomeUsuario() {
        System.out.println(this.usuarioLogado.getNome());
    }

    private void verIniciaisUsuario() {
        System.out.println(this.usuarioLogado.getIniciais());
    }

    // private void marcarExameComoRealizadoPaciente() {
    // System.out.println("Para marcar como realizado o exame, insira a data do
    // exame no formato dd/MM/yyyy: ");
    // String dataInserida = scanner.nextLine();

    // LocalDate dataRealizada = LocalDate.parse(dataInserida, formatter);

    // autorizacaoExames.marcarAutorizacaoExameComoRealizado(null, dataRealizada,
    // null);
    // }

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
    public void incluirAutorizacao(Medico medico) {
        System.out.print("\nEscolha o paciente:\n");

        Paciente paciente = selecionarPaciente();

        if (paciente == null) {
            System.out.println("Paciente não encontrado.");
            return;
        }

        TipoExame tipo = selecionarTipoExame();
        Exame exame = new Exame(tipo);

        AutorizacaoExame autorizacao = new AutorizacaoExame(medico, paciente, exame);

        autorizacaoExames.adicionarAutorizacao(autorizacao);
        System.out.println("Autorização de Exame criada.");
        System.out.println(autorizacao.toString());
    }

    public void listarAutorizacaoExames() {
        System.out.println("\nDeseja listar por:\n");
        System.out.println("1 - Paciente");
        System.out.println("2 - Tipo de Exame");
        int op = validaOp(1, 2);

        ArrayList<AutorizacaoExame> lista;

        switch (op) {
            case 1:
                Paciente paciente = selecionarPaciente();
                lista = autorizacaoExames.listarAutorizacaoExamesPorPaciente(paciente);
                break;
            case 2:
                TipoExame tipoExame = selecionarTipoExame();
                lista = autorizacaoExames.listarAutorizacaoExamesPorTipo(tipoExame);
                break;
            default:
                return;
        }

        lista.sort(Comparator.comparing(AutorizacaoExame::getDataCadastro));

        for (AutorizacaoExame a : lista) {
            System.out.println(a.toString());
        }
        mostrarMenuMedico();
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
