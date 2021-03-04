/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Classes.Endereco;
import Classes.FuncAgencia;
import Classes.FuncCEE;
import Classes.FuncCarga;
import Classes.Funcionario;
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
public class ViewCadastroFuncCarga extends javax.swing.JFrame {
    SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
    private List<Funcionario> funcionarios = null;
    
    public ViewCadastroFuncCarga() throws ParseException {
        initComponents();
        
        MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
        MaskFormatter maskData = new MaskFormatter("##/##/####");
          
        maskCPF.install(ftxtCPF);
        maskData.install(ftxtDataNasc);
        
        funcionarios = new ArrayList<>();
    }

     public boolean verificarCPF(String cpf){
        int dig1=0, dig2=0, calc1=0, calc2=0, aux1=10, aux2=11;
        int [] arrayCPF;
        boolean repetido = true;
        arrayCPF = new int[9];
        dig1 = Integer.parseInt(cpf.substring(12,13));
        dig2 = Integer.parseInt(cpf.substring(13,14));
        cpf = cpf.substring(0,3) + cpf.substring(4,7) + cpf.substring(8,11);
        for(int i=0; i<arrayCPF.length; i++){
            arrayCPF[i] = Integer.parseInt(cpf.substring(i, i+1));
            
            calc1 += aux1 * arrayCPF[i];
            aux1--;
            
            calc2 += aux2 * arrayCPF[i];
            aux2--;
            
            if(arrayCPF[0] != arrayCPF[i] && repetido)
                repetido = false;
        }
        calc2 += dig1 * aux2;
        
        calc1 = (calc1 * 10) % 11;
        calc2 = (calc2 * 10) % 11;
        
        if(calc1 == 10)
            calc1 = 0;
        
        if(calc2 == 10)
            calc2 = 0;
                 
        if(calc1 != dig1 || calc2 != dig2 || repetido)
            return false;
        else
            return true;
    }
    
    public boolean validaCampos(){
        if(ftxtCPF.getText().replace(" ", "").length()<13){
            JOptionPane.showMessageDialog(rootPane, "Preencha o cpf corretamente (deve conter 11 dígitos)");
            ftxtCPF.requestFocus();
            return false;
        }else{
            if (!verificarCPF(ftxtCPF.getText())){
                JOptionPane.showMessageDialog(rootPane, "CPF Inválido");  
                ftxtCPF.requestFocus();
                return false;
            }
        }
        
        if(!txtNome.getText().replace(" ", "").matches("[A-Za-z]{3,}")){
            JOptionPane.showMessageDialog(rootPane, "Preencha o nome corretamente (somente letras e sem acento)");
            txtNome.requestFocus();
            return false;
        }
        
        if(ftxtDataNasc.getText().replace(" ", "").length()<10){
            JOptionPane.showMessageDialog(rootPane, "Preencha a data de nascimento corretamente (deve conter 8 dígitos)");
            ftxtDataNasc.requestFocus();
            return false;
        }
        return true;
    }
    
     public void limparCampos(){
        txtNome.setText("");
        ftxtCPF.setText("");
        ftxtDataNasc.setText("");
        txtCodigo.setText("");
        cbxCargo.setSelectedIndex(0);
    }
    
    public void dadosCamposParaLista() throws ParseException{
        FuncCarga funcCarga;
        String cpf;String nome; Date dataNascimento; char sexo;
        int codigo; String cargo;
        
        if(validaCampos()){
            nome = txtNome.getText();
            dataNascimento = formatData.parse(ftxtDataNasc.getText());
            if(rbtnMasculino.isSelected()){
                sexo='M';
            }else{
                sexo='F';
            }
            cpf = ftxtCPF.getText();
            codigo = Integer.parseInt(txtCodigo.getText());
            cargo = cbxCargo.getItemAt(cbxCargo.getSelectedIndex());

            funcCarga = new FuncCarga(cpf, nome, dataNascimento, sexo, codigo, cargo);
            funcionarios.add(funcCarga);

            limparCampos();      
        }          
    }
    
    public void carregarEndereco(){
        String [] colunas = {"CPF","Nome", "Data Nascimento", "Sexo", "Cargo", "Codigo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0); //1º linha 
        
        if(funcionarios!=null){
            for(int i=0; i<funcionarios.size(); i++){
                funcionarios.get(i).imprimir();
                Object [] linha = {funcionarios.get(i).getCpf(),
                                    funcionarios.get(i).getNome(),
                                    formatData.format(funcionarios.get(i).getDataNascimento()),
                                    funcionarios.get(i).getSexo(),
                                    funcionarios.get(i).getCargo()+"",
                                    funcionarios.get(i).getCodigoCargo(),
                                  };
                model.addRow(linha);
            }
            tblCadastroFuncAgencia.setModel(model);
        }
    
    }  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblCarga = new javax.swing.JLabel();
        lblObservacao = new javax.swing.JLabel();
        ftxtCPF = new javax.swing.JFormattedTextField();
        txtNome = new javax.swing.JTextField();
        ftxtDataNasc = new javax.swing.JFormattedTextField();
        rbtnFeminino = new javax.swing.JRadioButton();
        rbtnMasculino = new javax.swing.JRadioButton();
        lblSexo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        cbxCargo = new javax.swing.JComboBox<>();
        lblCargo = new javax.swing.JLabel();
        spnlTabela = new javax.swing.JScrollPane();
        tblCadastroFuncAgencia = new javax.swing.JTable();
        btnCadastrar = new javax.swing.JButton();

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

        lblCarga.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblCarga.setForeground(new java.awt.Color(0, 51, 153));
        lblCarga.setText("Cadastro Funcionário de Carga");
        lblCarga.setAlignmentX(0.5F);

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

        txtCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Código: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        cbxCargo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gerente", "Motorista", "Funcionário" }));
        cbxCargo.setBorder(null);

        lblCargo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblCargo.setText("Cargo: *");

        tblCadastroFuncAgencia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        spnlTabela.setViewportView(tblCadastroFuncAgencia);

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(lblObservacao)
                .addGap(0, 507, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnlTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(ftxtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCargo)
                            .addComponent(cbxCargo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(ftxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSexo)
                            .addComponent(rbtnFeminino)
                            .addComponent(rbtnMasculino)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)))
                .addGap(18, 18, 18)
                .addComponent(btnCadastrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblCarga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblObservacao)
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ftxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblSexo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnFeminino)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnMasculino)))
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ftxtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblCargo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spnlTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        try {
            dadosCamposParaLista();
            carregarEndereco();
        } catch (ParseException ex) {
            Logger.getLogger(ViewCadastroFuncCarga.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnCadastrarActionPerformed

    /**
     * @param args the command line arguments
     */
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> cbxCargo;
    private javax.swing.JFormattedTextField ftxtCPF;
    private javax.swing.JFormattedTextField ftxtDataNasc;
    private javax.swing.JLabel lblCarga;
    private javax.swing.JLabel lblCargo;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JRadioButton rbtnFeminino;
    private javax.swing.JRadioButton rbtnMasculino;
    private javax.swing.JScrollPane spnlTabela;
    private javax.swing.JTable tblCadastroFuncAgencia;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
