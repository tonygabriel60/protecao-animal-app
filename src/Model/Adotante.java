package Model;

import java.util.ArrayList;

public class Adotante extends Pessoa implements Validavel, Relatorio {
    private int idade;
    private String preferenciasAnimal;
    private ArrayList<Animal> animaisAdotados;

    public Adotante(String nome, int idade, String cpf, String telefone, String endereco, String preferenciasAnimal) {
        super(nome, cpf, telefone, endereco);
        this.idade = idade;
        this.preferenciasAnimal = preferenciasAnimal;
        this.animaisAdotados = new ArrayList<>();
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getPreferenciasAnimal() {
        return preferenciasAnimal;
    }

    public void setPreferenciasAnimal(String preferenciasAnimal) {
        this.preferenciasAnimal = preferenciasAnimal;
    }

    public ArrayList<Animal> getAnimaisAdotados() {
        return animaisAdotados;
    }

    public void setAnimaisAdotados(ArrayList<Animal> animaisAdotados) {
        this.animaisAdotados = animaisAdotados;
    }

    @Override
    public boolean validar() {
        return validarDocumentacao();
    }

    @Override
    public String gerarRelatorio() {
        return "Relatório do Adotante:\n" +
                "Nome: " + nome + "\n" +
                "Idade: " + idade + "\n" +
                "CPF: " + cpf + "\n" +
                "Telefone: " + telefone + "\n" +
                "Endereço: " + endereco + "\n" +
                "Preferências: " + preferenciasAnimal + "\n" +
                "Total de animais adotados: " + animaisAdotados.size();
    }

    @Override
    public boolean validarDocumentacao() {
        if (idade < 18) {
            return false;
        }
        if (cpf == null) {
            return false;
        }
        String cpfApenasDigitos = cpf.replaceAll("[^0-9]", "");
        return cpfApenasDigitos.length() == 11;
    }

    public void adotarAnimal(Animal animal) {
        animal.setStatus("adotado");
        animaisAdotados.add(animal);
    }

    @Override
    public String toString() {
        return "Adotante{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", preferenciasAnimal='" + preferenciasAnimal + '\'' +
                ", quantidadeAnimaisAdotados=" + animaisAdotados.size() +
                '}';
    }
}