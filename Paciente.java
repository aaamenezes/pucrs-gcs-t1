import java.time.LocalDate;
import java.util.ArrayList;
 
public class Paciente extends Usuario {
 
    public Paciente(String nome) {
        super(nome);
    }
 
    public boolean marcarAutorizacaoExameComoRealizado(AutorizacaoExame autorizacaoExame, LocalDate data) {
        if (autorizacaoExame == null) {
            return false;
        }
        autorizacaoExame.getExame().setRealizado(true);
        autorizacaoExame.getExame().setDataRealizacao(data);
        return true;
    }
 
    // Listar autorizações exames
    public ArrayList<AutorizacaoExame> listarAutorizacaoExames(BancoAutorizacaoExames banco) {
        return banco.listarAutorizacaoExames(this);
    }
}