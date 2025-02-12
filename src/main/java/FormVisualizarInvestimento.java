//Kevin Luiz Botelho Lima - RA: 2266423

import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;


public class FormVisualizarInvestimento extends javax.swing.JFrame {
    private GerCliente gc = GerCliente.geraGerCliente();
    private Cliente c1 = new Cliente();
    private static FormVisualizarInvestimento visualizaInvestimentoUnic; // 1º passo singleton
    
    private FormVisualizarInvestimento() { // 2º passo singleton
        initComponents();
    }
    
    public static FormVisualizarInvestimento geraVisualizaInvestimento(Cliente c1){ //3º passo singleton
        if(visualizaInvestimentoUnic == null){
                visualizaInvestimentoUnic = new FormVisualizarInvestimento();
            }
            
            visualizaInvestimentoUnic.atualizaC1(c1);
            return visualizaInvestimentoUnic;
    }
    
    public void atualizaC1(Cliente c1){
        this.c1 = c1;
        preencherCampos();
    }
    
    //Metodo para informar na tela as informações da conta corrente selecionada
    private void preencherCampos(){
        
        labelDadoCpf.setText(String.valueOf(c1.getCpf()));
        labelDadoNome.setText(c1.getNome());
        labelDadoNConta.setText(String.valueOf(c1.getContaInvestimento().getNumeroConta()));
        labelDadoAgencia.setText(String.valueOf(c1.getContaInvestimento().getAgencia()));
        labelDadoDataAbertura.setText(c1.getContaInvestimento().getDataAbertura().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        labelDadoTaxaAdministrativa.setText(String.format("%.2f", c1.getContaInvestimento().getTaxaAdministracao()));
        labelDadoSaldo.setText(String.format("%.2f", c1.getContaInvestimento().getSaldo()));
               
        //Verifica se a conta possui uma compra de ações ativa
        //Se tiver, mostrará na tela as informações da compra de ações
        //Se não tiver, informará que não possui uma compra de ações ativa
        if(c1.getContaInvestimento().getCompraAcoes1().getCompraRealizada() == false){
            labelAcoes.setText("Não possui uma compra de acoes.");
            labelValorTotalCompraAcoes.setVisible(false);
            labelQuantAcoes.setVisible(false);
            labelValorCompraAcoes.setVisible(false);
            labelDataCompraAcoes.setVisible(false);
        } else{
            labelAcoes.setText("Possui uma compra de acoes:");
            labelValorTotalCompraAcoes.setVisible(true);
            labelQuantAcoes.setVisible(true);
            labelValorCompraAcoes.setVisible(true);
            labelDataCompraAcoes.setVisible(true);
            labelValorTotalCompraAcoes.setText("Valor da compra: R$"+ String.format("%.2f", c1.getContaInvestimento().getCompraAcoes1().getQuantAcoes()*c1.getContaInvestimento().getCompraAcoes1().getValorCompra()));
            labelQuantAcoes.setText("Quantidade de acoes: "+ c1.getContaInvestimento().getCompraAcoes1().getQuantAcoes());
            labelValorCompraAcoes.setText("Valor de compra de cada acao: R$"+ String.format("%.2f", c1.getContaInvestimento().getCompraAcoes1().getValorCompra()));
            labelDataCompraAcoes.setText("Data da compra: "+ c1.getContaInvestimento().getCompraAcoes1().getDataCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        
        labelPetrobras.setText("Hoje, as acoes da Petrobras estao custando R$"+ String.format("%.2f",FormPrincipal.acoesPetrobras.getValor()) +" por acao.");
        
    }
    
    //Metodo para a realização de deposito na conta Investimento
    public void depositar(){
        boolean depositoValido = true;
        
        while(depositoValido){
            Double deposito = Double.valueOf(JOptionPane.showInputDialog(
                                    null,
                                    "Informe o valor a ser depositado: ",
                                    "Deposito em conta investimento",
                                    JOptionPane.INFORMATION_MESSAGE
                            ));
            
            if(deposito > 0){
                c1.getContaInvestimento().depositar(deposito);
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
                        "Deposito em conta investimento",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
        }  
    }
    
    
    //Metodo para a realização de saque na conta corrente
    public void sacar(){
        try{
            Double saque = Double.valueOf(JOptionPane.showInputDialog(
                                    null,
                                    "Informe o valor de saque: ",
                                    "Saque em conta investimento",
                                    JOptionPane.INFORMATION_MESSAGE
                            ));
            
            c1.getContaInvestimento().sacar(saque);
            
            //Atualiza o cadastro do cliente em questão no array list
            c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                        null,
                        "Saque realizado com sucesso!",
                        "Saque em conta investimento",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
        }
        catch (SemSaldoException sse){
            JOptionPane.showMessageDialog(
                        null,
                        sse.impErrSaldo(),
                        "Saque em conta investimento",
                        JOptionPane.INFORMATION_MESSAGE
                );
        } 
    }
    
    public void compraAcoes(){
        int quantAcoes;
        double valorAcao;
        
        quantAcoes = Integer.parseInt(JOptionPane.showInputDialog(
                                    null,
                                    "Informe a quantidade de ações que deseja comprar: ",
                                    "Compra de ações",
                                    JOptionPane.INFORMATION_MESSAGE
                            ));

        valorAcao = FormPrincipal.acoesPetrobras.getValor();

        int resp =  JOptionPane.showConfirmDialog(
                null,
                "O valor da compra sera: R$"+ String.format("%.2f",quantAcoes*valorAcao) +"\nConfirme para realizar a compra.",
                "Confirmação de Compra",
                JOptionPane.YES_NO_OPTION
        );
        
        if(resp == 0){
           try{
                c1.getContaInvestimento().comprarAcoes(quantAcoes, valorAcao, FormPrincipal.hoje);
            
                //Atualiza o cadastro do cliente em questão no array list
                c1 = gc.atualizaClienteCpf(c1);

                if(c1 != null){
                    JOptionPane.showMessageDialog(
                            null,
                            "Compra de ações realizada com sucesso!",
                            "Compra de ações",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    preencherCampos();
                }
            }catch(JaPossuiAcoesException jpae){
                JOptionPane.showMessageDialog(
                    null,
                    jpae.impErrCompra(),
                    "Erro na compra de ações",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }catch(SemSaldoException sse){
                JOptionPane.showMessageDialog(
                    null,
                    sse.impErrSaldo(),
                    "Erro na compra de ações",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
       }else{
            JOptionPane.showMessageDialog(
                    null,
                    "Compra de ações cancelada pelo usuário",
                    "Compra de ações",
                    JOptionPane.INFORMATION_MESSAGE
                );
            preencherCampos();
        }

    }/*-----------------FIM DO MÉTODO compraAcoes()----------------------*/
    
    
    //Metodo para realizar a venda de ações
    public void vendaAcoes(){
        int k;
        boolean a = true;
        
        //Apresenta o valor atual das ações da petrobras, informa quanto o cliente terá de lucro se vender 
        //e solicita a confirmação para proceder com a venda das ações
        int resp =  JOptionPane.showConfirmDialog(
                null,
                "Hoje, as acoes da Petrobras estao custando R$"+ String.format("%.2f",FormPrincipal.acoesPetrobras.getValor()) +" por acao.\n"
                        + "Caso realize a venda, terá R$"+ String.format("%.2f", c1.getContaInvestimento().verificarLucro(FormPrincipal.acoesPetrobras.getValor()))+ " de lucro.\n"
                                + "Confirme para realizar a venda.",
                "Confirmação de Venda",
                JOptionPane.YES_NO_OPTION
        );
        
        if(resp == 0){
            //Executa o método para vender as acoes
            c1.getContaInvestimento().venderAcoes(FormPrincipal.acoesPetrobras.getValor());
            
            //Atualiza o cadastro do cliente em questão no array list
            c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                    null,
                    "Venda de ações realizada com sucesso!",
                    "Venda de ações",
                    JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
        }else{
            JOptionPane.showMessageDialog(
                    null,
                    "Venda de ações cancelada pelo usuário",
                    "Venda de ações",
                    JOptionPane.INFORMATION_MESSAGE
                );
            preencherCampos();
        }
     
    }/*-----------------FIM DO MÉTODO vendaAcoes()----------------------*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNome = new javax.swing.JLabel();
        labelDataAbertura = new javax.swing.JLabel();
        labelDadoCpf = new javax.swing.JLabel();
        labelNConta = new javax.swing.JLabel();
        labelDadoNome = new javax.swing.JLabel();
        labelDadoNConta = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        labelCpf = new javax.swing.JLabel();
        buttonDepositar = new javax.swing.JButton();
        labelDadoAgencia = new javax.swing.JLabel();
        labelTitulo = new javax.swing.JLabel();
        labelDadoDataAbertura = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        labelDadoTaxaAdministrativa = new javax.swing.JLabel();
        buttonSacar = new javax.swing.JButton();
        labelTaxaAdministrativa = new javax.swing.JLabel();
        labelDadoSaldo = new javax.swing.JLabel();
        labelQuantAcoes = new javax.swing.JLabel();
        labelAcoes = new javax.swing.JLabel();
        labelValorTotalCompraAcoes = new javax.swing.JLabel();
        labelDataCompraAcoes = new javax.swing.JLabel();
        labelValorCompraAcoes = new javax.swing.JLabel();
        labelPetrobras = new javax.swing.JLabel();
        buttonCompraAcoes = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelNome.setText("Nome:");

        labelDataAbertura.setText("Data de abertura:");

        labelDadoCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDadoCpf.setText("Default");

        labelNConta.setText("Numero da conta:");

        labelDadoNome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelDadoNome.setText("Default");

        labelDadoNConta.setText("Default");

        labelSaldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelSaldo.setText("Saldo (R$):");

        labelCpf.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelCpf.setText("CPF:");
        labelCpf.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                labelCpfComponentHidden(evt);
            }
        });

        buttonDepositar.setText("Depositar");
        buttonDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDepositarActionPerformed(evt);
            }
        });

        labelDadoAgencia.setText("Default");

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTitulo.setText("Visualizacao de Conta Investimento");

        labelDadoDataAbertura.setText("Default");

        labelAgencia.setText("Agencia:");

        labelDadoTaxaAdministrativa.setText("Default");

        buttonSacar.setText("Sacar");
        buttonSacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSacarActionPerformed(evt);
            }
        });

        labelTaxaAdministrativa.setText("Taxa administrativa mensal (R$): ");

        labelDadoSaldo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelDadoSaldo.setText("Default");

        labelQuantAcoes.setText("Default");

        labelAcoes.setText("Default");

        labelValorTotalCompraAcoes.setText("Default");

        labelDataCompraAcoes.setText("Default");

        labelValorCompraAcoes.setText("Default");

        labelPetrobras.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelPetrobras.setText("Default");

        buttonCompraAcoes.setText("Comprar Ações");
        buttonCompraAcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCompraAcoesActionPerformed(evt);
            }
        });

        jButton1.setText("Vender Ações");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                                    .addComponent(labelNome)
                                    .addComponent(labelCpf))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDadoNome)
                                    .addComponent(labelDadoCpf)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(labelSaldo)
                                .addGap(18, 18, 18)
                                .addComponent(labelDadoSaldo))
                            .addComponent(labelQuantAcoes)
                            .addComponent(labelValorTotalCompraAcoes)
                            .addComponent(labelDataCompraAcoes)
                            .addComponent(labelValorCompraAcoes)
                            .addComponent(labelPetrobras))
                        .addGap(0, 0, Short.MAX_VALUE))
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
                            .addComponent(labelAcoes)
                            .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelTaxaAdministrativa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDadoTaxaAdministrativa))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonDepositar)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSacar)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCompraAcoes)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                    .addComponent(labelTaxaAdministrativa)
                    .addComponent(labelDadoTaxaAdministrativa))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSaldo)
                    .addComponent(labelDadoSaldo))
                .addGap(44, 44, 44)
                .addComponent(labelAcoes)
                .addGap(18, 18, 18)
                .addComponent(labelValorTotalCompraAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelQuantAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelValorCompraAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDataCompraAcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelPetrobras)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonDepositar)
                    .addComponent(buttonSacar)
                    .addComponent(buttonCompraAcoes)
                    .addComponent(jButton1))
                .addGap(26, 26, 26))
        );

        setBounds(0, 0, 446, 509);
    }// </editor-fold>//GEN-END:initComponents

    private void labelCpfComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_labelCpfComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_labelCpfComponentHidden

    private void buttonDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDepositarActionPerformed
        depositar();
    }//GEN-LAST:event_buttonDepositarActionPerformed

    private void buttonSacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSacarActionPerformed
        sacar();
    }//GEN-LAST:event_buttonSacarActionPerformed

    private void buttonCompraAcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCompraAcoesActionPerformed
        compraAcoes();
    }//GEN-LAST:event_buttonCompraAcoesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        vendaAcoes();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormVisualizarInvestimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarInvestimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarInvestimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormVisualizarInvestimento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormVisualizarInvestimento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCompraAcoes;
    private javax.swing.JButton buttonDepositar;
    private javax.swing.JButton buttonSacar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel labelAcoes;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelDadoAgencia;
    private javax.swing.JLabel labelDadoCpf;
    private javax.swing.JLabel labelDadoDataAbertura;
    private javax.swing.JLabel labelDadoNConta;
    private javax.swing.JLabel labelDadoNome;
    private javax.swing.JLabel labelDadoSaldo;
    private javax.swing.JLabel labelDadoTaxaAdministrativa;
    private javax.swing.JLabel labelDataAbertura;
    private javax.swing.JLabel labelDataCompraAcoes;
    private javax.swing.JLabel labelNConta;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPetrobras;
    private javax.swing.JLabel labelQuantAcoes;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTaxaAdministrativa;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JLabel labelValorCompraAcoes;
    private javax.swing.JLabel labelValorTotalCompraAcoes;
    // End of variables declaration//GEN-END:variables
}
