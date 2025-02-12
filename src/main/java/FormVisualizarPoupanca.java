//Kevin Luiz Botelho Lima - RA: 2266423

import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class FormVisualizarPoupanca extends javax.swing.JFrame {
    private GerCliente gc = GerCliente.geraGerCliente();
    private Cliente c1 = new Cliente();
    private static FormVisualizarPoupanca visualizaPoupancaUnic; // 1º passo singleton
    
    private FormVisualizarPoupanca() { // 2º passo singleton
        initComponents();
    }
    
    public static FormVisualizarPoupanca geraVisualizaPoupanca(Cliente c1){ //3º passo singleton
        if(visualizaPoupancaUnic == null){
                visualizaPoupancaUnic = new FormVisualizarPoupanca();
            }
            
            visualizaPoupancaUnic.atualizaC1(c1);
            return visualizaPoupancaUnic;
    }
    
    public void atualizaC1(Cliente c1){
        this.c1 = c1;
        preencherCampos();
    }
    
    //Metodo para informar na tela as informações da conta corrente selecionada
    private void preencherCampos(){
        
        labelDadoCpf.setText(String.valueOf(c1.getCpf()));
        labelDadoNome.setText(c1.getNome());
        labelDadoNConta.setText(String.valueOf(c1.getContaPoupanca().getNumeroConta()));
        labelDadoAgencia.setText(String.valueOf(c1.getContaPoupanca().getAgencia()));
        labelDadoDataAbertura.setText(c1.getContaPoupanca().getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        labelDadoRendimentoMensal.setText(String.format("%.2f", c1.getContaPoupanca().getRendimentoMensal()));
        
        //Verifica se a conta em questão possui algum saque diário para utilizar.
        //Se possuir, apresenta o número de saques restantes
        //Se não possuir, informa que o cliente não pode realizar mais saques hoje
        if(c1.getContaPoupanca().getNumeroSaquesDiarios() == 0){
            labelDadoNSaques.setText("\nJá utilizou todos os saques diários. ");
        } else{
            labelNSaques.setText("\nNumero de saques diários restantes: ");
            labelDadoNSaques.setText(String.valueOf(c1.getContaPoupanca().getNumeroSaquesDiarios()));
        }
        
        labelDadoSaldo.setText(String.format("%.2f", c1.getContaPoupanca().getSaldo()));
        
    }
    
    //Metodo para a realização de deposito na conta poupaça
    public void depositar(){
        boolean depositoValido = true;
        
        while(depositoValido){
            Double deposito = Double.valueOf(JOptionPane.showInputDialog(
                                    null,
                                    "Informe o valor a ser depositado: ",
                                    "Deposito em conta poupanca",
                                    JOptionPane.INFORMATION_MESSAGE
                            ));
            
            if(deposito > 0){
                c1.getContaPoupanca().depositar(deposito);
                depositoValido = false;
            }else{
                JOptionPane.showMessageDialog(
                        null,
                        "O valor do deposito deve ser maior que R$ 0,00",
                        "Erro na entrada de dados",
                        JOptionPane.ERROR_MESSAGE
                    );
            }
            
            
            //Atualiza o cadastro do cliente em questão no array list
        c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                        null,
                        "Deposito realizado com sucesso!",
                        "Deposito em conta poupanca",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
        }  
    }
    
    //Metodo para a realização de saque na conta poupança
    public void sacar(){
        try{
            Double saque = Double.valueOf(JOptionPane.showInputDialog(
                                    null,
                                    "Informe o valor de saque: ",
                                    "Saque em conta poupanca",
                                    JOptionPane.INFORMATION_MESSAGE
                            ));
            
            c1.getContaPoupanca().sacar(saque);
            
            //Atualiza o cadastro do cliente em questão no array list
            c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                        null,
                        "Saque realizado com sucesso!",
                        "Saque em conta poupanca",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
        }
        catch (SemSaldoException sse){
            JOptionPane.showMessageDialog(
                        null,
                        sse.impErrSaldo(),
                        "Saque em conta poupanca",
                        JOptionPane.INFORMATION_MESSAGE
                );
        } 
        catch (NaoPossuiSaqueException npse){
            JOptionPane.showMessageDialog(
                        null,
                        npse.impErrSaque(),
                        "Saque em conta poupanca",
                        JOptionPane.INFORMATION_MESSAGE
                );
        }
    }
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelSaldo = new javax.swing.JLabel();
        buttonDepositar = new javax.swing.JButton();
        labelTitulo = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        buttonSacar = new javax.swing.JButton();
        labelNome = new javax.swing.JLabel();
        labelDataAbertura = new javax.swing.JLabel();
        labelDadoCpf = new javax.swing.JLabel();
        labelNConta = new javax.swing.JLabel();
        labelDadoNome = new javax.swing.JLabel();
        labelDadoNConta = new javax.swing.JLabel();
        labelCpf = new javax.swing.JLabel();
        labelDadoAgencia = new javax.swing.JLabel();
        labelDadoDataAbertura = new javax.swing.JLabel();
        labelRendimentoMensal = new javax.swing.JLabel();
        labelDadoRendimentoMensal = new javax.swing.JLabel();
        labelNSaques = new javax.swing.JLabel();
        labelDadoNSaques = new javax.swing.JLabel();
        labelDadoSaldo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelSaldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelSaldo.setText("Saldo (R$):");

        buttonDepositar.setText("Depositar");
        buttonDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDepositarActionPerformed(evt);
            }
        });

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTitulo.setText("Visualizacao de Conta Poupanca");

        labelAgencia.setText("Agencia:");

        buttonSacar.setText("Sacar");
        buttonSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSacarActionPerformed(evt);
            }
        });

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome:");

        labelDataAbertura.setText("Data de abertura:");

        labelDadoCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDadoCpf.setText("Default");

        labelNConta.setText("Numero da conta:");

        labelDadoNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDadoNome.setText("Default");

        labelDadoNConta.setText("Default");

        labelCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCpf.setText("CPF:");
        labelCpf.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                labelCpfComponentHidden(evt);
            }
        });

        labelDadoAgencia.setText("Default");

        labelDadoDataAbertura.setText("Default");

        labelRendimentoMensal.setText("Taxa de rendimento mensal (%): ");

        labelDadoRendimentoMensal.setText("Default");

        labelNSaques.setText("Número de saques diários disponíveis:");

        labelDadoNSaques.setText("Default");

        labelDadoSaldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelDadoSaldo.setText("Default");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNConta)
                                    .addComponent(labelAgencia)
                                    .addComponent(labelDataAbertura))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDadoNConta)
                                    .addComponent(labelDadoDataAbertura)
                                    .addComponent(labelDadoAgencia)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelNSaques)
                                .addGap(18, 18, 18)
                                .addComponent(labelDadoNSaques))
                            .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelRendimentoMensal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDadoRendimentoMensal)))
                        .addContainerGap(137, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonDepositar)
                                .addGap(51, 51, 51)
                                .addComponent(buttonSacar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNome)
                                    .addComponent(labelCpf))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDadoNome)
                                    .addComponent(labelDadoCpf)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSaldo)
                                .addGap(18, 18, 18)
                                .addComponent(labelDadoSaldo)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCpf)
                    .addComponent(labelDadoCpf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(labelDadoNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNConta)
                    .addComponent(labelDadoNConta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAgencia)
                    .addComponent(labelDadoAgencia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataAbertura)
                    .addComponent(labelDadoDataAbertura))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRendimentoMensal)
                    .addComponent(labelDadoRendimentoMensal))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNSaques)
                    .addComponent(labelDadoNSaques, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSaldo)
                    .addComponent(labelDadoSaldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDepositar)
                    .addComponent(buttonSacar))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDepositarActionPerformed
        depositar();
    }//GEN-LAST:event_buttonDepositarActionPerformed

    private void buttonSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSacarActionPerformed
        sacar();
    }//GEN-LAST:event_buttonSacarActionPerformed

    private void labelCpfComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_labelCpfComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_labelCpfComponentHidden

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
            java.util.logging.Logger.getLogger(FormVisualizarPoupanca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarPoupanca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarPoupanca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarPoupanca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVisualizarPoupanca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonDepositar;
    private javax.swing.JButton buttonSacar;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelDadoAgencia;
    private javax.swing.JLabel labelDadoCpf;
    private javax.swing.JLabel labelDadoDataAbertura;
    private javax.swing.JLabel labelDadoNConta;
    private javax.swing.JLabel labelDadoNSaques;
    private javax.swing.JLabel labelDadoNome;
    private javax.swing.JLabel labelDadoRendimentoMensal;
    private javax.swing.JLabel labelDadoSaldo;
    private javax.swing.JLabel labelDataAbertura;
    private javax.swing.JLabel labelNConta;
    private javax.swing.JLabel labelNSaques;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelRendimentoMensal;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
