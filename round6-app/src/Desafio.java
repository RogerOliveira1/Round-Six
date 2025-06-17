import java.util.ArrayList;
import java.util.List;

public class Desafio {
    private String nome;
    private int numeroMinimoParticipantes;
    private int numeroParticipantesPassam;
    private List<String> regras;
    private Local local;
    
    public Desafio(String nome, int numeroMinimoParticipantes, int numeroParticipantesPassam, List<String> regras, Local local) {
        this.nome = nome;
        this.numeroMinimoParticipantes = numeroMinimoParticipantes;
        this.numeroParticipantesPassam = numeroParticipantesPassam;
        this.regras = new ArrayList<>(regras);
        this.local = local;
    }
    
    // Getters
    public String getNome() {
        return nome;
    }
    
    public int getNumeroMinimoParticipantes() {
        return numeroMinimoParticipantes;
    }
    
    public int getNumeroParticipantesPassam() {
        return numeroParticipantesPassam;
    }
    
    public List<String> getRegras() {
        return new ArrayList<>(regras);
    }
    
    public Local getLocal() {
        return local;
    }
    
    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setNumeroMinimoParticipantes(int numeroMinimoParticipantes) {
        this.numeroMinimoParticipantes = numeroMinimoParticipantes;
    }
    
    public void setNumeroParticipantesPassam(int numeroParticipantesPassam) {
        this.numeroParticipantesPassam = numeroParticipantesPassam;
    }
    
    public void setRegras(List<String> regras) {
        this.regras = new ArrayList<>(regras);
    }
    
    public void setLocal(Local local) {
        this.local = local;
    }
    
    @Override
    public String toString() {
        return nome + " (Min: " + numeroMinimoParticipantes + ", Passam: " + numeroParticipantesPassam + ")";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Desafio desafio = (Desafio) obj;
        return nome.equals(desafio.nome);
    }
}

