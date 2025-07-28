package Model;

public class Gato extends Animal {
    private boolean conviveComOutrosGatos;

    public Gato(String id, String nome, int idade, String condicaoSaude, String temperamento, String status, boolean conviveComOutrosGatos) {
        super(id, nome, "Felino", idade, condicaoSaude, temperamento, status);
        this.conviveComOutrosGatos = conviveComOutrosGatos;
    }

    public boolean isConviveComOutrosGatos() {
        return conviveComOutrosGatos;
    }

    public void setConviveComOutrosGatos(boolean conviveComOutrosGatos) {
        this.conviveComOutrosGatos = conviveComOutrosGatos;
    }

    @Override
    public String getInformacoesEspecificas() {
        return "Convive com outros gatos: " + (conviveComOutrosGatos ? "Sim" : "Não");
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", conviveComOutrosGatos=" + conviveComOutrosGatos +
                '}';
    }
}