/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ngn.controller;

import javax.swing.JPasswordField;
import ngn.view.Litrs;

/**
 *
 * @author Валерик
 */
public class ToZero {

    public static void CustomerInfo() {
        Variables.cardCode = "";
        Variables.customerId = 0;
        Variables.couponId = 0;
        Variables.pin = "";
        Variables.name = "";
        Variables.litrnum = "";
        Variables.code = "";
        Variables.customerPrice = 0.00;
        Variables.uahBalance = 0.00;
        Variables.purse = "";
        Variables.limitDay = 0;
        Variables.limitLitrs = 0.00;
        Variables.usedLimitLitrs = 0.00;
        Variables.isLimitClient = false;
        Variables.limitLitrnum = "";
        Variables.newln = "";
        Variables.leftlitr = "";
        Variables.BalanceOneCardZero = 0;
        Variables.credit = 0.00;
        Variables.customerBalance = 0.00;
        Variables.sdate = "";
        
        Timers.GSoffCount = 0;
    }

    public static void FocusOff() {
        for (JPasswordField InputArray : Variables.InputArray) {
            InputArray.setFocusable(false);
        }
        Litrs.LitrsInput.setFocusable(false);
    }

    public static void TextOff() {
        for (JPasswordField InputArray : Variables.InputArray) {
            InputArray.setText("");
        }
        Litrs.LitrsInput.setText("");
    }

}
