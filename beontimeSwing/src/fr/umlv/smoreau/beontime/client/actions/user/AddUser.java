package fr.umlv.smoreau.beontime.client.actions.user;

import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Collection;

import javax.swing.JOptionPane;

import net.sf.hibernate.HibernateException;

import fr.umlv.smoreau.beontime.client.actions.Action;
import fr.umlv.smoreau.beontime.client.graphics.MainFrame;
import fr.umlv.smoreau.beontime.client.graphics.windows.AddModifyUserWindow;
import fr.umlv.smoreau.beontime.dao.DaoManager;
import fr.umlv.smoreau.beontime.filter.UserFilter;
import fr.umlv.smoreau.beontime.model.user.User;

/**
 * @author BeOnTime
 */
public class AddUser extends Action {
    protected AddUser(String name, String smallIcon, String icon, MainFrame mainFrame, String type) {
        super(name, smallIcon, icon, mainFrame);
        this.type = type;
    }

    private String type;


    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent arg0) {
        AddModifyUserWindow window = new AddModifyUserWindow(type, AddModifyUserWindow.TYPE_ADD);
        window.show();
        
        if (window.isOk()) {
            User user = new User();
            user.setName(window.getName());
            user.setFirstName(window.getSurname());
            user.setEMail(window.getCourrielMail());
            user.setBuildingNameForOffice(window.getBuilding());
            user.setOfficeName(window.getLocal());
            user.setTelephone(window.getPhone());
            user.setUserType(type);
            user.setFormationsInCharge(window.getFormations());
            
            try {
                String login = generateLogin(window.getName(), window.getSurname());
                user.setLogin(login);
                String password = generatePassword(8);
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                user.setPassword(new String(md.digest()));
                user = DaoManager.getUserDao().addUser(user);
                
                //mainFrame.getModel().fireRefreshUser(user, BoTModel.TYPE_ADD);
                
                JOptionPane.showMessageDialog(null, "Ajout effectué avec succès\nLogin: "+login+"\nMot de passe: "+password, "Login et mot de passe", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Une erreur interne est survenue", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private static final String generateLogin(String name, String firstName) throws RemoteException, HibernateException {
        name = name.toLowerCase();
        firstName = firstName.toLowerCase();
        String login = firstName.charAt(0) + name.substring(0, name.length() > 7 ? 7 : name.length());
        
        UserFilter filter = new UserFilter();
        filter.setLogin(login);
        Collection c = DaoManager.getUserDao().getUsers(filter);
        
        for (int i = 1; c.size() > 0; ++i) {
            login = Character.toString(firstName.charAt(0));
            login += name.substring(0, name.length() > 5 ? 5 : name.length());
            if (i < 10)
                login += "0" + i;
            else
                login += i;
            filter.setLogin(login);
            c = DaoManager.getUserDao().getUsers(filter);
        }
        
        return login;
    }
    
    private static char[] pwdChars = "abcdefghijklmnopqrstuvqxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();
    
    private static final String generatePassword(int length) throws RemoteException, HibernateException {
        char[] pwd = new char[length]; 
        try { 
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG"); 
            
            byte[] intbytes = new byte[4];       
            
            for (int i = 0; i < length; ++i) { 
                random.nextBytes(intbytes); 
                pwd[i] = pwdChars[Math.abs(getIntFromByte(intbytes) % pwdChars.length)]; 
            } 
        } 
        catch (Exception ex) { 
            // Don't really worry, we won't be using this if we can't use securerandom anyway 
        } 
        return new String(pwd); 
    }
    
    private static int getIntFromByte(byte[] bytes) { 
        int returnNumber = 0; 
        int pos = 0; 
        returnNumber += byteToInt(bytes[pos++]) << 24; 
        returnNumber += byteToInt(bytes[pos++]) << 16; 
        returnNumber += byteToInt(bytes[pos++]) << 8; 
        returnNumber += byteToInt(bytes[pos++]) << 0; 
        return returnNumber; 
    } 
    
    private static int byteToInt(byte b) { 
        return (int) b & 0xFF; 
    } 
}
