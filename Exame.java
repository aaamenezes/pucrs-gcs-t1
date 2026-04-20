import java.time.LocalDate;
import java.time.temporal.ChronoUnit; 

public class Exame{
    
    private static int id = 1;

    private AutorizacaoExame autorizacao;
    private int idExame;
    private LocalDate dataRealizacao;
    private boolean realizado;
    private TipoExame tipoExame;

    public Exame(TipoExame tipoExame){
        dataRealizacao = null;
        realizado = false;
        idAutorizacao = null;
        this.tipoExame = tipoExame;
        idExame = id;
        id++;
    }

    public TipoExame getTipoExame(){return this.tipoExame;}
    public boolean getRealizado(){return this.realizado;}
    public LocalDate getDataRealizacao(){return this.dataRealizacao;}

    public void realizarExame(LocalDate dataInformada, LocalDate dataAutorizacao){
        long diferenca = ChronoUnit.DAYS.between(dataInformada, dataAutorizacao);
        if (diferenca < 0)
            System.out.println("Erro: A data informada é anterior à data da autorização do exame.");
        else if (diferenca > 30)
            System.out.println("Erro: O exame só pode ser realizado dentro de 30 dias.");
        else {
            dataRealizacao = dataInformada;
            realizado = true;
            System.out.println("Exame realizado com sucesso!");
        }
    }

    public void setAutorizacao(AutorizacaoExame autorizacao){
        this.autorizacao = autorizacao;
    }

    public String toString(){
        if (autorizacao.isNull())
            return "Id: " + idExame + "; Tipo de Exame: " + tipoExame + "; Status: " + realizado
                + "; Data Realizacao: " + dataRealizacao;
        else 
            return "Id: " + idExame + "; Tipo de Exame: " + tipoExame + "; Status: " + realizado
                + "; Data Realizacao: " + dataRealizacao + "; Id da autorizacao: " + autorizacao.getId();
    }
    
}