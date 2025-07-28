package Model;

import java.time.LocalDate;

public class RegistroAdocao {
    private String id;
    private Animal animal;
    private Adotante adotante;
    private LocalDate dataAdocao;
    private String observacoes;

    public RegistroAdocao(String id, Animal animal, Adotante adotante, LocalDate dataAdocao, String observacoes) {
        this.id = id;
        this.animal = animal;
        this.adotante = adotante;
        this.dataAdocao = dataAdocao;
        this.observacoes = observacoes;
    }

    public String getId() {
        return id;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public LocalDate getDataAdocao() {
        return dataAdocao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    @Override
    public String toString() {
        return "RegistroAdocao{" +
                "id='" + id + '\'' +
                ", animal=" + animal.getNome() +
                ", adotante=" + adotante.getNome() +
                ", dataAdocao=" + dataAdocao +
                '}';
    }
}