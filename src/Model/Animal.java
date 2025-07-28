package Model;

public abstract class Animal implements Validavel, Relatorio {
    private String id;
    private String nome;
    private String especie;
    private int idade;
    private String condicaoSaude;
    private String temperamento;
    private String status;

    public Animal(String id, String nome, String especie, int idade, String condicaoSaude, String temperamento, String status) {
        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.idade = idade;
        this.condicaoSaude = condicaoSaude;
        this.temperamento = temperamento;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCondicaoSaude() {
        return condicaoSaude;
    }

    public void setCondicaoSaude(String condicaoSaude) {
        this.condicaoSaude = condicaoSaude;
    }

    public String getTemperamento() {
        return temperamento;
    }

    public void setTemperamento(String temperamento) {
        this.temperamento = temperamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean validar() {
        return status.equals("disponivel");
    }

    @Override
    public String gerarRelatorio() {
        return "Relatório do Animal:\n" +
                "ID: " + id + "\n" +
                "Nome: " + nome + "\n" +
                "Espécie: " + especie + "\n" +
                "Idade: " + idade + " anos\n" +
                "Condição de Saúde: " + condicaoSaude + "\n" +
                "Temperamento: " + temperamento + "\n" +
                "Status: " + status + "\n" +
                "Informações Específicas: " + getInformacoesEspecificas();
    }

    public abstract String getInformacoesEspecificas();

    @Override
    public String toString() {
        return "Animal{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", especie='" + especie + '\'' +
                ", idade=" + idade +
                ", condicaoSaude='" + condicaoSaude + '\'' +
                ", temperamento='" + temperamento + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}