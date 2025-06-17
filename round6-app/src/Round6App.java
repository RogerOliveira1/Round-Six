import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Round6App extends JFrame {
    private GerenciadorRound6 gerenciador;
    private JTabbedPane tabbedPane;
    
    // Componentes para Locais
    private JTable tabelaLocais;
    private DefaultTableModel modeloTabelaLocais;
    private JTextField campoNomeLocal;
    private JTextField campoCapacidadeLocal;
    
    // Componentes para Participantes
    private JTable tabelaParticipantes;
    private DefaultTableModel modeloTabelaParticipantes;
    private JTextField campoNomeParticipante;
    private JTextField campoNumeroParticipante;
    private JTextField campoIdadeParticipante;
    
    // Componentes para Desafios
    private JTable tabelaDesafios;
    private DefaultTableModel modeloTabelaDesafios;
    private JTextField campoNomeDesafio;
    private JTextField campoMinimoParticipantes;
    private JTextField campoParticipantesPassam;
    private JTextArea areaRegras;
    private JComboBox<Local> comboLocais;
    
    public Round6App() {
        gerenciador = new GerenciadorRound6();
        initializeComponents();
        setupLayout();
        setupEventListeners();
        
        setTitle("Round 6 - Gerenciador de Reality Show");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Adiciona alguns dados de exemplo
        adicionarDadosExemplo();
    }
    
    private void initializeComponents() {
        tabbedPane = new JTabbedPane();
        
        // Inicializa componentes para Locais
        String[] colunasLocais = {"Nome", "Capacidade Máxima"};
        modeloTabelaLocais = new DefaultTableModel(colunasLocais, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaLocais = new JTable(modeloTabelaLocais);
        campoNomeLocal = new JTextField(20);
        campoCapacidadeLocal = new JTextField(10);
        
        // Inicializa componentes para Participantes
        String[] colunasParticipantes = {"Número", "Nome", "Idade"};
        modeloTabelaParticipantes = new DefaultTableModel(colunasParticipantes, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaParticipantes = new JTable(modeloTabelaParticipantes);
        campoNomeParticipante = new JTextField(20);
        campoNumeroParticipante = new JTextField(10);
        campoIdadeParticipante = new JTextField(10);
        
        // Inicializa componentes para Desafios
        String[] colunasDesafios = {"Nome", "Min. Participantes", "Participantes Passam", "Local"};
        modeloTabelaDesafios = new DefaultTableModel(colunasDesafios, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaDesafios = new JTable(modeloTabelaDesafios);
        campoNomeDesafio = new JTextField(20);
        campoMinimoParticipantes = new JTextField(10);
        campoParticipantesPassam = new JTextField(10);
        areaRegras = new JTextArea(5, 20);
        areaRegras.setLineWrap(true);
        areaRegras.setWrapStyleWord(true);
        comboLocais = new JComboBox<>();
    }
    
    private void setupLayout() {
        // Painel para Locais
        JPanel painelLocais = criarPainelLocais();
        tabbedPane.addTab("Locais", painelLocais);
        
        // Painel para Participantes
        JPanel painelParticipantes = criarPainelParticipantes();
        tabbedPane.addTab("Participantes", painelParticipantes);
        
        // Painel para Desafios
        JPanel painelDesafios = criarPainelDesafios();
        tabbedPane.addTab("Desafios", painelDesafios);
        
        add(tabbedPane);
    }
    
    private JPanel criarPainelLocais() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Cadastro de Local"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoNomeLocal, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        painelFormulario.add(new JLabel("Capacidade:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoCapacidadeLocal, gbc);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        painelFormulario.add(painelBotoes, gbc);
        
        // Tabela
        JScrollPane scrollPane = new JScrollPane(tabelaLocais);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Locais Cadastrados"));
        
        painel.add(painelFormulario, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        // Event listeners para locais
        btnAdicionar.addActionListener(e -> adicionarLocal());
        btnEditar.addActionListener(e -> editarLocal());
        btnRemover.addActionListener(e -> removerLocal());
        btnLimpar.addActionListener(e -> limparCamposLocal());
        
        tabelaLocais.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherCamposLocal();
            }
        });
        
        return painel;
    }
    
    private JPanel criarPainelParticipantes() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Cadastro de Participante"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoNomeParticipante, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        painelFormulario.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoNumeroParticipante, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        painelFormulario.add(new JLabel("Idade:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoIdadeParticipante, gbc);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        painelFormulario.add(painelBotoes, gbc);
        
        // Tabela
        JScrollPane scrollPane = new JScrollPane(tabelaParticipantes);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Participantes Cadastrados"));
        
        painel.add(painelFormulario, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        // Event listeners para participantes
        btnAdicionar.addActionListener(e -> adicionarParticipante());
        btnEditar.addActionListener(e -> editarParticipante());
        btnRemover.addActionListener(e -> removerParticipante());
        btnLimpar.addActionListener(e -> limparCamposParticipante());
        
        tabelaParticipantes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherCamposParticipante();
            }
        });
        
        return painel;
    }
    
    private JPanel criarPainelDesafios() {
        JPanel painel = new JPanel(new BorderLayout());
        
        // Painel de formulário
        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setBorder(BorderFactory.createTitledBorder("Cadastro de Desafio"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.gridx = 0; gbc.gridy = 0;
        painelFormulario.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoNomeDesafio, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        painelFormulario.add(new JLabel("Min. Participantes:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoMinimoParticipantes, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        painelFormulario.add(new JLabel("Participantes Passam:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(campoParticipantesPassam, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        painelFormulario.add(new JLabel("Local:"), gbc);
        gbc.gridx = 1;
        painelFormulario.add(comboLocais, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        painelFormulario.add(new JLabel("Regras:"), gbc);
        gbc.gridx = 1;
        JScrollPane scrollRegras = new JScrollPane(areaRegras);
        painelFormulario.add(scrollRegras, gbc);
        
        // Painel de botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnLimpar = new JButton("Limpar");
        
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnLimpar);
        
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        painelFormulario.add(painelBotoes, gbc);
        
        // Tabela
        JScrollPane scrollPane = new JScrollPane(tabelaDesafios);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Desafios Cadastrados"));
        
        painel.add(painelFormulario, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        // Event listeners para desafios
        btnAdicionar.addActionListener(e -> adicionarDesafio());
        btnEditar.addActionListener(e -> editarDesafio());
        btnRemover.addActionListener(e -> removerDesafio());
        btnLimpar.addActionListener(e -> limparCamposDesafio());
        
        tabelaDesafios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherCamposDesafio();
            }
        });
        
        return painel;
    }
    
    private void setupEventListeners() {
        // Listener para atualizar combo de locais quando a aba de desafios for selecionada
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 2) { // Aba de desafios
                atualizarComboLocais();
            }
        });
    }
    
    // Métodos para Locais
    private void adicionarLocal() {
        try {
            String nome = campoNomeLocal.getText().trim();
            int capacidade = Integer.parseInt(campoCapacidadeLocal.getText().trim());
            
            Local local = new Local(nome, capacidade);
            
            if (gerenciador.adicionarLocal(local)) {
                atualizarTabelaLocais();
                limparCamposLocal();
                JOptionPane.showMessageDialog(this, "Local adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar local. Verifique se o nome não está duplicado e se a capacidade é válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Capacidade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarLocal() {
        int selectedRow = tabelaLocais.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um local para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String nome = campoNomeLocal.getText().trim();
            int capacidade = Integer.parseInt(campoCapacidadeLocal.getText().trim());
            
            Local localAntigo = gerenciador.getLocais().get(selectedRow);
            Local localNovo = new Local(nome, capacidade);
            
            if (gerenciador.editarLocal(localAntigo, localNovo)) {
                atualizarTabelaLocais();
                limparCamposLocal();
                JOptionPane.showMessageDialog(this, "Local editado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao editar local. Verifique se o nome não está duplicado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Capacidade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removerLocal() {
        int selectedRow = tabelaLocais.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um local para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este local?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            Local local = gerenciador.getLocais().get(selectedRow);
            
            if (gerenciador.removerLocal(local)) {
                atualizarTabelaLocais();
                limparCamposLocal();
                JOptionPane.showMessageDialog(this, "Local removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Não é possível remover este local pois está sendo usado por um desafio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void limparCamposLocal() {
        campoNomeLocal.setText("");
        campoCapacidadeLocal.setText("");
        tabelaLocais.clearSelection();
    }
    
    private void preencherCamposLocal() {
        int selectedRow = tabelaLocais.getSelectedRow();
        if (selectedRow != -1) {
            Local local = gerenciador.getLocais().get(selectedRow);
            campoNomeLocal.setText(local.getNome());
            campoCapacidadeLocal.setText(String.valueOf(local.getCapacidadeMaxima()));
        }
    }
    
    private void atualizarTabelaLocais() {
        modeloTabelaLocais.setRowCount(0);
        for (Local local : gerenciador.getLocais()) {
            modeloTabelaLocais.addRow(new Object[]{local.getNome(), local.getCapacidadeMaxima()});
        }
    }
    
    // Métodos para Participantes
    private void adicionarParticipante() {
        try {
            String nome = campoNomeParticipante.getText().trim();
            String numero = campoNumeroParticipante.getText().trim();
            int idade = Integer.parseInt(campoIdadeParticipante.getText().trim());
            
            Participante participante = new Participante(nome, numero, idade);
            
            if (gerenciador.adicionarParticipante(participante)) {
                atualizarTabelaParticipantes();
                limparCamposParticipante();
                JOptionPane.showMessageDialog(this, "Participante adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar participante. Verifique se o número não está duplicado e se os dados são válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número e idade devem ser valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarParticipante() {
        int selectedRow = tabelaParticipantes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um participante para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String nome = campoNomeParticipante.getText().trim();
            String numero = campoNumeroParticipante.getText().trim();
            int idade = Integer.parseInt(campoIdadeParticipante.getText().trim());
            
            Participante participanteAntigo = gerenciador.getParticipantes().get(selectedRow);
            Participante participanteNovo = new Participante(nome, numero, idade);
            
            if (gerenciador.editarParticipante(participanteAntigo, participanteNovo)) {
                atualizarTabelaParticipantes();
                limparCamposParticipante();
                JOptionPane.showMessageDialog(this, "Participante editado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao editar participante. Verifique se o número não está duplicado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número e idade devem ser valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removerParticipante() {
        int selectedRow = tabelaParticipantes.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um participante para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este participante?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            Participante participante = gerenciador.getParticipantes().get(selectedRow);
            
            if (gerenciador.removerParticipante(participante)) {
                atualizarTabelaParticipantes();
                limparCamposParticipante();
                JOptionPane.showMessageDialog(this, "Participante removido com sucesso!");
            }
        }
    }
    
    private void limparCamposParticipante() {
        campoNomeParticipante.setText("");
        campoNumeroParticipante.setText("");
        campoIdadeParticipante.setText("");
        tabelaParticipantes.clearSelection();
    }
    
    private void preencherCamposParticipante() {
        int selectedRow = tabelaParticipantes.getSelectedRow();
        if (selectedRow != -1) {
            Participante participante = gerenciador.getParticipantes().get(selectedRow);
            campoNomeParticipante.setText(participante.getNome());
            campoNumeroParticipante.setText(String.valueOf(participante.getNumero()));
            campoIdadeParticipante.setText(String.valueOf(participante.getIdade()));
        }
    }
    
    private void atualizarTabelaParticipantes() {
        modeloTabelaParticipantes.setRowCount(0);
        for (Participante participante : gerenciador.getParticipantes()) {
            modeloTabelaParticipantes.addRow(new Object[]{participante.getNumero(), participante.getNome(), participante.getIdade()});
        }
    }
    
    // Métodos para Desafios
    private void adicionarDesafio() {
        if (!gerenciador.podeAdicionarDesafio()) {
            JOptionPane.showMessageDialog(this, "Limite máximo de " + gerenciador.getMaxDesafios() + " desafios atingido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            String nome = campoNomeDesafio.getText().trim();
            int minParticipantes = Integer.parseInt(campoMinimoParticipantes.getText().trim());
            int participantesPassam = Integer.parseInt(campoParticipantesPassam.getText().trim());
            Local local = (Local) comboLocais.getSelectedItem();
            
            if (local == null) {
                JOptionPane.showMessageDialog(this, "Selecione um local para o desafio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<String> regras = new ArrayList<>();
            String textoRegras = areaRegras.getText().trim();
            if (!textoRegras.isEmpty()) {
                String[] linhasRegras = textoRegras.split("\n");
                for (String regra : linhasRegras) {
                    if (!regra.trim().isEmpty()) {
                        regras.add(regra.trim());
                    }
                }
            }
            
            Desafio desafio = new Desafio(nome, minParticipantes, participantesPassam, regras, local);
            
            if (gerenciador.adicionarDesafio(desafio)) {
                atualizarTabelaDesafios();
                limparCamposDesafio();
                JOptionPane.showMessageDialog(this, "Desafio adicionado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar desafio. Verifique se o nome não está duplicado, se o local tem capacidade suficiente e se os dados são válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número mínimo de participantes e participantes que passam devem ser valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarDesafio() {
        int selectedRow = tabelaDesafios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um desafio para editar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            String nome = campoNomeDesafio.getText().trim();
            int minParticipantes = Integer.parseInt(campoMinimoParticipantes.getText().trim());
            int participantesPassam = Integer.parseInt(campoParticipantesPassam.getText().trim());
            Local local = (Local) comboLocais.getSelectedItem();
            
            if (local == null) {
                JOptionPane.showMessageDialog(this, "Selecione um local para o desafio.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            List<String> regras = new ArrayList<>();
            String textoRegras = areaRegras.getText().trim();
            if (!textoRegras.isEmpty()) {
                String[] linhasRegras = textoRegras.split("\n");
                for (String regra : linhasRegras) {
                    if (!regra.trim().isEmpty()) {
                        regras.add(regra.trim());
                    }
                }
            }
            
            Desafio desafioAntigo = gerenciador.getDesafios().get(selectedRow);
            Desafio desafioNovo = new Desafio(nome, minParticipantes, participantesPassam, regras, local);
            
            if (gerenciador.editarDesafio(desafioAntigo, desafioNovo)) {
                atualizarTabelaDesafios();
                limparCamposDesafio();
                JOptionPane.showMessageDialog(this, "Desafio editado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao editar desafio. Verifique se o nome não está duplicado e se o local tem capacidade suficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Número mínimo de participantes e participantes que passam devem ser valores numéricos válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void removerDesafio() {
        int selectedRow = tabelaDesafios.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um desafio para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacao = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja remover este desafio?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            Desafio desafio = gerenciador.getDesafios().get(selectedRow);
            
            if (gerenciador.removerDesafio(desafio)) {
                atualizarTabelaDesafios();
                limparCamposDesafio();
                JOptionPane.showMessageDialog(this, "Desafio removido com sucesso!");
            }
        }
    }
    
    private void limparCamposDesafio() {
        campoNomeDesafio.setText("");
        campoMinimoParticipantes.setText("");
        campoParticipantesPassam.setText("");
        areaRegras.setText("");
        comboLocais.setSelectedIndex(-1);
        tabelaDesafios.clearSelection();
    }
    
    private void preencherCamposDesafio() {
        int selectedRow = tabelaDesafios.getSelectedRow();
        if (selectedRow != -1) {
            Desafio desafio = gerenciador.getDesafios().get(selectedRow);
            campoNomeDesafio.setText(desafio.getNome());
            campoMinimoParticipantes.setText(String.valueOf(desafio.getNumeroMinimoParticipantes()));
            campoParticipantesPassam.setText(String.valueOf(desafio.getNumeroParticipantesPassam()));
            comboLocais.setSelectedItem(desafio.getLocal());
            
            StringBuilder regrasTexto = new StringBuilder();
            for (String regra : desafio.getRegras()) {
                regrasTexto.append(regra).append("\n");
            }
            areaRegras.setText(regrasTexto.toString());
        }
    }
    
    private void atualizarTabelaDesafios() {
        modeloTabelaDesafios.setRowCount(0);
        for (Desafio desafio : gerenciador.getDesafios()) {
            modeloTabelaDesafios.addRow(new Object[]{
                desafio.getNome(), 
                desafio.getNumeroMinimoParticipantes(), 
                desafio.getNumeroParticipantesPassam(), 
                desafio.getLocal().getNome()
            });
        }
    }
    
    private void atualizarComboLocais() {
        comboLocais.removeAllItems();
        for (Local local : gerenciador.getLocais()) {
            comboLocais.addItem(local);
        }
    }
    
    private void adicionarDadosExemplo() {
        // Adiciona alguns locais de exemplo
        gerenciador.adicionarLocal(new Local("Arena Principal", 100));
        gerenciador.adicionarLocal(new Local("Sala de Jogos", 50));
        gerenciador.adicionarLocal(new Local("Pátio Externo", 200));
        
        // Adiciona alguns participantes de exemplo

        
        // Atualiza as tabelas
        atualizarTabelaLocais();
        atualizarTabelaParticipantes();
        atualizarComboLocais();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Round6App().setVisible(true);
        });
    }
}

