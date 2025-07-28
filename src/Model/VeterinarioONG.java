package Model;

public class VeterinarioONG {
    private String nome;
    private String crmv;
    private String telefone;
    private String especialidade;

    public VeterinarioONG(String nome, String crmv, String telefone, String especialidade) {
        this.nome = nome;
        this.crmv = crmv;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrmv() {
        return crmv;
    }

    public void setCrmv(String crmv) {
        this.crmv = crmv;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public void atualizarCondicaoSaude(Animal animal, String novaCondicao) {
        animal.setCondicaoSaude(novaCondicao);
        System.out.println("Veterinário " + this.nome + " atualizou a condição de " + animal.getNome() + " para: " + novaCondicao);
    }

    public void liberarParaAdocao(Animal animal) {
        if (animal.getStatus().equals("em_tratamento")) {
            animal.setStatus("disponivel");
            System.out.println(animal.getNome() + " foi liberado para adoção pelo veterinário " + this.nome + ".");
        } else {
            System.out.println(animal.getNome() + " não estava em tratamento e não pôde ser liberado.");
        }
    }
}