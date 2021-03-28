package correios.GUI;

import correios.Controller.ControllerEndereco;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ViewHomeCliente extends javax.swing.JFrame {
    String  cpfCli = null;
    ControllerEndereco enderecoController;

    public ViewHomeCliente() {
        initComponents();
        this.enderecoController = new ControllerEndereco();
        lblExibirEnderecos.setText("<html><u>Endereços</u></html>");
    }
    
    public ViewHomeCliente(String cpfCli) {
        initComponents();
        this.enderecoController = new ControllerEndereco();
        this.cpfCli = cpfCli;
        System.out.println("Cpf do cliente na Home: "+cpfCli);
        lblExibirEnderecos.setText("<html><u>Endereços</u></html>");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHead = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        MenuBar = new javax.swing.JPanel();
        lblExibirEnderecos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlHead.setBackground(new java.awt.Color(243, 243, 243));
        pnlHead.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        pnlHead.setMaximumSize(new java.awt.Dimension(163, 49));

        lblLogo.setBackground(new java.awt.Color(153, 153, 153));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/correios/Imagens/logo.png"))); // NOI18N

        MenuBar.setBackground(new java.awt.Color(255, 255, 255));

        lblExibirEnderecos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblExibirEnderecos.setForeground(new java.awt.Color(0, 51, 255));
        lblExibirEnderecos.setText("Ver Endereços Cadastrados");
        lblExibirEnderecos.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lblExibirEnderecosMouseMoved(evt);
            }
        });
        lblExibirEnderecos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExibirEnderecosMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblExibirEnderecosMouseExited(evt);
            }
        });

        javax.swing.GroupLayout MenuBarLayout = new javax.swing.GroupLayout(MenuBar);
        MenuBar.setLayout(MenuBarLayout);
        MenuBarLayout.setHorizontalGroup(
            MenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblExibirEnderecos)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        MenuBarLayout.setVerticalGroup(
            MenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuBarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblExibirEnderecos)
                .addContainerGap(396, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlHeadLayout = new javax.swing.GroupLayout(pnlHead);
        pnlHead.setLayout(pnlHeadLayout);
        pnlHeadLayout.setHorizontalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
            .addGroup(pnlHeadLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlHeadLayout.setVerticalGroup(
            pnlHeadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlHeadLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlHead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblExibirEnderecosMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExibirEnderecosMouseMoved
        lblExibirEnderecos.setForeground(Color.ORANGE);
    }//GEN-LAST:event_lblExibirEnderecosMouseMoved

    private void lblExibirEnderecosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExibirEnderecosMouseExited
        lblExibirEnderecos.setForeground(Color.BLUE);
    }//GEN-LAST:event_lblExibirEnderecosMouseExited

    private void lblExibirEnderecosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExibirEnderecosMouseClicked
        ViewEndereco viewEndereco = null;
        if(cpfCli!=null){
            try {
                System.out.println("Passando cpf para Endereco: "+cpfCli);
                viewEndereco = new ViewEndereco(cpfCli, enderecoController);
            } catch (SQLException ex) {
                Logger.getLogger(ViewHomeCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            enderecoController.abrirViewEndereco(cpfCli);
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(ViewHomeCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_lblExibirEnderecosMouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MenuBar;
    private javax.swing.JLabel lblExibirEnderecos;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JPanel pnlHead;
    // End of variables declaration//GEN-END:variables
}
