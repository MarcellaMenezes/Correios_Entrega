/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.FuncionarioDAO;
import Model.Conexao;
import Model.Endereco;
import Model.FuncAgencia;
import Model.FuncCEE;
import Model.FuncCarga;
import Model.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author marce
 */
public class ViewCadastroFuncCEE extends javax.swing.JFrame {

    SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formtDataBD = new SimpleDateFormat("yyyy-MM-dd");
    Funcionario funcionario = null;
    String tipoEdicao = "";
    PreparedStatement psQrFunc = null;
    ResultSet resultQrFunc = null;
    FuncionarioDAO funcDao = null;

    public ViewCadastroFuncCEE() throws ParseException, SQLException {
        initComponents();
        
        funcDao = new FuncionarioDAO();

        MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
        MaskFormatter maskData = new MaskFormatter("##/##/####");

        maskCPF.install(ftxtCPF);
        maskData.install(ftxtDataNasc);

        habilitarCampos(false);
        carregarFuncionarios();

    }

    public void limparCampos() {
        txtNome.setText("");
        ftxtCPF.setText("");
        ftxtDataNasc.setText("");
        cbxCargo.setSelectedIndex(0);
        funcionario = null;
    }

    public void habilitarCampos(boolean flag) {
        if(tipoEdicao.equals("E")) ftxtCPF.setEnabled(false);
        else ftxtCPF.setEnabled(flag);
        
        txtNome.setEnabled(flag);
        ftxtDataNasc.setEnabled(flag);
        cbxCargo.setEnabled(flag);
        
    }

    public void dadosCamposParaObjeto() throws ParseException {
        String cpf;
        String nome;
        Date dataNascimento;
        char sexo;
        int codigo;
        String cargo;

        if (validaCampos()) {
            nome = txtNome.getText();
            dataNascimento = formatData.parse(ftxtDataNasc.getText());
            //System.out.println("Data Nas: " + dataNascimento);
            if (rbtnMasculino.isSelected()) {
                sexo = 'M';
            } else {
                sexo = 'F';
            }
            cpf = ftxtCPF.getText();
            cargo = cbxCargo.getItemAt(cbxCargo.getSelectedIndex());

            funcionario = new FuncAgencia(0, cargo,"CEE",cpf, nome, dataNascimento, sexo);

        }
    }

    public void dadosLinhaParaCampos() {
        String cpf, nome, dataNasc, cargo, sexo;
        if (tblCadastroFuncCEE.getSelectedRow() != -1) {

            cpf = (String) tblCadastroFuncCEE.getValueAt(tblCadastroFuncCEE.getSelectedRow(), 0);
            nome = (String) tblCadastroFuncCEE.getValueAt(tblCadastroFuncCEE.getSelectedRow(), 1);
            dataNasc = (String) tblCadastroFuncCEE.getValueAt(tblCadastroFuncCEE.getSelectedRow(), 2);
            //System.out.println("Sexo :" + tblCadastroFuncAgencia.getValueAt(tblCadastroFuncAgencia.getSelectedRow(), 3));
            sexo = (String) tblCadastroFuncCEE.getValueAt(tblCadastroFuncCEE.getSelectedRow(), 3);
            cargo = (String) tblCadastroFuncCEE.getValueAt(tblCadastroFuncCEE.getSelectedRow(), 4);

            ftxtCPF.setText(cpf);
            txtNome.setText(nome);
            ftxtDataNasc.setText(dataNasc);
            cbxCargo.setSelectedItem(cargo);

            if (sexo.equals('M')) {
                rbtnMasculino.setSelected(true);
            } else {
                rbtnFeminino.setSelected(true);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione algum funcionario");
        }
    }

    public void carregarFuncionarios() throws SQLException {
        String[] colunas = {"CPF", "Nome", "Data Nascimento", "Sexo", "Cargo", "Codigo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0); //1º linha 
        String localTrabalho = "CEE";
        ArrayList<Object> funcionarios = funcDao.consulta(localTrabalho, "");
        
        for(Object funcionario : funcionarios){
            model.addRow((Object[]) funcionario);
        }
        
        tblCadastroFuncCEE.setModel(model);
    }

    public void cadastraFuncionario() throws SQLException {

        if (funcionario != null && tipoEdicao.equals("C")) {
            funcDao.adiciona(funcionario, "");
        } 
    }

    public void editarFuncionario() throws SQLException {
        if (funcionario != null && tipoEdicao.equals("E")){
            funcDao.altera(funcionario, "");
        }
    }

    public void excluirFuncionario() throws SQLException{
        
        if (tblCadastroFuncCEE.getSelectedRow() != -1) {
            String cpf = (String) tblCadastroFuncCEE.getValueAt(tblCadastroFuncCEE.getSelectedRow(), 0);
            funcDao.exclui(cpf);  
        } else {
            JOptionPane.showMessageDialog(rootPane, "Selecione algum funcionario");
        }
    }

    public boolean verificarCPF(String cpf) {
        int dig1 = 0, dig2 = 0, calc1 = 0, calc2 = 0, aux1 = 10, aux2 = 11;
        int[] arrayCPF;
        boolean repetido = true;
        arrayCPF = new int[9];
        dig1 = Integer.parseInt(cpf.substring(12, 13));
        dig2 = Integer.parseInt(cpf.substring(13, 14));
        cpf = cpf.substring(0, 3) + cpf.substring(4, 7) + cpf.substring(8, 11);
        for (int i = 0; i < arrayCPF.length; i++) {
            arrayCPF[i] = Integer.parseInt(cpf.substring(i, i + 1));

            calc1 += aux1 * arrayCPF[i];
            aux1--;

            calc2 += aux2 * arrayCPF[i];
            aux2--;

            if (arrayCPF[0] != arrayCPF[i] && repetido) {
                repetido = false;
            }
        }
        calc2 += dig1 * aux2;

        calc1 = (calc1 * 10) % 11;
        calc2 = (calc2 * 10) % 11;

        if (calc1 == 10) {
            calc1 = 0;
        }

        if (calc2 == 10) {
            calc2 = 0;
        }

        if (calc1 != dig1 || calc2 != dig2 || repetido) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validaCampos() {
        if (ftxtCPF.getText().replace(" ", "").length() < 13 && ftxtCPF.isEnabled()) {
            JOptionPane.showMessageDialog(rootPane, "Preencha o cpf corretamente (deve conter 11 dígitos)");
            ftxtCPF.requestFocus();
            return false;
        } else {
            if (!verificarCPF(ftxtCPF.getText()) && ftxtCPF.isEnabled()) {
                JOptionPane.showMessageDialog(rootPane, "CPF Inválido");
                ftxtCPF.requestFocus();
                return false;
            }
        }

        if (!txtNome.getText().replace(" ", "").matches("[A-Za-z]{3,}")) {
            JOptionPane.showMessageDialog(rootPane, "Preencha o nome corretamente (somente letras e sem acento)");
            txtNome.requestFocus();
            return false;
        }

        if (ftxtDataNasc.getText().replace(" ", "").length() < 10) {
            JOptionPane.showMessageDialog(rootPane, "Preencha a data de nascimento corretamente (deve conter 8 dígitos)");
            ftxtDataNasc.requestFocus();
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblCEE = new javax.swing.JLabel();
        lblObservacao = new javax.swing.JLabel();
        ftxtCPF = new javax.swing.JFormattedTextField();
        txtNome = new javax.swing.JTextField();
        ftxtDataNasc = new javax.swing.JFormattedTextField();
        rbtnFeminino = new javax.swing.JRadioButton();
        rbtnMasculino = new javax.swing.JRadioButton();
        lblSexo = new javax.swing.JLabel();
        cbxCargo = new javax.swing.JComboBox<>();
        lblCargo = new javax.swing.JLabel();
        spnlTabela = new javax.swing.JScrollPane();
        tblCadastroFuncCEE = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlHead.setBackground(new java.awt.Color(243, 243, 243));
        pnlHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlHead.setMaximumSize(new java.awt.Dimension(163, 49));

        lblLogo.setBackground(new java.awt.Color(153, 153, 153));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/correios/Imagens/logo.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 806, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblCEE.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblCEE.setForeground(new java.awt.Color(0, 51, 153));
        lblCEE.setText("Cadastro Funcionário do CEE");
        lblCEE.setAlignmentX(0.5F);

        lblObservacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblObservacao.setForeground(new java.awt.Color(51, 102, 255));
        lblObservacao.setText("(*) Os campos assinalados são obrigatórios");
        lblObservacao.setAlignmentX(0.5F);

        ftxtCPF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CPF:  *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nome: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        ftxtDataNasc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data de Nascimento:  *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        rbtnFeminino.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtnFeminino.setSelected(true);
        rbtnFeminino.setText("Feminimo");

        rbtnMasculino.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnMasculino.setText("Masculino");

        lblSexo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSexo.setText("Sexo: *");

        cbxCargo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Gerente", "Motorista" }));
        cbxCargo.setBorder(null);

        lblCargo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCargo.setText("Cargo: *");

        tblCadastroFuncCEE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        spnlTabela.setViewportView(tblCadastroFuncCEE);

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(lblObservacao)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblCEE, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                                .addComponent(ftxtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblSexo)
                                    .addComponent(rbtnMasculino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbtnFeminino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBodyLayout.createSequentialGroup()
                                .addComponent(ftxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(108, 108, 108))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(spnlTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVoltar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblCEE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblObservacao)
                        .addGap(26, 26, 26)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ftxtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createSequentialGroup()
                                .addComponent(lblCargo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(lblSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnFeminino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnMasculino)))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBody, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        tipoEdicao = "C";
        habilitarCampos(true);
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        if (tipoEdicao.equals("C") || tipoEdicao.equals("E")) {

            try {
                dadosCamposParaObjeto();
            } catch (ParseException ex) {
                Logger.getLogger(ViewCadastroFuncCEE.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (tipoEdicao.equals("C")) {
                try {
                    cadastraFuncionario();
                } catch (SQLException ex) {
                    Logger.getLogger(ViewCadastroFuncCEE.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (tipoEdicao.equals("E")) {
                try {
                    editarFuncionario();
                } catch (SQLException ex) {
                    Logger.getLogger(ViewCadastroFuncCEE.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        try {
                limparCampos();
                habilitarCampos(false);
                carregarFuncionarios();
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadastroFuncCEE.class.getName()).log(Level.SEVERE, null, ex);
            }

    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        tipoEdicao = "E";
        habilitarCampos(true);
        dadosLinhaParaCampos();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        tipoEdicao = "Ex";
        try {
            excluirFuncionario();
            carregarFuncionarios();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCadastroFuncCEE.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose();
        ViewHomeFuncionario vHomeFunc = new ViewHomeFuncionario();
        vHomeFunc.setVisible(true);
    }//GEN-LAST:event_btnVoltarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JComboBox<String> cbxCargo;
    private javax.swing.JFormattedTextField ftxtCPF;
    private javax.swing.JFormattedTextField ftxtDataNasc;
    private javax.swing.JLabel lblCEE;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JRadioButton rbtnFeminino;
    private javax.swing.JRadioButton rbtnMasculino;
    private javax.swing.JScrollPane spnlTabela;
    private javax.swing.JTable tblCadastroFuncCEE;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
