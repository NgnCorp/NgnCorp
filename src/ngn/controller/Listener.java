package ngn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import ngn.model.*;
import ngn.view.Card;

/**
 *
 * @author Офис
 */
public class Listener {

    public static String ObjectInFocus;

    public static class CheckFocus implements FocusListener {

        public CheckFocus() {

        }

        @Override
        public void focusGained(FocusEvent e) {
            ObjectInFocus = e.getComponent().getName();
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

    public static class CardCodeActionPerformed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String CardCode = e.getActionCommand();
            if (CardCode.length() == 10) {
                if (DB.cardCode(CardCode)) {
                    Card.CardDo();
                }
            }
        }
    }
}