//=================================================================================================
// Program		: Interactive Sentence Predictor
// Class		: Predictor.java
// Developer		: Renae Fisher
// Abstract		: This class works with the Predictor and Voce.
//=================================================================================================

import java.util.HashMap;
import voce.SpeechInterface;

/**
 *
 * @author fishe
 */
public class NatLangPredGUI extends javax.swing.JFrame {

    /**
     * Creates new form NatLangPredGUI
     */
    HashMap<String, Integer> unigrams;
    HashMap<String, Integer> bigrams;
    HashMap<String, Integer> trigrams;
    Load l;
    static Predictor p;
    int row;

    public NatLangPredGUI() {

        initComponents();

        l = new Load();

        unigrams = l.getUnigrams();
        bigrams = l.getBigrams();
        trigrams = l.getTrigrams();

        //TEST DATA USING HAND WRITTEN CSV
        //unigrams = TestLoad.loadNGrams("./data/unigrams.csv");
        //bigrams = TestLoad.loadNGrams("./data/bigrams.csv");
        //trigrams = TestLoad.loadNGrams("./data/trigrams.csv");

        p = new Predictor(unigrams, bigrams, trigrams);

        row = 0;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Listen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Natural Language Predictor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
                        .addComponent(jScrollPane1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        startVoiceRecognition();

    }//GEN-LAST:event_jButton1ActionPerformed

    public void startVoiceRecognition() {

        String speech = "";
        String bRes = "";
        String tRes = "";

        voce.SpeechInterface.init("data/", false, true, "data/", "final");

        boolean quit = false;
        while (!quit) {

            // Normally, applications would do application-specific things 
            // here.  For this sample, we'll just sleep for a little bit.
            try {

                Thread.sleep(50);

            } catch (InterruptedException e) {
                
            }

            while (voce.SpeechInterface.getRecognizerQueueSize() > 0) {
 
                speech = voce.SpeechInterface.popRecognizedString();

                if (speech.split(" ").length >= 3) {

                    bRes = p.bigramSentence(speech);
                    tRes = p.trigramSentence(speech);

                    quit = true;

                }

                //System.out.println("You said: " + speech);
                //System.out.println("BIGRAM MODEL: " + bRes);
                //System.out.println("TRIGRAM MODEL: " + tRes);
            }

        }

        voce.SpeechInterface.destroy();

        //System.exit(0);

        if (row > 50) {

            row = 0;

            jTextArea2.setText("");
            jTextArea1.setText("");
            jTextArea1.setText("");

        }

        jTextArea2.append("VOCE RECOGNIZED: " + speech + "\n");
        jTextArea1.append("BIGRAM MODEL: " + bRes + "\n");
        jTextArea1.append("TRIGRAM MODEL: " + tRes + "\n");
        row++;

    }

    public static void commandLineTest() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NatLangPredGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NatLangPredGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NatLangPredGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NatLangPredGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new NatLangPredGUI().setVisible(true);
            }

        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
}
