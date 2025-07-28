package Model;

public class Cao extends Animal {
    private String porte;
    private boolean necessitaPasseio;

    public Cao(String id, String nome, int idade, String condicaoSaude, String temperamento, String status, String porte, boolean necessitaPasseio) {
        super(id, nome, "Canino", idade, condicaoSaude, temperamento, status);
        this.porte = porte;
        this.necessitaPasseio = necessitaPasseio;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public boolean isNecessitaPasseio() {
        return necessitaPasseio;
    }

    public void setNecessitaPasseio(boolean necessitaPasseio) {
        this.necessitaPasseio = necessitaPasseio;
    }

    @Override
    public String getInformacoesEspecificas() {
        return "Porte: " + porte + ", Necessita passeio: " + (necessitaPasseio ? "Sim" : "Não");
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", porte='" + porte + '\'' +
                ", necessitaPasseio=" + necessitaPasseio +
                '}';
    }
}