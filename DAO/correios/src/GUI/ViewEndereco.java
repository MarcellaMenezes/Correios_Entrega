package GUI;

import DAO.EnderecoDAO;
import Model.Conexao;
import Model.Endereco;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ViewEndereco extends javax.swing.JFrame {
    PreparedStatement psQrEnd = null;
    ResultSet resultQrEnd = null;
    String cpfCli = null;
    EnderecoDAO endDao = null;
    
    
    ViewEndereco(String cpfCli) throws SQLException {
        initComponents();
        this.cpfCli = cpfCli;
        endDao = new EnderecoDAO();
        carregarEnderecos();
        
        System.out.println("Cpf no Endereco:"+this.cpfCli);
    }
    
    public void carregarEnderecos() throws SQLException {
        String [] colunas = {"Identificação","País", "CEP", "Rua", "Número", "Complemento", "Bairro", "Cidade", "UF"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0); //1º linha 
        ArrayList<Object> enderecos = endDao.consulta(cpfCli, "");
        
        for(Object endereco : enderecos){
            model.addRow((Object[]) endereco);
        }
        tblEndereco.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblEnderecosCadastrados = new javax.swing.JLabel();
        spnlTabela = new javax.swing.JScrollPane();
        tblEndereco = new javax.swing.JTable();
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
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));

        lblEnderecosCadastrados.setFont(new java.awt.Font("Arial", 0, 22)); // NOI18N
        lblEnderecosCadastrados.setForeground(new java.awt.Color(0, 51, 153));
        lblEnderecosCadastrados.setText("Endereços Cadastrados");
        lblEnderecosCadastrados.setAlignmentX(0.5F);

        tblEndereco.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        spnlTabela.setViewportView(tblEndereco);

        btnCadastrar.setBackground(new java.awt.Color(0, 51, 255));
        btnCadastrar.setForeground(new java.awt.Color(255, 255, 255));
        btnCadastrar.setText("Inserir Novo Endereço");
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
                .addContainerGap()
                .addGroup(pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(lblEnderecosCadastrados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1, 1, 1))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(spnlTabela, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlBodyLayout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        pnlBodyLayout.setVerticalGroup(
            pnlBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEnderecosCadastrados)
                .addGap(0, 0, 0)
                .addComponent(spnlTabela, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlHead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlBody, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(491, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(73, Short.MAX_VALUE)
                    .addComponent(pnlBody, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed

        ViewCadastroEndereco viewCadastroEndereco = null;
        try {
            viewCadastroEndereco = new ViewCadastroEndereco(cpfCli);
        } catch (ParseException ex) {
            Logger.getLogger(ViewEndereco.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        viewCadastroEndereco.setVisible(true);
          
    }//GEN-LAST:event_btnCadastrarActionPerformed

    
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JLabel lblEnderecosCadastrados;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlHead;
    private javax.swing.JScrollPane spnlTabela;
    private javax.swing.JTable tblEndereco;
    // End of variables declaration//GEN-END:variables
}
