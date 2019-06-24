/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.util;

/**
 *
 * @author mr foladare
 */
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MAMAT
 */
public class SessionUtil {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUsername() {
        HttpSession session = getSession();
        return (String) session.getAttribute("UserName");
    }

    public static String getKota() {
        HttpSession session = getSession();
        return (String) session.getAttribute("Kota");
    }

    public static String getUserID() {
        HttpSession session = getSession();
        if (session != null) {
            return (String) session.getAttribute("UserID");
        } else {
            return null;
        }
    }

    public static String getUserName() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        return session.getAttribute("username").toString();
    }

}
