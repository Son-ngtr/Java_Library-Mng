package Library_UI.Lib_UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImagingOpException;

public class Login_UI {
    private JFrame main_Frame;
    private ImageIcon bk_Icon, notepad_Icon, login_Ani, login_ef;
    private JLabel label, notepad_Label, login_Icon;
    private JButton button;
    private JTextField txtName, txt_name, txt_pass, txt_info, txt_Group;
    private JButton logIn;
    private JPanel inFo;
    private JPasswordField txtPassword;

    public Login_UI(){

        ImageIcon bk_Icon = new ImageIcon("src/Image_Icon/background/login (1).png");
        JLabel label = new JLabel(bk_Icon);
        label.setSize(935,499);


//        ImageIcon notepad_Icon = new ImageIcon("src/image/notepad_Gif.gif");
//        notepad_Label = new JLabel(notepad_Icon);
//        notepad_Label.setSize(80,80);
//        notepad_Label.setBounds(715,205+10,80,80);

        Font Font_me = new Font("MV Boli", Font.PLAIN, 12);
        Font Font_login = new Font("MV Boli", Font.PLAIN, 16);
        Font Font_me_2 = new Font("Lucida Console", Font.PLAIN, 48);
        Font Font_me_3 = new Font("MV Boli", Font.ITALIC, 12);

        Color Color_me = new Color(250,183,61);
        Color Color_ForeG = new Color(13,54,57);
        Color Color_ForeG_2 = new Color(43,51,31);

        txtName = new JTextField(362);
        txtName.setBackground(Color_me);
        txtName.setFont(Font_me );
        txtName.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txtName.setForeground(Color_ForeG);
        txtName.setBounds(350,220+10,362,25);

        txtPassword = new JPasswordField(362);
        txtPassword.setBackground(Color_me);
        txtPassword.setFont(Font_me );
        txtPassword.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txtPassword.setForeground(Color_ForeG);
        txtPassword.setEchoChar('.');
        txtPassword.setBounds(350,250+10,335,25);

        txt_name = new JTextField("     NAME",362);
        txt_name.setBackground(Color_me);
        txt_name.setFont(Font_me );
        txt_name.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_name.setForeground(Color_ForeG);
        txt_name.setBounds(222,220+10,100,25);
        txt_name.setEditable(false);

        txt_pass = new JTextField("   PASSWORD",362);
        txt_pass.setBackground(Color_me);
        txt_pass.setFont(Font_me );
        txt_pass.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_pass.setForeground(Color_ForeG);
        txt_pass.setBounds(222,250+10,100,25);
        txt_pass.setEditable(false);

        logIn = new JButton("LOG IN");
        logIn.setBounds(410,320+10,110,25);
        logIn.setFont(Font_login);
        logIn.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        logIn.setForeground(Color_ForeG);
        logIn.setBackground(Color_me);

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------





//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().equals("admin") && txtPassword.getText().equals("password")){
//                    JOptionPane.showMessageDialog(null, "Log in completed");
                    new Lobby_UI();
                }
                else JOptionPane.showMessageDialog(null,"Log in uncompleted");
            }
        });

//        ImageIcon login_Ani = new ImageIcon("src/image/login_Ani.gif");
//        login_Icon = new JLabel(login_Ani);
//        login_Icon.setSize(25,25);
//        login_Icon.setBackground(Color_me);
//        login_Icon.setBounds(415,320+10,25,25);

        txt_info = new JTextField("LIBRARY MANAGEMENT",362);
        txt_info.setBackground(new Color(84, 103, 71));
        txt_info.setFont(Font_me_2);
        txt_info.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_info.setForeground(Color_me);
        txt_info.setBounds(222,125,550,100);
        txt_info.setEditable(false);

        txt_Group = new JTextField("designed by TropicalHorseTeam");
        txt_Group.setBackground(new Color(84, 103, 71));
        txt_Group.setFont(Font_me_3);
        txt_Group.setBorder(BorderFactory.createLineBorder(new Color(84, 103, 71)));
        txt_Group.setForeground(Color_ForeG);
        txt_Group.setBounds(542,466,230,20);
        txt_Group.setEditable(false);

// set Eye password - Show - Hide
        JTextField b_G_T = new JTextField(" ");
        b_G_T.setBackground(Color_me);
        b_G_T.setForeground(Color_me);
        b_G_T.setBorder(BorderFactory.createLineBorder(Color_me));
        b_G_T.setBounds(684,250+11,27,23);

        ImageIcon eyeOpen_Icon = new ImageIcon("src/Image_Icon/icon/Eye_Open.png");
        JLabel eyeOpen_Label = new JLabel(eyeOpen_Icon);
        eyeOpen_Label.setSize(25,25);
        eyeOpen_Label.setBounds(685,260,25,25);
        eyeOpen_Label.setBackground(Color_me);
        eyeOpen_Label.setVisible(false);

        ImageIcon eyeClose_Icon = new ImageIcon("src/Image_Icon/icon/Eye_Close.png");
        JLabel eyeClose_Label = new JLabel(eyeClose_Icon);
        eyeClose_Label.setSize(25,25);
        eyeClose_Label.setBounds(685,260,25,25);
        eyeClose_Label.setVisible(true);

        eyeOpen_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eyeClose_Label.setVisible(true);
                eyeOpen_Label.setVisible(false);
                txtPassword.setEchoChar('.');
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        eyeClose_Label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eyeClose_Label.setVisible(false);
                eyeOpen_Label.setVisible(true);
                txtPassword.setEchoChar((char)0);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        label.add(eyeOpen_Label);
        label.add(eyeClose_Label);
        label.add(b_G_T);
        label.add(txtName);
        label.add(txtPassword);
        label.add(txt_name);
        label.add(txt_pass);
//        label.add(login_Icon);
        label.add(logIn);
        label.add(txt_info);
//        label.add(notepad_Label);
        label.add(txt_Group);

        main_Frame = new JFrame("Main_UI");
        main_Frame.add(label);
        main_Frame.setSize(933,496);
        main_Frame.setResizable(false);;
        main_Frame.setLayout(null);
        main_Frame.setDefaultCloseOperation(main_Frame.EXIT_ON_CLOSE);
        main_Frame.setLocationRelativeTo(null);
        main_Frame.setUndecorated(true);
        main_Frame.setVisible(true);
    }

//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------





    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        new Login_UI();
    }
}

