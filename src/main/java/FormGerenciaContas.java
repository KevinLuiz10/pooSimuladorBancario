//Kevin Luiz Botelho Lima - RA: 2266423

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class FormGerenciaContas extends javax.swing.JFrame {
    
    private GerCliente gc = GerCliente.geraGerCliente();
    private Cliente c1 = new Cliente();
    public boolean cc, cp, ci;
    
    private static FormGerenciaContas gerenciaContasUnic; // 1º passo singleton
    
    private FormGerenciaContas() { //2º passo singleton
        initComponents();
    }
    
    public static FormGerenciaContas geraGerenciaContas(Cliente c1){ //3º passo singleton
        if(gerenciaContasUnic == null){
                gerenciaContasUnic = new FormGerenciaContas();
            }
        
        gerenciaContasUnic.atualizaC1(c1);
        return gerenciaContasUnic;
    }
    
    public void atualizaC1(Cliente c1){
        this.c1 = c1;
        preencherCampos();
    }
    private void preencherCampos(){
        
        labelDadoCpf.setText(String.valueOf(c1.getCpf()));
        labelDadoNome.setText(c1.getNome());
        labelDadoDataNascimento.setText(c1.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        labelDadoSexo.setText(c1.getSexo());
        labelDadoCelular.setText(String.valueOf(c1.getCelular()));
        
        if(c1.getContaCorrente() == null){
            labelCorrente.setText("- Nao possui conta corrente -");
            buttonCorrente.setText("Criar Conta Corrente");
            cc = false;
        }else{
            labelCorrente.setText("- Possui conta corrente -");
            buttonCorrente.setText("Visualizar dados da Conta Corrente");
            cc = true;
        }

        if(c1.getContaPoupanca() == null){
            labelPoupanca.setText("- Nao possui conta poupanca -");
            buttonPoupanca.setText("Criar Conta Poupanca");
            cp = false;
        }else{
            labelPoupanca.setText("- Possui conta poupanca -");
            buttonPoupanca.setText("Visualizar dados da Conta Poupanca");
            cp = true;
        }

        if(c1.getContaInvestimento() == null){
            labelInvestimento.setText("- Nao possui conta de investimento -");
            buttonInvestimento.setText("Criar Conta de Investimento");
            ci = false;
        }else{
            labelInvestimento.setText("- Possui conta de investimento -");
            buttonInvestimento.setText("Visualizar dados da Conta de Investimento");
            ci = true;
        }
    }
    
/*-----------------------SEÇÃO CONTA CORRENTE----------------------------------------*/
    
    //Metodo para a criação de conta corrente
    public void criaCc(){
        //metodo que instancia uma conta do tipo corrente
        c1.criarCc();

        //Estapelece o número da agência;
        String agencia = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o numero da agencia: ",
                                    "Criação de conta corrente",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
        c1.getContaCorrente().setAgencia(Integer.parseInt(agencia));

        //Estabelece o limite de cheque especial da conta
        String chequeEspecial = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o limite de cheque especial para a conta: ",
                                    "Criação de conta corrente",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
        c1.getContaCorrente().setLimiteChequeEspecial(Double.parseDouble(chequeEspecial));
        
        //Inicia o numero de transferências gratuitas para 5;
        c1.getContaCorrente().setNumeroTransferenciasGratuitas(5);

        //Salva que a tarifa para a realização de transferência bancária é de 13 reais;
        c1.getContaCorrente().setValorTarifaTransferencia(13);

        //Inicia o saldo da conta como 0;
        c1.getContaCorrente().setSaldo(0);

        //Pega a data atual da aplicação e estabelece para o atributo dataAbertura;
        c1.getContaCorrente().setDataAbertura(FormPrincipal.hoje);
        
        //Atualiza o cadastro do cliente em questão no array list
        c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                        null,
                        "Conta corrente criada com sucesso!",
                        "Criação de conta corrente",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
    }
    
    //Metodo para chamar a tela de visualização dos dados da conta corrente
    public void geraVisualizarCorrente(){
        FormVisualizarCorrente.geraVisualizaCorrente(c1).setVisible(true);
    }
    
    /*-----------------------SEÇÃO CONTA POUPANÇA----------------------------------------*/
    
    //Metodo para a criação de conta corrente
    public void criaCp(){
        //metodo que instancia uma conta do tipo poupança
        c1.criarCp();

        //Estapelece o número da agência;
        String agencia = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o numero da agencia: ",
                                    "Criação de conta poupanca",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
        c1.getContaPoupanca().setAgencia(Integer.parseInt(agencia));

        //Estabelece o rendimento mensal da conta
        String rendimentoMensal = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o rendimento mensal da conta (em porcentagem): ",
                                    "Criação de conta poupanca",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
        c1.getContaPoupanca().setRendimentoMensal(Double.parseDouble(rendimentoMensal));
        
        //Inicia o numero de saques diarios para 3
        c1.getContaPoupanca().setNumeroSaquesDiarios(3);

        //Inicia o saldo da conta como 0;
        c1.getContaPoupanca().setSaldo(0);

        //Pega a data atual da aplicação e estabelece para o atributo dataAbertura;
        c1.getContaPoupanca().setDataAbertura(FormPrincipal.hoje);
        
        //Atualiza o cadastro do cliente em questão no array list
        c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                        null,
                        "Conta poupanca criada com sucesso!",
                        "Criação de conta poupanca",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
    }
    
    //Metodo para chamar a tela de visualização dos dados da conta poupanca
    public void geraVisualizarPoupanca(){
        FormVisualizarPoupanca.geraVisualizaPoupanca(c1).setVisible(true);
    }
    
    
/*-----------------------SEÇÃO CONTA INVESTIMENTO----------------------------------------*/
    
    //Metodo para a criação de conta investimento
    public void criaCi(){
        double depositoInicial;
        boolean depositoValido = true;
        
        //metodo que instancia uma conta do tipo investimento
        c1.criarCi();

        //Estapelece o número da agência;
        String agencia = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o numero da agencia: ",
                                    "Criação de conta investimento",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
        c1.getContaInvestimento().setAgencia(Integer.parseInt(agencia));

        //Estabelece a taxa mensal de administração (%)
        String taxaAdministracao = JOptionPane.showInputDialog(
                                    null,
                                    "Informe a taxa de administracao da conta (R$): ",
                                    "Criação de conta investimento",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
        c1.getContaInvestimento().setTaxaAdministracao(Double.parseDouble(taxaAdministracao));
        
        //Solicita um depósito inicial para a criação da conta investimento
        while(depositoValido){
            
                depositoInicial = Double.parseDouble(JOptionPane.showInputDialog(
                                        null,
                                        "Para finalizar a criacao da conta, é necessario o depósito de, no minimo R$ 100.00 \nInforme o valor de deposito (R$): ",
                                        "Criação de conta investimento",
                                        JOptionPane.INFORMATION_MESSAGE
                                ));

                if (depositoInicial < 100.0){
                    JOptionPane.showMessageDialog(
                        null,
                        "O valor do deposito deve ser, no minimo, R$100.00",
                        "Erro de tipo de Dados",
                        JOptionPane.ERROR_MESSAGE
                    );
                } else{
                    c1.getContaInvestimento().depositar(depositoInicial);
                    depositoValido = false;
                }
            }   

        //Pega a data atual da aplicação e estabelece para o atributo dataAbertura;
        c1.getContaInvestimento().setDataAbertura(FormPrincipal.hoje);
        
        //Atualiza o cadastro do cliente em questão no array list
        c1 = gc.atualizaClienteCpf(c1);

            if(c1 != null){
                JOptionPane.showMessageDialog(
                        null,
                        "Conta investimento criada com sucesso!",
                        "Criação de conta investimento",
                        JOptionPane.INFORMATION_MESSAGE
                );
                preencherCampos();
            }
    }
    
    //Metodo para chamar a tela de visualização dos dados da conta investimento
    public void geraVisualizarInvestimento(){
        FormVisualizarInvestimento.geraVisualizaInvestimento(c1).setVisible(true);
    }
    
/**/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitulo = new javax.swing.JLabel();
        labelSexo = new javax.swing.JLabel();
        labelNome = new javax.swing.JLabel();
        labelCelular = new javax.swing.JLabel();
        labelCorrente = new javax.swing.JLabel();
        labelDataNascimento = new javax.swing.JLabel();
        labelPoupanca = new javax.swing.JLabel();
        labelInvestimento = new javax.swing.JLabel();
        labelDadoCpf = new javax.swing.JLabel();
        labelDadoNome = new javax.swing.JLabel();
        labelDadoDataNascimento = new javax.swing.JLabel();
        labelDadoSexo = new javax.swing.JLabel();
        labelDadoCelular = new javax.swing.JLabel();
        labelCpf1 = new javax.swing.JLabel();
        buttonCorrente = new javax.swing.JButton();
        buttonPoupanca = new javax.swing.JButton();
        buttonInvestimento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        labelTitulo.setText("Gerenciamento de contas");

        labelSexo.setText("Sexo:");

        labelNome.setText("Nome:");

        labelCelular.setText("Celular:");

        labelCorrente.setText("Default");

        labelDataNascimento.setText("Data de nascimento:");

        labelPoupanca.setText("Default");

        labelInvestimento.setText("Default");

        labelDadoCpf.setText("Default");

        labelDadoNome.setText("Default");

        labelDadoDataNascimento.setText("Default");

        labelDadoSexo.setText("Default");

        labelDadoCelular.setText("Default");

        labelCpf1.setText("CPF:");
        labelCpf1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                labelCpf1ComponentHidden(evt);
            }
        });

        buttonCorrente.setText("Corrente");
        buttonCorrente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCorrenteActionPerformed(evt);
            }
        });

        buttonPoupanca.setText("Poupança");
        buttonPoupanca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPoupancaActionPerformed(evt);
            }
        });

        buttonInvestimento.setText("Investimento");
        buttonInvestimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInvestimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonInvestimento)
                    .addComponent(buttonPoupanca)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelDataNascimento)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(buttonCorrente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDadoNome)
                                    .addComponent(labelDadoDataNascimento)
                                    .addComponent(labelDadoCpf)
                                    .addComponent(labelDadoCelular)
                                    .addComponent(labelDadoSexo))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelInvestimento)
                                    .addComponent(labelPoupanca)
                                    .addComponent(labelCorrente)
                                    .addComponent(labelSexo)
                                    .addComponent(labelCelular)
                                    .addComponent(labelNome)
                                    .addComponent(labelCpf1))
                                .addContainerGap()))
                        .addComponent(labelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(labelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCpf1)
                    .addComponent(labelDadoCpf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNome)
                    .addComponent(labelDadoNome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDataNascimento)
                    .addComponent(labelDadoDataNascimento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSexo)
                    .addComponent(labelDadoSexo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCelular)
                    .addComponent(labelDadoCelular))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCorrente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPoupanca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelInvestimento)
                .addGap(18, 18, 18)
                .addComponent(buttonCorrente)
                .addGap(18, 18, 18)
                .addComponent(buttonPoupanca)
                .addGap(18, 18, 18)
                .addComponent(buttonInvestimento)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelCpf1ComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_labelCpf1ComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_labelCpf1ComponentHidden

    private void buttonCorrenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCorrenteActionPerformed
        if(cc){
            dispose();
            geraVisualizarCorrente();
        }else{
            criaCc();
        }
    }//GEN-LAST:event_buttonCorrenteActionPerformed

    private void buttonPoupancaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPoupancaActionPerformed
        if(cp){
            dispose();
            geraVisualizarPoupanca();
        }else{
            criaCp();
        }
    }//GEN-LAST:event_buttonPoupancaActionPerformed

    private void buttonInvestimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInvestimentoActionPerformed
        if(ci){
            dispose();
            geraVisualizarInvestimento();
        }else{
            criaCi();
        }
    }//GEN-LAST:event_buttonInvestimentoActionPerformed

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
            java.util.logging.Logger.getLogger(FormGerenciaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormGerenciaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormGerenciaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormGerenciaContas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                gerenciaContasUnic.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCorrente;
    private javax.swing.JButton buttonInvestimento;
    private javax.swing.JButton buttonPoupanca;
    private javax.swing.JLabel labelCelular;
    private javax.swing.JLabel labelCorrente;
    private javax.swing.JLabel labelCpf1;
    private javax.swing.JLabel labelDadoCelular;
    private javax.swing.JLabel labelDadoCpf;
    private javax.swing.JLabel labelDadoDataNascimento;
    private javax.swing.JLabel labelDadoNome;
    private javax.swing.JLabel labelDadoSexo;
    private javax.swing.JLabel labelDataNascimento;
    private javax.swing.JLabel labelInvestimento;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelPoupanca;
    private javax.swing.JLabel labelSexo;
    private javax.swing.JLabel labelTitulo;
    // End of variables declaration//GEN-END:variables
}
