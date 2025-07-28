package Model;

import java.time.LocalDate;

public class Adocao {
    private String id;
    private Adotante adotante;
    private Animal animalAdotado;
    private LocalDate data;
    private boolean termoAssinado;
    private String observacoes;

    public Adocao(String id, Adotante adotante, Animal animalAdotado, String observacoes) {
        this.id = id;
        this.adotante = adotante;
        this.animalAdotado = animalAdotado;
        this.data = LocalDate.now();
        this.termoAssinado = false;
        this.observacoes = observacoes;
    }

    public String getId() {
        return id;
    }

    public Adotante getAdotante() {
        return adotante;
    }

    public Animal getAnimalAdotado() {
        return animalAdotado;
    }

    public LocalDate getData() {
        return data;
    }

    public boolean isTermoAssinado() {
        return termoAssinado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public boolean podeSerFinalizada() {
        return adotante.validar() && animalAdotado.validar();
    }

    public boolean assinarTermo() {
        if (podeSerFinalizada()) {
            this.termoAssinado = true;
            return true;
        }
        return false;
    }

    public boolean finalizarAdocao() {
        if (termoAssinado && podeSerFinalizada()) {
            adotante.adotarAnimal(animalAdotado);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Adocao{" +
                "id='" + id + '\'' +
                ", adotante=" + adotante.getNome() +
                ", animalAdotado=" + animalAdotado.getNome() +
                ", data=" + data +
                ", termoAssinado=" + termoAssinado +
                '}';
    }
}