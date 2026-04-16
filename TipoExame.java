
public enum TipoExame {
    RAIO_X("Raio x"),
    TOMOGRAFIA("Tomografia"),
    RESSONANCIA_MAGNETICA("Ressonância Magnética"),
    ULTRASSOM("Ultrassom"),
    ELETROCARDIOGRAMA("Eletrocardiograma"),
    EXAME_SANGUE("Exame de Sangue"),
    PRESSAO_ARTERIAL("Pressão Arterial"),
    HEMOGRAMA("Hemograma"),
    GLICEMIA_JEJUM("Glicemia em Jejum"),
    COLESTEROL_TOTAL("Colesterol Total"),
    URINA_EAS("Urina EAS"),
    PARASITOLOGICO_FEZES("Parasitológico de Fezes"),
    CREATININA("Creatinina"),
    TSH("TSH"),
    T4_LIVRE("T4 Livre"),
    TRANSAMINASES_ALT_AST("Transaminases ALT/AST"),
    HEMOGLOBINA_GLICADA("Hemoglobina Glicada"),
    MAMOGRAFIA("Mamografia"),
    DENSITOMETRIA_OSSEA("Densitometria Óssea"),
    ECOCARDIOGRAMA("Ecocardiograma");

    private final String descricao;

    TipoExame(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
