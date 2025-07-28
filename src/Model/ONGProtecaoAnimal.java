package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ONGProtecaoAnimal {
    private String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Animal> animais;
    private List<Adotante> adotantes;
    private ArrayList<RegistroAdocao> registrosAdocao;
    private ArrayList<Adocao> adocoes;

    public ONGProtecaoAnimal(String cnpj, String nome, String telefone, String email, String endereco) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.animais = new ArrayList<>();
        this.adotantes = new ArrayList<>();
        this.registrosAdocao = new ArrayList<>();
        this.adocoes = new ArrayList<>();
    }

    public void cadastrarAnimal(Animal animal) {
        animais.add(animal);
    }

    public void removerAnimal(String idAnimal) {
        animais.removeIf(animal -> animal.getId().equals(idAnimal));
    }

    public Animal buscarAnimalPorId(String id) {
        return animais.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public ArrayList<Animal> getAnimaisDisponiveis() {
        return animais.stream()
                .filter(animal -> animal.getStatus().equals("disponivel"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void cadastrarAdotante(Adotante adotante) {
        if (adotante.validarDocumentacao()) {
            adotantes.add(adotante);
        }
    }

    public Adotante buscarAdotantePorCpf(String cpf) {
        return adotantes.stream()
                .filter(adotante -> adotante.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public Adocao criarAdocao(String cpfAdotante, String idAnimal, String observacoes) {
        Adotante adotante = buscarAdotantePorCpf(cpfAdotante);
        Animal animal = buscarAnimalPorId(idAnimal);

        if (adotante == null || animal == null) {
            return null;
        }

        String idAdocao = "ADO" + System.currentTimeMillis();
        Adocao adocao = new Adocao(idAdocao, adotante, animal, observacoes);

        if (adocao.podeSerFinalizada()) {
            adocoes.add(adocao);
            return adocao;
        }
        return null;
    }

    public boolean registrarAdocao(Adocao adocao) {
        if (adocao.isTermoAssinado()) {
            RegistroAdocao registro = new RegistroAdocao(
                    adocao.getId(),
                    adocao.getAnimalAdotado(),
                    adocao.getAdotante(),
                    adocao.getData(),
                    adocao.getObservacoes()
            );
            registrosAdocao.add(registro);
            return true;
        }
        return false;
    }

    public ArrayList<Adocao> getAdocoes() {
        return adocoes;
    }

    public List<Adotante> getAdotantes() {
        return adotantes;
    }

    public List<Animal> getAnimais() {
        return animais;
    }

    public void relatorioAnimais() {
        long disponiveis = animais.stream().filter(a -> a.getStatus().equals("disponivel")).count();
        long emTratamento = animais.stream().filter(a -> a.getStatus().equals("em_tratamento")).count();
        long adotados = animais.stream().filter(a -> a.getStatus().equals("adotado")).count();

        System.out.println("Animais - Disponíveis: " + disponiveis +
                ", Em tratamento: " + emTratamento +
                ", Adotados: " + adotados);
    }

    public void relatorioAdocoes() {
        System.out.println("=== ADOÇÕES REGISTRADAS ===");
        registrosAdocao.forEach(System.out::println);

        System.out.println("\n=== PROCESSOS DE ADOÇÃO ===");
        adocoes.forEach(System.out::println);
    }
}