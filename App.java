import java.util.Scanner;

public class App {
    Usuario usuarioLogado;
    static Scanner scanner = new Scanner(System.in);
    private BancoUsuarios banco = new BancoUsuarios();
    private BancoAutorizacaoExames bancoAutorizacaoExames = new BancoAutorizacaoExames();

    public App() {
        login();
    }
      
    public void executar() {

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
    }

    private void verNomeUsuario() {
        System.out.println(this.usuarioLogado.getNome());
    }

    private void verIniciaisUsuario() {
        System.out.println(this.usuarioLogado.getIniciais());
    }

      
 /*--------|   INCLUSÃO DE DADOS   |--------*/
    public void inicializarDados() {
        // ADICIONANDO 5 MÉDICOS 
        this.banco.incluirMedico("Ana Silva");
        this.banco.incluirMedico("Bruno Costa");
        this.banco.incluirMedico("Carla Souza");
        this.banco.incluirMedico("Diego Lima");
        this.banco.incluirMedico("Elena Pires");

        // ADICIONANDO 12 PACIENTES
        this.banco.incluirPaciente("Fabio Santos");
        this.banco.incluirPaciente("Gisele Reis");
        this.banco.incluirPaciente("Helio Junior");
        this.banco.incluirPaciente("Iara Costa");
        this.banco.incluirPaciente("Joao Mello");
        this.banco.incluirPaciente("Kelly Rocha");
        this.banco.incluirPaciente("Leonardo Paz");
        this.banco.incluirPaciente("Maria Alice");
        this.banco.incluirPaciente("Natan Silva");
        this.banco.incluirPaciente("Olivia Ramos");
        this.banco.incluirPaciente("Paulo Lopes");
        this.banco.incluirPaciente("Rosa Maria");

        //ADICIONANDO 3 ADMINISTRADORES
        this.banco.incluirAdministrador("Admin Geral");
        this.banco.incluirAdministrador("Supervisor TI");
        this.banco.incluirAdministrador("Diretoria");

        // BUSCANDO MEDICOS E PACIENTES PARA CRIAR AUTORIZACAO DE EXAME
        Medico med1 = banco.buscarMedicoPeloNome("Ana Silva");
        Medico med2 = banco.buscarMedicoPeloNome("Bruno Costa");
        Paciente pac1 = banco.buscarPacientePeloNome("Fabio Santos");
        Paciente pac2 = banco.buscarPacientePeloNome("Gisele Reis");

        //CRIANDO EXAMES E AUTORIZAÇÕES
        Exame e1 = new Exame(TipoExame.HEMOGRAMA);
        AutorizacaoExame aut1 = new AutorizacaoExame(med1, pac1, e1);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut1);

        // Exame 2
        Exame e2 = new Exame(TipoExame.RAIO_X);
        AutorizacaoExame aut2 = new AutorizacaoExame(med1, pac2, e2);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut2);

        // Exame 3
        Exame e3 = new Exame(TipoExame.ULTRASSOM);
        AutorizacaoExame aut3 = new AutorizacaoExame(med2, pac1, e3);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut3);

        // Exame 4
        Exame e4 = new Exame(TipoExame.RESSONANCIA_MAGNETICA);
        AutorizacaoExame aut4 = new AutorizacaoExame(med2, pac2, e4);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut4);

        // Exame 5
        Exame e5 = new Exame(TipoExame.TOMOGRAFIA);
        AutorizacaoExame aut5 = new AutorizacaoExame(med1, pac1, e5);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut5);

        // Exame 6
        Exame e6 = new Exame(TipoExame.ELETROCARDIOGRAMA);
        AutorizacaoExame aut6 = new AutorizacaoExame(med1, pac2, e6);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut6);

        // Exame 7
        Exame e7 = new Exame(TipoExame.EXAME_SANGUE);
        AutorizacaoExame aut7 = new AutorizacaoExame(med2, pac1, e7);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut7);

        // Exame 8
        Exame e8 = new Exame(TipoExame.PRESSAO_ARTERIAL);
        AutorizacaoExame aut8 = new AutorizacaoExame(med2, pac2, e8);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut8);

        // Exame 9
        Exame e9 = new Exame(TipoExame.GLICEMIA_JEJUM);
        AutorizacaoExame aut9 = new AutorizacaoExame(med1, pac1, e9);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut9); 

        // Exame 10
        Exame e10 = new Exame(TipoExame.COLESTEROL_TOTAL);
        AutorizacaoExame aut10 = new AutorizacaoExame(med1, pac2, e10);
        this.bancoAutorizacaoExames.adicionarAutorizacao(aut10);    
    }

      
}
