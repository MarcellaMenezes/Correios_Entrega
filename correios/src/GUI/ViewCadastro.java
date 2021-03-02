package GUI;

import Classes.Cliente;
import Classes.Endereco;
import Classes.Login;
import Classes.Usuario;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class ViewCadastro extends javax.swing.JFrame {
    Usuario usuario;
    Login login ;
    Endereco endereco;
    
    public ViewCadastro() throws ParseException {
        initComponents();
        
        MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
        MaskFormatter maskData = new MaskFormatter("##/##/####");
        MaskFormatter maskTel = new MaskFormatter("(##) # ####-####");
        MaskFormatter maskCEP = new MaskFormatter("#####-###");
        
        maskCPF.install(ftxtCPF);
        maskData.install(ftxtDataNasc);
        maskCEP.install(ftxtCEP);  
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
        
        if(!txtEmail.getText().replace(" ", "").contains("@") || !txtConfirmaEmail.getText().replace(" ", "").contains("@")){
            JOptionPane.showMessageDialog(rootPane, "Preencha o e-mail com @");
            txtEmail.requestFocus();
            return false;
        }
        
        if(!txtEmail.getText().equals(txtConfirmaEmail.getText())){
            JOptionPane.showMessageDialog(rootPane, "E-mails Não Conferem");
            txtEmail.requestFocus();
            return false;
        } 
        
        if(pwdSenha.getPassword().length < 4){
            JOptionPane.showMessageDialog(rootPane, "Senha deve ter no mínimo 4 dígitos");
            pwdSenha.requestFocus();
            return false;
        }  
        
        String senha = new String(pwdSenha.getPassword());
        String confirmaSenha = new String(pwdConfirmaSenha.getPassword());
        
        if(!senha.equals(confirmaSenha)){
            JOptionPane.showMessageDialog(rootPane, "Senhas Não Conferem");
            pwdSenha.requestFocus();
            return false;
        }  
        
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        btgSexo = new javax.swing.ButtonGroup();
        pnlHead = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlBody = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        pwdSenha = new javax.swing.JPasswordField();
        lblObservacao = new javax.swing.JLabel();
        ftxtDataNasc = new javax.swing.JFormattedTextField();
        txtNome = new javax.swing.JTextField();
        ftxtCPF = new javax.swing.JFormattedTextField();
        rbtnFeminino = new javax.swing.JRadioButton();
        rbtnMasculino = new javax.swing.JRadioButton();
        lblSexo = new javax.swing.JLabel();
        txtConfirmaEmail = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        pwdConfirmaSenha = new javax.swing.JPasswordField();
        lblTitle1 = new javax.swing.JLabel();
        txtRua = new javax.swing.JTextField();
        cbxPais = new javax.swing.JComboBox<>();
        ftxtCEP = new javax.swing.JFormattedTextField();
        txtNumero = new javax.swing.JTextField();
        txtComplemento = new javax.swing.JTextField();
        txtBairro = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        cbxEstado = new javax.swing.JComboBox<>();
        lblEstado = new javax.swing.JLabel();
        lblPais = new javax.swing.JLabel();
        btnCadastrar1 = new javax.swing.JButton();
        txtIdentEndereco = new javax.swing.JTextField();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(694, 720));

        pnlHead.setBackground(new java.awt.Color(243, 243, 243));
        pnlHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlHead.setMaximumSize(new java.awt.Dimension(163, 49));

        lblLogo.setBackground(new java.awt.Color(153, 153, 153));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/correios/Imagens/logo.png"))); // NOI18N

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(598, 800));

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        lblTitle.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(0, 51, 153));
        lblTitle.setText("Endereço Principal");
        lblTitle.setAlignmentX(0.5F);

        pwdSenha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Senha: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        lblObservacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblObservacao.setForeground(new java.awt.Color(51, 102, 255));
        lblObservacao.setText("(*) Os campos assinalados são obrigatórios");
        lblObservacao.setAlignmentX(0.5F);

        ftxtDataNasc.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data de Nascimento:  *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtNome.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nome: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        ftxtCPF.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CPF:  *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        btgSexo.add(rbtnFeminino);
        rbtnFeminino.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rbtnFeminino.setSelected(true);
        rbtnFeminino.setText("Feminimo");

        btgSexo.add(rbtnMasculino);
        rbtnMasculino.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        rbtnMasculino.setText("Masculino");

        lblSexo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblSexo.setText("Sexo: *");

        txtConfirmaEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirme seu E-mail: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtEmail.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "E-mail: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        pwdConfirmaSenha.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Confirme sua Senha: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        lblTitle1.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblTitle1.setForeground(new java.awt.Color(0, 51, 153));
        lblTitle1.setText("Dados cadastrais - Pessoa Física");
        lblTitle1.setAlignmentX(0.5F);

        txtRua.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Rua: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        cbxPais.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxPais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Argentina", "Bolívia", "Brasil", "Chile", "Colômbia", "Equador", "Guiana", "França (Guiana Francesa)", "Paraguai", "Peru", "Suriname", "Uruguai", "Venezuela" }));
        cbxPais.setBorder(null);

        ftxtCEP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CEP: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtNumero.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtComplemento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Complemento: ", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtBairro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bairro: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        txtCidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cidade: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N

        cbxEstado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acre (AC)", "Alagoas (AL)", "Amapá (AP)", "Amazonas (AM)", "Bahia (BA)", "Ceará (CE)", "Distrito Federal (DF)", "Espírito Santo (ES)", "Goiás (GO)", "Maranhão (MA)", "Mato Grosso (MT)", "Mato Grosso do Sul (MS)", "Minas Gerais (MG)", "Pará (PA)", "Paraíba (PB)", "Paraná (PR)", "Pernambuco (PE)", "Piauí (PI)", "Rio de Janeiro (RJ)", "Rio Grande do Norte (RN)", "Rio Grande do Sul (RS)", "Rondônia (RO)", "Roraima (RR)", "Santa Catarina (SC)", "São Paulo (SP)", "Sergipe (SE)", "Tocantins (TO)" }));
        cbxEstado.setBorder(null);

        lblEstado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblEstado.setText(" Estado/UF: *");

        lblPais.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblPais.setText(" País: *");

        btnCadastrar1.setBackground(new java.awt.Color(0, 51, 255));
        btnCadastrar1.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar1.setText("CADASTRAR");
        btnCadastrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrar1ActionPerformed(evt);
            }
        });

        txtIdentEndereco.setToolTipText("ex: casa");
        txtIdentEndereco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Identificação do Endereço: *", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Arial", 0, 14))); // NOI18N
        txtIdentEndereco.setInheritsPopupMenu(true);
        txtIdentEndereco.setName(""); // NOI18N

        javax.swing.GroupLayout pnlBodyLayout = new javax.swing.GroupLayout(pnlBody);
        pnlBody.setLayout(pnlBodyLayout);
        pnlBodyLayout.setHorizontalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstado)
                            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblObservacao)
                                .addComponent(txtConfirmaEmail)
                                .addComponent(ftxtCPF)
                                .addComponent(ftxtDataNasc)
                                .addComponent(txtEmail)
                                .addComponent(pwdConfirmaSenha, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                                .addGroup(pnlBodyLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblSexo)
                                        .addGroup(pnlBodyLayout.createSequentialGroup()
                                            .addComponent(rbtnFeminino)
                                            .addGap(30, 30, 30)
                                            .addComponent(rbtnMasculino))))
                                .addComponent(pwdSenha, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxPais, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbxEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ftxtCEP)
                                .addComponent(txtRua)
                                .addComponent(txtNumero)
                                .addComponent(txtComplemento)
                                .addComponent(txtCidade)
                                .addComponent(txtNome)
                                .addComponent(txtBairro))
                            .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdentEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(btnCadastrar1)))
                .addContainerGap(108, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBodyLayout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(lblTitle1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(294, Short.MAX_VALUE)))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblObservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ftxtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ftxtDataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSexo)
                .addGap(8, 8, 8)
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnFeminino)
                    .addComponent(rbtnMasculino))
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmaEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(pwdSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pwdConfirmaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addComponent(txtIdentEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(cbxPais, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEstado)
                .addGap(18, 18, 18)
                .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ftxtCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCadastrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlBodyLayout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(lblTitle1)
                    .addContainerGap(1350, Short.MAX_VALUE)))
        );

        jScrollPane1.setViewportView(pnlBody);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1395, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrar1ActionPerformed
        Usuario usuario;
        String email, confirmaEmail, senha, confirmaSenha, cpf, nome;
        Date dataNasc=null;
        char sexo;
        Login login;
        Endereco endereco;
        List<Endereco> enderecos = new ArrayList<>();
        String pais, uf, cep, identEndereco, rua, complemento, bairro, cidade;
        int numero;
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        
        if(validaCampos()){
            cpf = ftxtCPF.getText(); 
            email = txtEmail.getText();
            confirmaEmail = txtConfirmaEmail.getText(); 
            senha = new String(pwdSenha.getPassword());
            confirmaSenha = new String(pwdConfirmaSenha.getPassword());

            login = new Login(email, senha);


            nome = txtNome.getText();
            try {
                dataNasc = formatData.parse(ftxtDataNasc.getText());
            } catch (ParseException ex) {
                Logger.getLogger(ViewCadastro.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(rbtnMasculino.isSelected()){
                sexo='M';
            }else{
                sexo='F';
            }

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
            endereco = new Endereco(identEndereco, pais, cep, rua, numero, complemento, bairro, cidade, uf);

            enderecos.add(endereco);
            usuario = new Cliente(cpf, nome, dataNasc, sexo, enderecos, login);

            usuario.imprimir();

            this.dispose();
           // ViewLogin telaLogin = new ViewLogin((Cliente) usuario);
           // telaLogin.setVisible(true);      
        }
              
    }//GEN-LAST:event_btnCadastrar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgSexo;
    private javax.swing.JButton btnCadastrar1;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxPais;
    private javax.swing.JFormattedTextField ftxtCEP;
    private javax.swing.JFormattedTextField ftxtCPF;
    private javax.swing.JFormattedTextField ftxtDataNasc;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblObservacao;
    private javax.swing.JLabel lblPais;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitle1;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JPasswordField pwdConfirmaSenha;
    private javax.swing.JPasswordField pwdSenha;
    private javax.swing.JRadioButton rbtnFeminino;
    private javax.swing.JRadioButton rbtnMasculino;
    private javax.swing.JTextField txtBairro;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtComplemento;
    private javax.swing.JTextField txtConfirmaEmail;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtIdentEndereco;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
