package com.kharsh;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.io.FileWriter.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.text.ParseException;

import static java.lang.Thread.sleep;

public class Main {

    static JFrame frame = new JFrame("В Мире Линейности");
    static JFrame fr = new JFrame("for screen");
    static TableModel tM = new TableModel();
    static JTable regTable = new JTable(tM);
    static JTable regBattleTable = new JTable(tM);
    static JScrollPane regScrollPane = new JScrollPane(regTable);
    static JScrollPane regBattleScrollPane = new JScrollPane(regBattleTable);
    static JButton viborTableButton = new JButton("Выбор");
    static JButton otmenaTableButton = new JButton("Отмена");
    static JButton viborBattleTableButton = new JButton("Выбор");
    static JButton otmenaBattleTableButton = new JButton("Отмена");
    static JButton tableFragmentButton = new JButton("Фрагмент");
    static JTextField tableFragment = new JTextField(9);
    static CoordinateSystem cs = new CoordinateSystem();
    static final LinesComponent comp = new LinesComponent();
    static JDialog viborTable = new JDialog(frame, "Выбор Участника",true);
    static JDialog viborBattleTable = new JDialog(frame, "Выбор Участника",true);
    static String[] regRecord = new String[6];
    static JDialog messageBox = new JDialog(frame,"Сообщение",true);
    static JLabel message1 = new JLabel();
    static JLabel message2 = new JLabel();
    static JButton messageOK = new JButton("OK");
    static Send sD = new Send();
    static ImageIcon icon = new ImageIcon("minidraw.png");
    static JDialog signIn = new JDialog(frame, "Вход с паролем", true);
    static JButton vhod = new JButton("Вход");
    static JTextField logIn = new JTextField(12);
    static JPasswordField parole = new JPasswordField(12);
    static JLabel vvediteLogiParole = new JLabel("Введите свои логин и пароль.");
    static JLabel labelLogin = new JLabel("Логин:");
    static JLabel labelParole = new JLabel("Пароль:");

    static JDialog dialogRegistr = new JDialog(frame,"Регистрация",true);

    static JButton registracia = new JButton("Регистрация");
    //  Dialog Registration and its components

    static JLabel labelFamilia = new JLabel("Фамилия:");
    static JLabel labelImya = new JLabel("Имя:");
    static JLabel labelOtchestvo = new JLabel("Отчество:");
    static JLabel labelEmail = new JLabel("Электронный адрес:");
    static JLabel labelRegLogin = new JLabel("Логин:");
    static JLabel labelRegParole = new JLabel("Пароль");
    static JLabel labelPodtver = new JLabel("Подтверждение пароля:");
    static JLabel labelPhone = new JLabel("Телефон:");
    static JTextField familia = new JTextField(12);
    static JTextField imya = new JTextField(12);
    static JTextField otchestvo = new JTextField(12);
    static JTextField pochta = new JTextField(12);
    static JTextField regLogIn = new JTextField(12);
    static JTextField regPhone = new JTextField(12);
    static JPasswordField regParole = new JPasswordField(12);
    static JPasswordField regCofirm = new JPasswordField(12);
    static JButton zaregistrirovat = new JButton("Зарегистрировать.");
    static StriFont[] strFont = new StriFont[40];
    static JDialog daNet = new JDialog(frame,"",true);
    static JButton da = new JButton("Да");
    static JButton net = new JButton("Пока нет");
    static JButton daNetnazad = new JButton("НАЗАД");
    static JLabel poNyatno = new JLabel("Понятно ?");

    static JMenuBar menuBar = new JMenuBar();
    static JMenu menuSignIn = new JMenu("Логин");
    static JMenu menuTopics = new JMenu("Темы");
    static JMenu menuOptions = new JMenu("Дополнительно");
    static JMenu menuHelp = new JMenu("Для особо любознательных");
    static JMenu menuSettings = new JMenu("Настройки");

//    final static String adresURL = "http://217.23.139.149:8080/myWeb_war/";
//    final static String adresURL = "http://localhost:8080/";

    public static String viborUser(String cslogin,Boolean battleRegime,Boolean challenge,int bolelschik){
        int c,i;
        int ll;
        int j=0;
        String firstname="";
        String middlename="";
        String lastname="";
        String email="";
        String phone="";
//        String totalName ="";
        String login = "";
        String[][] regdata = new String[100][6];
        String otvet = "";
        String fragment = "";

        do {
            cs.viborMade = false;
            if(bolelschik == 0) {
                if (battleRegime) {
                    otvet = sD.doPost(cs.adresURL + "PoiskBattle", "login=" + cslogin);
                } else {
                    if (challenge) {
                        otvet = sD.doPost(cs.adresURL + "PoiskChallenge", "login=" + cslogin);
                    } else {
                        otvet = sD.doPost(cs.adresURL + "Poisk", "frag=" + fragment);
                    }
                }
            }
            else{
                if(bolelschik == 1){
                    otvet = sD.doPost(cs.adresURL + "PoiskBolelschika", "frag=" + fragment);
                }
                else{

                }
            }
            if(otvet.length()>0) {
                ll = otvet.length();
                i = 0;
                j = 0;
                while (i < ll && j < 100) {
                    while ((c = otvet.charAt(i++)) != ';') {
                        firstname += (char) c;
                    }
                    while ((c = otvet.charAt(i++)) != ';') {
                        middlename += (char) c;
                    }
                    while ((c = otvet.charAt(i++)) != ';') {
                        lastname += (char) c;
                    }
                    while ((c = otvet.charAt(i++)) != ';') {
                        login += (char) c;
                    }
                    while ((c = otvet.charAt(i++)) != ';') {
                        email += (char) c;
                    }
                    while ((c = otvet.charAt(i++)) != ';') {
                        phone += (char) c;
                    }
                    regdata[j][0] = firstname;
                    regdata[j][1] = middlename;
                    regdata[j][2] = lastname;
                    regdata[j][3] = email;
                    regdata[j][4] = phone;
                    regdata[j][5] = login;
                    tM.addData(regdata[j++]);
                    firstname = "";
                    middlename = "";
                    lastname = "";
                    email = "";
                    phone = "";
                    login = "";
                }
            }
            if(j>99){
                message1.setText("Слишком много строк. Введите фрагмент");
                message2.setText("фамилии, имени или отчества");
                messageBox.setVisible(true);
            }
            cs.SubTopic=0;
            if(battleRegime){
                viborBattleTable.setVisible(true);
 //               cs.SubTopic++;
            }
            else {
                viborTable.setVisible(true);
                fragment = tableFragment.getText();
            }
            j = 0;
            tM.clear();
        } while (( cs.viborMade == false) && cs.viborMadeOtmena == false);   // otvet.length()>0 ||
        return(regRecord[5]);
    }

    public static void main(String[] args) throws Exception{

        fr.setUndecorated(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(fr);
 //       cs.fio = args[0]+ " " + args[1];
        cs.menuTopics[0]="Координаты центра окружности";
        cs.menuTopics[1]="Точки с заданными координатами";
        cs.menuTopics[2]="Точки, удовлетворяющие условиям";
        cs.menuTopics[3]="Множества";
        cs.menuTopics[4]="Векторы";
        cs.menuTopics[5]="Линейные комбинации";
        cs.menuTopics[6]="Системы линейных уравнений";
        cs.menuTopics[7]="Скалярное произведение";
        cs.menuTopics[8]="Линейные операторы";
        cs.menuTopics[9]="Примеры";
        cs.menuTopics[10]="Определите матрицу";
        cs.menuTopics[11]="Умножение";
        cs.menuTopics[12]="Ортогональность";
        cs.menuTopics[13]="Траектории";
        cs.menuTopics[14]="Комплексные числа";


        fr.setVisible(false);
        cs.width = fr.getWidth();
        cs.height = fr.getHeight();
        cs.rightSpace = (int)(70./192.*cs.width);
        cs.downSpace = (int)(20./108.*cs.height);

//        final String adresURL = "http://217.23.139.149:8080/myWeb_war/";
     //   final String adresURL = "http://localhost:8080/";

        cs.otstup=(int)(3./192.*cs.width);
        cs.hskobka=(int)(8./108.*cs.height);
        cs.wskobka=(int)(2./192.*cs.width);
        cs.wchislo=(int)(4.5/192.*cs.width);


        cs.font = new Font("Times new Roman",Font.PLAIN,(int)(25.*cs.width/1920));
        cs.fontBold = new Font("Times new Roman",Font.BOLD,(int)(28.*cs.width/1920));
        cs.fontSmall = new Font("Times new Roman",Font.PLAIN,(int)(20.*cs.width/1920));
        cs.fontItalic = new Font("Times new Roman",Font.ITALIC,(int)(25.*cs.width/1920));
        cs.fontBig = new Font("Times new Roman",Font.PLAIN,(int)(60.*cs.width/1920));



        frame.setIconImage(icon.getImage());

        StriFont sf = new StriFont();
        frame.getContentPane().add(comp, BorderLayout.CENTER);

//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
 //       frame.setUndecorated(true);

        viborTable.setLocationRelativeTo(frame);
        viborTable.setLocation(cs.orx + cs.fxp-(int)(30.*cs.width/1920.),cs.ory - (int)(cs.fyp*cs.height/1080.));
        viborTable.setLayout(new GridBagLayout());
        viborTable.setSize((int)(800.*cs.width/1920.),(int)(900.*cs.height/1080.));
        viborTable.setLocationRelativeTo(frame);
        viborBattleTable.setLocation(cs.orx + cs.fxp-(int)(30.*cs.width/1920.),cs.ory - (int)(cs.fyp*cs.height/1080.));
        viborBattleTable.setLayout(new GridBagLayout());
        viborBattleTable.setSize((int)(800.*cs.width/1920.),(int)(900.*cs.height/1080.));
//        panelTable.setVisible(false);
//        frame.getContentPane().add(panelTable, BorderLayout.EAST);
//        comp.setPreferredSize(new Dimension((cs.orx+cs.fxp)*3/2,(int)((cs.ory+cs.fym)*1.1)));

  //      comp.setLayout(null);

        regTable.setRowHeight((int)(1.4*cs.font.getStringBounds("D", new FontRenderContext(new AffineTransform(), true, true)).getHeight()));
        regTable.setFont(cs.font);
        regBattleTable.setRowHeight((int)(1.4*cs.font.getStringBounds("D", new FontRenderContext(new AffineTransform(), true, true)).getHeight()));
        regBattleTable.setFont(cs.font);

        regScrollPane.setSize(new Dimension((int)(750.*cs.width/1920.),(int)(800.*cs.height/1080.)));
        regBattleScrollPane.setSize(new Dimension((int)(750.*cs.width/1920.),(int)(800.*cs.height/1080.)));
  //      regTable.setPreferredScrollableViewportSize(new Dimension(800,800));

 //       regScrollPane.setLocation(cs.orx + cs.fxp, cs.ory - cs.fyp);

//        JButton vibor = new JButton("Выбор");
//        JButton otmena = new JButton("Отмена");
//        JButton tableFragmentButton = new JButton("Фрагмент");
//        JTextField tableFragment = new JTextField(9);
        viborTableButton.setSize((int)(350.*cs.width/1920.),(int)(30.*cs.height/1080.));
        otmenaTableButton.setSize((int)(350.*cs.width/1920.),(int)(30.*cs.height/1080.));
        viborBattleTableButton.setSize((int)(350.*cs.width/1920.),(int)(30.*cs.height/1080.));
        otmenaBattleTableButton.setSize((int)(350.*cs.width/1920.),(int)(30.*cs.height/1080.));
        tableFragmentButton.setSize((int)(350.*cs.width/1920.),(int)(30.*cs.height/1080.));
        tableFragment.setSize((int)(350.*cs.width/1920.),(int)(30.*cs.height/1080.));



        viborTableButton.setFont(cs.font);
        otmenaTableButton.setFont(cs.font);
        viborBattleTableButton.setFont(cs.font);
        otmenaBattleTableButton.setFont(cs.font);
        tableFragmentButton.setFont(cs.font);
        tableFragment.setFont(cs.font);

        viborTable.add(regScrollPane,new GridBagConstraints(0, 0, 2, 3, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(40, 10, 10, 10), 0, 0));
        viborTable.add(tableFragment,new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 2, 10), 0, 0));
        viborTable.add(tableFragmentButton,new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 2, 10), 0, 0));
        viborTable.add(viborTableButton,new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 10, 10), 0, 0));
        viborTable.add(otmenaTableButton,new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 10, 10), 0, 0));


        viborBattleTable.add(regBattleScrollPane,new GridBagConstraints(0, 0, 2, 3, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(40, 10, 10, 10), 0, 0));
        viborBattleTable.add(viborBattleTableButton,new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 10, 10), 0, 0));
        viborBattleTable.add(otmenaBattleTableButton,new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 10, 10), 0, 0));



        viborTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = regTable.getSelectedRow();
                cs.viborMade = regTable.isRowSelected(rowIndex);
                for(int i=0;i<6;++i) {
                    regRecord[i]=tM.getValueAt(rowIndex, i).toString();
                }
                viborTable.dispose();
                cs.viborMadeOtmena = false;
     //           viborBattleTable.dispose();
     //           cs.viborMade = true;
            }
        });

        viborBattleTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowIndex = regBattleTable.getSelectedRow();
                cs.viborMade = regBattleTable.isRowSelected(rowIndex);
                for(int i=0;i<6;++i) {
                    regRecord[i]=tM.getValueAt(rowIndex, i).toString();
                }
                viborBattleTable.dispose();
                cs.viborMadeOtmena = false;
                //  cs.viborMade = true;
            }
        });

        tableFragmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viborTable.dispose();
            }
        });

        otmenaTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viborTable.dispose();
                viborBattleTable.dispose();
                cs.viborMadeOtmena = true;
            }
        });

        otmenaBattleTableButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viborBattleTable.dispose();
                cs.viborMadeOtmena = true;
            }
        });


        cs.orx=(int)((cs.width-cs.rightSpace)/1.95);
        cs.ory=(int)((cs.height-cs.downSpace)/1.95);
        cs.fxp = (int)(0.95f*cs.orx);
        cs.fxm = cs.fxp;
        cs.fyp = (int)(0.95f*cs.ory);
        cs.fym = cs.fyp;
        cs.lxp = (int)(0.96f*cs.fxp);
        cs.lxm = (int)(0.96f*cs.fxm);
        cs.lyp = cs.fyp - cs.fxp + cs.lxp;  //(int)(0.95f*fyp);
        cs.lym = cs.fym - cs.fxm + cs.lxm;  //          (int)(0.95f*fym);
        frame.setSize(cs.orx+cs.fxp+cs.rightSpace,cs.ory+cs.fym + cs.downSpace);
        frame.setResizable(false);
 //       delete fr;
 //       comp.backGround = frame.getContentPane().getBackground();
        cs.titulList();


//  Sign In Dialog and its components
 //       JDialog signIn = new JDialog(frame, "Вход с паролем", true);
//        JDialog messageBox = new JDialog(frame,"Сообщение",true);
        messageBox.setLocationRelativeTo(frame);
        messageBox.setLocation(cs.orx + cs.fxp+(int)(30.*cs.width/1920.),cs.ory + (int)(200.*cs.height/1080.));
        messageBox.setLayout(new GridBagLayout());
        messageBox.setSize((int)(600.*cs.width/1920.),(int)(200.*cs.height/1080.));
//        signIn.setSize(500,200);
        signIn.setLocationRelativeTo(frame);
        signIn.setLocation(cs.orx,cs.ory);
        signIn.setLayout(new GridBagLayout());
//        JTextField logIn = new JTextField(12);
//        JPasswordField parole = new JPasswordField(12);
//        JLabel vvediteLogiParole = new JLabel("Введите свои логин и пароль.");
//        JLabel message1 = new JLabel();
//        JLabel message2 = new JLabel();
//        JLabel labelLogin = new JLabel("Логин:");
//        JLabel labelParole = new JLabel("Пароль:");
 //       JButton messageOK = new JButton("OK");
        messageOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cs.animacia)
                    messageBox.dispose();
            }
        });

        message1.setFont(cs.font);
        message2.setFont(cs.font);
        messageOK.setFont(cs.font);
        familia.setFont(cs.font);
        imya.setFont(cs.font);
        otchestvo.setFont(cs.font);
        pochta.setFont(cs.font);
        regLogIn.setFont(cs.font);
        regParole.setFont(cs.font);
        regCofirm.setFont(cs.font);
        regPhone.setFont(cs.font);
        zaregistrirovat.setFont(cs.font);
        labelFamilia.setFont(cs.font);
        labelImya.setFont(cs.font);
        labelOtchestvo.setFont(cs.font);
        labelEmail.setFont(cs.font);
        labelRegLogin.setFont(cs.font);
        labelRegParole.setFont(cs.font);
        labelPodtver.setFont(cs.font);
        labelPhone.setFont(cs.font);

        //       dialogRegistr.setSize(500,200);
        dialogRegistr.setLocationRelativeTo(frame);
        dialogRegistr.setLocation(cs.orx,cs.ory);
        dialogRegistr.setLayout(new GridBagLayout());

        JDialog radio = new JDialog(frame,"Radio",true);
        radio.setLocationRelativeTo(frame);
        radio.setLocation(cs.orx + cs.fxp,cs.ory + cs.fym/2);
        radio.setLayout(new GridBagLayout());
        radio.setSize(450,200);
        ButtonGroup group = new ButtonGroup();
        //    radio.add(group);
        JRadioButton vibor1 = new JRadioButton("vibor1",false);
        JRadioButton vibor2 = new JRadioButton("vibor2",false);
        JRadioButton vibor3 = new JRadioButton("vibor3",false);
        JRadioButton vibor4 = new JRadioButton("vibor4",false);
        JButton radioOK = new JButton("OK");
        JButton radioCancel = new JButton("Отмена");


        group.add(vibor1);
        group.add(vibor2);
        group.add(vibor3);
        group.add(vibor4);
        vibor1.setFont(cs.font);
        vibor2.setFont(cs.font);
        vibor3.setFont(cs.font);
        vibor4.setFont(cs.font);
        radioOK.setFont(cs.font);
        radioCancel.setFont(cs.font);

        vibor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cs.pravilno==1){
                    message1.setText("Правильно!");
                    message2.setText("Оценка 3 !");
                }
                else {
                    message1.setText("Неправильно!");
                    message2.setText("Оценка 0 !");
                }
            }
        });
        vibor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cs.pravilno==2){
                    message1.setText("Правильно!");
                    message2.setText("Оценка 3 !");
                }
                else {
                    message1.setText("Неправильно!");
                    message2.setText("Оценка 0 !");
                }
            }
        });

        vibor3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cs.pravilno==3){
                    message1.setText("Правильно!");
                    message2.setText("Оценка 3 !");
                }
                else {
                    message1.setText("Неправильно!");
                    message2.setText("Оценка 0 !");
                }
            }
        });

        vibor4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cs.pravilno==4){
                    message1.setText("Правильно!");
                    message2.setText("Оценка 3 !");
                }
                else {
                    message1.setText("Неправильно!");
                    message2.setText("Оценка 0 !");
                }
            }
        });

        radioOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radio.dispose();
                messageBox.setVisible(true);

            }
        });

        radioCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                radio.dispose();
                cs.SubTopic++;
            }
        });


        radio.add(vibor1, new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 20, 2, 20), 0, 0));
        radio.add(vibor2, new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 20, 2, 20), 0, 0));
        radio.add(vibor3, new GridBagConstraints(0, 8, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 20, 2, 20), 0, 0));
        radio.add(vibor4, new GridBagConstraints(0, 9, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 20, 2, 20), 0, 0));
        radio.add(radioOK, new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 20, 2, 20), 0, 0));
        radio.add(radioCancel, new GridBagConstraints(1, 7, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 20, 2, 20), 0, 0));


        vhod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = "";
                String id = "";
                String otvet = "";
                String zolotov = "";
                String otvet2 = "";
                cs.login = cs.shifr(logIn.getText(),3);
                char[]  input = parole.getPassword();
                String shifPassword = cs.shifrChar(input,2);
                parole.setText("");
                String fileName = String.format("%s.lin",cs.login);
                String fileNameJop = String.format("%s.jop",cs.login);
//                Send sD = new Send();
                int c, i=0,j=0;
                cs.totalName = "";
                Boolean correctPassword = true;
                File file = new File(fileName);
                File fileJop = new File(fileNameJop);
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

                str=String.format("login=%s&password=%s",cs.login,shifPassword);
                otvet = sD.doPost(cs.adresURL + "avtorization", str);
                System.out.println(otvet);
                if(otvet.contains(";")){
                    str=String.format("login=%s",cs.login);
                    zolotov = sD.doPost(cs.adresURL + "Accept", str);
                    if(zolotov.length()>0){
                        cs.stroka=1.5;
                        cs.SubTopic=0;
                        i=0;;
                        strFont[i++] = new StriFont("Вас вызывает на баттл", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, i++);
                        i=0;;
                        strFont[i++] = new StriFont(zolotov, "", cs.font);
                        sf.textLine(strFont, cs.stroka++, i++);
                        String dada = da.getText();
                        String netNet = net.getText();
                        String nazadNazad = daNetnazad.getText();
                        da.setText("Принять");
                        daNetnazad.setText("Струсить");
                        net.setText("Я подумаю");
                        daNet.setVisible(true);
                        da.setText(dada);
                        daNetnazad.setText(nazadNazad);
                        net.setText(netNet);
                        i=0;
                        while(zolotov.charAt(i++)!= '='){

                        }

                        while(i<zolotov.length()){
                            id += zolotov.charAt(i++);
                        }
                        if(cs.SubTopic==1) {
                            str = String.format("login=%s&accept=true&date=%s&id=%s", cs.login, dateFormat.format(now),id);
                            otvet2 = sD.doPost(cs.adresURL + "Accept2", str);
                        }
                        else{
                            if(cs.SubTopic==-1){
                                str = String.format("login=%s&accept=false&date=%s&id=%s", cs.login, dateFormat.format(now),id);
                                otvet2 = sD.doPost(cs.adresURL + "Accept2", str);
                            }
                        }
                        if(otvet2.contains("OK")){
                            message1.setText("Ваш баттл с "+zolotov);
                            message2.setText("успешно зарегистрирован !");
                            messageBox.setVisible(true);
                        }
                    }
                }

                if(!file.exists()) {
      //              message1.setText("На Вашем компьютере нет такого логина !");
      //              message2.setText("Но может быть есть на сервере ... ");
      //              messageBox.setVisible(true);

                    if(otvet.contains(";")) {
                        try {
                            file.createNewFile();
                            fileJop.createNewFile();
                     //       PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF8");
                            out.write(";"+otvet);
                            out.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }

                            str = "";
                            for(i=0;i<7;++i) {
                                if(i<3) {
                                    while (otvet.charAt(j) != ';') {
                                        cs.totalName += otvet.charAt(j);
                                        j++;
                                    }
                                    j++;
                                    cs.totalName += " ";
                                }
                                if(i>2 && i<6) {
                                    while (otvet.charAt(j) != ';') {
                                        j++;
                                    }
                                    j++;
                                }
                                if(i==6){
                                    while (otvet.charAt(j) != ';') {
                                        str+=otvet.charAt(j);

                                        j++;
                                    }
                                    j++;
                                }

                            }
                            cs.status=(int)Double.parseDouble(str);



                        message1.setText("Вы успешно авторизованы !");
                        message2.setText("");
                        messageBox.setVisible(true);
                    }
                    else{
                        if(otvet.contains("noconn")){
                            message1.setText("Нет связи! Если ранее Вы регистрировались");
                            message2.setText("то сейчас поработайте без авторизации");
                            messageBox.setVisible(true);
                        }
                        if(otvet.contains("wrong")) {
                            message1.setText("Либо логин, либо пароль неверны !");
                            message2.setText("");
                            messageBox.setVisible(true);
                            correctPassword = false;
                        }
                        if(otvet.contains("NO")){
                            message1.setText("Вы не зарегистрированы");
                            messageBox.setVisible(true);
                        }
                    }
                }
                else{
                    String familia1="",imya1="",otchestvo1="",email1="",phone1="",parol1="",statusStr="";
                    try(InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"UTF8")){
                     //   c = reader.read();
                     //   if((c > 64 && c < 91) || (c > 96 && c < 123) || (c>1039 && c < 1104)){
                     //       familia1 += (char)c;
                     //   }
                        while ((c = reader.read()) != ';') {
                        }
                        while ((c = reader.read()) != ';') {
                            familia1 += (char)c;
                        }

                        while ((c = reader.read()) != ';') {
                            imya1 += (char)c;
                        }
                        while ((c = reader.read()) != ';') {
                            otchestvo1 += (char)c;
                        }
                        while ((c = reader.read()) != ';') {
                            email1 += (char)c;
                        }
                        while ((c = reader.read()) != ';') {
                            phone1 += (char)c;
                        }
                        while ((c = reader.read()) != ';') {
                            parol1 += (char)c;
                        }
                        while ((c = reader.read()) != ';') {
                            statusStr += (char)c;
                        }
                        cs.status = (int)Double.parseDouble(statusStr);
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }

                    cs.totalName = imya1 + " " + otchestvo1 + " " + familia1;

                    if(!shifPassword.equals(parol1)){
                        correctPassword = false;
                        message1.setText("Неверный пароль (локально)!");
                        message2.setText("");
                        messageBox.setVisible(true);
                        cs.login="";
                        cs.totalName="";
                    }
                }
                signIn.dispose();
                logIn.setText("");
                parole.setText("");
                comp.clearAll();
                cs.titulList();
            }
        });


        messageBox.add(message1,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));
        messageBox.add(message2,new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));
        messageBox.add(messageOK,new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));


        dialogRegistr.add(labelFamilia,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(familia,new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelImya,new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(imya,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelOtchestvo,new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(otchestvo,new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelEmail,new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(pochta,new GridBagConstraints(1, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelPhone,new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(regPhone,new GridBagConstraints(1, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelRegLogin,new GridBagConstraints(0, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(regLogIn,new GridBagConstraints(1, 5, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelRegParole,new GridBagConstraints(0, 6, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(regParole,new GridBagConstraints(1, 6, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(labelPodtver,new GridBagConstraints(0, 7, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(regCofirm,new GridBagConstraints(1, 7, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.add(zaregistrirovat,new GridBagConstraints(0, 8, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        dialogRegistr.pack();

        registracia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               signIn.dispose();
               dialogRegistr.setVisible(true);
            }
        });
        zaregistrirovat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.login = cs.shifr(regLogIn.getText(),3);
                String fileName = String.format("%s.lin",cs.login);
                String fileNameJop = String.format("%s.jop",cs.login);
                char[] input = regParole.getPassword();
                char[] input1 = regCofirm.getPassword();
                String firstName = imya.getText();
                String middleName = otchestvo.getText();
                String lastName = familia.getText();
                String eMail = pochta.getText();
                String pHone = regPhone.getText();
                if(firstName.length() == 0 || middleName.length() == 0 || lastName.length() == 0 || eMail.length() == 0 || pHone.length() == 0){
                    message1.setText("Имя, фамилия, отчество, почта и");
                    message2.setText("телефон не могут быть пустыми");
                    messageBox.setVisible(true);
                    cs.login = "";
                }
                else {
                    if (Arrays.equals(input, input1) && input.length >= 8) {
                        File fileJop = new File(fileNameJop);
                        File file = new File(fileName);
                        try {
                            if (file.exists()) {
                                message1.setText("Этот логин уже используется !");
                                message2.setText("");
                                messageBox.setVisible(true);
                                cs.login = "";
                            } else {
                                file.createNewFile();
                                fileJop.createNewFile();

                                cs.totalName = imya.getText() + " " + otchestvo.getText() + " " + familia.getText();
                                //          PrintWriter out = new PrintWriter(file.getAbsoluteFile());
                                OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF8");
                                String shifPassword = cs.shifrChar(input, 2);
                                try {

                                    String str = String.format(";%s;%s;%s;%s;%s;%s;0;", familia.getText(), imya.getText(), otchestvo.getText(), pochta.getText(), regPhone.getText(), shifPassword);
                                    out.write(str);

                                } finally {
                                    out.close();
                                }
                                Send sD = new Send();
                                //       String kod = System.getProperty("file.encoding");
                                //       comp.addPString(kod, cs.rightTextLocation(1.), cs.font);
                                String str = String.format("login=%s&password=%s&firstname=%s&middlename=%s&lastname=%s&email=%s&phone=%s", cs.login, shifPassword, imya.getText(), otchestvo.getText(), familia.getText(), pochta.getText(), regPhone.getText());
                                String otvet = sD.doPost(cs.adresURL + "myServlet", str);
                                System.out.println(otvet);
                                if (otvet.contains(familia.getText())) {
                                    message1.setText("Вы успешно зарегистрированы !");
                                    message2.setText("");
                                    messageBox.setVisible(true);
                                } else {
                                    if (otvet.contains("use")) {
                                        message1.setText("Этот логин уже используется,");
                                        message2.setText("подберите другой.");
                                        messageBox.setVisible(true);
                                    } else {
                                        if (otvet.contains("noconn")) {
                                            message1.setText("Вы зарегистрированы локально и можете");
                                            message2.setText("до появления связи проходить темы.");
                                            messageBox.setVisible(true);
                                        }
                                    }
                                }
                                regCofirm.setText("");
                                regParole.setText("");
                                regLogIn.setText("");
                                familia.setText("");
                                imya.setText("");
                                otchestvo.setText("");
                                pochta.setText("");
                                regPhone.setText("");
                                //   cs.usersFileName = fileName;
                                dialogRegistr.dispose();
                                comp.clearAll();
                                cs.titulList();
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        message1.setText("Не совпадают пароли или слишком короткие,");
                        message2.setText("длина пароля должна быть не менее 8 символов !");
                        messageBox.setVisible(true);
                        //      JOptionPane.showMessageDialog(frame, "Не совпадают пароли или слишком короткие ( < 8 ) !");
                    }
                }
            }
        });

        JButton gost = new JButton("Гость");
        gost.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn.dispose();;
            }
        });

        vhod.setFont(cs.font);
        registracia.setFont(cs.font);
        gost.setFont(cs.font);
        labelParole.setFont(cs.font);
        labelLogin.setFont(cs.font);
        parole.setFont(cs.font);
        logIn.setFont(cs.font);
        vvediteLogiParole.setFont(cs.font);
        signIn.add(vvediteLogiParole,new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(logIn,new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(parole,new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(labelLogin,new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(labelParole,new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(vhod,new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(registracia,new GridBagConstraints(0, 4, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        signIn.add(gost,new GridBagConstraints(0, 5, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));

        signIn.pack();






        //        frame.getContentPane().setBackground(Color.BLUE);
//        frame.add(comp, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
//        JButton vPered = new JButton("ВПЕРЁД");
        JButton nAzad = new JButton("НАЗАД");
 //       buttonsPanel.add(nAzad);
 //       buttonsPanel.add(vPered);
//        frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
//        buttonsPanel.add(regScrollPane);

// Dialogs for COORDINATES and YES NOT
        JDialog vvodStolbca = new JDialog(frame,"Ввод данных.", true);


        vvodStolbca.setLayout(new GridBagLayout());
        daNet.setLayout(new GridBagLayout());

        da.setFont(cs.font);
        net.setFont(cs.font);
        daNetnazad.setFont(cs.font);
        poNyatno.setFont(cs.font);
        daNet.setLocationRelativeTo(frame);
        daNet.setLocation(cs.orx + cs.fxp + (int)(40.*cs.width/1920), cs.ory + (int)(250.*cs.height/1080));
        daNet.setSize((int)(500.*cs.width/1920), (int)(200.*cs.height/1080));
        daNet.add(poNyatno, new GridBagConstraints(0, 0, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        daNet.add(da, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        daNet.add(net, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        daNet.add(daNetnazad, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
 //       JLabel zadanie = new JLabel("Введите координаты центра окружности:");
 //       zadanie.setFont(cs.font);
        vvodStolbca.setSize((int)(500.*cs.width/1920), (int)(270.*cs.height/1080));
        JTextField xx = new JTextField(3);
        JTextField yy = new JTextField(3);
        JLabel xxx = new JLabel("x - координата");
        JLabel yyy = new JLabel("y - координата");
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton(cs.canCel);
        xx.setFont(cs.font);
        yy.setFont(cs.font);
        xxx.setFont(cs.fontItalic);
        yyy.setFont(cs.fontItalic);
        okButton.setFont(cs.font);
        cancelButton.setFont(cs.font);
        nAzad.setFont(cs.font);
 //       vvodStolbca.add(zadanie, new GridBagConstraints(0, 0, 3, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
 //               new Insets(3, 10, 3, 10), 0, 0));
        vvodStolbca.add(xx, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 2), 0, 0));
        vvodStolbca.add(xxx, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 2, 10, 2), 0, 0));
        vvodStolbca.add(yy, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 2), 0, 0));
        vvodStolbca.add(yyy, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 2, 10, 2), 0, 0));
        vvodStolbca.add(okButton, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 2, 10, 10), 0, 0));
        vvodStolbca.add(cancelButton, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 2, 10, 10), 0, 0));
        vvodStolbca.add(nAzad, new GridBagConstraints(2, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 2, 10, 10), 0, 0));
        vvodStolbca.setLocationRelativeTo(frame);
        vvodStolbca.setLocation(cs.orx + cs.fxp + (int)(80.*cs.width/1920), cs.ory + (int)(250.*cs.height/1080));

// END for dialogs for COORDINATES and YES NOT

// Dialog for SETS
        JDialog defSets = new JDialog(frame,"Задайте множество", true);
        JDialog compare = new JDialog(frame,"Сравните два множества",true);
        defSets.setSize((int)(600.*cs.width/1920), (int)(300.*cs.height/1080));
        compare.setSize((int)(600.*cs.width/1920),(int)(200.*cs.height/1080));
        compare.setLayout(new GridBagLayout());
        defSets.setLayout(new GridBagLayout());
        JButton setsOK = new JButton("OK");
        JButton setsCancel = new JButton(cs.canCel);
        JButton setsBack = new JButton("НАЗАД");
        JButton compareOK = new JButton("OK");
        JButton compareCancel = new JButton(cs.canCel);
        JButton compareBack = new JButton("НАЗАД");
        JLabel setsLabel1 = new JLabel("Введите коэффициенты и знак \"=\",");
        JLabel setsLabel2 = new JLabel("\"<\", \">\", \"<=\" или \">=\"");
        JLabel compareLabel = new JLabel("Сравните два множества !");

        JLabel setsLabelX = new JLabel("X +");
        JLabel setsLabelY = new JLabel("Y");
        JTextField setsA = new JTextField(1);
        JTextField setsB = new JTextField(1);
        JTextField setsC = new JTextField(1);
        JTextField setsRelation = new JTextField(1);

        setsOK.setFont(cs.font);
        setsCancel.setFont(cs.font);
        setsBack.setFont(cs.font);
        compareOK.setFont(cs.font);
        compareCancel.setFont(cs.font);
        compareBack.setFont(cs.font);
        setsLabel1.setFont(cs.font);
        compareLabel.setFont(cs.font);
        setsLabel2.setFont(cs.font);
        setsLabelX.setFont(cs.fontItalic);
        setsLabelY.setFont(cs.fontItalic);
        setsA.setFont(cs.font);
        setsB.setFont(cs.font);
 //       setsB.setSize(5,10);
        setsC.setFont(cs.font);
        setsRelation.setFont(cs.fontItalic);

        defSets.add(setsLabel1, new GridBagConstraints(0, 0, 6, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsLabel2, new GridBagConstraints(0, 1, 6, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));

        defSets.add(setsOK, new GridBagConstraints(0, 3, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsCancel, new GridBagConstraints(4, 3, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsBack, new GridBagConstraints(2, 3, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsA, new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 50, 0));
        defSets.add(setsLabelX, new GridBagConstraints(1, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsB, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsLabelY, new GridBagConstraints(3, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsRelation, new GridBagConstraints(4, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        defSets.add(setsC, new GridBagConstraints(5, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        compare.add(compareLabel, new GridBagConstraints(0, 0, 6, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        compare.add(compareOK, new GridBagConstraints(0, 1, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        compare.add(compareCancel, new GridBagConstraints(4, 1, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        compare.add(compareBack, new GridBagConstraints(2, 1, 2, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));


        defSets.setLocationRelativeTo(frame);
        defSets.setLocation(cs.orx + cs.fxp + (int)(40.*cs.width/1920), cs.ory + (int)(200.*cs.height/1080));
        compare.setLocationRelativeTo(frame);
        compare.setLocation(cs.orx + cs.fxp + (int)(40.*cs.width/1920), cs.ory + (int)(200.*cs.height/1080));


// End of dialog for SETS

// MENU
//        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

//        JMenu menuSignIn = new JMenu("Логин");
//        JMenu menuTopics = new JMenu("Темы");
//        JMenu menuOptions = new JMenu("Дополнительно");
//        JMenu menuHelp = new JMenu("Помощь");
//        JMenu menuSettings = new JMenu("Настройки");

        menuSignIn.setFont(cs.font);
        menuTopics.setFont(cs.font);
        menuOptions.setFont(cs.font);
        menuHelp.setFont(cs.font);
        menuSettings.setFont(cs.font);

        menuBar.add(menuSignIn);
        menuBar.add(menuTopics);
        menuBar.add(menuOptions);
        menuBar.add(menuHelp);
        menuBar.add(menuSettings);


        JMenuItem menuLogIn = new JMenuItem("Авторизация");
        menuSignIn.add(menuLogIn);
        menuLogIn.setFont(cs.fontSmall);
        menuLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn.setVisible(true);
            }
        });

        JMenuItem menuLogOut = new JMenuItem("Обмен данными и выход");
        menuSignIn.add(menuLogOut);
        menuLogOut.setFont(cs.fontSmall);
        menuLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i, nomer=0, nn =0;
                int c;
                String string="";
                String date="";
                String topic="";
                String ocenka="";
                String maxcount="";
                String battleRegime = "";
                String result =  "";
                Send sD = new Send();
                LinkedList<RecordOcenka> records = new LinkedList<>();
                String fileName = String.format("%s.lin",cs.login);
                File file = new File(cs.login + ".jop");
                File fileLin = new File(fileName);
                if(!file.exists()) {
                    message1.setText("Вы не авторизованы !");
                    message2.setText("");
                    messageBox.setVisible(true);
                }
                else {

                    String familia1 = "", imya1 = "", otchestvo1 = "", email1 = "", phone1 = "", parol1 = "",status="";
                    try (InputStreamReader reader = new InputStreamReader(new FileInputStream(fileLin),"UTF8")) {
                        while ((c = reader.read()) != ';') {

                        }
                        while ((c = reader.read()) != ';') {
                            familia1 += (char) c;
                        }

                        while ((c = reader.read()) != ';') {
                            imya1 += (char) c;
                        }
                        while ((c = reader.read()) != ';') {
                            otchestvo1 += (char) c;
                        }
                        while ((c = reader.read()) != ';') {
                            email1 += (char) c;
                        }
                        while ((c = reader.read()) != ';') {
                            phone1 += (char) c;
                        }
                        while ((c = reader.read()) != ';') {
                            parol1 += (char) c;
                        }
                        while ((c = reader.read()) != ';') {
                            status += (char) c;
                        }
                        //                 cs.totalName = imya1 + " " + otchestvo1 + "  " + familia1;
                        reader.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }


                    String str = String.format("login=%s&password=%s&firstname=%s&middlename=%s&lastname=%s&email=%s&phone=%s", cs.login, parol1, imya1, otchestvo1, familia1, email1, phone1);
                    String otvet = sD.doPost(cs.adresURL + "myServlet", str);
                    System.out.println(otvet);
             //       if (!otvet.contains("already")) {
             //           if (otvet.contains(familia1)) {
                         //   cs.login = "";
                         //   message1.setText("Вы успешно зарегистрированы !");
                         //   message2.setText("");
                         //   messageBox.setVisible(true);
             //           } else {
                            if (otvet.contains("use")) {

                                message1.setText("Этот логин уже используется,");
                                message2.setText("нажмите ОК и подберите другой.");
                                messageBox.setVisible(true);
                                familia.setText(familia1);
                                imya.setText(imya1);
                                otchestvo.setText(otchestvo1);
                                pochta.setText(email1);
                                regPhone.setText(phone1);
                                regLogIn.setText("");
                                regParole.setText("");
                                regCofirm.setText("");
                                dialogRegistr.setVisible(true);
                            }
                 //       }
             //       }

                    if (otvet.contains("noconn")) {
                        message1.setText("Связи нет ! Продолжайте работу ");
                        message2.setText("локально.");
                        messageBox.setVisible(true);
                    } else {
                        cs.stroka = 0.5;
                     //   FileWriter writer = null;
                        try {
                     //       FileReader reader = new FileReader(file);
                            InputStreamReader reader = new InputStreamReader(new FileInputStream(file),"UTF8");

                    //         writer = new FileWriter(cs.login + ".lin",true);
                            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileLin,true),"UTF8");

                            while ((c = reader.read()) != -1) {
                                if ((char) c != ';')
                                    string += (char) c;
                                else {
                                    writer.write(string+';');
                                    if (nomer % 5 == 0) {
                                        date = string;
                                        string = "";
                                        if (nomer > 0) {
                                            records.add(new RecordOcenka(cs.login, date, topic, ocenka, maxcount, battleRegime));
                                            nn++;
                                        }
                                    }
                                    if (nomer % 5 == 1) {
                                        topic = string;
                                        string = "";
                                    }
                                    if (nomer % 5 == 2) {
                                        ocenka = string;
                                        string = "";
                                    }
                                    if (nomer % 5 == 3) {
                                        maxcount = string;
                                        string = "";
                                    }
                                    if (nomer % 5 == 4) {
                                        battleRegime = string;
                                        string = "";
                                    }
                                    nomer++;
                                }
                            }
                            //         date = string;
                            //         string="";
                            if (nomer > 0 && nomer % 5 == 0) {
                                records.add(new RecordOcenka(cs.login, date, topic, ocenka, maxcount, battleRegime));
                                nn++;
                            }
                            reader.close();
                            writer.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            OutputStreamWriter wr = new OutputStreamWriter(new FileOutputStream(file,false),"UTF8");
              //              PrintWriter wr = new PrintWriter(file);
                            wr.write("");
                            wr.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                 //       comp.clearAll();
                        str = "";
                        for (RecordOcenka record : records) {
                            str = String.format("login=%s&date=%s&topic=%s&ocenka=%s&maxcount=%s&battleregime=%s", cs.login, record.getDate(), record.getTopic(), record.getOcenka(), record.getMaxCount(), record.getbattleRegime());
                            result = sD.doPost(cs.adresURL + "makeRecord", str);
                            System.out.println("Good or bad ? " + result);
                        }
                        str = String.format("login=%s&date=%s&topic=%s&ocenka=%s&maxcount=%s&battleregime=%s", cs.login, "00-000-0000", "durak", "0","0","0");
                        result = sD.doPost(cs.adresURL + "makeRecord", str);
                        System.out.println(result);
                    }
              //      if (result.contains("durak")) {
              //          PrintWriter writer = null;
              //          try {
              //              writer = new PrintWriter(file);
              //          } catch (FileNotFoundException e1) {
              //              e1.printStackTrace();
              //          }
              //          writer.print("");
              //          writer.close();
               //     }
                    comp.clearAll();
                    cs.login = "";
                    cs.totalName = "";
                    cs.login1 = "";
                    cs.totalName1 = "";
                    cs.battleRegime=0;
                    cs.titulList();
                }

            }
        });


        comp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                StriFont[] strFont = new StriFont[10];
                int i=0;
                String string="";
                if(cs.clickPoints) {
                    Point point = new Point((double)e.getX() / cs.mm - (double)cs.orx / cs.mm, -(double)e.getY()  / cs.mm + (double)cs.ory / cs.mm);
                    if (e.getX() > cs.orx - cs.fxm && e.getX() < cs.orx + cs.fxp && e.getY() > cs.ory - cs.fyp && e.getY() < cs.ory + cs.fym) {
                        cs.count++;
                        comp.clearAll();
                        comp.addCoordinateSysytem();
                        comp.coordsys = true;
                        comp.logoTip = false;
                        cs.stroka=0.;
                        comp.addPString(cs.menuTopics[1], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                        cs.stroka++;
                        comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                        cs.ocen = cs.ocenka(cs.pp, point);
                        cs.ocenTopic += cs.ocen;
                        cs.p = cs.rand2(false);
                        cs.pp = new Point(cs.p.getX() / cs.mm - (double)cs.orx / cs.mm, -cs.p.getY() / cs.mm + (double)cs.ory / cs.mm);
                        string = String.format("Кликнине точку с координатами х = %.1f, y = %.1f", cs.pp.getX(), cs.pp.getY());
                        comp.addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                        comp.addCircle(e.getX(), e.getY(), 4, 4, Color.BLACK,8.f);

                        if(cs.count>cs.maxCount) {
                            cs.clickPoints = false;
                            String str = String.format("В теме " +cs.menuTopics[1]);
                            message1.setText(str);
                            str = String.format("Вы получили %d  баллов !",cs.ocenTopic);
                            message2.setText(str);
                            messageBox.setVisible(true);
                            cs.makeRecord(cs.menuTopics[1],cs.count);
                            comp.clearAll();
                            cs.titulList();
                        }
                    }
                }

                if(cs.clickLinearOper) {
                    Point point = new Point((double)e.getX() / cs.mm - (double)cs.orx / cs.mm, -(double)e.getY()  / cs.mm + (double)cs.ory / cs.mm);
                    if (e.getX() > cs.orx - cs.fxm && e.getX() < cs.orx + cs.fxp && e.getY() > cs.ory - cs.fyp && e.getY() < cs.ory + cs.fym) {
                        cs.count++;
                        comp.clearAll();
                        comp.addCoordinateSysytem();
                        comp.coordsys = true;
                        comp.logoTip = false;
                        cs.stroka=0.;
                        comp.addPString(cs.menuTopics[8], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                        cs.stroka++;
                        comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                        cs.mOtvet = cs.mulMat(cs.white, cs.mDano);
                        cs.ocen = cs.ocenka(cs.mOtvet.getA(), point);

                        Flag white = new Flag(cs.white,Color.WHITE);
                        comp.drawFlag(white);
                        comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.mDano.apix().getX(), (int) cs.mDano.apix().getY(), Color.RED, 8.f,"a");
                        comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.mOtvet.apix().getX(), (int) cs.mOtvet.apix().getY(), Color.RED, 8.f,"A(a)");
                        comp.addArrow(cs.orx, cs.ory, e.getX(), e.getY(), Color.BLUE, 8.f);

                        if(cs.ocen>1){
                            cs.ocenTopic += cs.ocen;
                            cs.stroka = 0;
                            if(cs.ocen==2)
                                string = String.format("Неплохо, оценка %d !",cs.ocen);
                            if(cs.ocen==3)
                                string = String.format("Отлично, оценка %d !",cs.ocen);
                            message1.setText(string);
                            message2.setText("");
                            messageBox.setVisible(true);
                            if(cs.count<cs.maxCount) {
                                comp.clearAll();
                                comp.addCoordinateSysytem();
                                comp.coordsys = true;
                                comp.logoTip = false;
                                cs.stroka=0.;
                                comp.addPString(cs.menuTopics[8], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                                cs.stroka++;
                                comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                                cs.white = cs.rand4(0.7);
                                cs.mDano = cs.rand4(0.7);

                                white = new Flag(cs.white, Color.WHITE);
                                comp.drawFlag(white);
                                comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.mDano.apix().getX(), (int) cs.mDano.apix().getY(), Color.RED, 8.f, "a");

                                i=0;
                                strFont[i++] = new StriFont("На рисунке Вы видите флаг линейного оператора", "", cs.font);
                                sf.textLine(strFont, cs.stroka++, i++);
                                i=0;
                                strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                                strFont[i++] = new StriFont("и вектор  ", "", cs.font);
                                strFont[i++] = new StriFont("a. ", "", cs.fontItalic);
                                strFont[i++] = new StriFont("Кликните мышью точку, в которую", "", cs.font);
                                sf.textLine(strFont, cs.stroka++, i++);
                                i=0;
                                strFont[i++] = new StriFont("переходит вектор  ", "", cs.font);
                                strFont[i++] = new StriFont("a ", "", cs.fontItalic);
                                strFont[i++] = new StriFont("при этом операторе.", "", cs.font);
                                sf.textLine(strFont, cs.stroka++, i++);
                            }
                        }
                        else{
                            cs.stroka = 0;
                            string = String.format("Оценка %d ! Попробуйте еще раз.",cs.ocen);
                            message1.setText(string);
                            message2.setText("");
                            messageBox.setVisible(true);
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            cs.stroka=0.;
                            comp.addPString(cs.menuTopics[8], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                            cs.stroka++;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            white = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(white);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.mDano.apix().getX(), (int) cs.mDano.apix().getY(), Color.RED, 8.f,"a");
                            i=0;
                            strFont[i++] = new StriFont("На рисунке Вы видите флаг линейного оператора", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и вектор  ", "", cs.font);
                            strFont[i++] = new StriFont("a. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Кликните мышью точку, в которую", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("переходит вектор  ", "", cs.font);
                            strFont[i++] = new StriFont("a ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("при этом операторе.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                        }


                        if(cs.count>=cs.maxCount) {
                            cs.clickLinearOper = false;
                            cs.SubTopic++;
                            string = String.format("В теме \"%s\"","Линейные операторы");
                            message1.setText(string);
                            string = String.format("Вы набрали %d баллов !",cs.ocenTopic);
                            message2.setText(string);
                            messageBox.setVisible(true);
                            cs.makeRecord("Линейные операторы",cs.count);
                            comp.clearAll();
                            cs.titulList();
                        }
                    }
                }

                if(cs.clickMatch){
                    String str = "";
                    cs.distance=10.;
                    Point pp = new Point(e.getX(),e.getY());
                    if (pp.getX() > cs.orx - cs.fxm && pp.getX() < cs.orx + cs.fxp && pp.getY() > cs.ory - cs.fyp && pp.getY()< cs.ory + cs.fym) {
                        Point point = new Point((double)pp.getX() / cs.mm - (double)cs.orx / cs.mm, -(double)pp.getY() / cs.mm + (double)cs.ory / cs.mm);
                        cs.count++;
                        comp.clearLines();
                        comp.addCoordinateSysytem();
                        comp.coordsys = true;
                        comp.logoTip = false;
                     //   cs.stroka=0.;
                     //   comp.addPString(cs.menuTopics[2], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                     //   cs.stroka++;
                        comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                     //   meet = cs.meet(point, cs.rr);
                        comp.addCircle(pp.getX(), pp.getY(), 4, 4,Color.BLACK, 8.f);
                        if(!cs.meet(point, cs.rr)) {
                            cs.ocen = -2;
                            comp.addPString("Ну нет, не удовлетворяет ! Штраф 2 балла !", cs.rightTextLocation(cs.stroka++), cs.font);
                        }
                        else{
                            for(Point point1 : cs.ppp){
                                if(Math.sqrt((point1.getX()-point.getX())*(point1.getX()-point.getX()) + (point1.getY()-point.getY())*(point1.getY()-point.getY()))<cs.distance)
                                    cs.distance = Math.sqrt((point1.getX()-point.getX())*(point1.getX()-point.getX()) + (point1.getY()-point.getY())*(point1.getY()-point.getY()));
                            }
                          //  cs.distance = 1./cs.distance;
                            if(cs.distance > 4){
                                cs.ocen=3;
                                str = String.format("Отлично ! Оценка %d !",cs.ocen);
                             //   pstr = new PString(str, downTextLocation,cs.font);
                            }
                            if(cs.distance<=4 && cs.distance > 2){
                                cs.ocen=2;
                                str = String.format("Неплохо, расстояние до ближайшей точки %.1f! Оценка %d !",cs.distance,cs.ocen);
                             //   pstr = new PString(str, downTextLocation,cs.font);
                            }
                            if(cs.distance<=2 && cs.distance > 1){
                                cs.ocen=1;
                                str = String.format("Расстояние до ближайшей точки %.1f ! Оценка %d !",cs.distance,cs.ocen);
                              //  pstr = new PString(str, downTextLocation,cs.font);
                            }
                            if(cs.distance<=1){
                                cs.ocen=0;
                                str = String.format("Расстояние до ближайшей точки %.1f ! Оценка %d !",cs.distance,cs.ocen);
                              //  pstr = new PString(str, downTextLocation,cs.font);
                            }
                            comp.addPString(str,cs.rightTextLocation(cs.stroka++),cs.font);
                            cs.ppp.add(point);
                        }
                        cs.ocenTopic+=cs.ocen;
                    }


                    if(cs.count>cs.maxCount) {
                        cs.countMatch++;
                        int cc = cs.count;
                        cs.count = 0;
                        if(cs.countMatch>cs.maxCount) {

                            cs.clickMatch = false;
                            cs.ppp.clear();
                            str = String.format("В теме " + cs.menuTopics[2]);
                            message1.setText(str);
                            str = String.format("Вы получили %d  баллов !", cs.ocenTopic);
                            message2.setText(str);
                            messageBox.setVisible(true);
                            cs.makeRecord(cs.menuTopics[2],cs.countMatch*cc);
                            comp.clearAll();
                            cs.titulList();
                        }
                        else{
                            cs.ppp.clear();

                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            cs.stroka=0.;
                            comp.addPString(cs.menuTopics[2], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                            cs.stroka++;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.rr = cs.rand3();
                            while (cs.rr.getR() == 0);
                            str = String.format("Кликните %d точек, удовлетворяющих неравенству", cs.maxCount);
                            comp.addPString(str, cs.rightTextLocation(cs.stroka++), cs.font);
                            if(cs.rr.getB() >= 0)
                                str = String.format("     %.1fx + %.1fy %s %.1f", cs.rr.getA(),cs.rr.getB(),cs.rr.relaT(cs.rr.getR()),cs.rr.getC());
                            else
                                str = String.format("     %.1fx - %.1fy %s %.1f", cs.rr.getA(),-cs.rr.getB(),cs.rr.relaT(cs.rr.getR()),cs.rr.getC());
                            comp.addPString(str, cs.rightTextLocation(cs.stroka++), cs.fontItalic);
                            comp.addPString("Постарайтесь точки выбирать максимально далеко", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("друг от друга, от этого зависит оценка.", cs.rightTextLocation(cs.stroka++), cs.font);
                            cs.clickMatch=true;
                        }
                    }
                }

                if(cs.clickScalarProduct){
                    String str = "";
                 //   cs.stroka=0;
                    cs.distance=10.;
                    Point pp = new Point(e.getX(),e.getY());
                    if (pp.getX() > cs.orx - cs.fxm && pp.getX() < cs.orx + cs.fxp && pp.getY() > cs.ory - cs.fyp && pp.getY()< cs.ory + cs.fym) {
                        Point point = new Point((double)pp.getX() / cs.mm - (double)cs.orx / cs.mm, -(double)pp.getY() / cs.mm + (double)cs.ory / cs.mm);
                        cs.count++;
                        comp.clearLines();
                        comp.addCoordinateSysytem();
                        comp.coordsys = true;
                        comp.logoTip = false;
                      //  cs.stroka=0.;
                      //  comp.addPString(cs.menuTopics[7], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                        comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                        comp.addMarkedArrow(cs.orx,cs.ory,(int)(cs.rr.getA()*cs.mm) + cs.orx, -(int)(cs.rr.getB()*cs.mm) + cs.ory, Color.RED, 8.f,"r");
                        comp.addArrow(cs.orx,cs.ory,(int)pp.getX(), (int)pp.getY(), Color.BLUE, 8.f);

                        if(!cs.meet(point, cs.rr)) {
                            cs.ocen = -2;
                            comp.addPString("Ну нет, не удовлетворяет ! Штраф 2 балла !", cs.rightTextLocation(cs.stroka++), cs.font);
                        }
                        else{
                            for(Point point1 : cs.ppp){
                                if(Math.sqrt((point1.getX()-point.getX())*(point1.getX()-point.getX()) + (point1.getY()-point.getY())*(point1.getY()-point.getY()))<cs.distance)
                                    cs.distance = Math.sqrt((point1.getX()-point.getX())*(point1.getX()-point.getX()) + (point1.getY()-point.getY())*(point1.getY()-point.getY()));
                            }
                            //  cs.distance = 1./cs.distance;
                            if(cs.distance > 4){
                                cs.ocen=3;
                                str = String.format("Отлично ! Оценка %d !",cs.ocen);
                                //   pstr = new PString(str, downTextLocation,cs.font);
                            }
                            if(cs.distance<=4 && cs.distance > 2){
                                cs.ocen=2;
                                str = String.format("Неплохо, расстояние до ближайшей точки %.1f! Оценка %d !",cs.distance,cs.ocen);
                                //   pstr = new PString(str, downTextLocation,cs.font);
                            }
                            if(cs.distance<=2 && cs.distance > 1){
                                cs.ocen=1;
                                str = String.format("Расстояние до ближайшей точки %.1f ! Оценка %d !",cs.distance,cs.ocen);
                                //  pstr = new PString(str, downTextLocation,cs.font);
                            }
                            if(cs.distance<=1){
                                cs.ocen=0;
                                str = String.format("Расстояние до ближайшей точки %.1f ! Оценка %d !",cs.distance,cs.ocen);
                                //  pstr = new PString(str, downTextLocation,cs.font);
                            }
                            comp.addPString(str,cs.rightTextLocation(cs.stroka++),cs.font);
                            cs.ppp.add(point);
                        }
                        cs.ocenTopic+=cs.ocen;
                    }


                    if(cs.count>cs.maxCount) {
                        cs.countMatch++;
                        int cc = cs.count;
                        cs.count = 0;
                        if(cs.countMatch>cs.maxCount) {

                            cs.clickScalarProduct = false;
                            cs.ppp.clear();
                            str = String.format("В теме "+ cs.menuTopics[7]);
                            message1.setText(str);
                            str = String.format("Вы получили %d  баллов !", cs.ocenTopic);
                            message2.setText(str);
                            messageBox.setVisible(true);
                            cs.makeRecord(cs.menuTopics[7],cs.countMatch*cc);
                            comp.clearAll();
                            cs.titulList();
                        }
                        else{
                            cs.ppp.clear();
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            cs.stroka=0.;
                            comp.addPString(cs.menuTopics[7], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                            cs.stroka++;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.rr = cs.rand3();
                            while (cs.rr.getR() == 0);
                            cs.rr = new Relation(cs.rr.getA()*0.3,cs.rr.getB()*0.3,cs.rr.getC()*0.3,cs.rr.getR());
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)(cs.rr.getA()*cs.mm) + cs.orx, -(int)(cs.rr.getB()*cs.mm) + cs.ory, Color.RED, 8.f,"r");
                            strFont[0] = new StriFont("Слева на рисунке Вы видите вектор ", "", cs.font);
                            strFont[1] = new StriFont("r = ", "", cs.fontItalic);
                            strFont[2] = new StriFont("(", "", cs.fontBig);
                            strFont[3] = new StriFont(String.format("%.1f",cs.rr.getA()), String.format("%.1f",cs.rr.getB()), cs.fontItalic);
                            strFont[4] = new StriFont(")", "", cs.fontBig);
                            sf.textLine(strFont, cs.stroka++, 5);
                            cs.stroka++;
                            strFont[0] = new StriFont("Кликните  ", "", cs.font);
                            strFont[1] = new StriFont(String.format("%d ",cs.maxCount), "", cs.fontItalic);
                            strFont[2] = new StriFont("точек, являющихся концами векторов ", "", cs.font);
                            strFont[3] = new StriFont("a, ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("удовлетворяющих неравенству ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont(String.format(" <a, r> %s %.1f",cs.rr.relaT(cs.rr.getR()),cs.rr.getC()), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 1);

                            comp.addPString("Постарайтесь точки выбирать максимально далеко", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("друг от друга, от этого зависит оценка.", cs.rightTextLocation(cs.stroka++), cs.font);
                        }
                    }
                }
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



        JMenu menuCoordinates = new JMenu("Общее знакомство с организмом человека ");
        JMenuItem menuClickPoints = new JMenuItem("Покровы тела. Атлас");
        JMenu menuMatch = new JMenu("Регуляция ");
        JMenu menuSets = new JMenu("Нервная система ");
        JMenu menuVectors = new JMenu("Эндокринная система ");
        JMenu menuLineaCombinationsr = new JMenu("Внутренняя среда организма ");
        JMenu menuSystemsEquations = new JMenu("Иммунная система ");
        JMenu menuScalarProdact = new JMenu("Система кровообращения и лимфооттока ");
        JMenu menuLinearOperators = new JMenu("Система органов дыхания ");
        JMenu menuExamples = new JMenu("Пищеварительная система ");
        JMenu menuGessMatrix = new JMenu("Мочевыделительная система ");
        JMenu menuMultiplcation = new JMenu("Обмен веществ и энергии ");
        JMenu menuOrthogonality = new JMenu("Система опоры и движения ");
        JMenu menuTrajectories = new JMenu("Сенсорные системы ");
        JMenu menuSex = new JMenu("Половая система и размножение");

        menuTopics.add(menuCoordinates);
        menuTopics.add(menuClickPoints);
        menuTopics.add(menuMatch);
        menuTopics.add(menuSets);
        menuTopics.add(menuVectors);
        menuTopics.add(menuLineaCombinationsr);
        menuTopics.add(menuSystemsEquations);
        menuTopics.add(menuScalarProdact);
        menuTopics.add(menuLinearOperators);
        menuTopics.add(menuExamples);
        menuTopics.add(menuGessMatrix);
        menuTopics.add(menuMultiplcation);
        menuTopics.add(menuOrthogonality);
        menuTopics.add(menuTrajectories);
        menuTopics.add(menuSex);
/*
        menuClickPoints.setFont(cs.fontSmall);
        menuMatch.setFont(cs.fontSmall);
        menuSets.setFont(cs.fontSmall);
        menuVectors.setFont(cs.fontSmall);
        //   menuLinearCombinations.setFont(cs.fontSmall);
        menuSystemsEquations.setFont(cs.fontSmall);
        menuScalarProdact.setFont(cs.fontSmall);
        menuLinearOperators.setFont(cs.fontSmall);
        menuExamples.setFont(cs.fontSmall);
        menuGessMatrix.setFont(cs.fontSmall);
        menuMultiplcation.setFont(cs.fontSmall);
        menuOrthogonality.setFont(cs.fontSmall);
        menuTrajectories.setFont(cs.fontSmall);
*/

        JMenu menuVnesh = new JMenu("Внешнее строение ");
        JMenu menuVnutr = new JMenu("Внутреннее строение ");

        JMenuItem menuVnesh1 = new JMenuItem("Основные области тела человека");
        menuVnesh1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnesh\\menuVnesh1\\osn_oblasti tela.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.5),cs.fxp/1.7,cs.fyp));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnesh\\menuVnesh1\\osn_oblasti tela2.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx+cs.fxp*0.1,cs.ory-cs.fyp*0.5),cs.fxp/1.6,cs.fyp*1.3));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


// Образец текста в заданном прямоугольнике
                            String text= "Основные области тела человека";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory-cs.fyp*0.7) ), text,cs.fontBold );

                            String text1= "Тело человека, как и других млекопитающих, состоит из головы, шеи, туловища и двух пар " +
                                    "конечностей. В свою очередь, каждая из этих областей тела подразделяется на несколько частей.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.4),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            String text2= "Основные области тела человека";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory+cs.fyp*0.6)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.7) ), text2,cs.fontItalic );

                            String text3= "Основные части тела человека";
                            cs.printInRectangle(new Point((double)cs.orx+cs.fxm*0.1, (double)(cs.ory+cs.fyp*0.9)),new Point((double)(cs.orx+cs.fxp*0.9),(double)(cs.ory+cs.fyp) ), text3,cs.fontItalic );

// Диалог ДаНет
                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();
// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnesh2 = new JMenuItem("Плоскости в теле человека");
        menuVnesh2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnesh\\menuVnesh2\\plos-tela 3_4.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.4),cs.fxp/1.7,cs.fyp/1.5));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnesh\\menuVnesh2\\plos-tela 3_1.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.1,cs.ory-cs.fyp*0.4),cs.fxp/1.7,cs.fyp/1.5));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnesh\\menuVnesh2\\plos-tela 3_2.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory+cs.fyp*0.3),cs.fxp/1.7,cs.fyp/1.5));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnesh\\menuVnesh2\\plos-tela 3_3.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.1,cs.ory+cs.fyp*0.3),cs.fxp/1.7,cs.fyp/1.5));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


// Образец текста в заданном прямоугольнике
                            String text= "Плоскости в теле человека";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory-cs.fyp*0.7) ), text,cs.fontBold );

                            String text1= "Для определения положения частей тела и органов используют три взаимно перпендикулярные плоскости: срединную, фронтальную и горизонтальную. Срединная плоскость вертикально \"рассекает\" " +
                                "тело человека на правую и левую половины. Фронтальная плоскость перпендикулярна к срединной. Она разделяет тело человека на переднюю и заднюю части. Горизонтальная плоскость отделяет верхнюю часть тела от нижней.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.9),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );
// Диалог ДаНет
                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();
// Образец построчного текста











                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;
                        case 2:
                            cs.stroka = 0.5;
                            comp.clearAll();
// Образец построчного текста

                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 3);
                comp.clearAll();
                cs.titulList();
            }
        });


        JMenu menuVnutr1 = new JMenu("Молекулы ");
        JMenu menuVnutr2 = new JMenu("Клетка  ");
        JMenu menuVnutr3 = new JMenu("Ткани  ");
        JMenuItem menuVnutr4 = new JMenuItem("Полости тела человека");
        JMenu menuVnutr5 = new JMenu("Внутренние органы ");


        JMenuItem menuVnutrm1 = new JMenuItem("Вещества в организме");
        menuVnutrm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm1\\massovay doly.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.1),cs.fxp/1.05,cs.fyp/1.7));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm1\\vesovoe sootnoshenie.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx+cs.fxp*0.1,cs.ory-cs.fyp*0.1),cs.fxp/1.05,cs.fyp/1.7));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }


// Образец текста в заданном прямоугольнике
                            String text= "Вещества в организме";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Из неорганических веществ в организме больше всего воды. Благодаря растворению других веществ в воде, облегчается их перенос в организме, а также возрастает скорость реакций обмена веществ. Остальные неорганические вещества " +
                                    "организма — это, в основном, минеральные соли натрия, калия и кальция.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp),(double)(cs.ory-cs.fyp*0.3) ), text1,cs.font );

                            String text2= "Из органических веществ для организма наибольшее значение имеют белки, углеводы, липиды и нуклеиновые кислоты. Как правило, их молекулы построены из набора структурных блоков, как из деталей конструктора. Благодаря различным сочетаниям этих блоков, " +
                                    "достигается огромное разнообразие строения и функций органических молекул.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.4)),new Point((double)(cs.orx+cs.fxp),(double)(cs.ory+cs.fym*0.1) ), text2,cs.font );


                            String text3= "Массовая доля различных веществ в клетке";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory+cs.fyp*0.6)),new Point((double)(cs.orx),(double)(cs.ory+cs.fym*0.7) ), text3,cs.fontItalic );

                            String text4= "Весовое соотношение органических веществ клетки";
                            cs.printInRectangle(new Point((double)cs.orx+cs.fxp*0.1, (double)(cs.ory+cs.fyp*0.6)),new Point((double)(cs.orx+cs.fxp*0.9),(double)(cs.ory+cs.fym*0.7) ), text4,cs.fontItalic );

// Диалог ДаНет
                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });


        JMenu menuVnutrm2 = new JMenu("Органические вещества ");
        JMenuItem menuVnutrm3 = new JMenuItem("Ферменты");
        menuVnutrm3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm3\\fermentu.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.4),cs.fxp*1.4,cs.fyp/1.5));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Ферменты";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "По своему строению подавляющее большинство ферментов – белки. Ферменты – это биологические катализаторы, в тысячи раз ускоряющие протекание всех химических реакций в организме. На рисунке показана реакция распада АТФ, катализируемая ферментом.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.4),(double)(cs.ory-cs.fyp*0.1) ), text1,cs.font );

                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        menuVnutrm1.setFont(cs.fontSmall);
        menuVnutrm2.setFont(cs.fontSmall);
        menuVnutrm3.setFont(cs.fontSmall);

        JMenuItem menuVnutrmov1 = new JMenuItem("Углеводы");
        menuVnutrmov1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm2\\menuVnutrmov1\\uglevodu.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.3),cs.fxp/1.2,cs.fyp*1.2));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Углеводы";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Небольшие углеводы (моно- и дисахариды) являются важным источником энергии, а также входят в состав других веществ.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.4),(double)(cs.ory-cs.fyp*0.5) ), text1,cs.font );

                            String text2= "Полисахариды используются организмом как строительный материал и как энергетический запас.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.5)),new Point((double)(cs.orx+cs.fxp*0.4),(double)(cs.ory-cs.fyp*0.2) ), text2,cs.font );

                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmov2 = new JMenuItem("Белки");
        menuVnutrmov2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm2\\menuVnutrmov2\\belki.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.4),cs.fxp/1.3,cs.fyp*1.2));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Белки";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Построены из аминокислот. Линейные цепи белка, по-разному сворачиваясь в пространстве, образуют молекулы самых разных форм. " +
                                    "Белки выполняют множество функций, непосредственно участвуя во всех процессах жизнедеятельности.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.4),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmov3 = new JMenuItem("Липиды");
        menuVnutrmov3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm2\\menuVnutrmov3\\liipidu.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.5),cs.fxm+cs.fyp*0.3,cs.fyp/1.7));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Липиды";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Сборная группа нерастворимых в воде веществ, разнообразных по строению и функциям.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });
        JMenuItem menuVnutrmov4 = new JMenuItem("Нуклеиновые кислоты");
        menuVnutrmov4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm2\\menuVnutrmov4\\nukleinu.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.4),cs.fxm-cs.fxm*0.1,cs.fyp+cs.fym*0.1));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Нуклеиновые кислоты";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Построены из нуклеотидов (главную часть нуклеотидов составляют азотистые основания). В последовательности нуклеотидов молекул ДНК закодирована генетическая " +
                                    "(наследственная) информация о строении белков. Молекулы РНК разных видов участвуют в синтезе белка.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmov5 = new JMenuItem("АТФ");
        menuVnutrmov5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr1\\menuVnutrm2\\menuVnutrmov5\\ATF.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.4),cs.fxm-cs.fxm*0.1,cs.fyp-cs.fyp*0.2));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "АТФ";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Молекула, в основе которой лежит адениловый нуклеотид. Образуется из АДФ и содержит в себе большой запас энергии, которая выделяется при распаде АТФ. Является универсальной «энергетической валютой», " +
                                    "обеспечивая энергией все процессы жизнедеятельности организма.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        menuVnutrmov1.setFont(cs.fontSmall);
        menuVnutrmov2.setFont(cs.fontSmall);
        menuVnutrmov3.setFont(cs.fontSmall);
        menuVnutrmov4.setFont(cs.fontSmall);
        menuVnutrmov5.setFont(cs.fontSmall);


        JMenuItem menuVnutrmkl1 = new JMenuItem("Хромосомы");
        menuVnutrmkl1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr2\\menuVnutrmkl1\\hromosomu.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.4),cs.fxm-cs.fxm*0.2,cs.fyp-cs.fyp*0.2));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Хромосомы";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Хромосомы — нитевидные структуры, образованные молекулами белков и ДНК. Молекула ДНК представляет собой линейную двухцепочную макромолекулу, содержащую множество последовательно " +
                                    "соединенных единиц — нуклеотидов. Участки молекулы ДНК, отвечающие за развитие в организме того или иного признака, называют генами, или единицами наследственности. " +
                                    "Хромосома на стадии метафазы при делении клетки представлена на рисунке.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmkl2 = new JMenuItem("ДНК");
        menuVnutrmkl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr2\\menuVnutrmkl2\\DNK.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.5),cs.fxm-cs.fxm*0.3,cs.fyp-cs.fyp*0.1));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "ДНК";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Молекула ДНК представляет собой две спирально закрученные одна вокруг другой нити. Такое строение возможно благодаря водородным связям, образующимся между " +
                                    "комплиментарными азотистыми основаниями соседних спиралей. ";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmkl3 = new JMenuItem("Животная клетка");
        menuVnutrmkl3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr2\\menuVnutrmkl3\\givotnay kletka.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.2),cs.fxm-cs.fxm*0.3,cs.fyp-cs.fyp*0.1));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "Животная клетка";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Клетка — это наименьшая и основная структурная и функциональная единица живого. В организме взрослого человека различают около 200 типов клеток, которые отличаются формой, " +
                                    "строением, химическим составом и характером обмена веществ. Несмотря на большое разнообразие клеток, они состоят из трех неразрывно связанных между собой частей: плазматической мембраны, цитоплазмы, содержащей органеллы, и ядра.";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.1) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmkl4 = new JMenuItem("Жизненный цикл клетки");
        menuVnutrmkl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr2\\menuVnutrmkl2\\DNK.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.5),cs.fxm-cs.fxm*0.3,cs.fyp-cs.fyp*0.1));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "ДНК";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Молекула ДНК представляет собой две спирально закрученные одна вокруг другой нити. Такое строение возможно благодаря водородным связям, образующимся между " +
                                    "комплиментарными азотистыми основаниями соседних спиралей. ";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmkl5 = new JMenuItem("Митоз");
        menuVnutrmkl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr2\\menuVnutrmkl2\\DNK.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.5),cs.fxm-cs.fxm*0.3,cs.fyp-cs.fyp*0.1));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "ДНК";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Молекула ДНК представляет собой две спирально закрученные одна вокруг другой нити. Такое строение возможно благодаря водородным связям, образующимся между " +
                                    "комплиментарными азотистыми основаниями соседних спиралей. ";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmkl6 = new JMenuItem("Мейоз");
        menuVnutrmkl2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i;
                cs.SubTopic=0;
                String string = "";
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
// Образец картинки
                            try {
                                Image image = ImageIO.read(new File("C:\\Users\\Admin\\Desktop\\Anatomy\\menuCoordinates\\menuVnutr\\menuVnutr2\\menuVnutrmkl2\\DNK.jpg"));
                                comp.addPImage(new PImage(image,new Point(cs.orx-cs.fxm*0.9,cs.ory-cs.fyp*0.5),cs.fxm-cs.fxm*0.3,cs.fyp-cs.fyp*0.1));
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            String text= "ДНК";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.8)),new Point((double)(cs.orx),(double)(cs.ory+cs.fyp*0.9) ), text,cs.fontBold );

                            String text1= "Молекула ДНК представляет собой две спирально закрученные одна вокруг другой нити. Такое строение возможно благодаря водородным связям, образующимся между " +
                                    "комплиментарными азотистыми основаниями соседних спиралей. ";
                            cs.printInRectangle(new Point((double)cs.orx-cs.fxm*0.9, (double)(cs.ory-cs.fyp*0.7)),new Point((double)(cs.orx+cs.fxp*0.6),(double)(cs.ory-cs.fyp*0.2) ), text1,cs.font );


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0.5;
                            comp.clearAll();


// Образец построчного текста
                            string = String.format("Выберите правильный ответ из четырех вариантов.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Где у человека какашки ?");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
// Образец диалога малтиплчойс
                            vibor1.setText("В голове.");
                            vibor2.setText("В мочевом пузыре.");
                            vibor3.setText("В толстой кишке.");
                            vibor4.setText("В селезенке.");
                            cs.pravilno=3;



                            radio.setVisible(true);



                            daNet.setVisible(true);

                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuVnutrmkl7 = new JMenuItem("Строение клетки. Атлас");
        menuVnutrmkl1.setFont(cs.fontSmall);
        menuVnutrmkl2.setFont(cs.fontSmall);
        menuVnutrmkl3.setFont(cs.fontSmall);
        menuVnutrmkl4.setFont(cs.fontSmall);
        menuVnutrmkl5.setFont(cs.fontSmall);
        menuVnutrmkl6.setFont(cs.fontSmall);
        menuVnutrmkl7.setFont(cs.fontSmall);


        JMenuItem menuVnutrmtk1 = new JMenuItem("Основные типы тканей");
        JMenu menuVnutrmtk2 = new JMenu("Соединительные ткани ");
        JMenu menuVnutrmtk3 = new JMenu("Эпителиальные ткани (эпителии) ");
        JMenu menuVnutrmtk4 = new JMenu("Мышечные ткани ");
        JMenu menuVnutrmtk5 = new JMenu("Нервная ткань ");
        JMenuItem menuVnutrmtk6 = new JMenuItem("Свойства тканей (проводимость нервной ткани, сократимость мышечной ткани)");
        menuVnutrmtk1.setFont(cs.fontSmall);
        menuVnutrmtk2.setFont(cs.fontSmall);
        menuVnutrmtk3.setFont(cs.fontSmall);
        menuVnutrmtk4.setFont(cs.fontSmall);
        menuVnutrmtk5.setFont(cs.fontSmall);
        menuVnutrmtk6.setFont(cs.fontSmall);

        JMenuItem menuVnutrtkl1 = new JMenuItem("Волокнистые соединительные ткани");
        JMenuItem menuVnutrtkl2 = new JMenuItem("Скелетные ткани");
        JMenuItem menuVnutrtkl3 = new JMenuItem("Жировая ткань");
        JMenuItem menuVnutrtkl4 = new JMenuItem("Кровь и лимфа");
        menuVnutrtkl1.setFont(cs.fontSmall);
        menuVnutrtkl2.setFont(cs.fontSmall);
        menuVnutrtkl3.setFont(cs.fontSmall);
        menuVnutrtkl4.setFont(cs.fontSmall);


        JMenuItem menuVnutrtk21 = new JMenuItem("Виды эпителиальных тканей");
        JMenuItem menuVnutrtk22 = new JMenuItem("Однослойные эпителии");
        JMenuItem menuVnutrtk23 = new JMenuItem("Многослойные эпителии");
        JMenuItem menuVnutrtk24 = new JMenuItem("Железы");
        menuVnutrtk21.setFont(cs.fontSmall);
        menuVnutrtk22.setFont(cs.fontSmall);
        menuVnutrtk23.setFont(cs.fontSmall);
        menuVnutrtk24.setFont(cs.fontSmall);

        JMenuItem menuVnutrtk31 = new JMenuItem("Виды мышечной ткани");
        JMenuItem menuVnutrtk32 = new JMenuItem("Гладкая мышечная ткань");
        JMenuItem menuVnutrtk33 = new JMenuItem("Сердечная мышечная ткань");
        JMenuItem menuVnutrtk34 = new JMenuItem("Скелетная (соматическая) мышечная ткань");
        menuVnutrtk31.setFont(cs.fontSmall);
        menuVnutrtk32.setFont(cs.fontSmall);
        menuVnutrtk33.setFont(cs.fontSmall);
        menuVnutrtk34.setFont(cs.fontSmall);


        JMenuItem menuVnutrtk41 = new JMenuItem("Нервная ткань");
        JMenuItem menuVnutrtk42 = new JMenuItem("Нейроны. Клетки-спутники");
        JMenu menuVnutrtk43 = new JMenu("Электрический и химический способы передачи информации в нервной ткани");
        menuVnutrtk41.setFont(cs.fontSmall);
        menuVnutrtk42.setFont(cs.fontSmall);
        menuVnutrtk43.setFont(cs.fontSmall);

        JMenuItem menuVnutrtk141 = new JMenuItem("Возбуждение");
        JMenuItem menuVnutrtk142 = new JMenuItem("Синапсы");
        menuVnutrtk141.setFont(cs.fontSmall);
        menuVnutrtk142.setFont(cs.fontSmall);

        JMenuItem menuVnutror1 = new JMenuItem("Органы головы и шеи");
        JMenuItem menuVnutror2 = new JMenuItem("Органы шеи и туловища");
        JMenuItem menuVnutror3 = new JMenuItem("Органы женского таза");
        menuVnutror1.setFont(cs.fontSmall);
        menuVnutror2.setFont(cs.fontSmall);
        menuVnutror3.setFont(cs.fontSmall);

        JMenuItem menuRegul1 = new JMenuItem("Что такое регуляция");
        JMenuItem menuRegul2 = new JMenuItem("Биологически активные вещества");
        JMenuItem menuRegul3 = new JMenuItem("Два механизма регуляции и три регулирующие системы");
        JMenuItem menuRegul4 = new JMenuItem("Общая схема регуляции и обратные связи");
        menuRegul1.setFont(cs.fontSmall);
        menuRegul2.setFont(cs.fontSmall);
        menuRegul3.setFont(cs.fontSmall);
        menuRegul4.setFont(cs.fontSmall);

        JMenu menuNerv1 = new JMenu("Строение и функции нервной системы");
        JMenu menuNerv2 = new JMenu("Поведение");
        JMenuItem menuNerv3 = new JMenuItem("Головной мозг. Атлас");
        menuNerv1.setFont(cs.fontSmall);
        menuNerv2.setFont(cs.fontSmall);
        menuNerv3.setFont(cs.fontSmall);

        JMenuItem menuNerv21 = new JMenuItem("Безусловные рефлексы. Cгибательный рефлекс");
        JMenuItem menuNerv22 = new JMenuItem("Условные рефлексы");
        JMenuItem menuNerv23 = new JMenuItem("Торможение условных рефлексов");
        menuNerv21.setFont(cs.fontSmall);
        menuNerv22.setFont(cs.fontSmall);
        menuNerv23.setFont(cs.fontSmall);


        JMenuItem menuNervl1 = new JMenuItem("Центральная и периферическая нервные системы");
        JMenu menuNervl2 = new JMenu("Строение центральной нервной системы ");
        JMenuItem menuNervl3 = new JMenuItem("Строение периферической нервной системы");
        JMenuItem menuNervl4 = new JMenuItem("Вегетативная (автономная) нервная система");
        JMenuItem menuNervl5 = new JMenuItem("Рефлексы и рефлекторные дуги");
        menuNervl1.setFont(cs.fontSmall);
        menuNervl2.setFont(cs.fontSmall);
        menuNervl3.setFont(cs.fontSmall);
        menuNervl4.setFont(cs.fontSmall);
        menuNervl5.setFont(cs.fontSmall);


        JMenuItem menuNerv121 = new JMenuItem("Отделы центральной нервной системы");
        JMenuItem menuNerv122 = new JMenuItem("Оболочки головного и спинного мозга");
        menuNerv121.setFont(cs.fontSmall);
        menuNerv122.setFont(cs.fontSmall);


        JMenuItem menuEndokr1 = new JMenuItem( "Работа эндокринной системы");
        JMenuItem menuEndokr2 = new JMenuItem("Железы внутренней секреции. Гормоны");
        JMenuItem menuEndokr3 = new JMenuItem("Щитовидная железа");
        JMenuItem menuEndokr4 = new JMenuItem("Поджелудочная железа");
        JMenu menuEndokr5 = new JMenu("Гипоталамо-гипофизарная система");
        JMenuItem menuEndokr6 = new JMenuItem("Регуляция секреции гормонов");
        JMenuItem menuEndokr7 = new JMenuItem("Эндокринная система. Атлас");
        menuEndokr1.setFont(cs.fontSmall);
        menuEndokr2.setFont(cs.fontSmall);
        menuEndokr3.setFont(cs.fontSmall);
        menuEndokr4.setFont(cs.fontSmall);
        menuEndokr5.setFont(cs.fontSmall);
        menuEndokr6.setFont(cs.fontSmall);
        menuEndokr7.setFont(cs.fontSmall);


        JMenuItem menuEndokr51 = new JMenuItem("Гипоталамус и гипофиз");
        JMenu menuEndokr52 = new JMenu("Гормоны гипофиза");
        menuEndokr51.setFont(cs.fontSmall);
        menuEndokr52.setFont(cs.fontSmall);


        JMenuItem menuEndokr521 = new JMenuItem("Гормоны аденогипофиза");
        JMenuItem menuEndokr522 = new JMenuItem("Гормоны нейрогипофиза");
        menuEndokr521.setFont(cs.fontSmall);
        menuEndokr522.setFont(cs.fontSmall);


        JMenuItem menuVnutrsred1 = new JMenuItem("Из чего состоит и что делает внутренняя среда");
        JMenuItem menuVnutrsred2 = new JMenuItem("Внутренняя среда и барьеры");
        JMenuItem menuVnutrsred3 = new JMenuItem("Движение внутренней среды");
        JMenuItem menuVnutrsred4 = new JMenuItem("Гомеостаз");
        JMenuItem menuVnutrsred5 = new JMenuItem("Кровь");
        JMenu menuVnutrsred6 = new JMenu("Состав крови ");
        JMenuItem menuVnutrsred7 = new JMenuItem("Влияние раствора NaCl на эритроциты");
        JMenuItem menuVnutrsred8 = new JMenuItem("Транспорт газов кровью");
        JMenuItem menuVnutrsred9 = new JMenuItem("Кроветворение");
        JMenu menuVnutrsred10 = new JMenu("Тромбообразование");
        JMenu menuVnutrsred11 = new JMenu("Группы крови");
        menuVnutrsred1.setFont(cs.fontSmall);
        menuVnutrsred2.setFont(cs.fontSmall);
        menuVnutrsred3.setFont(cs.fontSmall);
        menuVnutrsred4.setFont(cs.fontSmall);
        menuVnutrsred5.setFont(cs.fontSmall);
        menuVnutrsred6.setFont(cs.fontSmall);
        menuVnutrsred7.setFont(cs.fontSmall);
        menuVnutrsred8.setFont(cs.fontSmall);
        menuVnutrsred9.setFont(cs.fontSmall);
        menuVnutrsred10.setFont(cs.fontSmall);
        menuVnutrsred11.setFont(cs.fontSmall);


        JMenuItem menuVnutrsred61 = new JMenuItem("Плазма крови");
        JMenuItem menuVnutrsred62 = new JMenuItem("Форменные элементы");
        JMenuItem menuVnutrsred63 = new JMenuItem("Эритроциты");
        JMenuItem menuVnutrsred64 = new JMenuItem("Гемоглобин");
        JMenuItem menuVnutrsred65 = new JMenuItem("Лейкоциты");
        menuVnutrsred61.setFont(cs.fontSmall);
        menuVnutrsred62.setFont(cs.fontSmall);
        menuVnutrsred63.setFont(cs.fontSmall);
        menuVnutrsred64.setFont(cs.fontSmall);
        menuVnutrsred65.setFont(cs.fontSmall);


        JMenuItem menuVnutrsred101 = new JMenuItem("Система свертывания крови");
        JMenuItem menuVnutrsred102 = new JMenuItem("Свертывание крови");
        menuVnutrsred101.setFont(cs.fontSmall);
        menuVnutrsred102.setFont(cs.fontSmall);

        JMenuItem menuVnutrsred111 = new JMenuItem("Группы крови человека");
        JMenuItem menuVnutrsred112 = new JMenuItem("Система АВО. Переливание крови");
        JMenuItem menuVnutrsred113 = new JMenuItem("Система Rh");
        menuVnutrsred111.setFont(cs.fontSmall);
        menuVnutrsred112.setFont(cs.fontSmall);
        menuVnutrsred113.setFont(cs.fontSmall);


        JMenuItem menuImun1 = new JMenuItem("Строение иммунной системы");
        JMenuItem menuImun2 = new JMenuItem("Работа иммунной системы");
        JMenuItem menuImun3 = new JMenuItem("Антитела и антигены");
        JMenu menuImun4 = new JMenu("Виды иммунитета");
        JMenuItem menuImun5 = new JMenuItem("Фагоцитоз");
        JMenuItem menuImun6 = new JMenuItem("Воспаление");
        JMenuItem menuImun7 = new JMenuItem("ВИЧ-инфекция и СПИД");
        JMenuItem menuImun8 = new JMenuItem("Способы передачи ВИЧ");
        JMenuItem menuImun9 = new JMenuItem("Иммунная система. Атлас");
        menuImun1.setFont(cs.fontSmall);
        menuImun2.setFont(cs.fontSmall);
        menuImun3.setFont(cs.fontSmall);
        menuImun4.setFont(cs.fontSmall);
        menuImun5.setFont(cs.fontSmall);
        menuImun6.setFont(cs.fontSmall);
        menuImun7.setFont(cs.fontSmall);
        menuImun8.setFont(cs.fontSmall);
        menuImun9.setFont(cs.fontSmall);

        JMenuItem menuImun41 = new JMenuItem("Типы имунных ответов");
        JMenu menuImun42 = new JMenu("Противоинфекционный иммунитет");
        menuImun41.setFont(cs.fontSmall);
        menuImun42.setFont(cs.fontSmall);


        JMenuItem menuImun421 = new JMenuItem("Активный иммунитет (естественный, искусственный)");
        JMenuItem menuImun422 = new JMenuItem("Пассивный иммунитет (естественный, искусственный)");
        menuImun421.setFont(cs.fontSmall);
        menuImun422.setFont(cs.fontSmall);


        JMenu menuKrov1 = new JMenu("Кровеносная система");
        JMenu menuKrov2 = new JMenu("Лимфатическая система");
        JMenuItem menuKrov3 = new JMenuItem("Система кровообращения и лимфооттока. Атлас");
        menuKrov1.setFont(cs.fontSmall);
        menuKrov2.setFont(cs.fontSmall);
        menuKrov3.setFont(cs.fontSmall);

        JMenuItem menuKrov21 = new JMenuItem("Лимфатическая система");
        JMenuItem menuKrov22 = new JMenuItem("Лимфатические сосуды");
        menuKrov21.setFont(cs.fontSmall);
        menuKrov22.setFont(cs.fontSmall);

        JMenuItem menuKrov11 = new JMenuItem("Сердечно-сосудистая система");
        JMenuItem menuKrov12 = new JMenuItem("Строение сердца");
        JMenu menuKrov13 = new JMenu("Работа сердца");
        JMenu menuKrov14 = new JMenu("Кровеносные сосуды");
        menuKrov11.setFont(cs.fontSmall);
        menuKrov12.setFont(cs.fontSmall);
        menuKrov13.setFont(cs.fontSmall);
        menuKrov14.setFont(cs.fontSmall);


        JMenuItem menuKrov131 = new JMenuItem("Работа сердца");
        JMenuItem menuKrov132 = new JMenuItem("Проводящая система сердца");
        JMenuItem menuKrov133 = new JMenuItem("Сердечный цикл");
        JMenuItem menuKrov134 = new JMenuItem("Тоны сердца");
        JMenuItem menuKrov135 = new JMenuItem("Электрокардиограмма");
        menuKrov131.setFont(cs.fontSmall);
        menuKrov132.setFont(cs.fontSmall);
        menuKrov133.setFont(cs.fontSmall);
        menuKrov134.setFont(cs.fontSmall);
        menuKrov135.setFont(cs.fontSmall);


        JMenuItem menuKrov141 = new JMenuItem("Параметры кровеносных сосудов");
        JMenuItem menuKrov142 = new JMenuItem("Артерии");
        JMenuItem menuKrov143 = new JMenuItem("Вены");
        JMenuItem menuKrov144 = new JMenuItem("Капилляры");
        menuKrov141.setFont(cs.fontSmall);
        menuKrov142.setFont(cs.fontSmall);
        menuKrov143.setFont(cs.fontSmall);
        menuKrov144.setFont(cs.fontSmall);


        JMenuItem menuDihanie1 = new JMenuItem("Дыхание");
        JMenuItem menuDihanie2 = new JMenuItem("Строение органов дыхания");
        JMenu menuDihanie3 = new JMenu("Функции органов дыхания");
        JMenu menuDihanie4 = new JMenu("Этапы дыхания");
        JMenu menuDihanie5 = new JMenu("Газообмен и транспорт газов кровью");
        JMenuItem menuDihanie6 = new JMenuItem("Система органов дыхания. Атлас");
        menuDihanie1.setFont(cs.fontSmall);
        menuDihanie2.setFont(cs.fontSmall);
        menuDihanie3.setFont(cs.fontSmall);
        menuDihanie4.setFont(cs.fontSmall);
        menuDihanie5.setFont(cs.fontSmall);
        menuDihanie6.setFont(cs.fontSmall);


        JMenuItem menuDihanie31 = new JMenuItem("Функции верхних дыхательных путей");
        JMenuItem menuDihanie32 = new JMenuItem("Голосообразование");
        JMenuItem menuDihanie33 = new JMenuItem("Особенности дыхания в процессе приема пищи");
        JMenuItem menuDihanie34 = new JMenuItem("Образование речи");
        menuDihanie31.setFont(cs.fontSmall);
        menuDihanie32.setFont(cs.fontSmall);
        menuDihanie33.setFont(cs.fontSmall);
        menuDihanie34.setFont(cs.fontSmall);


        JMenu menuDihanie41 = new JMenu("Внешнее дыхание");
        JMenuItem menuDihanie42 = new JMenuItem("Легочные объемы");
        JMenuItem menuDihanie43 = new JMenuItem("Мертвое пространство");
        JMenuItem menuDihanie44 = new JMenuItem("Механизм газообмена между воздухом и кровью");
        menuDihanie41.setFont(cs.fontSmall);
        menuDihanie42.setFont(cs.fontSmall);
        menuDihanie43.setFont(cs.fontSmall);
        menuDihanie44.setFont(cs.fontSmall);


        JMenuItem menuDihanie411 = new JMenuItem("Аппарат внешнего дыхания");
        JMenuItem menuDihanie412 = new JMenuItem("Движения легких");
        JMenuItem menuDihanie413 = new JMenuItem("Движения грудной клетки");
        menuDihanie411.setFont(cs.fontSmall);
        menuDihanie412.setFont(cs.fontSmall);
        menuDihanie413.setFont(cs.fontSmall);

        JMenuItem menuDihanie51 = new JMenuItem("Газообмен и транспорт газов кровью");
        JMenuItem menuDihanie52 = new JMenuItem("Роль эритроцитов и плазмы в транспорте газов");
        JMenuItem menuDihanie53 = new JMenuItem("Диффузия газов");
        JMenuItem menuDihanie54 = new JMenuItem("Газообмен в легких");
        JMenuItem menuDihanie55 = new JMenuItem("Газообмен в других органах");
        menuDihanie51.setFont(cs.fontSmall);
        menuDihanie52.setFont(cs.fontSmall);
        menuDihanie53.setFont(cs.fontSmall);
        menuDihanie54.setFont(cs.fontSmall);
        menuDihanie55.setFont(cs.fontSmall);


        JMenuItem menuPs1 = new JMenuItem("Пищеварение");
        JMenu menuPs2 = new JMenu("Функции пищеварительной системы");
        JMenu menuPs3 = new JMenu("Органы пищеварительной системы");
        JMenu menuPs4 = new JMenu("Желудок, поджелудочная железа, печень и желчевыводящие пути");
        JMenu menuPs5 = new JMenu("Тонкий кишечник");
        JMenu menuPs6 = new JMenu("Толстый кишечник");
        JMenuItem menuPs7 = new JMenuItem("Пищеварительная система. Атлас");
        menuPs1.setFont(cs.fontSmall);
        menuPs2.setFont(cs.fontSmall);
        menuPs3.setFont(cs.fontSmall);
        menuPs4.setFont(cs.fontSmall);
        menuPs5.setFont(cs.fontSmall);
        menuPs6.setFont(cs.fontSmall);
        menuPs7.setFont(cs.fontSmall);


        JMenuItem menuPs21 = new JMenuItem("Моторика");
        JMenuItem menuPs22 = new JMenuItem("Секреция");
        JMenuItem menuPs23 = new JMenuItem("Переваривание и всасывание");
        menuPs21.setFont(cs.fontSmall);
        menuPs22.setFont(cs.fontSmall);
        menuPs23.setFont(cs.fontSmall);


        JMenuItem menuPs31 = new JMenuItem("Общий план строения органов пищеварения");
        JMenuItem menuPs32 = new JMenuItem("Ротовая полость");
        JMenu menuPs33 = new JMenu("Зубы");
        JMenuItem menuPs34 = new JMenuItem("Слюнные железы");
        JMenuItem menuPs35 = new JMenuItem("Язык");
        JMenuItem menuPs36 = new JMenuItem("Глотка и пищевод");
        menuPs31.setFont(cs.fontSmall);
        menuPs32.setFont(cs.fontSmall);
        menuPs33.setFont(cs.fontSmall);
        menuPs34.setFont(cs.fontSmall);
        menuPs35.setFont(cs.fontSmall);
        menuPs36.setFont(cs.fontSmall);


        JMenuItem menuPs331 = new JMenuItem("Строение зубов");
        JMenuItem menuPs332 = new JMenuItem("Развитие зубов");
        menuPs331.setFont(cs.fontSmall);
        menuPs332.setFont(cs.fontSmall);

        JMenuItem menuPs41 = new JMenuItem("Желудок, поджелудочная железа, печень и желчевыводящие пути");
        JMenuItem menuPs42 = new JMenuItem("Желудочно-кишечные гормоны");
        JMenuItem menuPs43 = new JMenuItem("Моторика желудка");
        JMenu menuPs44 = new JMenu("Печень");
        menuPs41.setFont(cs.fontSmall);
        menuPs42.setFont(cs.fontSmall);
        menuPs43.setFont(cs.fontSmall);
        menuPs44.setFont(cs.fontSmall);

        JMenuItem menuPs441 = new JMenuItem("Функции печени");
        JMenuItem menuPs442 = new JMenuItem("Функции желчи");
        menuPs441.setFont(cs.fontSmall);
        menuPs442.setFont(cs.fontSmall);

        JMenuItem menuPs51 = new JMenuItem("Строение и функции тонкого кишечника");
        JMenuItem menuPs52 = new JMenuItem("Полостное и пристеночное пищеварение");
        menuPs51.setFont(cs.fontSmall);
        menuPs52.setFont(cs.fontSmall);

        JMenuItem menuPs61 = new JMenuItem("Строение и функции толстого кишечника");
        JMenuItem menuPs62 = new JMenuItem("Микрофлора толстого кишечника");
        JMenuItem menuPs63 = new JMenuItem("Дефекация");
        menuPs61.setFont(cs.fontSmall);
        menuPs62.setFont(cs.fontSmall);
        menuPs63.setFont(cs.fontSmall);


        JMenuItem menuMocha1 = new JMenuItem("Выделение");
        JMenu menuMocha2 = new JMenu("Органы выделения");
        JMenuItem menuMocha3 = new JMenuItem("Образование мочи");
        JMenuItem menuMocha4 = new JMenuItem("Выведение мочи");
        JMenuItem menuMocha5 = new JMenuItem("Мочевыделительная система. Атлас");
        menuMocha1.setFont(cs.fontSmall);
        menuMocha2.setFont(cs.fontSmall);
        menuMocha3.setFont(cs.fontSmall);
        menuMocha4.setFont(cs.fontSmall);
        menuMocha5.setFont(cs.fontSmall);

        JMenuItem menuMocha21 = new JMenuItem("Органы выделения");
        JMenuItem menuMocha22 = new JMenuItem("Строение почек");
        JMenuItem menuMocha23 = new JMenuItem("Строение мочеточника");
        JMenuItem menuMocha24 = new JMenuItem("Мочевой пузырь и мочеиспускательный канал");
        menuMocha21.setFont(cs.fontSmall);
        menuMocha22.setFont(cs.fontSmall);
        menuMocha23.setFont(cs.fontSmall);
        menuMocha24.setFont(cs.fontSmall);

        JMenuItem menuOv1 = new JMenuItem("Обмен веществ");
        JMenuItem menuOv2 = new JMenuItem("Внешний обмен веществ");
        JMenuItem menuOv3 = new JMenuItem("Главные пути обмена органических веществ");
        JMenuItem menuOv4 = new JMenuItem("Обмен энергии");
        JMenuItem menuOv5 = new JMenuItem("Обмен неорганических веществ");
        menuOv1.setFont(cs.fontSmall);
        menuOv2.setFont(cs.fontSmall);
        menuOv3.setFont(cs.fontSmall);
        menuOv4.setFont(cs.fontSmall);
        menuOv5.setFont(cs.fontSmall);

        JMenuItem menuOpora1 = new JMenuItem("Опорно-двигательный аппарат");
        JMenu menuOpora2 = new JMenu("Пассивная часть системы опоры и движения");
        JMenu menuOpora3 = new JMenu("Активная часть системы опоры и движения");
        JMenuItem menuOpora4 = new JMenuItem("Система опоры и движения. Атлас");
        menuOpora1.setFont(cs.fontSmall);
        menuOpora2.setFont(cs.fontSmall);
        menuOpora3.setFont(cs.fontSmall);
        menuOpora4.setFont(cs.fontSmall);


        JMenuItem menuOpora21 = new JMenuItem("Скелет");
        JMenuItem menuOpora22 = new JMenuItem("Многообразие костей");
        JMenu menuOpora23 = new JMenu("Соединения костей");
        JMenuItem menuOpora24 = new JMenuItem("Особенности скелета человека");
        JMenuItem menuOpora25 = new JMenuItem("Сравнение скелета человека и собаки");
        JMenu menuOpora26 = new JMenu("Скелет конечностей");
        JMenu menuOpora27 = new JMenu("Скелет туловища");
        JMenuItem menuOpora28 = new JMenuItem("Скелет головы (череп)");
        menuOpora21.setFont(cs.fontSmall);
        menuOpora22.setFont(cs.fontSmall);
        menuOpora23.setFont(cs.fontSmall);
        menuOpora24.setFont(cs.fontSmall);
        menuOpora25.setFont(cs.fontSmall);
        menuOpora26.setFont(cs.fontSmall);
        menuOpora27.setFont(cs.fontSmall);
        menuOpora28.setFont(cs.fontSmall);


        JMenuItem menuOpora31 = new JMenuItem("Скелетные (соматические) мышцы");
        JMenuItem menuOpora32 = new JMenuItem("Соматические мышцы с особыми функциями");
        JMenuItem menuOpora33 = new JMenuItem("Регуляция работы мышц-антагонистов");
        menuOpora31.setFont(cs.fontSmall);
        menuOpora32.setFont(cs.fontSmall);
        menuOpora33.setFont(cs.fontSmall);

        JMenuItem menuOpora231 = new JMenuItem("Прерывные соединения костей – суставы");
        JMenuItem menuOpora232 = new JMenuItem("Непрерывные соединения костей");
        menuOpora231.setFont(cs.fontSmall);
        menuOpora232.setFont(cs.fontSmall);

        JMenuItem menuOpora261 = new JMenuItem("Скелет верхней конечности");
        JMenuItem menuOpora262 = new JMenuItem("Движение верхней конечности");
        JMenuItem menuOpora263 = new JMenuItem("Скелет нижней конечности");
        JMenuItem menuOpora264 = new JMenuItem("Пояс нижних конечностей");
        JMenuItem menuOpora265 = new JMenuItem("Скелет свободных конечностей");
        menuOpora261.setFont(cs.fontSmall);
        menuOpora262.setFont(cs.fontSmall);
        menuOpora263.setFont(cs.fontSmall);
        menuOpora264.setFont(cs.fontSmall);
        menuOpora265.setFont(cs.fontSmall);


        JMenu menuOpora271 = new JMenu("Позвоночник");
        JMenu menuOpora272 = new JMenu("Грудная клетка");
        menuOpora271.setFont(cs.fontSmall);
        menuOpora272.setFont(cs.fontSmall);

        JMenuItem menuOpora2711 = new JMenuItem("Позвоночник человека");
        JMenuItem menuOpora2712 = new JMenuItem("Соединение 1-го и 2-го шейных позвонков");
        JMenuItem menuOpora2713 = new JMenuItem("Строение и соединение типичных позвонков");
        menuOpora2711.setFont(cs.fontSmall);
        menuOpora2712.setFont(cs.fontSmall);
        menuOpora2713.setFont(cs.fontSmall);

        JMenuItem menuOpora2721 = new JMenuItem("Грудная клетка человека");
        JMenuItem menuOpora2722 = new JMenuItem("Соединение костей грудной клетки");
        menuOpora2721.setFont(cs.fontSmall);
        menuOpora2722.setFont(cs.fontSmall);


        JMenuItem menuSensor1 = new JMenuItem("Основные анализаторы");
        JMenuItem menuSensor2 = new JMenuItem("Основные виды ощущений. Рецепторы");
        JMenuItem menuSensor3 = new JMenuItem("Кожная чувствительность");
        JMenuItem menuSensor4 = new JMenuItem("Мышечно-суставная чувствительность");
        JMenuItem menuSensor5 = new JMenuItem("Вкусовая чувствительность");
        JMenuItem menuSensor6 = new JMenuItem("Обоняние");
        JMenu menuSensor7 = new JMenu("Зрительная сенсорная система");
        JMenu menuSensor8 = new JMenu("Слуховая сенсорная система");
        JMenuItem menuSensor9 = new JMenuItem("Слуховая система. Атлас");
        menuSensor1.setFont(cs.fontSmall);
        menuSensor2.setFont(cs.fontSmall);
        menuSensor3.setFont(cs.fontSmall);
        menuSensor4.setFont(cs.fontSmall);
        menuSensor5.setFont(cs.fontSmall);
        menuSensor6.setFont(cs.fontSmall);
        menuSensor7.setFont(cs.fontSmall);
        menuSensor8.setFont(cs.fontSmall);
        menuSensor9.setFont(cs.fontSmall);

        JMenu menuSensor71 = new JMenu("Строение глаза");
        JMenuItem menuSensor72 = new JMenuItem("Восприятие изображения");
        JMenuItem menuSensor73 = new JMenuItem("Зрачковая реакция на свет");
        JMenuItem menuSensor74 = new JMenuItem("Бинокулярное зрение");
        JMenu menuSensor75 = new JMenu("Оптика глаза");
        JMenu menuSensor76 = new JMenu("Механизмы зрения");
        menuSensor71.setFont(cs.fontSmall);
        menuSensor72.setFont(cs.fontSmall);
        menuSensor73.setFont(cs.fontSmall);
        menuSensor74.setFont(cs.fontSmall);
        menuSensor75.setFont(cs.fontSmall);
        menuSensor76.setFont(cs.fontSmall);

        JMenuItem menuSensor711 = new JMenuItem("Глазное яблоко");
        JMenu menuSensor712 = new JMenu("Вспомогательный аппарат глаза");
        menuSensor711.setFont(cs.fontSmall);
        menuSensor712.setFont(cs.fontSmall);

        JMenuItem menuSensor7121 = new JMenuItem("Слезный аппарат");
        JMenuItem menuSensor7122 = new JMenuItem("Глазодвигательные мышцы");
        menuSensor7121.setFont(cs.fontSmall);
        menuSensor7122.setFont(cs.fontSmall);

        JMenuItem menuSensor751 = new JMenuItem("Оптика глаза");
        JMenuItem menuSensor752 = new JMenuItem("Аккомодация");
        JMenuItem menuSensor753 = new JMenuItem("Близорукость и дальнозоркость");
        menuSensor751.setFont(cs.fontSmall);
        menuSensor752.setFont(cs.fontSmall);
        menuSensor753.setFont(cs.fontSmall);

        JMenuItem menuSensor761 = new JMenuItem("Острота зрения");
        JMenuItem menuSensor762 = new JMenuItem("Свет и сетчатка глаза");
        JMenuItem menuSensor763 = new JMenuItem("Цветовое зрение");
        JMenuItem menuSensor764 = new JMenuItem("«Фокусы» зрения");
        menuSensor761.setFont(cs.fontSmall);
        menuSensor762.setFont(cs.fontSmall);
        menuSensor763.setFont(cs.fontSmall);
        menuSensor764.setFont(cs.fontSmall);

        JMenuItem menuSensor81 = new JMenuItem("Строение уха");
        JMenuItem menuSensor82 = new JMenuItem("Чувство равновесия");
        JMenu menuSensor83 = new JMenu("Восприятие звуков");
        menuSensor81.setFont(cs.fontSmall);
        menuSensor82.setFont(cs.fontSmall);
        menuSensor83.setFont(cs.fontSmall);

        JMenuItem menuSensor831 = new JMenuItem("Основные структуры, проводящие и принимающие звук");
        JMenuItem menuSensor832 = new JMenuItem("Проведение информации в слуховой системе");
        menuSensor831.setFont(cs.fontSmall);
        menuSensor832.setFont(cs.fontSmall);

        JMenuItem menuSex1 = new JMenuItem("Половое размножение");
        JMenuItem menuSex2 = new JMenuItem("Половой диморфизм");

        menuSex.add(menuSex1);
        menuSex.add(menuSex2);

        JMenu menuSex3 = new JMenu("Мужская половая система");
        JMenu menuSex4 = new JMenu("Женская половая система");
        JMenu menuSex5 = new JMenu("Женский половой цикл");
        JMenuItem menuSex6 = new JMenuItem("Половая система и размножение. Атлас");

        JMenuItem menuSex31 = new JMenuItem("Мужские половые органы");
        menuSex31.setFont(cs.fontSmall);

        JMenuItem menuSex41 = new JMenuItem("Молочная железа");
        JMenuItem menuSex42 = new JMenuItem("Женские половые органы");
        menuSex41.setFont(cs.fontSmall);
        menuSex42.setFont(cs.fontSmall);

        JMenuItem menuSex51 = new JMenuItem("Женский половой цикл");
        JMenuItem menuSex52 = new JMenuItem("Менструальный цикл");
        JMenuItem menuSex53 = new JMenuItem("Первые семь дней новой жизни");
        JMenuItem menuSex54 = new JMenuItem("Беременность");
        JMenuItem menuSex55 = new JMenuItem("Роды");
        menuSex51.setFont(cs.fontSmall);
        menuSex52.setFont(cs.fontSmall);
        menuSex53.setFont(cs.fontSmall);
        menuSex54.setFont(cs.fontSmall);
        menuSex55.setFont(cs.fontSmall);


        menuSex5.add(menuSex51);
        menuSex5.add(menuSex52);
        menuSex5.add(menuSex53);
        menuSex5.add(menuSex54);
        menuSex5.add(menuSex55);


        menuSex4.add(menuSex41);
        menuSex4.add(menuSex42);

        menuSex3.add(menuSex31);

        menuSex.add(menuSex3);
        menuSex.add(menuSex4);
        menuSex.add(menuSex5);
        menuSex.add(menuSex6);

        menuSensor83.add(menuSensor831);
        menuSensor83.add(menuSensor832);

        menuSensor8.add(menuSensor81);
        menuSensor8.add(menuSensor82);
        menuSensor8.add(menuSensor83);

        menuSensor76.add(menuSensor761);
        menuSensor76.add(menuSensor762);
        menuSensor76.add(menuSensor763);
        menuSensor76.add(menuSensor764);

        menuSensor75.add(menuSensor751);
        menuSensor75.add(menuSensor752);
        menuSensor75.add(menuSensor753);

        menuSensor712.add(menuSensor7121);
        menuSensor712.add(menuSensor7122);

        menuSensor71.add(menuSensor711);
        menuSensor71.add(menuSensor712);

        menuSensor7.add(menuSensor71);
        menuSensor7.add(menuSensor72);
        menuSensor7.add(menuSensor73);
        menuSensor7.add(menuSensor74);
        menuSensor7.add(menuSensor75);
        menuSensor7.add(menuSensor76);

        menuTrajectories.add(menuSensor1);
        menuTrajectories.add(menuSensor2);
        menuTrajectories.add(menuSensor3);
        menuTrajectories.add(menuSensor4);
        menuTrajectories.add(menuSensor5);
        menuTrajectories.add(menuSensor6);
        menuTrajectories.add(menuSensor7);
        menuTrajectories.add(menuSensor8);
        menuTrajectories.add(menuSensor9);

        menuOpora272.add(menuOpora2721);
        menuOpora272.add(menuOpora2722);

        menuOpora271.add(menuOpora2711);
        menuOpora271.add(menuOpora2712);
        menuOpora271.add(menuOpora2713);

        menuOpora27.add(menuOpora271);
        menuOpora27.add(menuOpora272);

        menuOpora26.add(menuOpora261);
        menuOpora26.add(menuOpora262);
        menuOpora26.add(menuOpora263);
        menuOpora26.add(menuOpora264);
        menuOpora26.add(menuOpora265);


        menuOpora23.add(menuOpora231);
        menuOpora23.add(menuOpora232);

        menuOpora3.add(menuOpora31);
        menuOpora3.add(menuOpora32);
        menuOpora3.add(menuOpora33);

        menuOpora2.add(menuOpora21);
        menuOpora2.add(menuOpora22);
        menuOpora2.add(menuOpora23);
        menuOpora2.add(menuOpora24);
        menuOpora2.add(menuOpora25);
        menuOpora2.add(menuOpora26);
        menuOpora2.add(menuOpora27);
        menuOpora2.add(menuOpora28);

        menuOrthogonality.add(menuOpora1);
        menuOrthogonality.add(menuOpora2);
        menuOrthogonality.add(menuOpora3);
        menuOrthogonality.add(menuOpora4);

        menuMultiplcation.add(menuOv1);
        menuMultiplcation.add(menuOv2);
        menuMultiplcation.add(menuOv3);
        menuMultiplcation.add(menuOv4);
        menuMultiplcation.add(menuOv5);

        menuMocha2.add(menuMocha21);
        menuMocha2.add(menuMocha22);
        menuMocha2.add(menuMocha23);
        menuMocha2.add(menuMocha24);

        menuGessMatrix.add(menuMocha1);
        menuGessMatrix.add(menuMocha2);
        menuGessMatrix.add(menuMocha3);
        menuGessMatrix.add(menuMocha4);
        menuGessMatrix.add(menuMocha5);


        menuPs6.add(menuPs61);
        menuPs6.add(menuPs62);
        menuPs6.add(menuPs63);

        menuPs5.add(menuPs51);
        menuPs5.add(menuPs52);

        menuPs44.add(menuPs441);
        menuPs44.add(menuPs442);

        menuPs4.add(menuPs41);
        menuPs4.add(menuPs42);
        menuPs4.add(menuPs43);
        menuPs4.add(menuPs44);

        menuPs33.add(menuPs331);
        menuPs33.add(menuPs332);

        menuPs3.add(menuPs31);
        menuPs3.add(menuPs32);
        menuPs3.add(menuPs33);
        menuPs3.add(menuPs34);
        menuPs3.add(menuPs35);
        menuPs3.add(menuPs36);

        menuPs2.add(menuPs21);
        menuPs2.add(menuPs22);
        menuPs2.add(menuPs23);

        menuExamples.add(menuPs1);
        menuExamples.add(menuPs2);
        menuExamples.add(menuPs3);
        menuExamples.add(menuPs4);
        menuExamples.add(menuPs5);
        menuExamples.add(menuPs6);
        menuExamples.add(menuPs7);

        menuDihanie5.add(menuDihanie51);
        menuDihanie5.add(menuDihanie52);
        menuDihanie5.add(menuDihanie53);
        menuDihanie5.add(menuDihanie54);
        menuDihanie5.add(menuDihanie55);

        menuDihanie41.add(menuDihanie411);
        menuDihanie41.add(menuDihanie412);
        menuDihanie41.add(menuDihanie413);

        menuDihanie3.add(menuDihanie31);
        menuDihanie3.add(menuDihanie32);
        menuDihanie3.add(menuDihanie33);
        menuDihanie3.add(menuDihanie34);

        menuDihanie4.add(menuDihanie41);
        menuDihanie4.add(menuDihanie42);
        menuDihanie4.add(menuDihanie43);
        menuDihanie4.add(menuDihanie44);


        menuLinearOperators.add(menuDihanie1);
        menuLinearOperators.add(menuDihanie2);
        menuLinearOperators.add(menuDihanie3);
        menuLinearOperators.add(menuDihanie4);
        menuLinearOperators.add(menuDihanie5);
        menuLinearOperators.add(menuDihanie6);

        menuScalarProdact.add(menuKrov1);
        menuScalarProdact.add(menuKrov2);
        menuScalarProdact.add(menuKrov3);

        menuKrov2.add(menuKrov21);
        menuKrov2.add(menuKrov22);

        menuKrov1.add(menuKrov11);
        menuKrov1.add(menuKrov12);
        menuKrov1.add(menuKrov13);
        menuKrov1.add(menuKrov14);

        menuKrov13.add(menuKrov131);
        menuKrov13.add(menuKrov132);
        menuKrov13.add(menuKrov133);
        menuKrov13.add(menuKrov134);
        menuKrov13.add(menuKrov135);

        menuKrov14.add(menuKrov141);
        menuKrov14.add(menuKrov142);
        menuKrov14.add(menuKrov143);
        menuKrov14.add(menuKrov144);






        menuVnutr1.add(menuVnutrm1);
        menuVnutr1.add(menuVnutrm2);
        menuVnutr1.add(menuVnutrm3);

        menuMatch.add(menuRegul1);
        menuMatch.add(menuRegul2);
        menuMatch.add(menuRegul3);
        menuMatch.add(menuRegul4);

        menuSets.add(menuNerv1);
        menuSets.add(menuNerv2);
        menuSets.add(menuNerv3);

        menuNerv2.add(menuNerv21);
        menuNerv2.add(menuNerv22);
        menuNerv2.add(menuNerv23);

        menuNerv1.add(menuNervl1);
        menuNerv1.add(menuNervl2);
        menuNerv1.add(menuNervl3);
        menuNerv1.add(menuNervl4);
        menuNerv1.add(menuNervl5);

        menuNervl2.add(menuNerv121);
        menuNervl2.add(menuNerv122);

        menuVectors.add(menuEndokr1);
        menuVectors.add(menuEndokr2);
        menuVectors.add(menuEndokr3);
        menuVectors.add(menuEndokr4);
        menuVectors.add(menuEndokr5);
        menuVectors.add(menuEndokr6);
        menuVectors.add(menuEndokr7);

        menuEndokr5.add(menuEndokr51);
        menuEndokr5.add(menuEndokr52);

        menuEndokr52.add(menuEndokr521);
        menuEndokr52.add(menuEndokr522);

        menuLineaCombinationsr.add(menuVnutrsred1);
        menuLineaCombinationsr.add(menuVnutrsred2);
        menuLineaCombinationsr.add(menuVnutrsred3);
        menuLineaCombinationsr.add(menuVnutrsred4);
        menuLineaCombinationsr.add(menuVnutrsred5);
        menuLineaCombinationsr.add(menuVnutrsred6);
        menuLineaCombinationsr.add(menuVnutrsred7);
        menuLineaCombinationsr.add(menuVnutrsred8);
        menuLineaCombinationsr.add(menuVnutrsred9);
        menuLineaCombinationsr.add(menuVnutrsred10);
        menuLineaCombinationsr.add(menuVnutrsred11);

        menuVnutrsred6.add(menuVnutrsred61);
        menuVnutrsred6.add(menuVnutrsred62);
        menuVnutrsred6.add(menuVnutrsred63);
        menuVnutrsred6.add(menuVnutrsred64);
        menuVnutrsred6.add(menuVnutrsred65);

        menuVnutrsred10.add(menuVnutrsred101);
        menuVnutrsred10.add(menuVnutrsred102);

        menuVnutrsred11.add(menuVnutrsred111);
        menuVnutrsred11.add(menuVnutrsred112);
        menuVnutrsred11.add(menuVnutrsred113);

        menuSystemsEquations.add(menuImun1);
        menuSystemsEquations.add(menuImun2);
        menuSystemsEquations.add(menuImun3);
        menuSystemsEquations.add(menuImun4);
        menuSystemsEquations.add(menuImun5);
        menuSystemsEquations.add(menuImun6);
        menuSystemsEquations.add(menuImun7);
        menuSystemsEquations.add(menuImun8);
        menuSystemsEquations.add(menuImun9);

        menuImun4.add(menuImun41);
        menuImun4.add(menuImun42);


        menuImun42.add(menuImun421);
        menuImun42.add(menuImun422);







        menuVnutrm2.add(menuVnutrmov1);
        menuVnutrm2.add(menuVnutrmov2);
        menuVnutrm2.add(menuVnutrmov3);
        menuVnutrm2.add(menuVnutrmov4);
        menuVnutrm2.add(menuVnutrmov5);

        menuVnutr2.add(menuVnutrmkl1);
        menuVnutr2.add(menuVnutrmkl2);
        menuVnutr2.add(menuVnutrmkl3);
        menuVnutr2.add(menuVnutrmkl4);
        menuVnutr2.add(menuVnutrmkl5);
        menuVnutr2.add(menuVnutrmkl6);
        menuVnutr2.add(menuVnutrmkl7);

        menuVnutr3.add(menuVnutrmtk1);
        menuVnutr3.add(menuVnutrmtk2);
        menuVnutr3.add(menuVnutrmtk3);
        menuVnutr3.add(menuVnutrmtk4);
        menuVnutr3.add(menuVnutrmtk5);
        menuVnutr3.add(menuVnutrmtk6);


        menuVnutrmtk2.add(menuVnutrtkl1);
        menuVnutrmtk2.add(menuVnutrtkl2);
        menuVnutrmtk2.add(menuVnutrtkl3);
        menuVnutrmtk2.add(menuVnutrtkl4);


        menuVnutrmtk3.add(menuVnutrtk21);
        menuVnutrmtk3.add(menuVnutrtk22);
        menuVnutrmtk3.add(menuVnutrtk23);
        menuVnutrmtk3.add(menuVnutrtk24);

        menuVnutrmtk4.add(menuVnutrtk31);
        menuVnutrmtk4.add(menuVnutrtk32);
        menuVnutrmtk4.add(menuVnutrtk33);
        menuVnutrmtk4.add(menuVnutrtk34);

        menuVnutrmtk5.add(menuVnutrtk41);
        menuVnutrmtk5.add(menuVnutrtk42);
        menuVnutrmtk5.add(menuVnutrtk43);

        menuVnutrtk43.add(menuVnutrtk141);
        menuVnutrtk43.add(menuVnutrtk142);

        menuVnutr5.add(menuVnutror1);
        menuVnutr5.add(menuVnutror2);
        menuVnutr5.add(menuVnutror3);










        menuVnesh.add(menuVnesh1);
        menuVnesh.add(menuVnesh2);


        menuVnutr.add(menuVnutr1);
        menuVnutr.add(menuVnutr2);
        menuVnutr.add(menuVnutr3);
        menuVnutr.add(menuVnutr4);
        menuVnutr.add(menuVnutr5);


        menuCoordinates.add(menuVnesh);
        menuCoordinates.add(menuVnutr);

        menuVnutr.setFont(cs.fontSmall);
        menuVnesh.setFont(cs.fontSmall);
        menuVnesh1.setFont(cs.fontSmall);
        menuVnesh2.setFont(cs.fontSmall);

        menuVnutr1.setFont(cs.fontSmall);
        menuVnutr2.setFont(cs.fontSmall);
        menuVnutr3.setFont(cs.fontSmall);
        menuVnutr4.setFont(cs.fontSmall);
        menuVnutr5.setFont(cs.fontSmall);





        menuCoordinates.setFont(cs.fontSmall);
        menuClickPoints.setFont(cs.fontSmall);
        menuMatch.setFont(cs.fontSmall);
        menuSets.setFont(cs.fontSmall);
        menuVectors.setFont(cs.fontSmall);
        menuLineaCombinationsr.setFont(cs.fontSmall);
        menuSystemsEquations.setFont(cs.fontSmall);
        menuScalarProdact.setFont(cs.fontSmall);
        menuLinearOperators.setFont(cs.fontSmall);
        menuExamples.setFont(cs.fontSmall);
        menuGessMatrix.setFont(cs.fontSmall);
        menuMultiplcation.setFont(cs.fontSmall);
        menuOrthogonality.setFont(cs.fontSmall);
        menuTrajectories.setFont(cs.fontSmall);
        menuSex.setFont(cs.fontSmall);
        menuSex1.setFont(cs.fontSmall);
        menuSex2.setFont(cs.fontSmall);
        menuSex3.setFont(cs.fontSmall);
        menuSex4.setFont(cs.fontSmall);
        menuSex5.setFont(cs.fontSmall);
        menuSex6.setFont(cs.fontSmall);









        setsOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cs.codOtnoshenia(setsRelation.getText())<3 && cs.isNumber(setsA.getText()) && cs.isNumber(setsB.getText()) && cs.isNumber(setsC.getText()))
                defSets.dispose();
            }
        });
        compareOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compare.dispose();
            }
        });
        setsCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic = 9;
                defSets.dispose();
            }
        });
        setsBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic--;
                defSets.dispose();
            }
        });
        compareCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic = 9;
                compare.dispose();
            }
        });
        compareBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic--;
                compare.dispose();
            }
        });



        JDialog steps = new JDialog(frame,"Векторы", true);
        steps.setLayout(new GridBagLayout());
        steps.setSize((int)(500.*cs.width/1920), (int)(270.*cs.height/1080));
        steps.setLocationRelativeTo(frame);
        steps.setLocation(cs.orx + cs.fxp + (int)(40.*cs.width/1920), cs.ory + (int)(200.*cs.height/1080));
        JButton stepsA = new JButton("a");
        JButton stepsB = new JButton("b");
        JButton stepsMinusA = new JButton("-a");
        JButton stepsMinusB = new JButton("-b");
        JButton stepsOK = new JButton("OK");
        JButton stepsCancel = new JButton(cs.canCel);
        JButton stepsNazad = new JButton("НАЗАД");
        stepsA.setFont(cs.fontItalic);
        stepsB.setFont(cs.fontItalic);
        stepsMinusA.setFont(cs.fontItalic);
        stepsMinusB.setFont(cs.fontItalic);
        stepsOK.setFont(cs.font);
        stepsCancel.setFont(cs.font);
        stepsNazad.setFont(cs.font);
        stepsA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.na++;
                steps.dispose();
            }
        });
        stepsB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.nb++;
                steps.dispose();
            }
        });
        stepsMinusA.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.na--;
                steps.dispose();
            }
        });
        stepsMinusB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.nb--;
                steps.dispose();
            }
        });
        stepsOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                steps.dispose();
            }
        });
        stepsCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic=9;
                steps.dispose();
            }
            });
        stepsNazad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic--;
                steps.dispose();
            }
        });
        steps.add(stepsA, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        steps.add(stepsB, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        steps.add(stepsMinusA, new GridBagConstraints(1, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        steps.add(stepsMinusB, new GridBagConstraints(1, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        steps.add(stepsOK, new GridBagConstraints(2, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        steps.add(stepsCancel, new GridBagConstraints(2, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));
        steps.add(stepsNazad, new GridBagConstraints(2, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(10, 10, 10, 10), 0, 0));




        JDialog linearCombination = new JDialog(frame,"Линейная комбинация", true);
        linearCombination.setLayout(null);
        linearCombination.setSize((int)(500.*cs.width/1920), (int)(280.*cs.height/1080));
        linearCombination.setLocationRelativeTo(frame);
        linearCombination.setLocation(cs.orx + cs.fxp + (int)(40.*cs.width/1920), cs.ory + (int)(200.*cs.height/1080));
        JTextField linCombU = new JTextField(6);
        JTextField linCombV = new JTextField(4);
        JButton linCombOK = new JButton("OK");
        JButton linCombCancel = new JButton(cs.canCel);
        JButton linCombBack = new JButton("НАЗАД");
        JLabel linCombC = new JLabel("c =");
        JLabel linCombA = new JLabel("a +");
        JLabel linCombB = new JLabel("b");
        linCombU.setFont(cs.font);
        linCombV.setFont(cs.font);
        linCombOK.setFont(cs.font);
        linCombCancel.setFont(cs.font);
        linCombBack.setFont(cs.font);
        linCombC.setFont(cs.fontItalic);
        linCombA.setFont(cs.fontItalic);
        linCombB.setFont(cs.fontItalic);

        linCombC.setSize(cs.wchislo,cs.hskobka/2);
        linCombU.setSize(cs.wchislo,cs.hskobka/2);
        linCombA.setSize(cs.wchislo,cs.hskobka/2);
        linCombV.setSize(cs.wchislo,cs.hskobka/2);
        linCombB.setSize(cs.wchislo,cs.hskobka/2);
        linCombOK.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));
        linCombCancel.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));
        linCombBack.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));

        linCombC.setLocation(cs.otstup,cs.otstup);
        linCombU.setLocation(cs.otstup+cs.wchislo,cs.otstup);
        linCombA.setLocation(cs.otstup+2*cs.wchislo,cs.otstup);
        linCombV.setLocation(cs.otstup+3*cs.wchislo,cs.otstup);
        linCombB.setLocation(cs.otstup+4*cs.wchislo,cs.otstup);
        linCombOK.setLocation(cs.otstup,cs.otstup+cs.hskobka);
        linCombCancel.setLocation(cs.otstup+(int)(220.*cs.width/1920),cs.otstup+cs.hskobka);
        linCombBack.setLocation(cs.otstup,cs.otstup+2*cs.hskobka-cs.hskobka/3);

        linearCombination.add(linCombC);
        linearCombination.add(linCombU);
        linearCombination.add(linCombA);
        linearCombination.add(linCombV);
        linearCombination.add(linCombB);
        linearCombination.add(linCombOK);
        linearCombination.add(linCombBack);
        linearCombination.add(linCombCancel);

        linCombOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cs.isNumber(linCombU.getText()) && cs.isNumber(linCombV.getText()))
                    linearCombination.dispose();
            }
        });

        linCombCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic=9;
                linearCombination.dispose();
            }
        });

        linCombBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic--;
                linearCombination.dispose();
            }
        });

 //       JMenuItem menuLinearCombinations = new JMenuItem(cs.menuTopics[5]);
 //       menuLinearCombinations.addActionListener(new ActionListener() {
 //           @Override
 //           public void actionPerformed(ActionEvent e) {

//            }
 //       });

        JDialog linearCombinationS = new JDialog(frame,"Системы линейных уравнений", true);
        linearCombinationS.setLayout(null);
        linearCombinationS.setSize((int)(500.*cs.width/1920), (int)(300.*cs.height/1080));
        linearCombinationS.setLocationRelativeTo(frame);
        linearCombinationS.setLocation(cs.orx + cs.fxp + (int)(40.*cs.width/1920), cs.ory + (int)(200.*cs.height/1080));
        JTextField linCombUS = new JTextField(3);
        JTextField linCombVS = new JTextField(3);
        JButton linCombOKS = new JButton("OK");
        JButton linCombCancelS = new JButton(cs.canCel);
        JButton linCombBackS = new JButton("НАЗАД");
        JButton linCombPicture = new JButton("Картинка");
        JLabel linCombCSup = new JLabel("cup");
        JLabel linCombASup = new JLabel("aup");
        JLabel linCombBSup = new JLabel("bup");
        JLabel linCombCSdown = new JLabel("cdown");
        JLabel linCombASdown = new JLabel("adown");
        JLabel linCombBSdown = new JLabel("bdown");
        JLabel linCombLeft1 = new JLabel("(");
        JLabel linCombLeft2 = new JLabel("(");
        JLabel linCombLeft3 = new JLabel("(");
        JLabel linCombRight1 = new JLabel(")");
        JLabel linCombRight2 = new JLabel(")");
        JLabel linCombRight3 = new JLabel(")");
        JLabel linCombPlus = new JLabel("+");
        JLabel linCombRavno = new JLabel("=");
        linCombLeft1.setFont(cs.fontBig);
        linCombLeft2.setFont(cs.fontBig);
        linCombLeft3.setFont(cs.fontBig);
        linCombRight1.setFont(cs.fontBig);
        linCombRight2.setFont(cs.fontBig);
        linCombRight3.setFont(cs.fontBig);
        linCombLeft1.setSize(cs.wskobka,cs.hskobka);
        linCombLeft2.setSize(cs.wskobka,cs.hskobka);
        linCombLeft3.setSize(cs.wskobka,cs.hskobka);
        linCombRight1.setSize(cs.wskobka,cs.hskobka);
        linCombRight2.setSize(cs.wskobka,cs.hskobka);
        linCombRight3.setSize(cs.wskobka,cs.hskobka);
        linCombLeft1.setLocation(cs.otstup,cs.otstup);
        linCombLeft2.setLocation(cs.otstup+3*cs.wchislo+2*cs.wskobka,cs.otstup);
        linCombLeft3.setLocation(cs.otstup+6*cs.wchislo+4*cs.wskobka,cs.otstup);
        linCombRight1.setLocation(cs.otstup+cs.wchislo+cs.wskobka,cs.otstup);
        linCombRight2.setLocation(cs.otstup+4*cs.wchislo+3*cs.wskobka,cs.otstup);
        linCombRight3.setLocation(cs.otstup+7*cs.wchislo+5*cs.wskobka,cs.otstup);
        linCombUS.setSize(cs.wchislo,cs.hskobka/2);
        linCombUS.setLocation(cs.otstup+2*cs.wchislo+2*cs.wskobka,cs.otstup+cs.hskobka/3);
        linCombVS.setSize(cs.wchislo,cs.hskobka/2);
        linCombVS.setLocation(cs.otstup+5*cs.wchislo+4*cs.wskobka,cs.otstup+cs.hskobka/3);
        linCombPlus.setSize(cs.wchislo,cs.hskobka/2);
        linCombRavno.setSize(cs.wchislo,cs.hskobka/2);
        linCombPlus.setLocation(cs.wchislo,cs.hskobka/2);
        linCombRavno.setLocation(cs.otstup+cs.wchislo+2*cs.wskobka,cs.otstup+cs.hskobka/3);
        linCombPlus.setLocation(cs.otstup+4*cs.wchislo+4*cs.wskobka,cs.otstup+cs.hskobka/3);

        linCombCSup.setSize(cs.wchislo,cs.hskobka/2);
        linCombASup.setSize(cs.wchislo,cs.hskobka/2);
        linCombBSup.setSize(cs.wchislo,cs.hskobka/2);
        linCombCSdown.setSize(cs.wchislo,cs.hskobka/2);
        linCombASdown.setSize(cs.wchislo,cs.hskobka/2);
        linCombBSdown.setSize(cs.wchislo,cs.hskobka/2);

        linCombCSup.setLocation(cs.otstup+cs.wskobka,cs.otstup);
        linCombASup.setLocation(cs.otstup+3*cs.wskobka+3*cs.wchislo,cs.otstup);
        linCombBSup.setLocation(cs.otstup+5*cs.wskobka+6*cs.wchislo,cs.otstup);
        linCombCSdown.setLocation(cs.otstup+cs.wskobka,cs.otstup+cs.hskobka/2);
        linCombASdown.setLocation(cs.otstup+3*cs.wskobka+3*cs.wchislo,cs.otstup+cs.hskobka/2);
        linCombBSdown.setLocation(cs.otstup+5*cs.wskobka+6*cs.wchislo,cs.otstup+cs.hskobka/2);

        linCombOKS.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));
        linCombCancelS.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));
        linCombBackS.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));
        linCombPicture.setSize((int)(200.*cs.width/1920),(int)(40.*cs.height/1080));

        linCombOKS.setLocation(cs.otstup,cs.otstup+cs.hskobka+cs.hskobka/3);
        linCombCancelS.setLocation(cs.otstup+(int)(220.*cs.width/1920),cs.otstup+cs.hskobka+cs.hskobka/3);
        linCombBackS.setLocation(cs.otstup,cs.otstup+2*cs.hskobka);
        linCombPicture.setLocation(cs.otstup+(int)(220.*cs.width/1920),cs.otstup+2*cs.hskobka);


        linCombPlus.setFont(cs.fontItalic);
        linCombRavno.setFont(cs.fontItalic);
        linCombUS.setFont(cs.font);
        linCombVS.setFont(cs.font);
        linCombOKS.setFont(cs.font);
        linCombCancelS.setFont(cs.font);
        linCombBackS.setFont(cs.font);
        linCombPicture.setFont(cs.font);
        linCombCSup.setFont(cs.fontItalic);
        linCombASup.setFont(cs.fontItalic);
        linCombBSup.setFont(cs.fontItalic);
        linCombCSdown.setFont(cs.fontItalic);
        linCombASdown.setFont(cs.fontItalic);
        linCombBSdown.setFont(cs.fontItalic);
        linearCombinationS.add(linCombUS);
        linearCombinationS.add(linCombVS);
        linearCombinationS.add(linCombOKS);
        linearCombinationS.add(linCombCancelS);
        linearCombinationS.add(linCombBackS);
        linearCombinationS.add(linCombCSup);
        linearCombinationS.add(linCombASup);
        linearCombinationS.add(linCombBSup);
        linearCombinationS.add(linCombCSdown);
        linearCombinationS.add(linCombASdown);
        linearCombinationS.add(linCombBSdown);
        linearCombinationS.add(linCombLeft1);
        linearCombinationS.add(linCombLeft2);
        linearCombinationS.add(linCombLeft3);
        linearCombinationS.add(linCombRight1);
        linearCombinationS.add(linCombRight2);
        linearCombinationS.add(linCombRight3);
        linearCombinationS.add(linCombPlus);
        linearCombinationS.add(linCombRavno);
        linearCombinationS.add(linCombPicture);

        linCombOKS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((cs.isNumber(linCombUS.getText()) && cs.isNumber(linCombVS.getText())) || cs.showPicture )
                linearCombinationS.dispose();

            }
        });

        linCombCancelS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic = 9;
                linearCombinationS.dispose();;
            }
        });

        linCombBackS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic--;
                linearCombinationS.dispose();;
            }
        });

        linCombPicture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.showPicture = true;
                linearCombinationS.dispose();;
            }
        });

        menuLinearOperators.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });





        JMenuItem menuMyResult = new JMenuItem("Мои результаты");
        menuOptions.add(menuMyResult);
        menuMyResult.setFont(cs.fontSmall);
        menuMyResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    cs.totalName ="";
                Date when = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                String string = "";
                cs.stroka=0.5;
                int battleRegime=0;
                int i, nomer=0, nnn=1,total=0;
                int[] maxcount = new int[20];
                double[] average = new double[20];
                double averageTotal = 0.;
                if(cs.login.length() > 0) {
                    File file = new File(cs.login + ".lin");
                    File fileJop = new File(cs.login + ".jop");
                    if (!file.exists()) {
                        message1.setText("Кто Вы ? Вам нужно авторизоваться !");
                        message2.setText("Или зарегистрироваться !");
                        messageBox.setVisible(true);
                    } else {
                        comp.clearAll();
                        try {
                            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF8");
                            InputStreamReader readerJop = new InputStreamReader(new FileInputStream(fileJop), "UTF8");
                            int c;
                            for (i = 0; i < 8; ++i) {
                                while ((c = reader.read()) != ';') {
                                    //           cs.totalName += (char)c;
                                }
                                //       cs.totalName += " ";
                            }
                            cs.ntopics = 0;
                            strFont[0] = new StriFont(cs.totalName, "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            while ((c = reader.read()) != -1) {
                                if ((char) c != ';')
                                    string += (char) c;
                                else {
                                    if (nomer % 5 == 0) {
                                        //                 try {
                                        //                      when = dateFormat.parse(string);
                                        //                  } catch (ParseException e1) {
                                        //                      e1.printStackTrace();
                                        //                  }
                                        string = "";
                                    }
                                    if (nomer % 5 == 1) {
                                        cs.topics[cs.ntopics] = string;
                                        string = "";
                                    }
                                    if (nomer % 5 == 2) {
                                        cs.ocenka[cs.ntopics] = (int) Double.parseDouble(string);
                                        string = "";

                                    }
                                    if (nomer % 5 == 3) {
                                        maxcount[cs.ntopics] = (int) Double.parseDouble(string);
                                        if(maxcount[cs.ntopics] == 0){
                                            maxcount[cs.ntopics] = 1;
                                        }
                                        string = "";
                                        average[cs.ntopics] = (double) cs.ocenka[cs.ntopics] / maxcount[cs.ntopics];
                                        for (i = 0; i < cs.ntopics; ++i) {
                                            if (cs.topics[i].equals(cs.topics[cs.ntopics])) {
                                                nnn = 0;
                                                if (average[i] < average[cs.ntopics])
                                                    average[i] = average[cs.ntopics];
                                            }
                                        }
                                        cs.ntopics += nnn;
                                        nnn = 1;
                                    }
                                    if (nomer % 5 == 4) {
                                        battleRegime = (int) Double.parseDouble(string);
                                        string = "";

                                    }
                                    nomer++;
                                }
                            }
                            while ((c = readerJop.read()) != -1) {
                                if ((char) c != ';')
                                    string += (char) c;
                                else {
                                    if (nomer % 5 == 0) {
                                        //       try {
                                        //           when = dateFormat.parse(string);
                                        //       } catch (ParseException e1) {
                                        //           e1.printStackTrace();
                                        //       }
                                        string = "";
                                    }
                                    if (nomer % 5 == 1) {
                                        cs.topics[cs.ntopics] = string;
                                        string = "";
                                    }
                                    if (nomer % 5 == 2) {
                                        cs.ocenka[cs.ntopics] = (int) Double.parseDouble(string);
                                        string = "";

                                    }
                                    if (nomer % 5 == 3) {
                                        maxcount[cs.ntopics] = (int) Double.parseDouble(string);
                                        string = "";
                                        average[cs.ntopics] = (double) cs.ocenka[cs.ntopics] / maxcount[cs.ntopics];
                                        for (i = 0; i < cs.ntopics; ++i) {
                                            if (cs.topics[i].equals(cs.topics[cs.ntopics])) {
                                                nnn = 0;
                                                if (average[i] < average[cs.ntopics])
                                                    average[i] = average[cs.ntopics];
                                            }
                                        }
                                        cs.ntopics += nnn;
                                        nnn = 1;
                                    }
                                    if (nomer % 5 == 4) {
                                        battleRegime = (int) Double.parseDouble(string);
                                        string = "";

                                    }
                                    nomer++;
                                }
                            }
                            int k=0;
                            comp.addPString("Тема,                                            оценка,    однотипных", cs.rightTextLocation1(cs.stroka++,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            total=0;
                            averageTotal = 0.;

                            for (i = 0; i < cs.ntopics; ++i) {
                                comp.addPString(cs.topics[i], cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                                comp.addPString(String.format("%3d  %10d", cs.ocenka[i], maxcount[i]), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                                total += cs.ocenka[i];
                                averageTotal += average[i];
                            }
                            comp.addPString("Итого", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString(String.format("%d", total), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString("Средний бал", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString(String.format("%5.2f", (float) averageTotal), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
                else {
                    message1.setText("Кто Вы ? Вам нужно авторизоваться !");
                    message2.setText("Или зарегистрироваться !");
                    messageBox.setVisible(true);
                }
            }
        });
/*
        JDialog poisk = new JDialog(frame, "Поиск юзера",true);
        poisk.setLocationRelativeTo(frame);
        poisk.setLocation(cs.orx + cs.fxp,cs.ory + (int)(200.*cs.height/1080));
        poisk.setLayout(new GridBagLayout());
        poisk.setSize((int)(650.*cs.width/1920),(int)(300.*cs.height/1080));
        JLabel poiskLabel1 = new JLabel("Введите один или несколько разделённых знаком \";\"");
        JLabel poiskLabel2 = new JLabel("фрагментов фамилии, имени, отчества, имейла и тел.");
        JTextField poiskFragment = new JTextField(20);
        JButton poiskOK = new JButton("OK");
        JButton poiskCancel = new JButton("Отмена");
        poiskLabel1.setFont(cs.font);
        poiskLabel2.setFont(cs.font);
        poiskOK.setFont(cs.font);
        poiskCancel.setFont(cs.font);
        poiskFragment.setFont(cs.font);


        poisk.add(poiskLabel1,new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));
        poisk.add(poiskLabel2,new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));
        poisk.add(poiskFragment,new GridBagConstraints(0, 2, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));
        poisk.add(poiskOK,new GridBagConstraints(0, 3, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 2, 10), 0, 0));
        poisk.add(poiskCancel,new GridBagConstraints(0, 4, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 10, 10, 10), 0, 0));

        poiskOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poisk.dispose();
            }
        });
        poiskCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poisk.dispose();
                cs.SubTopic++;
            }
        });
*/




        JMenuItem menuResult = new JMenuItem("Результаты");
        menuOptions.add(menuResult);
        menuResult.setFont(cs.fontSmall);
        menuResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

          //      Date when = new Date();
          //      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
                String string = "";
                cs.stroka=0.5;
                String otvet = "";
                int battleRegime = 0;

                otvet = sD.doPost(cs.adresURL + "GetStatus", "login="+cs.login + "&status=");
                cs.status = (int)Double.parseDouble(otvet);
                //   String[] topic = new String[20];
                //   int[] ocenka = new int[20];
                StriFont[] strFont = new StriFont[20];
                int i=0, nomer=0, nnn=1,total=0;
                int c;
                int ll;

                String totalName ="";
                String login = "";
                int[] maxcount = new int[20];
                double[] average = new double[20];
                double averageTotal = 0.;
                cs.SubTopic = 0;

                if(cs.login.length() > 0) {
                    if(cs.status > 0) {

                        login = viborUser(cs.login,false,false,0);

                        if(cs.viborMadeOtmena == false) {
                            otvet = sD.doPost(cs.adresURL + "Result", "login=" + login);
                            comp.clearAll();
                            tM.clear();
                            cs.ntopics = 0;
                            totalName = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                            strFont[0] = new StriFont(totalName, "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            ll = otvet.length();
                            i = 0;
                            nomer = 0;
                            while (i < ll) {
                                c = otvet.charAt(i++);
                                if ((char) c != ';') {
                                    string += (char) c;
                                } else {
                                    if (nomer % 5 == 0) {
                                        string = "";
                                    }
                                    if (nomer % 5 == 1) {
                                        cs.topics[cs.ntopics] = string;
                                        string = "";
                                    }
                                    if (nomer % 5 == 2) {
                                        cs.ocenka[cs.ntopics] = (int) Double.parseDouble(string);
                                        string = "";
                                    }
                                    if (nomer % 5 == 3) {
                                        maxcount[cs.ntopics] = (int) Double.parseDouble(string);
                                        if (maxcount[cs.ntopics] == 0) {
                                            maxcount[cs.ntopics] = 1;
                                        }
                                        string = "";
                                        average[cs.ntopics] = (double) cs.ocenka[cs.ntopics] / maxcount[cs.ntopics];
                                        for (int ii = 0; ii < cs.ntopics; ++ii) {
                                            if (cs.topics[ii].equals(cs.topics[cs.ntopics])) {
                                                nnn = 0;
                                                if (average[ii] < average[cs.ntopics])
                                                    average[ii] = average[cs.ntopics];
                                            }
                                        }
                                        cs.ntopics += nnn;
                                        nnn = 1;
                                    }
                                    if (nomer % 5 == 4) {
                                        battleRegime = (int) Double.parseDouble(string);
                                        string = "";
                                    }
                                    nomer++;
                                }
                            }
                            int k=0;
                            comp.addPString("Тема,                                            оценка,    однотипных", cs.rightTextLocation1(cs.stroka++,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            total=0;
                            averageTotal = 0.;
                            for (i = 0; i < cs.ntopics; ++i) {
                                comp.addPString(cs.topics[i], cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                                comp.addPString(String.format("%3d  %10d", cs.ocenka[i], maxcount[i]), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                                total += cs.ocenka[i];
                                averageTotal += average[i];
                            }
                            comp.addPString("Итого", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString(String.format("%d", total), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString("Средний бал", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString(String.format("%5.2f", (float) averageTotal), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        }
                    }
                    else{
                        message1.setText("У Вас нет прав пользоваться этим сервисом");
                        message2.setText("");
                        messageBox.setVisible(true);
                    }
                }
                else {
                    message1.setText("Вам нужно авторизоваться !");
                    message2.setText("Или зарегистрироваться !");
                    messageBox.setVisible(true);
                }
            }
        });

        JMenuItem menuAdmin = new JMenuItem("Администратор");
        menuOptions.add(menuAdmin);
        menuAdmin.setFont(cs.fontSmall);
        menuAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = "";
                cs.stroka=0.5;
                String otvet = "";
                Send sD = new Send();
                otvet = sD.doPost(cs.adresURL + "GetStatus", "login="+cs.login + "&status=");
                cs.status = (int)Double.parseDouble(otvet);

                String login = "";
                String totalName = "";

                if(cs.login.length() > 0) {
                    if(cs.status > 1) {
                        login = viborUser(cs.login,false,false,0);
                        if(cs.viborMadeOtmena == false) {
                            comp.clearAll();
                            tM.clear();
                            totalName = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                            strFont[0] = new StriFont(totalName, "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            //                  ll = otvet.length();
                            strFont[0] = new StriFont("Вы хотите предоставить этому человеку статус 1 ?", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);

                            cs.SubTopic = 0;
                            // String dada = da.getText();
                            String netnet = net.getText();
                            String nazadnazad = daNetnazad.getText();
                            //     da.setText("Да");
                            net.setText("Я подумаю");
                            daNetnazad.setText("Нет");
                            do {
                                daNet.setVisible(true);
                                if (cs.SubTopic == 1) {
                                    otvet = sD.doPost(cs.adresURL + "GetStatus", "login=" + login + "&status=1");
                                }
                            } while (cs.SubTopic == 0);
                            net.setText(netnet);
                            daNetnazad.setText(nazadnazad);
                            comp.clearAll();
                            cs.titulList();
                        }
                    }
                    else{
                        message1.setText("У Вас нет прав пользоваться этим сервисом");
                        message2.setText("");
                        messageBox.setVisible(true);
                    }
                }
                else {
                    message1.setText("Вам нужно авторизоваться !");
                    message2.setText("Или зарегистрироваться !");
                    messageBox.setVisible(true);
                }
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuChallenge = new JMenuItem("Вызов на баттл");
        menuOptions.add(menuChallenge);
        menuChallenge.setFont(cs.fontSmall);
        menuChallenge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

                String challengee = "";
                String totalName = "";
                String otvet = "";
                if(cs.login.length()==0){
                    message1.setText("Вам нужно авторизоваться !");
                    message2.setText("Или зарегистрироваться !");
                    messageBox.setVisible(true);
                }
                else {
                    challengee = viborUser(cs.login,false,true,0);
                    if(cs.viborMadeOtmena == false) {
                        cs.SubTopic = 0;
                        comp.clearAll();
                        tM.clear();
                        cs.stroka = 0.5;
                        totalName = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                        strFont[0] = new StriFont(totalName, "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        strFont[0] = new StriFont("Вы хотите послать вызов на баттл этому", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        strFont[0] = new StriFont("участнику ?", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);

                        String netnet = net.getText();
                        String nazadnazad = daNetnazad.getText();
                        net.setText("Я подумаю");
                        daNetnazad.setText("Нет");
                        daNet.setVisible(true);
                        if (cs.SubTopic == 1) {
                            otvet = sD.doPost(cs.adresURL + "Challenge", String.format("date=%s&challenger=%s&challengee=%s", dateFormat.format(now), cs.login, challengee));
                        }
                        net.setText(netnet);
                        daNetnazad.setText(nazadnazad);
                        comp.clearAll();
                        cs.titulList();
                    }
                }
            }
        });


        JMenuItem menuBattleRegime = new JMenuItem("Режим баттла");
        menuOptions.add(menuBattleRegime);
        menuBattleRegime.setFont(cs.fontSmall);
        menuBattleRegime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chall = "";
                String totalName = "";
                String otvet = "";
                cs.SubTopic = 0;
                if(cs.login.length() == 0){
                    message1.setText("Вам нужно авторизоваться !");
                    message2.setText("Или зарегистрироваться !");
                    messageBox.setVisible(true);
                }
                else {

                    int battleRegime = (int) Double.parseDouble(viborUser(cs.login,true,false,0));
                    if(cs.viborMadeOtmena==false) {
                        comp.clearAll();
//                tM.clear();
                        cs.stroka = 0.5;
                        cs.totalName1 = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                        cs.login1 = regRecord[3];

                        strFont[0] = new StriFont(totalName, "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        strFont[0] = new StriFont("Вы хотите перейти в режим баттла с", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        strFont[0] = new StriFont("этим участником ?", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        String netnet = net.getText();
                        String nazadnazad = daNetnazad.getText();
                        String mess = poNyatno.getText();
                        net.setText("Я подумаю");
                        daNetnazad.setText("Нет");
                        poNyatno.setText("");
                        if (cs.SubTopic == 0) {
                            //   do {
                            daNet.setVisible(true);
                            if (cs.SubTopic == 1) {
                                cs.battleRegime = battleRegime;
                            }
                            //   } while (cs.SubTopic == 0);
                        }
                        net.setText(netnet);
                        daNetnazad.setText(nazadnazad);
                        poNyatno.setText(mess);
                        comp.clearAll();
                        cs.titulList();
                    }
                }
            }
        });

        JMenuItem menuBattleStop = new JMenuItem("Стоп баттл");
        menuOptions.add(menuBattleStop);
        menuBattleStop.setFont(cs.fontSmall);
        menuBattleStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chall = "";
                String totalName = "";
                String otvet = "";
                cs.SubTopic = 0;
                if(cs.login.length() == 0){
                    message1.setText("Вам нужно авторизоваться !");
                    message2.setText("Или зарегистрироваться !");
                    messageBox.setVisible(true);
                }
                else {

                    int battleid = (int) Double.parseDouble(viborUser(cs.login,true,false,0));
                    if(cs.viborMadeOtmena==false) {
                        comp.clearAll();
//                tM.clear();
                        cs.stroka = 0.5;
                        cs.totalName1 = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                        cs.login1 = regRecord[3];

                        strFont[0] = new StriFont(totalName, "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        strFont[0] = new StriFont("Вы хотите остановить баттл с", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        strFont[0] = new StriFont("этим участником ?", "", cs.font);
                        sf.textLine(strFont, cs.stroka++, 1);
                        String netnet = net.getText();
                        String nazadnazad = daNetnazad.getText();
                        String mess = poNyatno.getText();
                        net.setText("Я подумаю");
                        daNetnazad.setText("Нет");
                        poNyatno.setText("");
                        if (cs.SubTopic == 0) {
                            daNet.setVisible(true);
                            if (cs.SubTopic == 1) {
                                otvet = sD.doPost(cs.adresURL + "StopBattle", "id=" +battleid);
                                if(otvet.contains("OK")){
                                    message1.setText("Баттл успешно остановлен !");
                                    message2.setText("");
                                    messageBox.setVisible(true);
                                }
                                else{
                                    message1.setText("Баттл остановить не удалось,");
                                    message2.setText("возможно, нет связи.");
                                    messageBox.setVisible(true);
                                }
                            }
                        }
                        net.setText(netnet);
                        daNetnazad.setText(nazadnazad);
                        poNyatno.setText(mess);
                        comp.clearAll();
                        cs.titulList();
                    }
                }
            }
        });


        JMenuItem menuBattleResult = new JMenuItem("Результаты баттла");
        menuOptions.add(menuBattleResult);
        menuBattleResult.setFont(cs.fontSmall);
        menuBattleResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = "";
                String string1 = "";
                String string2 = "";
                cs.stroka=0.5;
                String otvet = "";

                int i=0, nomer=0, nnn=1,total=0;
                int c;
                int ll;
                int battleRegime=0;
 //               String totalName ="";
                String login = "";
                int[] maxcount = new int[20];
                double[] average = new double[20];
                double averageTotal = 0.;
                cs.SubTopic = 0;
                comp.clearAll();

                if(cs.battleRegime>0){
                    comp.addPString("Режим баттла", cs.rightTextLocation1(0.5,-cs.fxp/2.), cs.fontBold);
                    comp.addPString(cs.totalName1, cs.rightTextLocation1(1.5,-cs.fxp*cs.sdvigVlevo), cs.font);
                    comp.addPString(cs.totalName, cs.rightTextLocation1(1.5,0.), cs.font);
                }
  //              else {
  //                  if (totalName.length() > 0) {
  //                      comp.addPString(totalName, cs.rightTextLocation(0.5), cs.fontSmall);
  //                  }
  //              }

                if(cs.login.length() > 0 && cs.battleRegime >0) {
                    for(int k=0;k<2;++k) {
                        cs.stroka = 2.5;
                        if(k==0) {
                            otvet = sD.doPost(cs.adresURL + "Result", "login=" + cs.login);
                        }
                        else{
                            otvet = sD.doPost(cs.adresURL + "Result", "login=" + cs.login1);
                        }
                        cs.ntopics = 0;
 //                       comp.addPString(totalName, cs.rightTextLocation1(cs.stroka++,-cs.sdvigVlevo*k*cs.fxp), cs.font);

                        ll = otvet.length();
                        i = 0;
                        nomer = 0;
                        while (i < ll) {
                            c = otvet.charAt(i++);
                            if ((char) c != ';') {
                                string += (char) c;
                            } else {
                                if (nomer % 5 == 0) {
                                    string = "";
                                }
                                if (nomer % 5 == 1) {
                                    cs.topics[cs.ntopics] = string;
                                    string = "";
                                }
                                if (nomer % 5 == 2) {
                                    string1 = string;

                                    string = "";
                                }
                                if (nomer % 5 == 3) {
                                    string2 = string;
                       //             maxcount[cs.ntopics] = (int) Double.parseDouble(string);
                       //             if (maxcount[cs.ntopics] == 0) {
                       //                 maxcount[cs.ntopics] = 1;
                       //             }
                                    string = "";
                       //             average[cs.ntopics] = (double) cs.ocenka[cs.ntopics] / maxcount[cs.ntopics];
                       //             for (int ii = 0; ii < cs.ntopics; ++ii) {
                       //                 if (cs.topics[ii].equals(cs.topics[cs.ntopics])) {
                       //                     nnn = 0;
                       //                     if (average[ii] < average[cs.ntopics])
                       //                         average[ii] = average[cs.ntopics];
                       //                 }
                       //             }
                       //             cs.ntopics += nnn;
                       //             nnn = 1;
                                }
                                if (nomer % 5 == 4) {
                                    battleRegime = (int) Double.parseDouble(string);
                                    string = "";
                                    if(battleRegime == cs.battleRegime){
                                        cs.ocenka[cs.ntopics] = (int) Double.parseDouble(string1);
                                        maxcount[cs.ntopics] = (int) Double.parseDouble(string2);
                                        if (maxcount[cs.ntopics] == 0) {
                                            maxcount[cs.ntopics] = 1;
                                        }
                                        string = "";
                                        average[cs.ntopics] = (double) cs.ocenka[cs.ntopics] / maxcount[cs.ntopics];
                                        for (int ii = 0; ii < cs.ntopics; ++ii) {
                                            if (cs.topics[ii].equals(cs.topics[cs.ntopics])) {
                                                nnn = 0;
                                                if (average[ii] < average[cs.ntopics])
                                                    average[ii] = average[cs.ntopics];
                                            }

                                        }
                                        cs.ntopics += nnn;
                                        nnn = 1;
                                    }
                                }
                                nomer++;
                            }
                        }
                        comp.addPString("Тема,                                            оценка,    однотипных", cs.rightTextLocation1(cs.stroka++,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        total=0;
                        averageTotal = 0.;
                        for (i = 0; i < cs.ntopics; ++i) {
                            comp.addPString(cs.topics[i], cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString(String.format("%3d  %10d", cs.ocenka[i], maxcount[i]), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            total += cs.ocenka[i];
                            averageTotal += average[i];
                        }
                        comp.addPString("Итого", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        comp.addPString(String.format("%d", total), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        comp.addPString("Средний бал", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        comp.addPString(String.format("%5.2f", (float) averageTotal), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                    }
                }
                else {
                    message1.setText("Вам нужно авторизоваться и");
                    message2.setText("войти в режим баттла");
                    messageBox.setVisible(true);
                }
            }
        });

        JMenuItem menuBolelschik = new JMenuItem("Режим болельщика");
        menuOptions.add(menuBolelschik);
        menuBolelschik.setFont(cs.fontSmall);
        menuBolelschik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = "";
                String string1 = "";
                String string2 = "";
                String totalName1 ="";
                String totalName2 ="";
                cs.stroka=0.5;
                String otvet = "";

                int i=0, nomer=0, nnn=1,total=0;
                int c;
                int ll;
                int battleRegime=0;
                String login1 = "";
                String login2 = "";
                int[] maxcount = new int[20];
                double[] average = new double[20];
                double averageTotal = 0.;
                cs.SubTopic = 0;
                comp.clearAll();
                message1.setText("Выберите сначала одного, затем другого");
                message2.setText("участников действующего баттла");
                messageBox.setVisible(true);

                login1 = viborUser(cs.login,false,false,1);
                totalName1 = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                battleRegime = (int) Double.parseDouble(viborUser(login1,true,false,0));
                totalName2 = regRecord[0] + " " + regRecord[1] + " " + regRecord[2];
                login2=regRecord[3];

                if(battleRegime>0){
                    comp.addPString("Баттл", cs.rightTextLocation1(0.5,-cs.fxp/2.), cs.fontBold);
                    comp.addPString(totalName2, cs.rightTextLocation1(1.5,-cs.fxp*cs.sdvigVlevo), cs.font);
                    comp.addPString(totalName1, cs.rightTextLocation1(1.5,0.), cs.font);

                    for(int k=0;k<2;++k) {
                        cs.stroka = 2.5;
                        if(k==0) {
                            otvet = sD.doPost(cs.adresURL + "Result", "login=" + login1);
                        }
                        else{
                            otvet = sD.doPost(cs.adresURL + "Result", "login=" + login2);
                        }
                        cs.ntopics = 0;

                        ll = otvet.length();
                        i = 0;
                        nomer = 0;
                        while (i < ll) {
                            c = otvet.charAt(i++);
                            if ((char) c != ';') {
                                string += (char) c;
                            } else {
                                if (nomer % 5 == 0) {
                                    string = "";
                                }
                                if (nomer % 5 == 1) {
                                    cs.topics[cs.ntopics] = string;
                                    string = "";
                                }
                                if (nomer % 5 == 2) {
                                    string1 = string;

                                    string = "";
                                }
                                if (nomer % 5 == 3) {
                                    string2 = string;

                                    string = "";

                                }
                                if (nomer % 5 == 4) {
                                 //   battleRegime = (int) Double.parseDouble(string);

                                    if(battleRegime == (int)Double.parseDouble(string)){
                                        cs.ocenka[cs.ntopics] = (int)Double.parseDouble(string1);
                                        maxcount[cs.ntopics] = (int)Double.parseDouble(string2);
                                        if (maxcount[cs.ntopics] == 0) {
                                            maxcount[cs.ntopics] = 1;
                                        }
                                        string = "";
                                        average[cs.ntopics] = (double) cs.ocenka[cs.ntopics] / maxcount[cs.ntopics];
                                        for (int ii = 0; ii < cs.ntopics; ++ii) {
                                            if (cs.topics[ii].equals(cs.topics[cs.ntopics])) {
                                                nnn = 0;
                                                if (average[ii] < average[cs.ntopics])
                                                    average[ii] = average[cs.ntopics];
                                            }

                                        }
                                        cs.ntopics += nnn;
                                        nnn = 1;
                                    }
                                }
                                nomer++;
                            }
                        }
                        comp.addPString("Тема,                                            оценка,    однотипных", cs.rightTextLocation1(cs.stroka++,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        total=0;
                        averageTotal = 0.;
                        for (i = 0; i < cs.ntopics; ++i) {
                            comp.addPString(cs.topics[i], cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            comp.addPString(String.format("%3d  %10d", cs.ocenka[i], maxcount[i]), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                            total += cs.ocenka[i];
                            averageTotal += average[i];
                        }
                        comp.addPString("Итого", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        comp.addPString(String.format("%d", total), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        comp.addPString("Средний бал", cs.rightTextLocation1(cs.stroka,-cs.sdvigVlevo*k*cs.fxp), cs.font);
                        comp.addPString(String.format("%5.2f", (float) averageTotal), cs.rightTextLocation1(cs.stroka++, cs.rightSpace * 0.6-cs.sdvigVlevo*k*cs.fxp), cs.font);
                    }
                }
                else {
                    message1.setText("Вам нужно авторизоваться и");
                    message2.setText("войти в режим баттла");
                    messageBox.setVisible(true);
                }
            }
        });

        JDialog settings = new JDialog(frame,"Настройка",true);
        settings.setLocationRelativeTo(frame);
        settings.setLocation(cs.orx,cs.ory);
        settings.setSize((int)(600.*cs.width/1920),(int)(200.*cs.height/1080));
        settings.setLayout(new GridBagLayout());
        JLabel settingsLabel = new JLabel("Количество повторений однотипного упражнения:");
        JTextField settingsField = new JTextField(3);
        JButton settingsOK = new JButton("OK");
        settingsLabel.setFont(cs.font);
        settingsField.setFont(cs.font);
        settingsOK.setFont(cs.font);

        settings.add(settingsLabel, new GridBagConstraints(0, 0, 6, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        settings.add(settingsField, new GridBagConstraints(0, 1, 6, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));
        settings.add(settingsOK, new GridBagConstraints(0, 2, 6, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(2, 2, 2, 2), 0, 0));

        JMenuItem menuItemSettings = new JMenuItem("Количество однотипных");
        menuSettings.add(menuItemSettings);
        menuItemSettings.setFont(cs.fontSmall);
        menuItemSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsField.setText(String.format("%d",cs.maxCount));
                settings.setVisible(true);
            }
        });

        settingsOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.maxCount=(int)Double.parseDouble(settingsField.getText());
                settings.dispose();
            }
        });



        JMenuItem menuAdvises = new JMenuItem("О чём это ?");
        menuAdvises.setFont(cs.fontSmall);
        menuHelp.add(menuAdvises);
        menuAdvises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);

                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("В начале Семьнадцатого века великие Рене Декарт", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("и Галилео Галилей совершили открытие, которое", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("изменило всю дальнейшую судьбу Человечества и", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("запустило старт технического прогресса. Теперь", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нам трудно понять, что тут великого и трудного?", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Каждый школьник знает, что такое координаты", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("точки на плоскости или в пространстве. Но другой", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("великий математик древности, Евклид, в третьем", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("веке до Н. Э. не смог найти этого простого ключа", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("к пониманию устройства нашего пространства, того", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("пространства, в котором мы живем. Он пошел по", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("пути формулировок аксиом Евклида, пути, кото-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("рому было суждено остаться в стороне от прогрес-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("са науки. А путь Декарта и Галилея привел к по-", cs.rightTextLocation(cs.stroka++), cs.font);
                         //   comp.addPString("пути, которому было суждено остаться в стороне", cs.rightTextLocation(cs.stroka++), cs.font);


                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("ниманию, к глубинному истинному пониманию того,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("в каком пространстве мы живем. Путь Декарта и Га-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("лилея привел нас к автомобилям и компьютерам,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("к навигаторам, айфонам и андроидам.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Предлагаемая программа (или приложение, как те-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("перь принято говорить) ровно об этом. От темы к", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("теме мы пройдем начало этого пути, пройдем не", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("непрерывной линией, а пунктиром. Наша цель не", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("подготовка квалифицированных математиков-про-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("фессионалов, а приглашение любознательной моло-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("дежи к начальным сюжетам линейной алгебры, науки,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("которая возникла в результате открытия Декарта и", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Галилея. Мы полностью исключили даже простейшие", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("вычисления и логические рассуждения, предлага-", cs.rightTextLocation(cs.stroka++), cs.font);
                            //   comp.addPString("пути, которому было суждено остаться в стороне", cs.rightTextLocation(cs.stroka++), cs.font);


                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("емый продукт состоит из упражнений, основанных", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("исключительно на наглядно-геометрических сообра-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("жениях. ", cs.rightTextLocation(cs.stroka++), cs.font);

                            comp.addPString("Легкость упражнений нескольких первых тем не", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("должна никого вводить в заблуждение: трудность", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("упражнений быстро возрастает по мере продвиже-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ния, и некоторые упражнения ближе к концу неред-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ко ставят в тупик самих авторов.", cs.rightTextLocation(cs.stroka++), cs.font);



                            daNet.setVisible(true);
                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("Предлагаемый Вашему вниманию продукт состоит", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("из четырнадцати тем, названия этих тем Вы можете", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("видеть в той части меню, которая расположена под", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("позицией \"Темы\" менюБара, так принято называть", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("верхнюю строчку меню. Наш продукт не ограничива-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ет студента (так с Вашего позволения мы уважитель-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("но будем называть всех \"читателей\" или \"пользова-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("телей\" или \"юзеров\" нашего продукта) в выборе", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("последовательности прохождения тем, но рекомен-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("дуем проходить их в последовательности сверху вниз.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Слишком юным студентам, которые еще не знакомы", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("с записью чисел в виде десятичных дробей, мы не", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("рекомендуем пока нашего продукта, хотя первые", cs.rightTextLocation(cs.stroka++), cs.font);


                            daNet.setVisible(true);
                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("две темы могли бы их развлечь. Мы не рекомен-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("дуем нашего продукта тем, кому нужна помощь", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("в прохождении школьной программы по математике", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("или помощь в подготовке к вступительным экзаме-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нам в вуз по математике. Ни той ни другой задачи ", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("наш \"Мир линейности\" решить не может. Го-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("раздо легче сказать, кому данный продукт не ре-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("комендован, чем сказать, кому он рекомендован.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Этот продукт рекомендован любознательным лю-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("дям, которые хотят прикоснуться к одной из самых", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("серьезных и важных сторон математики, не изучая", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ее так основательно, как это делают профессиональ-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ные математики.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;
                        case 5:
                        cs.stroka = 0;
                        comp.clearAll();
                        comp.addCoordinateSysytem();
                        comp.coordsys = true;
                        comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                        comp.addPString("Вы можете зарегистрироваться и проходить темы", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("под своим именем, в этом случае программа будет", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("вести запись Ваших результатов, и Вы сможете эти", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("результаты в виде количества набранных баллов ви-", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("деть. При этом Вы можете возвращаться и проходить", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("любую тему повторно, в окончательной суммарной", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("оценке будет учтен Ваш максимальный результат", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("по каждой теме. Но регистрация и создание своего", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("эккаунта необязательны, Вы можете проходить темы", cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("в любой последовательности и без этого. Каждое ", cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("упражнение предлагается выполнить %d раз, каждый",cs.maxCount);
                        comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                        comp.addPString("раз с разными случайными данными. Ответ на каждое", cs.rightTextLocation(cs.stroka++), cs.font);


                        daNet.setVisible(true);
                        break;
                        case 6:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);

                            comp.addPString("задание оценивается от 0 до 3 баллов. Если Ваш", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ответ получил оценку менее 2 баллов (т.е. 0 или", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("1), то Вам будет предложено уточнить ответ того", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("же упражнения, такая неудачная попытка не будет", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("учтена.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("В настоящем разделе меню \"Для особо любозна-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("тельных\" мы в какой-то степени восполняем отсут-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ствие строгих определений и доказательств в ос-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("новной части продукта, на тот случай, если кто-то", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("из наших читателей испытает в этом потребность.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Самым лучшим и очень доступным во всех смыслах", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("слова изложением основ линейной алгебры является", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("книга И.М.Гельфанда \"Лекции по линейной алгебре\",", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("которую можно в виде PDF-файла бесплатно скачать", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("на многих книжных сайтах.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;
                        case 7:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);

                            comp.addPString("Если Ваш компьютер хотя бы изредка подключён к", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("интернету, то эккаунт, который Вы создадите при", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("регистрации, будет существовать и на нашем сер-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("вере в Институте новых технологий, и мы сможем", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("видеть Ваши результаты. Для нас это очень важно", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("и приятно, мы сможем связываться с Вами по элек-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("тронной почте или телефону.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Регистрация на нашем сервере возможна лишь при", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("наличии связи с интернетом. В дальнейшем, если", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Вы хотите, чтобы Ваши достижения при прохожде-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нии наших тем были записаны на серввере, Вы", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("должны авторизоваться, а наличие связи с интер-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нетом необязательна. Запись происходит при вы-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ходе из авторизации, при условии наличия связи.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;
                        case 8:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);


                            comp.addPString("Если связи нет, то Ваши достижения будут сохра-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нены локально и не будут потеряны. При каждом", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("выходе из авторизации, если  в этот момент", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("имеется связь с интернетом, будут переданы", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("на сервер все незаписанные ранее результаты.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;


                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 9);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpCoordinates = new JMenuItem("Координаты");
        menuHelpCoordinates.setFont(cs.fontSmall);
        menuHelp.add(menuHelpCoordinates);
        menuHelpCoordinates.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                StriFont[] strFont = new StriFont[10];
                do{
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("лежит понятие числовой прямой, т.е. прямой, на", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("которой зафиксированы точки \"0\" и \"1\". Не-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("которое интуитивное чувство, которое можно", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("назвать \"чувством линейности\", подсказывает", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нам, как расположены на этой прямой все осталь-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ные числа, точнее было бы сказать, все осталь-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ные вещественные числа. Точки прямой при этом", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("оказываются находящимися во взаимно-однозначном", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("соответствии с вещественными числами, т.е. с", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("числами, задаваемыми десятичными дробями, воз-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("можно, бесконечными. Точное понимание всех этих", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("утверждений необязательно, достаточно того, что", cs.rightTextLocation(cs.stroka++), cs.font);



                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("нормальному человеку экспериментально очевидно", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("понятие числовой прямой: деревянная линейка,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("какая почти всегда есть у школьника начальной", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("школы, с нанесенными сантиметрами и милиметрами", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("наглядно демонстрирует нам понятие числовой пря-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("мой.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Но вот более тонкий вопрос: как на числовой пря-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("мой определить сложение чисел? Пусть имеется", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("два числа, т.е. две точки на числовой прямой.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Как построить точку, которая соответствует сум-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ме этих двух чисел? Наилучшим образом это пост-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("роение делается следующим образом.", cs.rightTextLocation(cs.stroka++), cs.font);


                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            strFont[0] = new StriFont("Назовем ", "", cs.font);
                            strFont[1] = new StriFont("сдвигом ", "", cs.fontItalic);
                            strFont[2] = new StriFont("отображение прямой на себя, при", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("котором все ее точки сдвигаются на одинаковое", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("расстояние и в одну и ту же сторону. Вся прямая", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("при этом сдвигается, как твердая палка. Каждую", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("точку на числовой прямой отождествим со сдвигом,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("при котором нулевая точка переходит в эту точку.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Каждой точке соответствует сдвиг, но и обратно,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("каждому сдвигу соответствует точка, а именно та,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("точка, в которую при этом сдвиге переходит нуле-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("вая точка. Таким бразом мы установили взаимно-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("однозначное соответствие между точками на чис-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ловой прямой и ее сдвигами. Для сдвигов опера-", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("ция сложения определяется, как композиция: сум-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("мой двух сдвигов называется сдвиг, который по-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("лучается в результате последовательного выпол-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нения двух сдвигов одного за другим. Так опре-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("делённая операция сложения задаёт на числовой", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("структуру группы, важное понятие, к которому", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("мы не раз будем возвращаться. Вот строгое опре-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("деление.", cs.rightTextLocation(cs.stroka++), cs.font);

                        daNet.setVisible(true);
                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);

                     //       comp.addPString("деление.", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("Группой ", "", cs.fontItalic);
                            strFont[1] = new StriFont("называется мнжество ", "", cs.font);
                            strFont[2] = new StriFont("G ", "", cs.fontItalic);
                            strFont[3] = new StriFont("с заданной опе-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("рацией, называемой, обычно, умножением: ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont(string.format("a, b %s G, ab %s G, ",(char)1013,(char)1013), "", cs.fontItalic);
                            strFont[1] = new StriFont("для которой имеют место", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("следующие свойства:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("ассоциативность:  ", "", cs.font);
                            strFont[1] = new StriFont("(ab)c = a(bc);", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("существование единицы: элемент ", "", cs.font);
                            strFont[1] = new StriFont(string.format("e %s G,",(char)1013), "", cs.fontItalic);
                            strFont[2] = new StriFont("для которого", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);

                            //      strFont[0] = new StriFont("для которого  ", "", cs.font);
                            strFont[0] = new StriFont("ea = ae = a ", "", cs.fontItalic);
                            strFont[1] = new StriFont("для всех ", "", cs.font);
                            strFont[2] = new StriFont(String.format("a %s G;",(char)1013), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("существование обратного элемента ", "", cs.font);
                            strFont[1] = new StriFont("a", "", cs.fontItalic);
                            strFont[2] = new StriFont("-1 ", "", cs.fontSmall,-0.2);
                            strFont[3] = new StriFont("для любого ", "", cs.font);

                            sf.textLine(strFont, cs.stroka++, 4);

                            strFont[0] = new StriFont(String.format("a %s G: ",(char)1013), "", cs.fontItalic);
                            strFont[1] = new StriFont("a", "", cs.fontItalic);
                            strFont[2] = new StriFont("-1", "", cs.fontSmall,-0.2);
                            strFont[3] = new StriFont("a = ", "", cs.fontItalic);
                            strFont[4] = new StriFont("aa", "", cs.fontItalic);
                            strFont[5] = new StriFont("-1 ", "", cs.fontSmall,-0.2);
                            strFont[6] = new StriFont("= e.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 7);
                            comp.addPString("Числовая прямая является группой относительно", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("операции сложения, роль единицы играет ноль.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;

                        case 5:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addCircle(cs.orx+3*cs.mm, cs.ory - 2*cs.mm, 4, 4, Color.BLACK,8.f);
                            comp.addLine(cs.orx+3*cs.mm, cs.ory - 2*cs.mm,cs.orx, cs.ory - 2*cs.mm,Color.BLACK,0.5f);
                            comp.addLine(cs.orx+3*cs.mm, cs.ory - 2*cs.mm,cs.orx + 3*cs.mm, cs.ory,Color.BLACK,0.5f);
                            comp.addPString("Расмотрим две числовые прямые и расположим их на", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("экране компьютера, как это изображено на рисунке", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("слева. Эти две прямые называются осями координат:", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("горизонтальную ось принято называть осью ", "", cs.font);
                            strFont[1] = new StriFont("x, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("а вер-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);

                            strFont[0] = new StriFont("тикальную - осью ", "", cs.font);
                            strFont[1] = new StriFont("y., ", "", cs.fontItalic);
                            strFont[2] = new StriFont("Через любую точку на экране мы", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("можем провести две прямые: вертикальную и гори-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("зонтальную. Пересечения этих двух прямых с осями", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("координат определяют два числа, которые называют-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ся координатами точки. На рисунке слева представ-", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("лена точка с координатами ", "", cs.font);
                            strFont[1] = new StriFont("x = 3, y = 2.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);


                            daNet.setVisible(true);
                            break;


                    }

                }while(cs.SubTopic > -1 && cs.SubTopic < 6 );
                comp.clearAll();
                cs.titulList();
            }
        });


        JMenuItem menuHelpMatch = new JMenuItem("Точки, удовлетворяющие условиям");
        menuHelpMatch.setFont(cs.fontSmall);
        menuHelp.add(menuHelpMatch);
        menuHelpMatch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                int k=0;
                StriFont[] strFont = new StriFont[10];
                cs.rr = new Relation(2.,3.,6.,-2);
                Line line = cs.granica(cs.rr);
                int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            strFont[0] = new StriFont("Пусть ", "", cs.font);
                            strFont[1] = new StriFont("M - ", "", cs.fontItalic);
                            strFont[2] = new StriFont("произвольное множество, и пусть ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("D: M -----> {И, Л} -", "", cs.fontItalic);
                            strFont[1] = new StriFont("отображение этого множества", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("в множество ", "", cs.font);
                            strFont[1] = new StriFont("{И, Л}, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("состоящее всего из двух эле-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("ментов: \"истина\" и \"ложь\". Такое отображение", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("задает подмножество множества  ", "", cs.font);
                            strFont[1] = new StriFont("М, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("а именно ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("подмножество всех элементов ", "", cs.font);
                            strFont[1] = new StriFont(string.format("m %s М, ",(char)1013), "", cs.fontItalic);
                            strFont[2] = new StriFont("для которых", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("D(m) = И. ", "", cs.fontItalic);
                            strFont[1] = new StriFont("И обратно, если задано подмножество", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("N ", "", cs.fontItalic);
                            strFont[1] = new StriFont("множества ", "", cs.font);
                            strFont[2] = new StriFont("M, ", "", cs.fontItalic);
                            strFont[3] = new StriFont("то тем самым задано отображение ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("M -----> {И, Л}, ", "", cs.fontItalic);
                            strFont[1] = new StriFont("при котором элементы из ", "", cs.font);
                            strFont[2] = new StriFont("N ", "", cs.fontItalic);
                            strFont[3] = new StriFont("пере-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("ходят в ", "", cs.font);
                            strFont[1] = new StriFont("И, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("а остальные элементы в  ", "", cs.font);
                            strFont[3] = new StriFont("Л.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 4);

                         //   comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            strFont[0] = new StriFont("В нашем случае множество ", "", cs.font);
                            strFont[1] = new StriFont("M - ", "", cs.fontItalic);
                            strFont[2] = new StriFont("координатная плос-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("кость, или, если быть совсем точным, множество", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("точек экрана компьютера. В этом случае любое", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("уравнение или неравенство, в котором стороны за-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("висят от переменных ", "", cs.font);
                            strFont[1] = new StriFont("x, y, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("можно рассматривать, как", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("отображение плоскости в множество ", "", cs.font);
                            strFont[1] = new StriFont("{И, Л}. ", "", cs.fontItalic);
                            strFont[2] = new StriFont("В самом", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("деле, каждая точка координатной плоскости имеет", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("координаты  ", "", cs.font);
                            strFont[1] = new StriFont("x, y. ", "", cs.fontItalic);
                            strFont[2] = new StriFont("Подставим значения этих коорди-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("нат в уравнение или неравенство, после чего оно", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("приобретет одно из двух значений, либо \"истина\",", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("либо \"ложь\". В теме \"Точки, удовлетворяющие", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            comp.addPString("условиям\" Вы кликаете точки и получаете ответ:", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ИСТИНА или ЛОЖЬ: если ИСТИНА, Вы получаете", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("оценку, а если ЛОЖЬ, то штраф 2 балла.", cs.rightTextLocation(cs.stroka++), cs.font);


                            //   comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);

                            comp.addLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2(),Color.BLACK,2.f);

                            strFont[0] = new StriFont("Рассмотрим простой пример. Пусть задано неравен- ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("ство  ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y <= 6.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("Последовательность действий такова: ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("1. Вместо неравенства рассмотрим вначале уравнение", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("2x + 3y = 6.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("2. Заметим, что точка ", "", cs.font);
                            strFont[1] = new StriFont("x = 3, y = 0 ", "", cs.fontItalic);
                            strFont[2] = new StriFont("удовлетворяет это-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("му уравнению.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("3. Заметим также, что точка ", "", cs.font);
                            strFont[1] = new StriFont("x = 0, y = 2 ", "", cs.fontItalic);
                            strFont[2] = new StriFont("тоже удов-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("летворяет этому уравнению.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("4. Далее мы замечаем, что все точки прямой, прохо-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("дящей через эти две точки, тоже удовлетворяют это-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("му уравнению.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);

                            //   comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2(),Color.BLACK,2.f);

                            comp.addPString("В этом месте внимательный читатель спросит: а", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("можем ли мы доказать, что если две точки удо-", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("влетворяют уравнению  ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y = 6, ", "", cs.fontItalic);
                            strFont[2] = new StriFont(" ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);
                            comp.addPString("то и все точки прямой, проходящей через эти", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("две точки, тоже ему удовлетворяют? Ответ:", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нет, доказать этого мы не можем, т.к. для", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("доказательства этого нам нужно иметь опреде-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ление прямой, а его у нас нет. Собственно", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("одной из целей линейной геометрии как раз", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("и является поимание того, что такое прямая.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Но продолжим нашу последовательность дей-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ствий.", cs.rightTextLocation(cs.stroka++), cs.font);


                            //   comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2(),Color.BLACK,2.f);

                            comp.addPString("5. Заметим, что для всех точек плоскости,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("лежащих по одну сторону от нашей прямой,", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("имеет место неравенство ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y > 6, ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            comp.addPString("а для всех точек по другую сторону от", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("прямой имеет место неравенство ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y < 6, ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            comp.addPString("но как узнать, какое конкретно из этих двух", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("неравенств имеет место по ту сторону от пря-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("прямой, которая содержит начало координат?", cs.rightTextLocation(cs.stroka++), cs.font);

                            strFont[0] = new StriFont("Подставим точку ", "", cs.font);
                            strFont[1] = new StriFont("x = 0, y = 0 ", "", cs.fontItalic);
                            strFont[2] = new StriFont("в любое из двух", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("неравенств, и увидим, что в начале координат", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("выполняется неравенство ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y < 6, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("следовательно,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("множество точек, удовлетворяющих неравенству", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("2x + 3y <= 6, ", "", cs.fontItalic);
                            strFont[1] = new StriFont("таково, см. следующую страницу.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);

                            //   comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 5:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            cs.rr = new Relation(2.,3.,6.,-2);
                            comp.addLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2(),Color.BLACK,2.f);
                            for(int i=0;i<10000;i++) {
                                cs.p = cs.rand2(true);
                                cs.pp = new Point(cs.p.getX()/cs.mm - (double)cs.orx/cs.mm, -cs.p.getY()/cs.mm + (double)cs.ory/cs.mm);
                                if (cs.meet(cs.pp, cs.rr)) {
                                    if(cs.p.getX() > cs.orx - cs.lxm + 110 || cs.p.getY() > cs.ory -cs.lyp + 100)
                                        if(cs.p.getX()>cs.orx+shirina || cs.p.getX()<cs.orx || cs.p.getY()>cs.ory-cs.lyp+shirina)
                                            if(cs.p.getX()<cs.orx+cs.lxp-shirina || cs.p.getY()<cs.ory-shirina || cs.p.getY()>cs.ory)
                                                comp.addCircle(cs.p.getX(), cs.p.getY(), 5, 5, Color.BLUE,1.f);
                                }
                            }

                            comp.addPString("5. Заметим, что для всех точек плоскости,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("лежащих по одну сторону от нашей прямой,", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("имеет место неравенство ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y > 6, ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            comp.addPString("а для всех точек по другую сторону от", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("прямой имеет место неравенство ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y < 6, ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            comp.addPString("но как узнать, какое конкретно из этих двух", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("неравенств имеет место по ту сторону от пря-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("прямой, которая содержит начало координат?", cs.rightTextLocation(cs.stroka++), cs.font);

                            strFont[0] = new StriFont("Подставим точку ", "", cs.font);
                            strFont[1] = new StriFont("x = 0, y = 0 ", "", cs.fontItalic);
                            strFont[2] = new StriFont("в любое из двух", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("неравенств, и увидим, что в начале координат", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("выполняется неравенство ", "", cs.font);
                            strFont[1] = new StriFont("2x + 3y < 6, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("следовательно,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("множество точек, удовлетворяющих неравенству", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("2x + 3y <= 6, ", "", cs.fontItalic);
                            strFont[1] = new StriFont("таково, см. на картинке слева.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);

                            //   comp.addPString("В основе понятия координат точки на плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            daNet.setVisible(true);
                            break;




                        case 6:
                            cs.stroka = 0;
                            comp.clearAll();
                            k=20;
                            for(cs.stroka=0;cs.stroka<20;cs.stroka++)
                                for(int i=0;i<20;++i)
                                    comp.addPString(string.format("%d,%s",k,(char)k++), cs.rightTextLocation1(cs.stroka,-2.*cs.fxp+i*80.), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 7:
                            cs.stroka = 0;
                            comp.clearAll();
                            for(cs.stroka=0;cs.stroka<20;cs.stroka++)
                                for(int i=0;i<20;++i)
                                    comp.addPString(string.format("%d,%s",k,(char)k++), cs.rightTextLocation1(cs.stroka,-2.*cs.fxp+i*80.), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 8:
                            cs.stroka = 0;
                            comp.clearAll();
                            for(cs.stroka=0;cs.stroka<20;cs.stroka++)
                                for(int i=0;i<20;++i)
                                    comp.addPString(string.format("%d,%s",k,(char)k++), cs.rightTextLocation1(cs.stroka,-2.*cs.fxp+i*80.), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 9:
                            cs.stroka = 0;
                            comp.clearAll();
                            for(cs.stroka=0;cs.stroka<20;cs.stroka++)
                                for(int i=0;i<20;++i)
                                    comp.addPString(string.format("%d,%s",k,(char)k++), cs.rightTextLocation1(cs.stroka,-2.*cs.fxp+i*80.), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 10:
                            cs.stroka = 0;
                            comp.clearAll();
                            for(cs.stroka=0;cs.stroka<20;cs.stroka++)
                                for(int i=0;i<20;++i)
                                    comp.addPString(string.format("%d,%s",k,(char)k++), cs.rightTextLocation1(cs.stroka,-2.*cs.fxp+i*80.), cs.font);
                            daNet.setVisible(true);
                            break;
                        case 11:
                            cs.stroka = 0;
                            comp.clearAll();
                            for(cs.stroka=0;cs.stroka<20;cs.stroka++)
                                for(int i=0;i<20;++i)
                                    comp.addPString(string.format("%d,%s",k,(char)k++), cs.rightTextLocation1(cs.stroka,-2.*cs.fxp+i*80.), cs.font);
                            daNet.setVisible(true);
                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 12);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpSets = new JMenuItem("Множества");
        menuHelpSets.setFont(cs.fontSmall);
        menuHelp.add(menuHelpSets);
        menuHelpSets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                int k=0,i=0;
                StriFont[] strFont = new StriFont[10];
                cs.rr = new Relation(2.,3.,6.,-2);
                Line line = cs.granica(cs.rr);
                int shirina = 30;
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("Понятие множества лежит в основе Математики,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("оно является первым понятием математики и его", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("невозможно определить строго. Но мы в данной", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("теме говорим не о множествах вообще, а лишь", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("о подмножествах координатной плоскости. Коор-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("динатную плоскость мы договорились обозначать", cs.rightTextLocation(cs.stroka++), cs.font);
                            i=0;
                            strFont[i++] = new StriFont("через ", "", cs.font);
                            strFont[i++] = new StriFont("R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(". Произвольное множество ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("M %s  R",(char)1017), "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("опре-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("деляет отображение ", "", cs.font);
                            strFont[i++] = new StriFont("R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("--------> ", "", cs.font);
                            strFont[i++] = new StriFont("{И, Л} ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("координат-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ной плоскости в множество, состоящее из двух", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("элементов: ", "", cs.font);
                            strFont[i++] = new StriFont("ИСТИНА ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и ", "", cs.font);
                            strFont[i++] = new StriFont("ЛОЖЬ, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("а именно: элемен-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ты из ", "", cs.font);
                            strFont[i++] = new StriFont("M ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("переходят в ", "", cs.font);
                            strFont[i++] = new StriFont("ИСТИНУ, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("а элементы, не из", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("M ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("переходят в ", "", cs.font);
                            strFont[i++] = new StriFont("ЛОЖЬ. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("И обратно: любое отобра-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("жение ", "", cs.font);
                            strFont[i++] = new StriFont("R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("--------> ", "", cs.font);
                            strFont[i++] = new StriFont("{И, Л} ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("определяет множество", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("на координатной плоскости. ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("Уравнения или неравенства, в которые входят сим-", cs.rightTextLocation(cs.stroka++), cs.font);
                            i=0;
                            strFont[i++] = new StriFont("волы ", "", cs.font);
                            strFont[i++] = new StriFont("x, y, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("являющиеся стандартными обозначения-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            comp.addPString("ми координат точки на координатной плоскости,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("определяют отображения координатной плоскости", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("в множество двух элементов, которые мы также", cs.rightTextLocation(cs.stroka++), cs.font);
                            i=0;
                            strFont[i++] = new StriFont("стандартно обозначаем символами ", "", cs.font);
                            strFont[i++] = new StriFont("И, Л  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и, следо-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            comp.addPString("вательно, задают подмножества координатной плос-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("кости.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);

                            break;
                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();

            }
        });

        JMenuItem menuHelpVectors = new JMenuItem("Векторы и векторные пространства");
        menuHelpVectors.setFont(cs.fontSmall);
        menuHelp.add(menuHelpVectors);
        menuHelpVectors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                StriFont[] strFont = new StriFont[25];
                int i=0;
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("Векторы и векторные пространства являются важ-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("нейшими понятиями математики. Самое главное это", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("то, что векторы можно складывать и умножать на", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("числа. Под числами в этом контексте мы понимаем", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("вещественные или рациональные числа. Можно также", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("иметь ввиду и комплексные числа для тех, кто зна-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ет, что это такое, но нельзя в данном контексте", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("под числами понимать целые или натуральные числа.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Для определения векторных пространств важно, что-", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("бы у каждого числа ", "", cs.font);
                            strFont[1] = new StriFont("с ", "", cs.fontItalic);
                            strFont[2] = new StriFont("было противоположное число ", "", cs.font);
                            strFont[3] = new StriFont("-с, ", "", cs.fontItalic);

                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("т.е. такое, которое в сумме с ", "", cs.font);
                            strFont[1] = new StriFont("с ", "", cs.fontItalic);
                            strFont[2] = new StriFont("дает ", "", cs.font);
                            strFont[3] = new StriFont("0, ", "", cs.fontItalic);
                            strFont[4] = new StriFont("а также, чтобы", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("у каждого ненулевого числа ", "", cs.font);
                            strFont[1] = new StriFont("с ", "", cs.fontItalic);
                            strFont[2] = new StriFont("было обратное число", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);

                            strFont[0] = new StriFont("1/с, ", "", cs.fontItalic);
                            strFont[1] = new StriFont("т.е такое, которое в произведении с ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("с ", "", cs.fontItalic);
                            strFont[1] = new StriFont("даёт единицу ", "", cs.font);
                            strFont[2] = new StriFont("1.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 3);




                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("Такие множества \"чисел\", в которых можно вычи-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("тать и делить на любой ненулевой элемент, назы-", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("ваются ", "", cs.font);
                            strFont[1] = new StriFont("полями. ", "", cs.fontItalic);
                            strFont[2] = new StriFont("Вещественные, рациональные и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("комплексные числа являются полями, а целые и", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("вещественные неотрицательные числа полями не", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("являются. ", "", cs.font);
                            strFont[1] = new StriFont("Векторные пространства ", "", cs.fontItalic);
                            strFont[2] = new StriFont("определяются", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("над произвольными полями, как множества с двумя", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("операциями: сложения и умножения на элемент поля.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Для полной строгости необходимо еще перечислить", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("требования (аксиомы), которым эти операции дол-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("жны удовлетворять. Мы не приводим здесь всех", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("этих аксиом, дотошный читатель, легко найдет их", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("во многих учебниках.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);

                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            comp.addPString("Идеальным и простейшим примером векторного прос-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("транства является координатная плоскость, наглядно", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("представленная нам в виде экрана компьютера. Дру-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("гим (а точнее тем же самым) примером является мно-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("жество пар чисел, записанных в виде столбца.", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Операции сложения и умножения на число лежат в", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("основе понятия ", "", cs.font);
                            strFont[1] = new StriFont("линейности, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("важнейшего понятия", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("математики и физики. Поэтому эти две операции", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("можно называть ", "", cs.font);
                            strFont[1] = new StriFont("операциями линейности. ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);

                            daNet.setVisible(true);

                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);

                            strFont[0] = new StriFont("Пусть  ", "", cs.font);
                            strFont[1] = new StriFont("V - ", "", cs.fontItalic);
                            strFont[2] = new StriFont("векторное пространство над полем ", "", cs.font);
                            strFont[3] = new StriFont("R.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("Функция  ", "", cs.font);
                            strFont[1] = new StriFont("f : V ------> R ", "", cs.fontItalic);
                            strFont[2] = new StriFont("называется ", "", cs.font);
                            strFont[3] = new StriFont("линейной, ", "", cs.fontItalic);
                            strFont[4] = new StriFont("если", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("для любых ", "", cs.font);
                            strFont[1] = new StriFont(String.format("a, b %s V ",(char)1013), "", cs.fontItalic);
                            strFont[2] = new StriFont("и любого  ", "", cs.font);
                            strFont[3] = new StriFont(String.format("r %s R ",(char)1013), "", cs.fontItalic);
                            strFont[4] = new StriFont("имеют место ра-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("венства ", "", cs.font);
                            strFont[1] = new StriFont("f(a + b) = f(a) + f(b) ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("f(ra) = rf(a). ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("Пусть  ", "", cs.font);
                            strFont[1] = new StriFont("V - ", "", cs.fontItalic);
                            strFont[2] = new StriFont("координатная плоскость, ", "", cs.font);
                            strFont[3] = new StriFont("R - ", "", cs.fontItalic);
                            strFont[4] = new StriFont("поле ве-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("щественных чисел и пусть ", "", cs.font);
                            strFont[1] = new StriFont("f : V ------> R -", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("линейная функция. Произвольный вектор ", "", cs.font);
                            strFont[1] = new StriFont(String.format("a %s V",(char)1013), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("в координатной форме имеет вид ", "", cs.font);
                            strFont[i++] = new StriFont("a = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("a", "a", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "2", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("=", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("a", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("* ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("+ a", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("* ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont("). ", "", cs.fontBig);
                            strFont[i++] = new StriFont("В силу линейности", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("f(a) = f(a", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("* ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("+ a", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("* ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(") = a", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("*f ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("+ a", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("*f ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(").", "", cs.fontBig);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);

                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            strFont[0] = new StriFont("Пара чисел ", "", cs.font);
                            strFont[1] = new StriFont("f ", "", cs.fontItalic);
                            strFont[2] = new StriFont("(", "", cs.fontBig);
                            strFont[3] = new StriFont("1", "0", cs.fontItalic);
                            strFont[4] = new StriFont("), ", "", cs.fontBig);
                            strFont[5] = new StriFont("f ", "", cs.fontItalic);
                            strFont[6] = new StriFont("(", "", cs.fontBig);
                            strFont[7] = new StriFont("0", "1", cs.fontItalic);
                            strFont[8] = new StriFont(") ", "", cs.fontBig);
                            strFont[9] = new StriFont("называется координатами", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 10);
                            cs.stroka+=0.5;
                            strFont[0] = new StriFont("линейной функции ", "", cs.font);
                            strFont[1] = new StriFont("f. ", "", cs.fontItalic);
                            strFont[2] = new StriFont("В отличие от координат вектора,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("которые принято записывать в виде столбца, координаты", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("линейной функции принято записывать в виде строки.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("Применение линейной функции ", "", cs.font);
                            strFont[1] = new StriFont("f  ", "", cs.fontItalic);
                            strFont[2] = new StriFont("к вектору ", "", cs.font);
                            strFont[3] = new StriFont("a, ", "", cs.fontItalic);
                            strFont[4] = new StriFont("а именно", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            cs.stroka+=0.5;
                            strFont[0] = new StriFont("f(a), ", "", cs.fontItalic);
                            strFont[1] = new StriFont("совпадает с произведением ", "", cs.font);
                            strFont[2] = new StriFont("(f", "", cs.fontItalic);
                            strFont[3] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[4] = new StriFont(", f", "", cs.fontItalic);
                            strFont[5] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[6] = new StriFont(")  * ", "", cs.fontItalic);
                            strFont[7] = new StriFont("(", "", cs.fontBig);
                            strFont[8] = new StriFont("a", "a", cs.fontItalic);
                            strFont[9] = new StriFont("1", "2", cs.fontSmall,0.2);
                            strFont[10] = new StriFont(") ", "", cs.fontBig);
                            strFont[11] = new StriFont("=", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 12);
                            cs.stroka++;
                            strFont[0] = new StriFont("= f", "", cs.fontItalic);
                            strFont[1] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[2] = new StriFont("a", "", cs.fontItalic);
                            strFont[3] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[4] = new StriFont("+  f", "", cs.fontItalic);
                            strFont[5] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[6] = new StriFont("a", "", cs.fontItalic);
                            strFont[7] = new StriFont("2 ", "", cs.fontSmall,0.2);

                            strFont[8] = new StriFont("строки  ", "", cs.font);
                            strFont[9] = new StriFont("(f", "", cs.fontItalic);
                            strFont[10] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[11] = new StriFont(", f", "", cs.fontItalic);
                            strFont[12] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[13] = new StriFont(")  ", "", cs.fontItalic);
                            strFont[14] = new StriFont("координат функции ", "", cs.font);
                            strFont[15] = new StriFont("f ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 16);
                            cs.stroka+=0.5;
                            strFont[0] = new StriFont("на столбец ", "", cs.font);
                            strFont[1] = new StriFont("(", "", cs.fontBig);
                            strFont[2] = new StriFont("a", "a", cs.fontItalic);
                            strFont[3] = new StriFont("1", "2", cs.fontSmall,0.2);
                            strFont[4] = new StriFont(") ", "", cs.fontBig);
                            strFont[5] = new StriFont("координат вектора  ", "", cs.font);
                            strFont[6] = new StriFont("а. ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 7);
                            cs.stroka++;
                            strFont[0] = new StriFont("Произведение строки на столбец нам еще не раз ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("будет встречаться в дальнейшем, особенно, когда ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("мы будем иметь дело с линейными операторами и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("их матрицами.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            daNet.setVisible(true);

                            break;
                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 5);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpLinearCombinations = new JMenuItem("Линейные комбинации");
        menuHelpLinearCombinations.setFont(cs.fontSmall);
        menuHelp.add(menuHelpLinearCombinations);
        menuHelpLinearCombinations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                int i=0;
                StriFont[] strFont = new StriFont[20];
         //       cs.rr = new Relation(2.,3.,6.,-2);
         //       Line line = cs.granica(cs.rr);
         //       int shirina = 30;
                do{
                    switch(cs.SubTopic){
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            Matrix  m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"");
                            double nna = Math.random()*4. - 2.;
                            double nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "");

                            comp.addPString("Операции сложения и умножения на числа векторов", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("позволяют выражать одни векторы через другие, та-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("кие выражения, в которых используются только опе-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("рации сложения и умножения на числа, называются", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("линейными комбинациями. На координатной плоско-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("сти любой вектор может быть представлен в виде", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("линейной комбинации двух базисных векторов:", cs.rightTextLocation(cs.stroka++), cs.font);
                            cs.stroka+=0.5;

                            strFont[0] = new StriFont("u = ", "", cs.fontItalic);
                            strFont[1] = new StriFont("(", "", cs.fontBig);
                            strFont[2] = new StriFont("1", "0", cs.fontItalic);
                            strFont[3] = new StriFont("),   ", "", cs.fontBig);
                            strFont[4] = new StriFont("v = ", "", cs.fontItalic);
                            strFont[5] = new StriFont("(", "", cs.fontBig);
                            strFont[6] = new StriFont("0", "1", cs.fontItalic);
                            strFont[7] = new StriFont("). ", "", cs.fontBig);
                            strFont[8] = new StriFont("В самом деле, любой стол-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 9);
                            cs.stroka++;
                            strFont[0] = new StriFont("бец  ", "", cs.font);
                            strFont[1] = new StriFont("(", "", cs.fontBig);
                            strFont[2] = new StriFont("a", "b", cs.fontItalic);
                            strFont[3] = new StriFont(")  ", "", cs.fontBig);
                            strFont[4] = new StriFont("может быть представлен в виде", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            cs.stroka++;
                            strFont[0] = new StriFont("(", "", cs.fontBig);
                            strFont[1] = new StriFont("a", "b", cs.fontItalic);
                            strFont[2] = new StriFont(")  ", "", cs.fontBig);
                            strFont[3] = new StriFont("=   a", "", cs.fontItalic);
                            strFont[4] = new StriFont("(", "", cs.fontBig);
                            strFont[5] = new StriFont("1", "0", cs.fontItalic);
                            strFont[6] = new StriFont(")  ", "", cs.fontBig);
                            strFont[7] = new StriFont("+   b", "", cs.fontItalic);
                            strFont[8] = new StriFont("(", "", cs.fontBig);
                            strFont[9] = new StriFont("0", "1", cs.fontItalic);
                            strFont[10] = new StriFont(") ", "", cs.fontBig);
                            strFont[11] = new StriFont("=  au + bv", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 12);


                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"u");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"v");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");

                          //  comp.addPString("Вопрос: ", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("Пусть заданы два произвольных вектора ", "", cs.font);
                            strFont[1] = new StriFont("u ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("v. ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("Можно ли произвольный вектор ", "", cs.font);
                            strFont[1] = new StriFont("c ", "", cs.fontItalic);
                            strFont[2] = new StriFont("представить в", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("виде линейной комбинации ", "", cs.font);
                            strFont[1] = new StriFont("c = au + bv ? ", "", cs.fontItalic);
                            strFont[2] = new StriFont("Ответ:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("не всегда. Остаётся выяснить, когда это можно?", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("Два вектора ", "", cs.font);
                            strFont[1] = new StriFont("u ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("v ", "", cs.fontItalic);
                            strFont[4] = new StriFont("называются линейно независи-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("мыми, если их линейная комбинация ", "", cs.font);
                            strFont[1] = new StriFont("au + bv ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("может быть равна нулю ", "", cs.font);
                            strFont[1] = new StriFont("au + bv = 0 ", "", cs.fontItalic);
                            strFont[2] = new StriFont("только при", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("a = 0 ", "", cs.fontItalic);
                            strFont[1] = new StriFont("и ", "", cs.font);
                            strFont[2] = new StriFont("b = 0.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 3);
                            comp.addPString("С наглядно-геометрической точки зрения два век-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("тора линейно независимы, если они не лежат на од-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ной прямой. Если векторы заданы в координатах:", cs.rightTextLocation(cs.stroka++), cs.font);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("u = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("u", "u", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "2", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("),  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("v = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("v", "v", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "2", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  то векторы ", "", cs.font);
                            strFont[i++] = new StriFont("u  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и  ", "", cs.font);
                            strFont[i++] = new StriFont("v  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("линейно", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            strFont[0] = new StriFont("независимы тогда и только тогда, когда ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;

                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("- u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("!= 0", "", cs.fontItalic);

                            sf.textLine(strFont, cs.stroka, i++);



                            daNet.setVisible(true);

                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"u");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"v");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");

                            //  comp.addPString("Вопрос: ", cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("Если векторы ", "", cs.font);
                            strFont[1] = new StriFont("u ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("v  ", "", cs.fontItalic);
                            strFont[4] = new StriFont("линейно независимы, то лю-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("бой вектор ", "", cs.font);
                            strFont[1] = new StriFont("c ", "", cs.fontItalic);
                            strFont[2] = new StriFont("можно представить в виде линейной", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("комбинации ", "", cs.font);
                            strFont[1] = new StriFont("c = au + bv ", "", cs.fontItalic);
                            strFont[2] = new StriFont("векторов ", "", cs.font);
                            strFont[3] = new StriFont("u ", "", cs.fontItalic);
                            strFont[4] = new StriFont("и ", "", cs.font);
                            strFont[5] = new StriFont("v.  ", "", cs.fontItalic);
                            strFont[6] = new StriFont("Мы дока-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 7);
                            strFont[0] = new StriFont("жем это в комментариях к следующей теме.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);


                            daNet.setVisible(true);

                            break;
                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 3);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpSystems = new JMenuItem("Системы линейных уравнений");
        menuHelpSystems.setFont(cs.fontSmall);
        menuHelp.add(menuHelpSystems);
        menuHelpSystems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                int i=0;
                StriFont[] strFont = new StriFont[20];
             //   cs.rr = new Relation(2.,3.,6.,-2);
             //   Line line = cs.granica(cs.rr);
             //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            Matrix  m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            double nna = Math.random()*4. - 2.;
                            double nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");
                            strFont[0] = new StriFont("Задача представления вектора ", "", cs.font);
                            strFont[1] = new StriFont("c ", "", cs.fontItalic);
                            strFont[2] = new StriFont("в виде линейной", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("комбинации векторов ", "", cs.font);
                            strFont[1] = new StriFont("a ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("b ", "", cs.fontItalic);
                            strFont[4] = new StriFont("в координатной форме", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("имеет вид системы двух уравнений", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            cs.stroka+=0.5;
                            strFont[0] = new StriFont("(", "", cs.fontBig);
                            strFont[1] = new StriFont(String.format("%.1f",m.getA().getX()), String.format("%.1f",m.getA().getY()), cs.fontItalic);
                            strFont[2] = new StriFont(")", "", cs.fontBig);
                            strFont[3] = new StriFont("*u + ", "", cs.fontItalic);
                            strFont[4] = new StriFont("(", "", cs.fontBig);
                            strFont[5] = new StriFont(String.format("%.1f",m.getB().getX()), String.format("%.1f",m.getB().getY()), cs.fontItalic);
                            strFont[6] = new StriFont(")", "", cs.fontBig);
                            strFont[7] = new StriFont("*v = ", "", cs.font);
                            strFont[8] = new StriFont("(", "", cs.fontBig);
                            strFont[9] = new StriFont(String.format("%.1f",m.getA().getX()*nna+m.getB().getX()*nnb), String.format("%.1f",m.getA().getY()*nna+m.getB().getY()*nnb), cs.fontItalic);
                            strFont[10] = new StriFont(") ", "", cs.fontBig);
                            strFont[11] = new StriFont(",", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 12);
                            cs.stroka+=0.5;
                            strFont[0] = new StriFont("в которой буквами ", "", cs.font);
                            strFont[1] = new StriFont("u ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("v ", "", cs.fontItalic);
                            strFont[4] = new StriFont("обозначены неизвестные.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("В более привычном для школьников виде эта", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("система двух линейных уравнений с двумя неиз-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("вестными записывается так:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            if(m.getB().getX()>-0.05)
                                strFont[0] = new StriFont(String.format("%.1fu + %.1fv = %.1f",m.getA().getX(),m.getB().getX(),m.getA().getX()*nna+m.getB().getX()*nnb), "", cs.fontItalic);
                            else
                                strFont[0] = new StriFont(String.format("%.1fu - %.1fv = %.1f",m.getA().getX(),-m.getB().getX(),m.getA().getX()*nna+m.getB().getX()*nnb), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 1);
                            if(m.getB().getY()>-0.05)
                                strFont[0] = new StriFont(String.format("%.1fu + %.1fv = %.1f",m.getA().getY(),m.getB().getY(),m.getA().getY()*nna+m.getB().getY()*nnb), "", cs.fontItalic);
                            else
                                strFont[0] = new StriFont(String.format("%.1fu - %.1fv = %.1f",m.getA().getY(),-m.getB().getY(),m.getA().getY()*nna+m.getB().getY()*nnb), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("Всегда ли существует решение этой системы. Дру-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("гими словами, всегда ли существуют такие два числа,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("которые будучи подставленными в систему вместо", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("символов ", "", cs.font);
                            strFont[1] = new StriFont("u ", "", cs.fontItalic);
                            strFont[2] = new StriFont("и ", "", cs.font);
                            strFont[3] = new StriFont("v, ", "", cs.fontItalic);
                            strFont[4] = new StriFont("превратят ее в два верных равенства?", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 5);
                            strFont[0] = new StriFont("Ответ на этот вопрос дан на следующей странице.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);

                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");
                            strFont[0] = new StriFont("Обозначим коэффициенты системы через ", "", cs.font);
                            strFont[1] = new StriFont("m", "", cs.fontItalic);
                            strFont[2] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[3] = new StriFont(", m", "", cs.fontItalic);
                            strFont[4] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[5] = new StriFont(",", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, 6);
                            strFont[0] = new StriFont("m", "", cs.fontItalic);
                            strFont[1] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[2] = new StriFont(", m", "", cs.fontItalic);
                            strFont[3] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[4] = new StriFont(", а правые части через ", "", cs.font);
                            strFont[5] = new StriFont("b", "", cs.fontItalic);
                            strFont[6] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[7] = new StriFont("и ", "", cs.font);
                            strFont[8] = new StriFont("b", "", cs.fontItalic);
                            strFont[9] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[10] = new StriFont(". ", "", cs.fontItalic);
                            strFont[11] = new StriFont("Тогда система", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 12);
                            strFont[0] = new StriFont("будет иметь вид:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                      //      cs.stroka+=0.5;
                            strFont[0] = new StriFont("m", "", cs.fontItalic);
                            strFont[1] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[2] = new StriFont("u + m", "", cs.fontItalic);
                            strFont[3] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[4] = new StriFont("v = ", "", cs.fontItalic);
                            strFont[5] = new StriFont("b", "", cs.fontItalic);
                            strFont[6] = new StriFont("1", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, 7);
                            strFont[0] = new StriFont("m", "", cs.fontItalic);
                            strFont[1] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[2] = new StriFont("u + m", "", cs.fontItalic);
                            strFont[3] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[4] = new StriFont("v = ", "", cs.fontItalic);
                            strFont[5] = new StriFont("b", "", cs.fontItalic);
                            strFont[6] = new StriFont("2", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, 7);
                     //       cs.stroka+=0.5;
                            strFont[0] = new StriFont("Число ", "", cs.font);
                            strFont[1] = new StriFont("d = m", "", cs.fontItalic);
                            strFont[2] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[3] = new StriFont("m", "", cs.fontItalic);
                            strFont[4] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[5] = new StriFont("- m", "", cs.fontItalic);
                            strFont[6] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[7] = new StriFont("m", "", cs.fontItalic);
                            strFont[8] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[9] = new StriFont("называется определи-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 10);
                            strFont[0] = new StriFont("телем системы. Система имеет решение и это ре-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("шение единственно тогда и только тогда, когда", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("определитель системы не равен нулю.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("Доказательство.", "", cs.fontItalic);
                            strFont[1] = new StriFont("Пусть ", "", cs.font);
                            strFont[2] = new StriFont("d != 0 ", "", cs.fontItalic);
                            strFont[3] = new StriFont("(знак != обозначает", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("\"не равно\"). Умножим первое уравнение на ", "", cs.font);
                            strFont[1] = new StriFont("m", "", cs.fontItalic);
                            strFont[2] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[3] = new StriFont(",", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("второе уравнение на ", "", cs.font);
                            strFont[1] = new StriFont("m", "", cs.fontItalic);
                            strFont[2] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[3] = new StriFont("и вычтем одно уравнение", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 4);
                            strFont[0] = new StriFont("из другого:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("(m", "", cs.fontItalic);
                            strFont[1] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[2] = new StriFont("m", "", cs.fontItalic);
                            strFont[3] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[4] = new StriFont("- m", "", cs.fontItalic);
                            strFont[5] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[6] = new StriFont("m", "", cs.fontItalic);
                            strFont[7] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[8] = new StriFont(")u = ", "", cs.fontItalic);
                            strFont[9] = new StriFont("m", "", cs.fontItalic);
                            strFont[10] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[11] = new StriFont("b", "", cs.fontItalic);
                            strFont[12] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[13] = new StriFont("- m", "", cs.fontItalic);
                            strFont[14] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[15] = new StriFont("b", "", cs.fontItalic);
                            strFont[16] = new StriFont("2", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, 17);


                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");

                            strFont[0] = new StriFont("Выражение в скобках является определителем,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("который не равен нулю, поэтому, поделив пос-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("леднее уравнение на определитель, мы получаем", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("u. ", "", cs.fontItalic);
                            strFont[1] = new StriFont("Вернемся к первоначальной системе уравнений.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 2);
                            strFont[0] = new StriFont("Заметим, что из двух коэффициентов ", "", cs.font);
                            strFont[1] = new StriFont("m", "", cs.fontItalic);
                            strFont[2] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[3] = new StriFont("или ", "", cs.font);
                            strFont[4] = new StriFont("m", "", cs.fontItalic);
                            strFont[5] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[6] = new StriFont(",", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 7);
                            strFont[0] = new StriFont("стоящих при ", "", cs.font);
                            strFont[1] = new StriFont("v, ", "", cs.fontItalic);
                            strFont[2] = new StriFont("хотя бы один не равен нулю,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("в противном случае определитель был бы равен", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("нулю. Подставив уже найденное значение ", "", cs.font);
                            strFont[1] = new StriFont("u ", "", cs.fontItalic);
                            strFont[2] = new StriFont("в то", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("уравнение, в котором коэффициент при ", "", cs.font);
                            strFont[1] = new StriFont("v ", "", cs.fontItalic);
                            strFont[2] = new StriFont("не ра-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("вен нулю, находим ", "", cs.font);
                            strFont[1] = new StriFont("v. ", "", cs.fontItalic);
                            strFont[2] = new StriFont("Подставляя найденные значе-", "", cs.font);

                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("ния ", "", cs.font);
                            strFont[1] = new StriFont("u, v ", "", cs.fontItalic);
                            strFont[2] = new StriFont("в исходные уравнения, убеждаемся в том,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("что это решение системы.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("Докажем теперь единственность. Пусть имеется", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;
                            strFont[i++] = new StriFont("два решения системы ", "", cs.font);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("и  u", "", cs.font);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(". Это значит,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");


                            strFont[0] = new StriFont("что справедливы следующие четыре равенства:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("+ m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("+ m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("+ m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("+ m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Введем обозначения: ", "", cs.font);
                            strFont[i++] = new StriFont("u = u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("- u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("v = v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("- v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(".", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            strFont[0] = new StriFont("Вычтем из первого равенста третье, а из вто-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("рого четвертое, после простых преобразований", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("получим:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);

                            strFont[i++] = new StriFont("+ m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);

                            strFont[i++] = new StriFont("= 0", "", cs.fontItalic);

                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);

                            strFont[i++] = new StriFont("+ m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);

                            strFont[i++] = new StriFont("= 0", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Умножим первое равенство на ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", второе равен-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ство на ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("и вычтем одно равенство из другого:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("(m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("- m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(")u = 0", "", cs.fontItalic);

                            sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");



                            strFont[0] = new StriFont("Выражение в скобках является определителем", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("системы, который не равен нулю, следователь-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;
                            strFont[i++] = new StriFont("но ", "", cs.font);
                            strFont[i++] = new StriFont("u = 0, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("но ", "", cs.font);
                            strFont[i++] = new StriFont("u = u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("- u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("следовательно, ", "", cs.font);
                            strFont[i++] = new StriFont("u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= u", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(". ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Доказательство того, что ", "", cs.font);
                            strFont[i++] = new StriFont("v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= v", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("аналогично.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            strFont[0] = new StriFont("Докажем обратное утверждение, которое состоит", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("в том, что если определитель системы равен ну-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("лю, то система либо вообще не имеет решения,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("либо решение есть, но оно не единственно.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;
                            strFont[i++] = new StriFont("Пусть ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("- m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(" = 0. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Возможно, что все", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("четыре числа ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("равны нулю.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("В этом случае если хотя бы одно из чисел ", "", cs.font);
                            strFont[i++] = new StriFont("b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("не равно нулю, то система не имеет решения, а", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("если оба числа ", "", cs.font);
                            strFont[i++] = new StriFont("b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("равны нулю, то система имеет", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("бесконечное множество решений. Рассмотрим", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("теперь случай, когда хотя бы одно из чисел ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("не равно нулю. Пусть для определённости", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                        case 5:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            nna = Math.random()*4. - 2.;
                            nnb = Math.random()*4. - 2.;
                            cs.p = new Point(nna * m.apix().getX() + nnb * m.bpix().getX() - (nna + nnb - 1) * cs.orx, nna * m.apix().getY() + nnb * m.bpix().getY() - (nna + nnb - 1) * cs.ory);
                            comp.addMarkedArrow(cs.orx, cs.ory, (int) cs.p.getX(), (int) cs.p.getY(), Color.GREEN, 8.f, "c");



                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("!= 0. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Умножим первое уравнение на ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("/m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",","", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;

                            strFont[i++] = new StriFont("в результате получим уравнение", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u + ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("/m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v = m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("/m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(". Поскольку", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;

                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("/m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", то левая часть этого уравнения", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("совпадает с левой частью второго уравнения сис-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("темы: ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u + m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(". ", "", cs.font);
                            strFont[i++] = new StriFont("Если и правые части этих", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("двух уравнений совпадают, т.е. если ", "", cs.font);
                            strFont[i++] = new StriFont("m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("/m", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("1 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= b", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("то система имеет бесконечное множество решений,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("если же правые части не совпадают, то система не", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("имеет решения.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);



                            daNet.setVisible(true);
                            break;
                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 6);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpScalar = new JMenuItem("Скалярное произведение");
        menuHelpScalar.setFont(cs.fontSmall);
        menuHelp.add(menuHelpScalar);
        menuHelpScalar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/7.5);
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            Matrix  m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");
                            i=0;
                            strFont[i++] = new StriFont("Функция ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("двух переменных ", "", cs.font);
                            strFont[i++] = new StriFont("a, b, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("принадле-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            i=0;
                            strFont[i++] = new StriFont("жащих координатной плоскости ", "", cs.font);
                            strFont[i++] = new StriFont("R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("(так принято", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("обозначать координатную плоскость), называется ", "", cs.font);

                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("билинейной функцией, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("если она линейна по каждому", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("из двух аргументов при фиксированном втором.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Более подробно: функция ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("билинейна, если", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("для любых ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("a, b, с %s R",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("и любого ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%s %s R ",(char)945,(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("имеют место", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("равенства: ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("g(a + c, b) = g(a, b) + g(c, b)", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("g(a, b + c) = g(a, b) + g(a, c)", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("g(%sa, b) = %sg(a, b)",(char)945,(char)945), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("g(a, %sb) = %sg(a, b).",(char)945,(char)945), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Функция ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называется ", "", cs.font);
                            strFont[i++] = new StriFont("симметрической ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("если", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ее значение не изменяется при перестановке аргу-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ментов, т.е. ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) = g(b, a) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("при всех ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("a, b %s R",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(".", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);



                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");

                            i=0;
                            strFont[i++] = new StriFont("Симметрическую билинейную функцию ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);

                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("называют также ", "", cs.font);
                            strFont[i++] = new StriFont("квадратичной формой. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Квадра-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("тичная форма ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называется ", "", cs.font);
                            strFont[i++] = new StriFont("положительно", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("определённой ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("если ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, a) > 0 ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("для всех ненулевых", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("a %s R",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(". Положительно определенную квадратичную", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("форму ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называют ", "", cs.font);
                            strFont[i++] = new StriFont("скалярным произведением", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("или ", "", cs.font);
                            strFont[i++] = new StriFont("метрикой, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("в данном случае, на плоскости ", "", cs.font);
                            strFont[i++] = new StriFont("R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(".", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Пусть на координатной плоскости ", "", cs.font);
                            strFont[i++] = new StriFont("R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("задана мет-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("рика ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b). ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Введем обозначения: ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.9;
                            i=0;
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            m = cs.rand4(0.55);
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.apix().getX(), (int)m.apix().getY(), Color.RED, 8.f,"a");
                            comp.addMarkedArrow(cs.orx,cs.ory,(int)m.bpix().getX(), (int)m.bpix().getY(), Color.BLUE, 8.f,"b");

                            i=0;
                            strFont[i++] = new StriFont("В силу билинейности метрики ", "", cs.font);
                            strFont[i++] = new StriFont("g(a, b) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("числа ", "", cs.font);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(",", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("полностью определяют метрику, в", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("силу симметричности ", "", cs.font);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", в силу положи-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("тельной определённости ", "", cs.font);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("> 0, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("> 0. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Числа", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(", g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("принято записывать в виде таб-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("лицы:  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("g", "g", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("g", "g", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "22", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(", в таком виде они называ-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.9;
                            i=0;
                            strFont[i++] = new StriFont("ются ", "", cs.font);
                            strFont[i++] = new StriFont("матрицей  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("квадратичной формы или мет-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;

                            strFont[i++] = new StriFont("рики. Благодаря наличию твердых тел в реаль-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ном Мире он (реальный Мир) обладает метрикой.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Экран нашего компьютера является частью реаль-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ного Мира, поэтому он тоже обладает метрикой:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= 1, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= g", "", cs.fontItalic);
                            strFont[i++] = new StriFont("21 ", "", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= 0. ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;


                            daNet.setVisible(true);
                            break;
                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 3);
                comp.clearAll();
                cs.titulList();
            }
        });


        JMenuItem menuHelpLinearOperators = new JMenuItem("Линейные операторы");
        menuHelpLinearOperators.setFont(cs.fontSmall);
        menuHelp.add(menuHelpLinearOperators);
        menuHelpLinearOperators.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                cs.identity = new Matrix(new Point(1,0),new Point(0,1));
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            Flag flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));

                            i=0;
                            strFont[i++] = new StriFont("Отображение ", "", cs.font);
                            strFont[i++] = new StriFont("A : R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("------> R", "", cs.fontItalic);
                            strFont[i++] = new StriFont("2  ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("координатной ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("плоскости на себя называется ", "", cs.font);
                            strFont[i++] = new StriFont("линейным пре-", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("образованием  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("или ", "", cs.font);
                            strFont[i++] = new StriFont("линейным оператором, ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("если для любых двух векторов ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("a, b %s R",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("2  ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("и лю-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("бого числа ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%s  ",(char)945), "", cs.fontItalic);
                            strFont[i++] = new StriFont("справедливы равенства", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;

                            strFont[i++] = new StriFont("A(a + b) = A(a) + A(b),", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("A(%sa) = %sA(a).",(char)945,(char)945), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Линейный оператор однозачно определяется", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("своими значениями на базисных векторах.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Более точно: пусть заданы два произвольных", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("вектора ", "", cs.font);
                            strFont[i++] = new StriFont("a,  b.  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Существует единственный", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("линейный оператор ", "", cs.font);
                            strFont[i++] = new StriFont("A, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("для которого", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= a,  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= b,  ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));


                            i=0;

                            strFont[i++] = new StriFont("Доказательство. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Пусть  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("%s",(char)945), String.format("%s",(char)946), cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("произвольный", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("вектор, заданный в координатной форме. Этот", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("вектор представляется в виде линейной комби-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("нации базисных векторов следующим образом:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("%s",(char)945), String.format("%s",(char)946), cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("= %s",(char)945), "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("+ %s",(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(".  Далее, ", "", cs.font);
                            strFont[i++] = new StriFont("A", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("%s",(char)945), String.format("%s",(char)946), cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("=", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("= A", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format(" %s",(char)945), "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("+ %s",(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("= %sA",(char)945), "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("+ %sA",(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("в силу линейности оператора  ", "", cs.font);
                            strFont[i++] = new StriFont("A. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Последнее выра-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("жение равно  ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%sa + %sb. ",(char)945,(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("Тем самым доказано, что", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("A", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("%s",(char)945), String.format("%s",(char)946), cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%sa + %sb. ",(char)945,(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("Это равенство доказывает един-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("ственность линейного ", "", cs.font);
                            strFont[i++] = new StriFont("оператора  ", "", cs.font);
                            strFont[i++] = new StriFont("A, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("оно же может", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("быть принято за его определение и тем самым", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("доказывает его существование.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));



                            i=0;
                            strFont[i++] = new StriFont("Мы договорились называть ", "", cs.font);
                            strFont[i++] = new StriFont("флагом ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("треугольник", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("на координатной плоскости с одной вершиной в", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("начале координат и отмеченной черным кружоч-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ком одной из двух других вершин. Мы называем", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("единичным флагом ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("флаг с вершинами в точках", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(",  с отмеченной вершиной ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(".", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.7;
                            i=0;
                            strFont[i++] = new StriFont("На рисунке единичный флаг изображён желтым.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Каждому линейному оператору ", "", cs.font);
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("соответствует", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("флаг, а именно тот флаг, который является обра-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("зом единичного флага при действии оператора", "", cs.font);

                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("A, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и обратно, любой флаг на координатной плос-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("кости однозначно определяет линейный оператор,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("как мы только что доказали.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));


                            i=0;
                            strFont[i++] = new StriFont("В координатной форме флаг представляет собой", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("пару столбцов: первый столбец координат отме-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ченной вершины флага, второй столбец координат", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("неотмеченой ненулевой вершины. Эти два столбца", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.4;
                            i=0;
                            strFont[i++] = new StriFont("записываются в виде: ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("%.1f   %.1f", cs.white.getA().getX(), cs.white.getB().getX()), String.format("%.1f   %.1f", cs.white.getA().getY(), cs.white.getB().getY()), cs.fontItalic);
                            strFont[i++] = new StriFont("). ", "", cs.fontBig);
                            strFont[i++] = new StriFont("Это называ-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.7;
                            i=0;
                            strFont[i++] = new StriFont("ется ", "", cs.font);
                            strFont[i++] = new StriFont("матрицей ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("флага (в данном случае белого", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("флага на рисунке), а также ", "", cs.font);
                            strFont[i++] = new StriFont("матрицей ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("соответству-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ющего линейного оператора. Матрица, как и флаг,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("однозначно определяет линейный оператор. Имеет", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("место следующая явная формула. Пусть заданы", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("матрица  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("линейного оператора  ", "", cs.font);
                            strFont[i++] = new StriFont("А", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.9;
                            i=0;
                            strFont[i++] = new StriFont("и вектор ", "", cs.font);
                            strFont[i++] = new StriFont("a = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("u", "v", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("в координатной форме. Тогда", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp+ cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));

                            i=0;
                            strFont[i++] = new StriFont("A(a) = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "21", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u + ", "u + ", cs.fontItalic);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "v", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(". ", "", cs.font);
                            strFont[i++] = new StriFont("Последнее выражение", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.7;
                            i=0;
                            strFont[i++] = new StriFont("называют произведением матрицы на столбец", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("и записывают в виде  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "21", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("u + ", "u + ", cs.fontItalic);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("v", "v", cs.fontItalic);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka+=0.7;
                            i=0;
                            strFont[i++] = new StriFont("= ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("u", "v", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(". ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 5);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpExamples = new JMenuItem("Примеры");
        menuHelpExamples.setFont(cs.fontSmall);
        menuHelp.add(menuHelpExamples);
        menuHelpExamples.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                cs.identity = new Matrix(new Point(1,0),new Point(0,1));
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            Flag flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));

                            i=0;
                            strFont[i++] = new StriFont("Наиболее простые и естественные примеры", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("линейных преобразований - это отражения и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("повороты. Отражение относительно любой", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("прямой, проходящей через начало координат,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("является линейным преобразованием (кстати,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("попробуйте это доказать: неплохое упражне-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ние для тренировки). Поворот координатной", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("плоскости на произвольный угол тоже являет-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ся линейным преобразованием.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            i=0;
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall,0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("u", "v", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(". ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);



                            daNet.setVisible(true);
                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 1);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpGessMatrix = new JMenuItem("Определите матрицу");
        menuHelpGessMatrix.setFont(cs.fontSmall);
        menuHelp.add(menuHelpGessMatrix);
        menuHelpGessMatrix.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                cs.identity = new Matrix(new Point(1,0),new Point(0,1));
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            Flag flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));

                            daNet.setVisible(true);
                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 1);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpMultiplication = new JMenuItem("Умножение");
        menuHelpMultiplication.setFont(cs.fontSmall);
        menuHelp.add(menuHelpMultiplication);
        menuHelpMultiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 2;
                cs.ocenTopic = 0;
                cs.count = 0;
                int i=0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                Matrix black;
                StriFont[] strFont = new StriFont[30];
                do{

                    switch(cs.SubTopic){
                        case 0:
                            black = cs.rand4(1.);
                            cs.white = cs.rand4(1.);

                            //  cs.mDano = cs.rand4((double)cs.mm/cs.lxp);
                            //cs.count++;


                            cs.stroka = 1.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            //   cs.white = cs.mulMat(cs.mDano, black);

                            //   comp.addLine(cs.orx - cs.lym,cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp,Color.BLACK,3.f);
                            comp.drawFlag(comp.xxx[0]);
                            comp.drawFlag(new Flag(cs.white,Color.WHITE));


                      //      string = String.format("Композиция (т.е. последовательное выполнение)");
                            comp.addPString("Композиция (т.е. последовательное выполнение)", cs.rightTextLocation(cs.stroka++), cs.font);
                      //      string = String.format("двух линейных преобразований является опера-");
                            comp.addPString("двух линейных преобразований является опера-", cs.rightTextLocation(cs.stroka++), cs.font);
                      //      string = String.format("цией, относительно которой так называемые");
                            comp.addPString("цией, относительно которой так называемые", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("невырожденные линейные преобразования при-", cs.rightTextLocation(cs.stroka++), cs.font);

                            i=0;
                            strFont[i++] = new StriFont("обретают структуру ", "", cs.font);
                            strFont[i++] = new StriFont("группы. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Понятие  ", "", cs.font);
                            strFont[i++] = new StriFont("группы ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            comp.addPString("является важнейшим понятием математики. Оно", cs.rightTextLocation(cs.stroka++), cs.font);

                            i=0;
                            strFont[i++] = new StriFont("было придумано молодым французским матема-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("тиком ", "", cs.font);
                            strFont[i++] = new StriFont("Эваристом Галуа, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("которому это понятие", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            comp.addPString("потребовалось для доказательства невозможности", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("решения алгебраического уравнения 5 степени та-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("ким же способом, какой применяется для решения", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("уравнений 2, 3 и 4 степеней, т.е. при помощи фор-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("мул, содержащих радикалы. Галуа родился в", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("1811 году и был убит на дуэли 30 мая 1832 года", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("в возрасте всего 20 лет.", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;
                        case 1:
                            black = cs.rand4(1.);
                            cs.white = cs.rand4(1.);

                            //  cs.mDano = cs.rand4((double)cs.mm/cs.lxp);
                            //cs.count++;


                            cs.stroka = 1.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            //   cs.white = cs.mulMat(cs.mDano, black);

                            //   comp.addLine(cs.orx - cs.lym,cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp,Color.BLACK,3.f);
                            comp.drawFlag(comp.xxx[0]);
                            comp.drawFlag(new Flag(cs.white,Color.WHITE));


                            i=0;
                            strFont[i++] = new StriFont("Группой ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называется множество ", "", cs.font);
                            strFont[i++] = new StriFont("G, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("на котором", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("задана операция, обычно называемая ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("умноже-",(char)1013,(char)1013), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("нием: gh %s  G, g, h %s G. ",(char)1013,(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("При этом должны быть", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("выполнены следующие 3 условия:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("Ассоциативность: (gh)k = g(hk), g, h, k %s G,",(char)1013), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("Существование единицы: e %s G, ", (char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("для которой ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ge = eg = g ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("для любого ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("g %s G,", (char)1013), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Существование обратного: g ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("-1 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(String.format("%s G ", (char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("для любого ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("g %s G, ", (char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("это значит: ", "", cs.font);
                            strFont[i++] = new StriFont("gg ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("-1 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("= g ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("-1", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont("g = e.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            comp.addPString("Мы все хорошо знакомы с понятием группы,", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("хотя и не произносим этого слова, на при-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("мере чисел: целые числа образуют группу", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("относительно сложения, но не образуют", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("группу относительно умножения, а ненуле-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("вые вещественные или рациональные числа", cs.rightTextLocation(cs.stroka++), cs.font);

                            daNet.setVisible(true);
                            break;
                        case 2:
                            black = cs.rand4(1.);
                            cs.white = cs.rand4(1.);

                            //  cs.mDano = cs.rand4((double)cs.mm/cs.lxp);
                            //cs.count++;


                            cs.stroka = 1.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            //   cs.white = cs.mulMat(cs.mDano, black);

                            //   comp.addLine(cs.orx - cs.lym,cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp,Color.BLACK,3.f);
                            comp.drawFlag(comp.xxx[0]);
                            comp.drawFlag(new Flag(cs.white,Color.WHITE));


                            comp.addPString("образуют группу относительно умножения (но", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("не образуют группу относительно сложения).", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("Мы все знаем, как важно для нас то, что числа", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("можно складывать и умножать. В других груп-", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("пах операция умножения не менее важна, хоть", cs.rightTextLocation(cs.stroka++), cs.font);
                            comp.addPString("это не настолько общеизвестно.", cs.rightTextLocation(cs.stroka++), cs.font);

                            i=0;
                            strFont[i++] = new StriFont("Пусть ", "", cs.font);
                            strFont[i++] = new StriFont("G - ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("группа и ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("H %s G - ",(char)1017), "", cs.fontItalic);
                            strFont[i++] = new StriFont("подмножество.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Подмножество ", "", cs.font);
                            strFont[i++] = new StriFont("H ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называется подгруппой груп-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("пы ", "", cs.font);
                            strFont[i++] = new StriFont("G, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("если множество ", "", cs.font);
                            strFont[i++] = new StriFont("H ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("само является группой", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("относительно той же операции умножения.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Другими словами:  ", "", cs.font);
                            strFont[i++] = new StriFont("H ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называется подгруппой", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("группы ", "", cs.font);
                            strFont[i++] = new StriFont("G, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("если ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("e %s H ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("и для любых ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("g, h %s H ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("их", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("произведение и обратные также принадлежат ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("H:  gh %s H, g ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("-1 ", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(String.format("%s H.",(char)1013), "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);



                            daNet.setVisible(true);
                            break;
                        case 3:
                            black = cs.rand4(1.);
                            cs.white = cs.rand4(1.);

                            //  cs.mDano = cs.rand4((double)cs.mm/cs.lxp);
                            //cs.count++;


                            cs.stroka = 1.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            //   cs.white = cs.mulMat(cs.mDano, black);

                            //   comp.addLine(cs.orx - cs.lym,cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp,Color.BLACK,3.f);
                            comp.drawFlag(comp.xxx[0]);
                            comp.drawFlag(new Flag(cs.white,Color.WHITE));


                            i=0;
                            strFont[i++] = new StriFont("Пусть ", "", cs.font);
                            strFont[i++] = new StriFont("G, H - ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("две группы. Отображение", "", cs.font);

                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("f: G ------> H ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("называется ", "", cs.font);
                            strFont[i++] = new StriFont("гомоморфизмом", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("групп ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("если для любых ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("g, h %s G ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("имеют место", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("равенства  ", "", cs.font);
                            strFont[i++] = new StriFont("f(gh) = f(g)f(h),  f(g ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("-1", "", cs.fontSmall,-0.2);
                            strFont[i++] = new StriFont(") = f(g) ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("-1", "", cs.fontSmall,-0.2);
                            sf.textLine(strFont, cs.stroka++, i++);


                            comp.addPString("Вернемся к линейным преобразованиям.", cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Линейные преобразования, так же, как и соответ-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("ствующие им матрицы, можно перемножать, и это ");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("очень важно, нетривиально и интересно. Пусть име-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            i=0;
                            strFont[i++] = new StriFont("ются два линейных преобразования  ", "", cs.font);
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и ", "", cs.font);
                            strFont[i++] = new StriFont("B. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Их про-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("изведением называется линейное пеобразование ", "", cs.font);
                            strFont[i++] = new StriFont("AB,", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);

                            string = String.format("которое определяется, как последовательное выпол-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            i=0;
                            strFont[i++] = new StriFont("нение преобразований, сначала ", "", cs.font);
                            strFont[i++] = new StriFont("B, ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("а затем ", "", cs.font);
                            strFont[i++] = new StriFont("A: ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("AB(u) = A(B(u)). ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Порядок выполнения линейных", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;

                        case 4:
                            black = cs.rand4(1.);
                            cs.white = cs.rand4(1.);

                            //  cs.mDano = cs.rand4((double)cs.mm/cs.lxp);
                            //cs.count++;


                            cs.stroka = 1.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            //   cs.white = cs.mulMat(cs.mDano, black);

                            //   comp.addLine(cs.orx - cs.lym,cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp,Color.BLACK,3.f);
                            comp.drawFlag(comp.xxx[0]);
                            comp.drawFlag(new Flag(cs.white,Color.WHITE));




                            string = String.format("преобразований существен. Примером непереста-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("новочных линейных преобразований являются отра-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            strFont[0] = new StriFont("жения относительно оси ","",cs.font);
                            strFont[1] = new StriFont("х ","",cs.fontItalic);
                            strFont[2] = new StriFont("и диагональной прямой,","",cs.font);
                            sf.textLine(strFont,cs.stroka++,3);
                            string = String.format("рассмотренные в теме \"Примеры\". Матрицы этих");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("двух преобразований называются матрицами Паули.");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("Композиции линейных преобразований соответству-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("ет умножение матриц в обычном смысле \"строка на ");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("столбец\". Пусть");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);

                         //  cs.stroka+=0.5;

                            i=0;
                            strFont[i++] = new StriFont("M = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("- матрица преобразования  ", "", cs.font);
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("пусть ", "", cs.font);
                            strFont[i++] = new StriFont("N = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("- матрица преобразования ", "", cs.font);
                            strFont[i++] = new StriFont("B.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("Тогда  ", "", cs.font);
                            strFont[i++] = new StriFont("M*N = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "21", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "11 ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("+ m", "+ m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("21   ", "21   ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "21", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "12 ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("+ m", "+ m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "22 ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("матрица преобразования  ", "", cs.font);
                            strFont[i++] = new StriFont("AB.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                        case 11:
                            black = cs.rand4(1.);
                            cs.white = cs.rand4(1.);
                            // cs.count++;
                            cs.stroka = 1.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            comp.drawFlag(comp.xxx[0]);
                            comp.drawFlag(new Flag(cs.white,Color.WHITE));
                            string = String.format("Композиции линейных преобразований соответству-");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("ет умножение матриц в обычном смысле \"строка на ");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                            string = String.format("столбец\". Пусть");
                            comp.addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);

                            cs.stroka+=0.5;

                            i=0;
                            strFont[i++] = new StriFont("M = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("- матрица преобразования  ", "", cs.font);
                            strFont[i++] = new StriFont("A ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);

                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("пусть ", "", cs.font);
                            strFont[i++] = new StriFont("N = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("- матрица преобразования ", "", cs.font);
                            strFont[i++] = new StriFont("B.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("Тогда  ", "", cs.font);
                            strFont[i++] = new StriFont("M*N = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "21", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("11 ", "11 ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("+ m", "+ m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("21   ", "21   ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11", "21", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("12 ", "12 ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("+ m", "+ m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("n", "n", cs.fontItalic);
                            strFont[i++] = new StriFont("22 ", "22 ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("матрица преобразования  ", "", cs.font);
                            strFont[i++] = new StriFont("AB.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);



                            daNet.setVisible(true);
                            break;
                        case 5:
                            black = cs.rand4(1.);
                            //      cs.white = cs.rand4(1.);
                            //  cs.count++;
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            comp.xxx[0] = new Flag(black,Color.YELLOW);
                            comp.drawFlag(comp.xxx[0]);
                            strFont[0] = new StriFont("Напомним, что матрица ","",cs.font);
                            strFont[1] = new StriFont("E = ","",cs.fontItalic);
                            strFont[2] = new StriFont("(","",cs.fontBig);
                            strFont[3] = new StriFont("1   0","0   1",cs.fontItalic);
                            strFont[4] = new StriFont(")","",cs.fontBig);
                            strFont[5] = new StriFont(" называется","",cs.font);
                            sf.textLine(strFont,cs.stroka++,6);
                            cs.stroka++;
                            strFont[0] = new StriFont("единичной матрицей, ","",cs.font);
                            strFont[1] = new StriFont("EM = ME = M ","",cs.fontItalic);
                            strFont[2] = new StriFont("для любой","",cs.font);
                            sf.textLine(strFont,cs.stroka++,3);
                            strFont[0] = new StriFont("матрицы ","",cs.font);
                            strFont[1] = new StriFont("M. ","",cs.fontItalic);
                            strFont[2] = new StriFont("Если две матрицы ","",cs.font);
                            strFont[3] = new StriFont("M ","",cs.fontItalic);
                            strFont[4] = new StriFont("и ","",cs.font);
                            strFont[5] = new StriFont("N ","",cs.fontItalic);
                            strFont[6] = new StriFont("таковы, что ","",cs.font);
                            sf.textLine(strFont,cs.stroka++,7);
                            i=0;
                            strFont[i++] = new StriFont("MN = E, ","",cs.fontItalic);
                            strFont[i++] = new StriFont("то эти матрицы называются взаимно об-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i);

                            strFont[0] = new StriFont("ратными. Не каждая матрица обратима, т.е. имеет","",cs.font);
                            sf.textLine(strFont,cs.stroka++,1);
                            i=0;
                            strFont[i++] = new StriFont("обратную. Матрица ","",cs.font);
                            strFont[i++] = new StriFont("M = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("имеет об-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka++;
                            i=0;
                            strFont[i++] = new StriFont("ратную тогда и только тогда, когда  ","",cs.font);
                            strFont[i++] = new StriFont("m","",cs.fontItalic);
                            strFont[i++] = new StriFont("11","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m","",cs.fontItalic);
                            strFont[i++] = new StriFont("22 ","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("- m","",cs.fontItalic);
                            strFont[i++] = new StriFont("12","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("m","",cs.fontItalic);
                            strFont[i++] = new StriFont("21","",cs.fontSmall,0.2);
                            sf.textLine(strFont,cs.stroka++,i++);
                            strFont[0] = new StriFont("не равно нулю. Это число называется определителем","",cs.font);
                            sf.textLine(strFont,cs.stroka++,1);
                            strFont[0] = new StriFont("матрицы ","",cs.font);
                            strFont[1] = new StriFont("M. ","",cs.fontItalic);
                            strFont[2] = new StriFont("Модуль определителя матрицы ","",cs.font);
                            strFont[3] = new StriFont("M ","",cs.fontItalic);
                            strFont[4] = new StriFont("равен","",cs.font);
                            sf.textLine(strFont,cs.stroka++,5);
                            strFont[0] = new StriFont("удвоенной площади, занимаемой флагом матрицы ","",cs.font);
                            strFont[1] = new StriFont("M. ","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,2);
                            strFont[0] = new StriFont("Если определитель матрицы равен нулю, то флаг","",cs.font);
                            sf.textLine(strFont,cs.stroka++,1);
                            strFont[0] = new StriFont("этой матрицы вырожден, т.е. расплющен. Обрати-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,1);
                            strFont[0] = new StriFont("мые матрицы образуют группу, которая имеет","",cs.font);
                            sf.textLine(strFont,cs.stroka++,1);
                            strFont[0] = new StriFont("стандартное обозначение: ","",cs.font);
                            strFont[1] = new StriFont("GL(2). ","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,2);

                            daNet.setVisible(true);
                            break;

                    }

                }while(cs.SubTopic>-1 && cs.SubTopic < 6);

                comp.clearAll();
                cs.titulList();
            }
        });


        JMenuItem menuHelpOrthogonality = new JMenuItem("Ортогональность");
        menuHelpOrthogonality.setFont(cs.fontSmall);
        menuHelp.add(menuHelpOrthogonality);
        menuHelpOrthogonality.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                cs.identity = new Matrix(new Point(1,0),new Point(0,1));
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            double alfa = Math.random()*3.14159*2.;
                            cs.white = new Matrix(new Point(Math.cos(alfa),Math.sin(alfa)),new Point(-Math.sin(alfa),Math.cos(alfa)));
                      //      do
                      //          cs.white = cs.rand4(1.);
                      //      while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            Flag flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            i=0;
                            strFont[i++] = new StriFont("Линейный оператор А называется ортогональ-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ным, если он не меняет расстояний между точ-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ками. Можно сказать и иначе: линейный опера-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("тор А ортогонален, если он не меняет скаляр-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ного произведения векторов:  ","",cs.font);
                            strFont[i++] = new StriFont("<A(a), A(b)> =","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont(" = <a, b>. ","",cs.fontItalic);
                            strFont[i++] = new StriFont("Для координатной плоскости, кото-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("рую мы видим на экране нашего компьютера,","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("скалярное произведение определяется следу-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ющими своими значениями на базисных век-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("торах:","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);

                            cs.stroka+=0.5;

                            i=0;
                            strFont[i++] = new StriFont("g","",cs.fontItalic);
                            strFont[i++] = new StriFont("11 ","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= ","",cs.fontItalic);
                            strFont[i++] = new StriFont("<(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(", ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")> ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= 1,  ","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.8;
                            i=0;
                            strFont[i++] = new StriFont("g","",cs.fontItalic);
                            strFont[i++] = new StriFont("22 ","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= ","",cs.fontItalic);
                            strFont[i++] = new StriFont("<(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(", ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")> ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= 1,","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.8;
                            i=0;
                            strFont[i++] = new StriFont("g","",cs.fontItalic);
                            strFont[i++] = new StriFont("12 ","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= ","",cs.fontItalic);
                            strFont[i++] = new StriFont("g","",cs.fontItalic);
                            strFont[i++] = new StriFont("21 ","",cs.fontSmall,0.2);
                            strFont[i++] = new StriFont("= ","",cs.fontItalic);
                            strFont[i++] = new StriFont("<(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1", "0", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(", ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("0", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")> ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= 0,","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);


                            daNet.setVisible(true);
                            break;

                        case 1:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            alfa = Math.random()*3.14159*2.;
                            cs.white = new Matrix(new Point(Math.cos(alfa),Math.sin(alfa)),new Point(-Math.sin(alfa),Math.cos(alfa)));
                            //      do
                            //          cs.white = cs.rand4(1.);
                            //      while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            i=0;
                            strFont[i++] = new StriFont("Ортогональный линейный оператор передвигает","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("точки плоскости так, как будто плоскость сде-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("лана из твердого материала: при этом не меня-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ются длины отрезков и углы. Матрица","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("M = ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(")  ", "", cs.fontBig);
                            strFont[i++] = new StriFont("является матрицей ортого-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.5;
                            i=0;
                            strFont[i++] = new StriFont("нального преобразования тогда и только тогда,","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.9;
                            i=0;
                            strFont[i++] = new StriFont("когда  ","",cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "21  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("12", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("* ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("11  ", "12  ", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont("m", "m", cs.fontItalic);
                            strFont[i++] = new StriFont("21", "22", cs.fontSmall, 0.2);
                            strFont[i++] = new StriFont(") ", "", cs.fontBig);
                            strFont[i++] = new StriFont("= ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont("1   ", "0", cs.fontItalic);
                            strFont[i++] = new StriFont("0   ", "1", cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(".", "", cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.9;
                            i=0;
                            strFont[i++] = new StriFont("Обычно это матричное соотношение записывают","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.7;
                            i=0;
                            strFont[i++] = new StriFont("с использованием знака  транспонирования ","",cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont("t", "", cs.fontItalic,-0.8);
                            strFont[i++] = new StriFont(".", "", cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.7;
                            i=0;
                            strFont[i++] = new StriFont("Напомним, что транспонированием матрицы на-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("зывается обмен местами верхего правого элемен-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("та матрицы с нижним левым. Можно сказать ина-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);





                            daNet.setVisible(true);
                            break;
                        case 2:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            alfa = Math.random()*3.14159*2.;
                            cs.white = new Matrix(new Point(Math.cos(alfa),Math.sin(alfa)),new Point(-Math.sin(alfa),Math.cos(alfa)));
                            //      do
                            //          cs.white = cs.rand4(1.);
                            //      while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            i=0;
                            strFont[i++] = new StriFont("че: транспонирование матрицы, это ее отраже-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ние относительно главной диагонали. Матрица","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("M ","",cs.fontItalic);
                            strFont[i++] = new StriFont("является матрицей ортогонального преобра-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("зования тогда и только тогда, когда ","",cs.font);
                            strFont[i++] = new StriFont("M*M ","",cs.fontItalic);
                            strFont[i++] = new StriFont("t ","",cs.fontSmall,-0.3);
                            strFont[i++] = new StriFont("= E.","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("Такие матрицы еще называют ортогональными","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("матрицами. Ортогональные матрицы обратимы:","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("транспонированная матрица служит им обрат-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ной, поэтому ортогональные матрицы образу-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("ют группу. Эта группа имеет стандартное обо-","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("значение:  ","",cs.font);
                            strFont[i++] = new StriFont("O(2) ","",cs.fontItalic);
                            strFont[i++] = new StriFont("и является подгруппой группы","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("GL(2).","",cs.fontItalic);
                            sf.textLine(strFont,cs.stroka++,i++);
                            strFont[0] = new StriFont("Все ортогональные операторы на плоскости яв-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("ляются либо поворотами на некоторый угол,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("либо отражениями относительно некоторой пря-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("мой, проходящей через начало координат.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);

                            daNet.setVisible(true);
                            break;
                        case 3:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            alfa = Math.random()*3.14159*2.;
                            cs.white = new Matrix(new Point(Math.cos(alfa),Math.sin(alfa)),new Point(-Math.sin(alfa),Math.cos(alfa)));
                            //      do
                            //          cs.white = cs.rand4(1.);
                            //      while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            strFont[0] = new StriFont("Матрица поворота плоскости на угол ", "", cs.font);
                            strFont[1] = new StriFont(String.format("%s  ",(char)945), "", cs.fontItalic);
                            strFont[2] = new StriFont("такова:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("(", "", cs.fontBig);
                            strFont[1] = new StriFont(String.format("cos(%s)   -sin(%s)",(char)945,(char)945),String.format("sin(%s)    cos(%s)",(char)945,(char)945), cs.fontItalic);
                            strFont[2] = new StriFont(")  ", "", cs.fontBig);
                            strFont[3] = new StriFont(".  Пусть ", "", cs.font);
                            strFont[4] = new StriFont(String.format("%s  ",(char)945), "", cs.fontItalic);
                            strFont[5] = new StriFont("и  ", "", cs.font);
                            strFont[6] = new StriFont(String.format("%s  ",(char)946), "", cs.fontItalic);
                            strFont[7] = new StriFont("два произволь-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 8);
                            cs.stroka++;
                            strFont[0] = new StriFont("ных угла. Если совершить один за другим поворо-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("ты на эти углы, то получим в результате поворот", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("на угол  ", "", cs.font);
                            strFont[1] = new StriFont(String.format("%s + %s.  ",(char)945,(char)946), "", cs.fontItalic);
                            strFont[2] = new StriFont("Перемножая матрицы этих поворо-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 3);
                            strFont[0] = new StriFont("тов, получаем:", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("(", "", cs.fontBig);
                            strFont[1] = new StriFont(String.format("cos(%s + %s)   -sin(%s + %s)",(char)945,(char)946,(char)945,(char)946), String.format("sin(%s + %s)   cos(%s + %s)",(char)945,(char)946,(char)945,(char)946), cs.fontItalic);
                            strFont[2] = new StriFont(")=", "", cs.fontBig);
                            sf.textLine(strFont, cs.stroka++, 3);
                            cs.stroka++;
                            strFont[0] = new StriFont("=(", "", cs.fontBig);
                            strFont[1] = new StriFont(String.format("cos(%s)   -sin(%s)",(char)945,(char)945), String.format("sin(%s)   cos(%s)",(char)945,(char)945), cs.fontItalic);
                            strFont[2] = new StriFont(")(", "", cs.fontBig);
                            strFont[3] = new StriFont(String.format("cos(%s)   -sin(%s)",(char)946,(char)946), String.format("sin(%s)   cos(%s)",(char)946,(char)946), cs.fontItalic);
                            strFont[4] = new StriFont(")", "", cs.fontBig);
                            sf.textLine(strFont, cs.stroka++, 5);
                            cs.stroka++;
                            strFont[0] = new StriFont("Из этого равенства следуют основные формулы", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            strFont[0] = new StriFont("тригонометрии.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);

                            daNet.setVisible(true);
                            break;
                        case 4:
                            cs.stroka = 0;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            alfa = Math.random()*3.14159*2.;
                            Line line = cs.granica(new Relation(Math.cos(alfa),Math.sin(alfa),0.,0));
                            comp.addLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2(),Color.BLACK,3.f);
                            alfa = Math.random()*3.14159*2.;
                            line = cs.granica(new Relation(Math.cos(alfa),Math.sin(alfa),0.,0));
                            comp.addLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2(),Color.BLACK,3.f);

                           // cs.white = new Matrix(new Point(Math.cos(alfa),Math.sin(alfa)),new Point(-Math.sin(alfa),Math.cos(alfa)));
                            //      do
                            //          cs.white = cs.rand4(1.);
                            //      while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                          //  flag = new Flag(cs.white,Color.WHITE);
                          //  comp.drawFlag(flag);
                          //  comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            i=0;
                            strFont[i++] = new StriFont("Пусть имеется прямая, проходящая через","",cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont(String.format("начало координат под углом %s к оси ",(char)945),"",cs.font);
                            strFont[i++] = new StriFont("x. ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("Мат-", "", cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            i=0;
                            strFont[i++] = new StriFont("рица отражения относительно этой прямой", "", cs.font);
                            sf.textLine(strFont,cs.stroka++,i++);
                            cs.stroka+=0.6;
                            i=0;
                            strFont[i++] = new StriFont("такова:  ", "", cs.font);
                            strFont[i++] = new StriFont("(", "", cs.fontBig);
                            strFont[i++] = new StriFont(String.format("cos(2%s)   sin(2%s)",(char)945,(char)945),String.format("sin(2%s)    -cos(2%s)",(char)945,(char)945), cs.fontItalic);
                            strFont[i++] = new StriFont(")", "", cs.fontBig);
                            strFont[i++] = new StriFont(". Пусть имеются", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i);
                            cs.stroka++;
                            strFont[0] = new StriFont("две прямые, проходящие через начало ко-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, 1);
                            i=0;
                            strFont[i++] = new StriFont("ординат под углами ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%s ",(char)945), "", cs.fontItalic);
                            strFont[i++] = new StriFont("и ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%s ",(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("соответственно. ", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Тогда, очевидно, угол между этими прямыми", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("будет равен ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("%s - %s. ",(char)945,(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("Присмотримся к композиции", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("двух отражений относительно этих прямых.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Геометрически почти очевидно, что эта компо-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("зиция равна повороту на угол ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("2(%s - %s) ",(char)945,(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("в одну", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("или другую сторону. При этом композиция этих", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("же отражений в другой последовательности", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("совпадает с поворотом на угол ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("2(%s - %s) ",(char)945,(char)946), "", cs.fontItalic);
                            strFont[i++] = new StriFont("в другую", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("сторону.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);


                            daNet.setVisible(true);
                            break;
                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 5);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpTrajectories = new JMenuItem("Траектории");
        menuHelpTrajectories.setFont(cs.fontSmall);
        menuHelp.add(menuHelpTrajectories);
        menuHelpTrajectories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                cs.identity = new Matrix(new Point(1,0),new Point(0,1));
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            Flag flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                         //   double stroka = 0.;
                         //   String text="В.А.Гребёнкин работает в компании Рене с 2007 года в качестве 1С-программиста. Он был приглашён на эту работу в связи с переходом компании на систему учёта 1С8 (до этого компания пользовалась системой 1С7). Фактически именно тогда учет в компании стал по настоящему полноценным и надежным. Впервые удалось добиться корректных взаиморасчетов со всеми контрагентами, корректных товарных остатков по складам. Был организован управленческий учет, позволяющий оценивать эффективность работы каждого отдельного подразделения, так называемая.";
                         //   cs.printInRectangle(new Point((double)cs.rightTextLocation(1.).getX(), (double)(cs.ory-cs.fyp)),new Point((double)(cs.rightSpace+cs.orx+cs.fxp-40),(double)(cs.fyp+cs.fym) ), text,cs.font );
                            i=0;
                            strFont[i++] = new StriFont("Касательные матрицы, используемые в этой теме,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("являются частным, но очень важным случаем по-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("нятия касательного вектора в единице группы Ли.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Касательные матрицы, как и любые матрицы, мож-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("но перемножать, но в данном случае это не имеет", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("никакого смысла, в отличие от матриц линейных", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("операторов, перемножение которых, как Вы пом-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ните, соответствует композиции операторов.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            i=0;
                            strFont[i++] = new StriFont("В применении к касательным матрицам имеет важ-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ный смысл операция ", "", cs.font);
                            strFont[i++] = new StriFont("коммутирования:", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("  [M, N] = MN - NM. ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Очевидно, что ", "", cs.font);
                            strFont[i++] = new StriFont("[M, N] = -[N, M].", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Не совсем очевидно, но нетрудно доказать, что", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("имеет место следующее ", "", cs.font);
                            strFont[i++] = new StriFont("тождество Якоби:", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("[M, [N, K]] + [N, [K, M]] + [K, [M, N]] = 0.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                     //       strFont[i++] = new StriFont(String.format("2(%s - %s) ",(char)945,(char)946), "", cs.fontItalic);
                     //       strFont[i++] = new StriFont("в другую", "", cs.font);
                     //       sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            //   double stroka = 0.;
                            //   String text="В.А.Гребёнкин работает в компании Рене с 2007 года в качестве 1С-программиста. Он был приглашён на эту работу в связи с переходом компании на систему учёта 1С8 (до этого компания пользовалась системой 1С7). Фактически именно тогда учет в компании стал по настоящему полноценным и надежным. Впервые удалось добиться корректных взаиморасчетов со всеми контрагентами, корректных товарных остатков по складам. Был организован управленческий учет, позволяющий оценивать эффективность работы каждого отдельного подразделения, так называемая.";
                            //   cs.printInRectangle(new Point((double)cs.rightTextLocation(1.).getX(), (double)(cs.ory-cs.fyp)),new Point((double)(cs.rightSpace+cs.orx+cs.fxp-40),(double)(cs.fyp+cs.fym) ), text,cs.font );

                            i=0;
                            strFont[i++] = new StriFont("Множество ", "", cs.font);
                            strFont[i++] = new StriFont("L ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("с заданной на нём бинарной опера-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("цией  ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("[M, N] %s L ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("для любых ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("M, N %s L, ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("обладающей", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("свойствами  ", "", cs.font);
                            strFont[i++] = new StriFont("[M, N] = -[N, M]  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("[M, [N, K]] + [N, [K, M]] + [K, [M, N]] = 0,", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("называется ", "", cs.font);
                            strFont[i++] = new StriFont("кольцом Ли.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);

                            i=0;
                            //       strFont[i++] = new StriFont(String.format("2(%s - %s) ",(char)945,(char)946), "", cs.fontItalic);
                            //       strFont[i++] = new StriFont("в другую", "", cs.font);
                            //       sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });

        JMenuItem menuHelpComplexNumbers = new JMenuItem("Комплексные числа");
        menuHelpComplexNumbers.setFont(cs.fontSmall);
        menuHelp.add(menuHelpComplexNumbers);
        menuHelpComplexNumbers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.clickPoints = false;
                cs.clickMatch = false;
                cs.clickScalarProduct = false;
                cs.clickLinearOper = false;
                comp.ramka=false;
                cs.SubTopic = 0;
                cs.ocen = 0;
                cs.ocenTopic = 0;
                cs.count = 0;
                String string="";
                cs.mm=(int)(cs.fxp/3.5);
                cs.identity = new Matrix(new Point(1,0),new Point(0,1));
                int i=0;
                StriFont[] strFont = new StriFont[30];
                //   cs.rr = new Relation(2.,3.,6.,-2);
                //   Line line = cs.granica(cs.rr);
                //   int shirina = 30;
                do {
                    switch (cs.SubTopic) {
                        case 0:
                            cs.stroka = 0.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            Flag flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            //   double stroka = 0.;
                            //   String text="В.А.Гребёнкин работает в компании Рене с 2007 года в качестве 1С-программиста. Он был приглашён на эту работу в связи с переходом компании на систему учёта 1С8 (до этого компания пользовалась системой 1С7). Фактически именно тогда учет в компании стал по настоящему полноценным и надежным. Впервые удалось добиться корректных взаиморасчетов со всеми контрагентами, корректных товарных остатков по складам. Был организован управленческий учет, позволяющий оценивать эффективность работы каждого отдельного подразделения, так называемая.";
                            //   cs.printInRectangle(new Point((double)cs.rightTextLocation(1.).getX(), (double)(cs.ory-cs.fyp)),new Point((double)(cs.rightSpace+cs.orx+cs.fxp-40),(double)(cs.fyp+cs.fym) ), text,cs.font );
                            i=0;
                            strFont[i++] = new StriFont("Касательные матрицы, используемые в этой теме,", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("являются частным, но очень важным случаем по-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("нятия касательного вектора в единице группы Ли.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Касательные матрицы, как и любые матрицы, мож-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("но перемножать, но в данном случае это не имеет", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("никакого смысла, в отличие от матриц линейных", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("операторов, перемножение которых, как Вы пом-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ните, соответствует композиции операторов.", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            i=0;
                            strFont[i++] = new StriFont("В применении к касательным матрицам имеет важ-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("ный смысл операция ", "", cs.font);
                            strFont[i++] = new StriFont("коммутирования:", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("  [M, N] = MN - NM. ", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Очевидно, что ", "", cs.font);
                            strFont[i++] = new StriFont("[M, N] = -[N, M].", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("Не совсем очевидно, но нетрудно доказать, что", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("имеет место следующее ", "", cs.font);
                            strFont[i++] = new StriFont("тождество Якоби:", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("[M, [N, K]] + [N, [K, M]] + [K, [M, N]] = 0.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            //       strFont[i++] = new StriFont(String.format("2(%s - %s) ",(char)945,(char)946), "", cs.fontItalic);
                            //       strFont[i++] = new StriFont("в другую", "", cs.font);
                            //       sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;
                        case 1:
                            cs.stroka = 0.;
                            comp.clearAll();
                            comp.addCoordinateSysytem();
                            comp.coordsys = true;
                            comp.logoTip = false;
                            comp.addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                            do
                                cs.white = cs.rand4(1.);
                            while(cs.white.getA().getX()+cs.white.getB().getY()<0.);
                            flag = new Flag(cs.white,Color.WHITE);
                            comp.drawFlag(flag);
                            comp.drawFlag(new Flag(cs.identity,Color.YELLOW));
                            //   double stroka = 0.;
                            //   String text="В.А.Гребёнкин работает в компании Рене с 2007 года в качестве 1С-программиста. Он был приглашён на эту работу в связи с переходом компании на систему учёта 1С8 (до этого компания пользовалась системой 1С7). Фактически именно тогда учет в компании стал по настоящему полноценным и надежным. Впервые удалось добиться корректных взаиморасчетов со всеми контрагентами, корректных товарных остатков по складам. Был организован управленческий учет, позволяющий оценивать эффективность работы каждого отдельного подразделения, так называемая.";
                            //   cs.printInRectangle(new Point((double)cs.rightTextLocation(1.).getX(), (double)(cs.ory-cs.fyp)),new Point((double)(cs.rightSpace+cs.orx+cs.fxp-40),(double)(cs.fyp+cs.fym) ), text,cs.font );

                            i=0;
                            strFont[i++] = new StriFont("Множество ", "", cs.font);
                            strFont[i++] = new StriFont("L ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("с заданной на нём бинарной опера-", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("цией  ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("[M, N] %s L ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("для любых ", "", cs.font);
                            strFont[i++] = new StriFont(String.format("M, N %s L, ",(char)1013), "", cs.fontItalic);
                            strFont[i++] = new StriFont("обладающей", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("свойствами  ", "", cs.font);
                            strFont[i++] = new StriFont("[M, N] = -[N, M]  ", "", cs.fontItalic);
                            strFont[i++] = new StriFont("и", "", cs.font);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("[M, [N, K]] + [N, [K, M]] + [K, [M, N]] = 0,", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);
                            i=0;
                            strFont[i++] = new StriFont("называется ", "", cs.font);
                            strFont[i++] = new StriFont("кольцом Ли.", "", cs.fontItalic);
                            sf.textLine(strFont, cs.stroka++, i++);

                            i=0;
                            //       strFont[i++] = new StriFont(String.format("2(%s - %s) ",(char)945,(char)946), "", cs.fontItalic);
                            //       strFont[i++] = new StriFont("в другую", "", cs.font);
                            //       sf.textLine(strFont, cs.stroka++, i++);

                            daNet.setVisible(true);
                            break;

                    }
                }while(cs.SubTopic > -1 && cs.SubTopic < 2);
                comp.clearAll();
                cs.titulList();
            }
        });


        nAzad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic--;
                vvodStolbca.dispose();
            }
        });

        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
  //              Point pp = new Point(Double.parseDouble(xx.getText()),Double.parseDouble(yy.getText()));
                if(cs.isNumber(xx.getText()) && cs.isNumber(yy.getText()))
                vvodStolbca.dispose();
  //              cs.ocen = cs.ocenka(pp, cs.pp);
 //               comp.clearAll();
 //               comp.addPString(oc.getString(), cs.downTextLocation, oc.getPString().getFont());
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cs.SubTopic = 11;
                vvodStolbca.dispose();
            }
        });

        da.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cs.animacia) {
                    cs.SubTopic++;
                    comp.clearAll();
                    daNet.dispose();
                }
             }
        });

        net.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!cs.animacia)
                    daNet.dispose();
            }
        });

        daNetnazad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!cs.animacia) {
                    cs.SubTopic--;
                    comp.clearAll();
                    daNet.dispose();
                }
            }
        });
        frame.setVisible(true);
    }

}
