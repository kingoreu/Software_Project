/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package manager;

/**
 *
 * @author 원채연
 */
public class A_MemberManage extends javax.swing.JFrame {

    /**
     * Creates new form A_UserManage
     */
    
    private String panelName;
    
    public A_MemberManage() {
        initComponents();
        setTitle("회원관리");
        setLocationRelativeTo(null);
        setSize(700,800);
        setDefaultCloseOperation(A_MemberManage.EXIT_ON_CLOSE);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usermng_butt = new javax.swing.JButton();
        businessmng_butt = new javax.swing.JButton();
        back_butt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 800));
        setResizable(false);

        usermng_butt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        usermng_butt.setText("사용자");
        usermng_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usermng_buttActionPerformed(evt);
            }
        });

        businessmng_butt.setFont(new java.awt.Font("맑은 고딕", 0, 18)); // NOI18N
        businessmng_butt.setText("사업자");
        businessmng_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                businessmng_buttActionPerformed(evt);
            }
        });

        back_butt.setText("뒤로가기");
        back_butt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usermng_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(businessmng_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(244, 244, 244))
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(back_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(195, Short.MAX_VALUE)
                .addComponent(usermng_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(businessmng_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123)
                .addComponent(back_butt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usermng_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usermng_buttActionPerformed
        // TODO add your handling code here:
        A_UserManage UM = new A_UserManage();
        UM.setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_usermng_buttActionPerformed

    private void businessmng_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_businessmng_buttActionPerformed
        // TODO add your handling code here:
        A_BusinessManage BM = new A_BusinessManage();
        BM.setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_businessmng_buttActionPerformed

    private void back_buttActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttActionPerformed
        // TODO add your handling code here:
        
        ManagerMode mm = new ManagerMode(panelName);
        mm.setVisible(true);
        setVisible(false);
        dispose();
    }//GEN-LAST:event_back_buttActionPerformed

    /**
     * @param args the command line arguments
     */

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
       public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new A_MemberManage().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back_butt;
    private javax.swing.JButton businessmng_butt;
    private javax.swing.JButton usermng_butt;
    // End of variables declaration//GEN-END:variables
}
