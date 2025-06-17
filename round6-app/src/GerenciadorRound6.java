import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;

public class GerenciadorRound6 {
    private List<Desafio> desafios;
    private List<Participante> participantes;
    private List<Local> locais;
    private static final int MAX_DESAFIOS = 5;
    
    public GerenciadorRound6() {
        this.desafios = new ArrayList<>();
        this.participantes = new ArrayList<>();
        this.locais = new ArrayList<>();
    }
    
    // Métodos para Locais
    public boolean adicionarLocal(Local local) {
        if (local == null || local.getNome().trim().isEmpty() || local.getCapacidadeMaxima() <= 0) {
            return false;
        }
        
        // Verifica se já existe um local com o mesmo nome
        for (Local l : locais) {
            if (l.getNome().equalsIgnoreCase(local.getNome())) {
                return false;
            }
        }
        
        return locais.add(local);
    }
    
    public boolean removerLocal(Local local) {
        // Verifica se o local está sendo usado por algum desafio
        for (Desafio d : desafios) {
            if (d.getLocal().equals(local)) {
                return false; // Não pode remover local em uso
            }
        }
        return locais.remove(local);
    }
    
    public boolean editarLocal(Local localAntigo, Local localNovo) {
        int index = locais.indexOf(localAntigo);
        if (index == -1) return false;
        
        // Verifica se o novo nome já existe (exceto para o próprio local)
        for (int i = 0; i < locais.size(); i++) {
            if (i != index && locais.get(i).getNome().equalsIgnoreCase(localNovo.getNome())) {
                return false;
            }
        }
        
        locais.set(index, localNovo);
        
        // Atualiza referências nos desafios
        for (Desafio d : desafios) {
            if (d.getLocal().equals(localAntigo)) {
                d.setLocal(localNovo);
            }
        }
        
        return true;
    }
    
    public List<Local> getLocais() {
        return new ArrayList<>(locais);
    }
    
    // Métodos para Participantes
    public boolean adicionarParticipante(Participante participante) {
        if (participante == null || participante.getNome().trim().isEmpty() ||
            participante.getIdade() <= 0) {
            return false;
        }

        if (participante.getNumero() == null || participante.getNumero().trim().isEmpty()) {
            int numeroAleatorio;
            do {
                numeroAleatorio = (int) (Math.random() * 10000) + 1; // Gera número entre 1 e 10000
            } while (numeroJaExiste(numeroAleatorio)); // Garante que o número é único

            participante.setNumero(String.valueOf(numeroAleatorio)); // Atribui o número aleatório ao participante
        }

        // Verifica se já existe um participante com o mesmo número
        for (Participante p : participantes) {
            if (p.getNumero() == participante.getNumero()) {
                return false;
            }
        }

        return participantes.add(participante);
    }
    private boolean numeroJaExiste(int numero) {
        for (Participante p : participantes) {
            if (p.getNumero().equals(String.valueOf(numero))) {
                return true; // Já existe
            }
        }
        return false;
    }

    public boolean removerParticipante(Participante participante) {
        return participantes.remove(participante);
    }
    
    public boolean editarParticipante(Participante participanteAntigo, Participante participanteNovo) {
        int index = participantes.indexOf(participanteAntigo);
        if (index == -1) return false;

        // Verifica se o novo número já existe (exceto para o próprio participante)
        for (int i = 0; i < participantes.size(); i++) {
            if (i != index && participantes.get(i).getNumero() == participanteNovo.getNumero()) {
                return false;
            }
        }
        
        participantes.set(index, participanteNovo);
        return true;
    }
    
    public List<Participante> getParticipantes() {
        return new ArrayList<>(participantes);
    }
    
    // Métodos para Desafios
    public boolean adicionarDesafio(Desafio desafio) {
        if (desafio == null || desafio.getNome().trim().isEmpty() || 
            desafio.getNumeroMinimoParticipantes() <= 0 || 
            desafio.getNumeroParticipantesPassam() <= 0 ||
            desafio.getLocal() == null ||
            desafios.size() >= MAX_DESAFIOS) {
            return false;
        }
        
        // Verifica se o local tem capacidade suficiente
        if (desafio.getLocal().getCapacidadeMaxima() < desafio.getNumeroMinimoParticipantes()) {
            return false;
        }
        
        // Verifica se já existe um desafio com o mesmo nome
        for (Desafio d : desafios) {
            if (d.getNome().equalsIgnoreCase(desafio.getNome())) {
                return false;
            }
        }
        
        return desafios.add(desafio);
    }
    
    public boolean removerDesafio(Desafio desafio) {
        return desafios.remove(desafio);
    }
    
    public boolean editarDesafio(Desafio desafioAntigo, Desafio desafioNovo) {
        int index = desafios.indexOf(desafioAntigo);
        if (index == -1) return false;
        
        // Verifica se o novo nome já existe (exceto para o próprio desafio)
        for (int i = 0; i < desafios.size(); i++) {
            if (i != index && desafios.get(i).getNome().equalsIgnoreCase(desafioNovo.getNome())) {
                return false;
            }
        }
        
        // Verifica se o local tem capacidade suficiente
        if (desafioNovo.getLocal().getCapacidadeMaxima() < desafioNovo.getNumeroMinimoParticipantes()) {
            return false;
        }
        
        desafios.set(index, desafioNovo);
        return true;
    }
    
    public List<Desafio> getDesafios() {
        return new ArrayList<>(desafios);
    }
    
    public int getMaxDesafios() {
        return MAX_DESAFIOS;
    }
    
    public boolean podeAdicionarDesafio() {
        return desafios.size() < MAX_DESAFIOS;
    }
}

