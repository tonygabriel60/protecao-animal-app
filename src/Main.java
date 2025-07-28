import Model.*;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static ONGProtecaoAnimal ong;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarONG();
        exibirMenuPrincipal();
    }

    private static void inicializarONG() {
        ong = new ONGProtecaoAnimal(
                "12.345.678/0001-90",
                "ONG Proteção dos animas é a nossa, prioridade",
                "(48) 998449849",
                "contato@amigosanimais.org",
                "Rua Alfredo, 340 - Fatima - Joinville - SC"
        );
    } 

    private static void exibirMenuPrincipal() {
        int opcao;
        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("[1] Gestão de Adotantes");
            System.out.println("[2] Gestão de Animais");
            System.out.println("[3] Gestão de Adoções");
            System.out.println("[4] Relatórios");
            System.out.println("[5] Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    menuAdotantes();
                    break;
                case 2:
                    menuAnimais();
                    break;
                case 3:
                    menuAdocoes();
                    break;
                case 4:
                    menuRelatorios();
                    break;
                case 5:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }

    private static void menuAdotantes() {
        int opcao;
        do {
            System.out.println("\n=== GESTÃO DE ADOTANTES ===");
            System.out.println("[1] Adicionar Perfil Adotante");
            System.out.println("[2] Edição de Perfil Adotante");
            System.out.println("[3] Desabilitar/Habilitar adotante");
            System.out.println("[4] Listar Todos Adotantes");
            System.out.println("[5] Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Digite um número válido!");
                scanner.nextLine();
                opcao = -1; 
            }

            switch (opcao) {
                case 1:
                    adicionarAdotante();
                    break;
                case 2:
                    editarAdotante();
                    break;
                case 3:
                    alternarStatusAdotante();
                    break;
                case 4:
                    listarTodosAdotantes();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (true);
    }

    private static void adicionarAdotante() {
        System.out.println("\n=== ADICIONAR NOVO ADOTANTE ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("CPF (formato 123.456.789-00): ");
        String cpf = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Preferências de animal: ");
        String preferencias = scanner.nextLine();

        Adotante novoAdotante = new Adotante(nome, idade, cpf, telefone, endereco, preferencias);
        ong.cadastrarAdotante(novoAdotante);
    }

    private static void editarAdotante() {
        System.out.println("\n=== EDITAR PERFIL DO ADOTANTE ===");
        System.out.print("Digite o CPF do adotante: ");
        String cpf = scanner.nextLine();

        Adotante adotante = ong.buscarAdotantePorCpf(cpf);

        if (adotante == null) {
            System.out.println("Adotante não encontrado!");
            return;
        }

        System.out.println("\nDados atuais do adotante:");
        System.out.println(adotante.gerarRelatorio());

        System.out.println("\nQuais dados deseja editar?");
        System.out.println("[1] Nome");
        System.out.println("[2] Telefone");
        System.out.println("[3] Endereço");
        System.out.println("[4] Preferências");
        System.out.println("[5] Cancelar");
        System.out.print("Escolha: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Novo nome: ");
                adotante.setNome(scanner.nextLine());
                break;
            case 2:
                System.out.print("Novo telefone: ");
                adotante.setTelefone(scanner.nextLine());
                break;
            case 3:
                System.out.print("Novo endereço: ");
                adotante.setEndereco(scanner.nextLine());
                break;
            case 4:
                System.out.print("Novas preferências: ");
                adotante.setPreferenciasAnimal(scanner.nextLine());
                break;
            case 5:
                System.out.println("Edição cancelada.");
                return;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("Dados do adotante atualizados com sucesso!");
    }

    private static void alternarStatusAdotante() {
        System.out.println("\n=== ALTERAR STATUS DO ADOTANTE ===");
        System.out.print("Digite o CPF do adotante: ");
        String cpf = scanner.nextLine();

        Adotante adotante = ong.buscarAdotantePorCpf(cpf);

        if (adotante == null) {
            System.out.println("Adotante não encontrado!");
            return;
        }

        boolean statusAtual = adotante.validarDocumentacao();

        System.out.println("\nStatus atual: " + (statusAtual ? "HABILITADO" : "DESABILITADO"));
        System.out.print("Deseja " + (statusAtual ? "desabilitar" : "habilitar") + " este adotante? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (statusAtual) {
                
                adotante.setIdade(17);
            } else {
                
                adotante.setIdade(18);
            }
            System.out.println("Status do adotante alterado com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private static void listarTodosAdotantes() {
        System.out.println("\n=== RELATÓRIO DE TODOS OS ADOTANTES ===");
        System.out.println("Total de adotantes: " + ong.getAdotantes().size());

        ong.getAdotantes().forEach(adotante -> {
            System.out.println("\n" + adotante.gerarRelatorio());
            System.out.println("Status: " + (adotante.validarDocumentacao() ? "HABILITADO" : "DESABILITADO"));
            System.out.println("-----------------------");
        });
    }

    private static void menuAnimais() {
        int opcao;
        do {
            System.out.println("\n=== GESTÃO DE ANIMAIS ===");
            System.out.println("[1] Adicionar Cachorro");
            System.out.println("[2] Adicionar Gato");
            System.out.println("[3] Remover Animal");
            System.out.println("[4] Desabilitar/Habilitar Animal");
            System.out.println("[5] Listar Todos Animais");
            System.out.println("[6] Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarCachorro();
                    break;
                case 2:
                    adicionarGato();
                    break;
                case 3:
                    removerAnimal();
                    break;
                case 4:
                    alternarStatusAnimal();
                    break;
                case 5:
                    listarTodosAnimais();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (true);
    }

    private static void adicionarCachorro() {
        System.out.println("\n=== ADICIONAR CACHORRO ===");

        System.out.print("ID do animal: ");
        String id = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Condição de saúde: ");
        String condicaoSaude = scanner.nextLine();

        System.out.print("Temperamento: ");
        String temperamento = scanner.nextLine();

        System.out.print("Status (disponivel/em_tratamento/adotado): ");
        String status = scanner.nextLine();

        System.out.print("Porte (pequeno/medio/grande): ");
        String porte = scanner.nextLine();

        System.out.print("Necessita passeio? (true/false): ");
        boolean necessitaPasseio = scanner.nextBoolean();
        scanner.nextLine();

        Cao cao = new Cao(id, nome, idade, condicaoSaude, temperamento, status, porte, necessitaPasseio);
        ong.cadastrarAnimal(cao);
        System.out.println("Cachorro cadastrado com sucesso!");
    }

    private static void adicionarGato() {
        System.out.println("\n=== ADICIONAR GATO ===");

        System.out.print("ID do animal: ");
        String id = scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Condição de saúde: ");
        String condicaoSaude = scanner.nextLine();

        System.out.print("Temperamento: ");
        String temperamento = scanner.nextLine();

        System.out.print("Status (disponivel/em_tratamento/adotado): ");
        String status = scanner.nextLine();

        System.out.print("Convive com outros gatos? (true/false): ");
        boolean conviveComOutrosGatos = scanner.nextBoolean();
        scanner.nextLine();

        Gato gato = new Gato(id, nome, idade, condicaoSaude, temperamento, status, conviveComOutrosGatos);
        ong.cadastrarAnimal(gato);
        System.out.println("Gato cadastrado com sucesso!");
    }

    private static void removerAnimal() {
        System.out.println("\n=== REMOVER ANIMAL ===");
        System.out.print("Digite o ID do animal a ser removido: ");
        String id = scanner.nextLine();

        Animal animal = ong.buscarAnimalPorId(id);
        if (animal != null) {
            ong.removerAnimal(id);
            System.out.println("Animal removido com sucesso!");
        } else {
            System.out.println("Animal não encontrado!");
        }
    }

    private static void alternarStatusAnimal() {
        System.out.println("\n=== ALTERAR STATUS DO ANIMAL ===");
        System.out.print("Digite o ID do animal: ");
        String id = scanner.nextLine();

        Animal animal = ong.buscarAnimalPorId(id);
        if (animal == null) {
            System.out.println("Animal não encontrado!");
            return;
        }

        System.out.println("\nStatus atual: " + animal.getStatus());
        System.out.println("Opções de status:");
        System.out.println("[1] disponivel");
        System.out.println("[2] em_tratamento");
        System.out.println("[3] adotado");
        System.out.print("Escolha o novo status: ");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                animal.setStatus("disponivel");
                break;
            case 2:
                animal.setStatus("em_tratamento");
                break;
            case 3:
                animal.setStatus("adotado");
                break;
            default:
                System.out.println("Opção inválida!");
                return;
        }

        System.out.println("Status do animal atualizado com sucesso!");
    }

    private static void listarTodosAnimais() {
        System.out.println("\n=== RELATÓRIO DE TODOS OS ANIMAIS ===");
        System.out.println("Total de animais: " + ong.getAnimais().size());

        System.out.println("\n=== CACHORROS ===");
        ong.getAnimais().stream()
                .filter(animal -> animal instanceof Cao)
                .forEach(animal -> {
                    System.out.println(animal.gerarRelatorio());
                    System.out.println("-----------------------");
                });

        System.out.println("\n=== GATOS ===");
        ong.getAnimais().stream()
                .filter(animal -> animal instanceof Gato)
                .forEach(animal -> {
                    System.out.println(animal.gerarRelatorio());
                    System.out.println("-----------------------");
                });
    }

    private static void menuAdocoes() {
        int opcao;
        do {
            System.out.println("\n=== GESTÃO DE ADOÇÕES ===");
            System.out.println("[1] Gerar Adoção");
            System.out.println("[2] Realizar Adoção");
            System.out.println("[3] Listar Adoções");
            System.out.println("[4] Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    gerarAdocao();
                    break;
                case 2:
                    realizarAdocao();
                    break;
                case 3:
                    listarAdocoes();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (true);
    }

    private static void gerarAdocao() {
        System.out.println("\n=== GERAR ADOÇÃO ===");

        // Listar adotantes disponíveis
        System.out.println("\nAdotantes disponíveis:");
        ong.getAdotantes().stream()
                .filter(Adotante::validar)
                .forEach(adotante -> System.out.println("CPF: " + adotante.getCpf() + " - Nome: " + adotante.getNome()));

        System.out.print("\nDigite o CPF do adotante: ");
        String cpfAdotante = scanner.nextLine();

        // Listar animais disponíveis
        System.out.println("\nAnimais disponíveis para adoção:");
        ong.getAnimaisDisponiveis().forEach(animal ->
                System.out.println("ID: " + animal.getId() + " - Nome: " + animal.getNome()));

        System.out.print("\nDigite o ID do animal: ");
        String idAnimal = scanner.nextLine();

        System.out.print("Observações: ");
        String observacoes = scanner.nextLine();

        // Criar a adoção
        Adocao adocao = ong.criarAdocao(cpfAdotante, idAnimal, observacoes);
        if (adocao != null) {
            System.out.println("Adoção gerada com sucesso! ID: " + adocao.getId());
            System.out.println("Adotante: " + adocao.getAdotante().getNome());
            System.out.println("Animal: " + adocao.getAnimalAdotado().getNome());
            System.out.println("Termo assinado: " + (adocao.isTermoAssinado() ? "Sim" : "Não"));
        } else {
            System.out.println("Não foi possível gerar a adoção. Verifique os dados.");
        }
    }

    private static void realizarAdocao() {
        System.out.println("\n=== REALIZAR ADOÇÃO ===");

        // Listar adoções pendentes (termo = false)
        System.out.println("\nAdoções pendentes:");
        ong.getAdocoes().stream()
                .filter(adocao -> !adocao.isTermoAssinado())
                .forEach(adocao -> System.out.println(
                        "ID: " + adocao.getId() +
                                " | Adotante: " + adocao.getAdotante().getNome() +
                                " | Animal: " + adocao.getAnimalAdotado().getNome() +
                                " | Status Animal: " + adocao.getAnimalAdotado().getStatus()
                ));

        if (ong.getAdocoes().stream().noneMatch(adocao -> !adocao.isTermoAssinado())) {
            System.out.println("Não há adoções pendentes.");
            return;
        }

        System.out.print("\nDigite o ID da adoção a ser realizada: ");
        String idAdocao = scanner.nextLine();

        // Encontrar a adoção
        Adocao adocao = ong.getAdocoes().stream()
                .filter(a -> a.getId().equals(idAdocao))
                .findFirst()
                .orElse(null);

        if (adocao == null) {
            System.out.println("Adoção não encontrada!");
            return;
        }

        if (adocao.isTermoAssinado()) {
            System.out.println("Esta adoção já foi realizada!");
            return;
        }

        // Mostrar detalhes da adoção
        System.out.println("\nDetalhes da Adoção:");
        System.out.println("ID: " + adocao.getId());
        System.out.println("Adotante: " + adocao.getAdotante().getNome());
        System.out.println("Animal: " + adocao.getAnimalAdotado().getNome());
        System.out.println("Data: " + adocao.getData());
        System.out.println("Observações: " + adocao.getObservacoes());

        System.out.print("\nConfirmar realização da adoção? (S/N): ");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            if (adocao.assinarTermo() && adocao.finalizarAdocao()) {
                System.out.println("Adoção realizada com sucesso!");
                System.out.println("Status do animal atualizado para: " + adocao.getAnimalAdotado().getStatus());
            } else {
                System.out.println("Falha ao realizar a adoção. Verifique os requisitos.");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private static void listarAdocoes() {
        System.out.println("\n=== RELATÓRIO DE ADOÇÕES ===");
        System.out.println("Total de adoções: " + ong.getAdocoes().size());

        if (ong.getAdocoes().isEmpty()) {
            System.out.println("Nenhuma adoção cadastrada.");
            return;
        }

        ong.getAdocoes().forEach(adocao -> {
            System.out.println("\nID: " + adocao.getId());
            System.out.println("Adotante: " + adocao.getAdotante().getNome());
            System.out.println("Animal: " + adocao.getAnimalAdotado().getNome());
            System.out.println("Data: " + adocao.getData());
            System.out.println("Termo assinado: " + (adocao.isTermoAssinado() ? "Sim" : "Não"));
            System.out.println("Observações: " + adocao.getObservacoes());
            System.out.println("Status: " + (adocao.isTermoAssinado() ? "CONCLUÍDA" : "PENDENTE"));
            System.out.println("-----------------------");
        });
    }

    private static void menuRelatorios() {
        int opcao;
        do {
            System.out.println("\n=== RELATÓRIOS ===");
            System.out.println("[1] Relatório de animais");
            System.out.println("[2] Relatório de adoções");
            System.out.println("[3] Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    ong.relatorioAnimais();
                    break;
                case 2:
                    ong.relatorioAdocoes();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (true);
    }
}