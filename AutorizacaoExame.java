import java.time.LocalDate;

public class AutorizacaoExame {
    private static int contadorSequencial = 1000;
    private int codigo;
    private LocalDate dataCadastro;
    private Medico medicoSolicitante;
    private Paciente paciente;
    private Exame exame;

    public AutorizacaoExame(Medico medicoSolicitante, Paciente paciente, Exame exame) {
        this.medicoSolicitante = medicoSolicitante;
        this.paciente = paciente;
        this.exame = exame;

        this.dataCadastro = LocalDate.now();
        this.codigo =  contadorSequencial;
        contadorSequencial++;
    }



    public int getCodigo() {
        return this.codigo;
    }
    public Medico getMedicoSolicitante() {
        return this.medicoSolicitante;
    }
    public Paciente getPaciente() {
        return this.paciente;
    }
    public Exame getExame() {
        return this.exame;
    }
}