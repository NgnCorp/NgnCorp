package ngn.view;

/**
 *
 * @author Офис
 */
public class Pin {
    
    private javax.swing.JPanel EnterPin;
    private javax.swing.JLabel MarkPinCodeEnter;
    private javax.swing.JLabel MarkPinCodeExit;
    private javax.swing.JLabel PinAnimate;
    private javax.swing.JPasswordField PinCode;
    private javax.swing.JLabel PinCodeText;
    
    public Pin() {
        EnterPin = new javax.swing.JPanel();
        PinCode = new javax.swing.JPasswordField();
        PinAnimate = new javax.swing.JLabel();
        PinCodeText = new javax.swing.JLabel();
        MarkPinCodeExit = new javax.swing.JLabel();
        MarkPinCodeEnter = new javax.swing.JLabel();
        
        //EnterPin.setBackground(EnterCard.getBackground());
        EnterPin.setLayout(new java.awt.GridBagLayout());

        PinCode.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        PinCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        PinCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //PinCodeActionPerformed(evt);
            }
        });
        
        //PinCode.addFocusListener(new CustomListener());
  
        PinCodeText.setText("<html>Введите PIN-код");

        MarkPinCodeExit.setText("Для ВЫХОДА нажмите звездочку (*) на клавиатуре.");

        MarkPinCodeEnter.setText("Для ПОДТВЕРЖДЕНИЯ pin-кода нажмите решетку (#) на клавиатуре.");


    }
}
