import java.util.Date;

public class AutorizacaoExame {
    private static int contadorSequencial = 1000;
    private int codigo;
    private Date dataCadastro;
    private Medico medicoSolicitante;
    private Paciente paciente;
    private Exame exame;

    public AutorizacaoExame(Medico medicoSolicitante, Paciente paciente, Exame exame) {
        this.medicoSolicitante = medicoSolicitante;
        this.paciente = paciente;
        this.exame = exame;

        this.dataCadastro = new Date();
        this.codigo =  contadorSequencial;
        contadorSequencial++;
    }



    public int getCodigo() {
        return codigo;
    }
    public Medico getMedicoSolicitante() {
        return medicoSolicitante;
    }
    public Paciente getPaciente() {
        return paciente;
    }
    public Exame getExame() {
        return exame;
    }
}
