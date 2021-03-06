/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correios.GUI;

import correios.Controller.ControllerEndereco;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author marce
 */
public class ViewCadastroEndereco extends javax.swing.JFrame {
   String cpf;
   ControllerEndereco enderecoController;
   Object[] endereco;
    public ViewCadastroEndereco(String cpf, ControllerEndereco enderecoController) throws ParseException {
        initComponents();
        MaskFormatter maskCEP = new MaskFormatter("#####-###");
        maskCEP.install(ftxtCEP);
        endereco = new Object[9];
        this.cpf = cpf;
        this.enderecoController = enderecoController;    
    }

    public boolean validaCampos(){   
        if(txtIdentEndereco.getText().length()<1){
            JOptionPane.showMessageDialog(rootPane, "Insira uma Identificação do Endereço");
            txtIdentEndereco.requestFocus();
            return false;
        }  

        if(ftxtCEP.getText().replace(" ", "").length()<9){
            JOptionPane.showMessageDialog(rootPane, "Preencha o CEP corretamente (deve conter 8 dígitos)");
            ftxtCEP.requestFocus();
            return false;
        }
        
        if(txtRua.getText().length()<1){
            JOptionPane.showMessageDialog(rootPane, "Insira sua Rua");
            txtRua.requestFocus();
            return false;
        }  
        
        if(!txtNumero.getText().replace(" ", "").matches("[0-9]{1,}")){
            JOptionPane.showMessageDialog(rootPane, "Preencha o numero da moradia corretamente (somente numeros)");
            txtNumero.requestFocus();
            return false;
        }
        
        if(txtBairro.getText().length()<1){
            JOptionPane.showMessageDialog(rootPane, "Insira seu Bairro");
            txtBairro.requestFocus();
            return false;
        }
        
        if(txtCidade.getText().length()<1){
            JOptionPane.showMessageDialog(rootPane, "Insira sua Cidade");
            txtCidade.requestFocus();
            return false;
        } 
         
        return true;
    }

    public void cadastraEndereco() throws SQLException{
        if (endereco != null) {         
            enderecoController.cadastraEndereco(endereco, cpf);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlBody = new javax.swing.JPanel();
        btnCadastrar = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        txtIdentEndereco = new javax.swing.JTextField();
        lblPais = new javax.swing.JLabel();
        cbxPais = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        cbxEstado = new javax.swing.JComboBox<>();
        ftxtCEP = new javax.swing.JFormattedTextField();
        txtRua = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();

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
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setRequestFocusEnabled(false);

        btnCadastrar.setBackground(new java.awt.Color(0, 51, 255));
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 51, 153));
        lblTitle.setText("Endereço ");
        lblTitle.setAlignmentX(0.5F);

        txtIdentEndereco.setToolTipText("ex: casa");
        txtIdentEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Identificação do Endereço: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N
        txtIdentEndereco.setInheritsPopupMenu(true);
        txtIdentEndereco.setName(""); // NOI18N

        lblPais.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblPais.setText(" País: *");

        cbxPais.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Argentina", "Bolívia", "Brasil", "Chile", "Colômbia", "Equador", "Guiana", "França (Guiana Francesa)", "Paraguai", "Peru", "Suriname", "Uruguai", "Venezuela" }));
        cbxPais.setBorder(null);
        cbxPais.setPreferredSize(new java.awt.Dimension(16, 46));

        lblEstado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblEstado.setText(" Estado/UF: *");

        cbxEstado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" }));
        cbxEstado.setBorder(null);

        ftxtCEP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CEP: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtRua.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rua: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtComplemento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Complemento: ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtBairro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bairro: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidade: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIdentEndereco)
                                    .addComponent(lblPais, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxPais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbxEstado, 0, 499, Short.MAX_VALUE)
                                    .addComponent(ftxtCEP)
                                    .addComponent(txtRua)
                                    .addComponent(txtNumero)
                                    .addComponent(txtComplemento))
                                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(178, Short.MAX_VALUE))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtIdentEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(cbxPais, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ftxtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(pnlBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHead, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
        String pais, uf, cep, identEndereco, rua, complemento, bairro, cidade;
        int numero;

        if(validaCampos()){   
            pais = cbxPais.getItemAt(cbxPais.getSelectedIndex());
            uf = cbxEstado.getItemAt(cbxEstado.getSelectedIndex());
            cep = ftxtCEP.getText();
            System.out.println("CEP: "+ftxtCEP.getText());
            identEndereco = txtIdentEndereco.getText();
            rua = txtRua.getText();
            numero = Integer.parseInt(txtNumero.getText());
            complemento = txtComplemento.getText();
            bairro = txtBairro.getText();
            cidade = txtCidade.getText();
            
            endereco[0] = identEndereco;
            endereco[1] = pais;
            endereco[2] = cep;
            endereco[3] = rua;
            endereco[4] = numero;
            endereco[5] = complemento;
            endereco[6] = bairro;
            endereco[7] = cidade;
            endereco[8] = uf;    
            
            try {
                cadastraEndereco();
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadastroEndereco.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                enderecoController.abrirViewEndereco(cpf);
            } catch (SQLException ex) {
                Logger.getLogger(ViewCadastroEndereco.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }
       
    }//GEN-LAST:event_btnCadastrarActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxPais;
    private javax.swing.JFormattedTextField ftxtCEP;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtIdentEndereco;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
