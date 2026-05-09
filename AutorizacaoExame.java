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
    public LocalDate getDataCadastro(){
        return this.dataCadastro;
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

    @Override
    public String toString(){
        return "Código: " + this.codigo + 
                         "; Data Cadastro:  " + this.dataCadastro + 
                         "; Médico Solicitante:  " + this.medicoSolicitante.getNome() 
                             + "; Paciente: " + this.paciente.getNome() +
                             "; Exame: " + this.exame.getIdExame();
    }
}