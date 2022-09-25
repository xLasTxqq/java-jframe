package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Main {
    private static final String url = "jdbc:mariadb://127.0.0.1/portal_yii";
    private static final String user = "mysql";
    private static final String password = "";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static DatabaseMetaData metadata;

    public static void main(String[] args) {
        String query = "select * from rent";

        try {
            con = DriverManager.getConnection(url, user, password);

            metadata=con.getMetaData();
            if(metadata.getTables(null,null,"rent",null).next()) {
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            }
            else {
                String sql = "CREATE TABLE rent "
                    + "(id INTEGER not NULL AUTO_INCREMENT, "
//                    + " first VARCHAR(255), "
//                    + " Month VARCHAR(255), "
                    + " Year INTEGER, "
                    + " Month INTEGER, "
                    + " Size INTEGER, "
                    + " Money INTEGER, "
                    + " PRIMARY KEY ( id ))";
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            rs = stmt.executeQuery(query);

            }
            System.out.println(con);
            System.out.println(stmt);
            System.out.println(rs);
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
                String img = rs.getString("img");
                System.out.println(img);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        new Window();
    }
}
class Window extends JFrame{
    private static final String url = "jdbc:mariadb://127.0.0.1/portal_yii";
    private static final String user = "mysql";
    private static final String password = "";
    private static ResultSet Result;
    private void BtnClick(JButton btn){
        JOptionPane.showMessageDialog(null,"Нажал");
    }
    public Window(){
        try{
            Result = DriverManager.getConnection(url, user, password).createStatement().executeQuery("select * from rent");
//            DriverManager.getConnection(url, user, password).createStatement().executeUpdate("INSERT INTO");
            while (Result.next()) {
                System.out.println(Result.getString(1));
//        new JLabel();
            }
        }
        catch (SQLException sqlEx){
            sqlEx.printStackTrace();
        }
        finally {
            try { Result.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel Panel=new JPanel();
        getContentPane().add(Panel);
        setBounds(0,0,800,600);
        Panel.setBackground(Color.GREEN);
        setTitle("Расчет кварплаты");
        setIconImage(new ImageIcon(getClass().getResource("icon.jpg")).getImage());



        JButton btn=new JButton();
        JLabel label=new JLabel("Посмотри на это");
        label.setSize(100,25);
        label.setLocation(100,50);
        label.setBounds(130,0,300,50);
        btn.setSize(100,25);
        btn.setLocation(100,25);
        btn.setText("Кнопка");
        label.setForeground(new Color(50,50,50,50));
        btn.setBackground(Color.orange);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BtnClick((JButton)e.getSource());
            }
        });
        JTextArea text1 =new JTextArea();
        text1.setSize(100,25);
        Panel.add(text1);
        Panel.add(label);
        Panel.add(btn);
//        con.add(pan);
        Panel.setLayout(null);
        setVisible(true);

    }
}
