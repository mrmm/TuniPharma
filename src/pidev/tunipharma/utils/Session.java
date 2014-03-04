/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.tunipharma.utils;

import pidev.tunipharma.classes.Compte;

/**
 *
 * @author elron
 */
public class Session {

    private static Session ses;
    private static Compte cptConn;

    private Session(Compte c) {
        cptConn = c;
    }

    public static Session connecter(Compte c) {
        if (ses == null) {
            ses = new Session(c);
            cptConn = c;
        }
        return (ses);

    }

    public static void deconnecter() {
        ses = null;
        cptConn = null;
        GUIUtils.showMsgBox("Vous êtes deconnecté !");
    }

    public static Compte getCptConn() {
        return cptConn;
    }

    public static Session getSes() {
        return ses;
    }

}
