/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.NguoiChoi;
import model.TranDau;

/**
 *
 * @author Okami
 */
public class Game extends javax.swing.JFrame {

    /**
     * Creates new form Game
     */
    private int totalTime;
    private int timeStart;
    
    private NguoiChoi user1;
    private NguoiChoi user2;
    private TranDau tranDau;
    
    private int turn;
    private String dir = "image/";
    private Timer timerTurn = new Timer("Timer");
    private Timer timerStart = new Timer("Timer");

    public Game(TranDau tranDau) {
        initComponents();
        turn = 1;
        this.user1 = tranDau.getListVanChoi().get(0).getNcvc1().getNguoiChoi();
        this.user2 = tranDau.getListVanChoi().get(0).getNcvc2().getNguoiChoi();
        this.user1Label.setText(this.user1.getUsername());
        this.user2Label.setText(this.user2.getUsername());
        this.resultLabel.setVisible(false);
//        newTurn();
    }
    
    public Game() {
        initComponents();
        turn = 1;
        setResultLabelStatus(false);
//        newTurn();
    }
    
//    public void newTurn() {
//        try {
//            timeStart = 3;
//            this.timeLabel.setVisible(false);
//            this.turnLabel.setText(turn + "");
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    if (timeStart > 0) {
//                        setTimeStartLabelStatus(true);
//                        setTimeStartLabel(timeStart);
//                        timeStart = timeStart - 1;
//                    } else if (timeStart == 0) {
//                        timeStart = timeStart - 1;
//                        setTimeStartLabelStatus(false);
//                        setTimeCounter(15);
//                        timerStart.cancel();
//                    }
//                }
//            };
//            long delay = 1000L;
//            timerTurn = new Timer(turn+"");
//            timerStart.schedule(timerTask, 0, delay);   
//        } catch (Exception e) {
//            
//        }
//    }
//    
//    public void setTimeCounter(int total) {
//        try {
//            setResultLabelStatus(false);
//            this.totalTime = total;
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    if (totalTime > 0) {
//                        setTimeLabelStatus(true);
//                        setTimeLabel(totalTime);
//                        totalTime = totalTime - 1;
//                    } else if (totalTime == 0) {
//                        totalTime = totalTime - 1;
//                        setTimeLabelStatus(false);
//                        setResultLabelStatus(true);
//                        turn = turn + 1;
//                        if (turn < 4) {
//                            newTurn();
//                        } else {
//                            turn = turn + 1;
//                        }
//                        timerTurn.cancel();
//                    }
//                }
//            };
//            long delay = 1000L;
//            timerStart = new Timer(turn+"");
//            timerTurn.schedule(timerTask, 0, delay);
//        } catch (Exception e) {
//            
//        }
//    }
    
    public void setTurnLabelStatus(boolean status) {
        turnLabel.setVisible(status);
    }
    
    public void setTurnLabel(int turn) {
        turnLabel.setText(turn + "");
    }
    
    public void setTimeStartLabelStatus(boolean status) {
        timeStartLabel.setVisible(status);
    }
    
    public void setTimeStartLabel(int time) {
        timeStartLabel.setText("Ván đấu sẽ bắt đầu trong " + time + "s");
    }
    
    public void setTimeLabelStatus(boolean status) {
        timeLabel.setVisible(status);
    }
    
    public void setTimeLabel(int time) {
        timeLabel.setText(time + "s");
    }
    
    public void setResultLabelStatus(boolean status) {
        resultLabel.setVisible(status);
    }
    
    public void setResultLabel(String result) {
        this.resultLabel.setText(result);
    }
    
    public void addKeoButtonListener(ActionListener actionListener) {
        keoButton.addActionListener(actionListener);
    }
    
    public void addBuaButtonListener(ActionListener actionListener) {
        buaButton.addActionListener(actionListener);
    }
    
    public void addBaoButtonListener(ActionListener actionListener) {
        baoButton.addActionListener(actionListener);
    }
    
    public void addExitButtonListener(ActionListener actionListener) {
        exitButton.addActionListener(actionListener);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        user1Image = new javax.swing.JPanel();
        avtUser1 = new javax.swing.JLabel();
        user2Image = new javax.swing.JPanel();
        avtUser2 = new javax.swing.JLabel();
        user1Label = new javax.swing.JLabel();
        user2Label = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        turnLabel = new javax.swing.JLabel();
        exitButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        keoButton = new javax.swing.JButton();
        buaButton = new javax.swing.JButton();
        baoButton = new javax.swing.JButton();
        timeStartLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 100));

        jPanel1.setBackground(new java.awt.Color(0, 51, 51));

        avtUser1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/boy.png"))); // NOI18N

        javax.swing.GroupLayout user1ImageLayout = new javax.swing.GroupLayout(user1Image);
        user1Image.setLayout(user1ImageLayout);
        user1ImageLayout.setHorizontalGroup(
            user1ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avtUser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        user1ImageLayout.setVerticalGroup(
            user1ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avtUser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        avtUser2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/girl.png"))); // NOI18N

        javax.swing.GroupLayout user2ImageLayout = new javax.swing.GroupLayout(user2Image);
        user2Image.setLayout(user2ImageLayout);
        user2ImageLayout.setHorizontalGroup(
            user2ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avtUser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        user2ImageLayout.setVerticalGroup(
            user2ImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(avtUser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        user1Label.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        user1Label.setForeground(new java.awt.Color(255, 0, 51));
        user1Label.setText("User 1");

        user2Label.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        user2Label.setForeground(new java.awt.Color(255, 0, 51));
        user2Label.setText("User 2");

        timeLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(255, 0, 51));
        timeLabel.setText("1");

        resultLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        resultLabel.setForeground(new java.awt.Color(255, 255, 255));
        resultLabel.setText("Bao thắng búa: lengan thắng");

        jPanel4.setBackground(new java.awt.Color(255, 51, 102));
        jPanel4.setPreferredSize(new java.awt.Dimension(0, 3));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 534, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hãy đưa ra quyết định đúng đắn cho nước đi của bạn!!!");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Ván:");

        turnLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        turnLabel.setForeground(new java.awt.Color(240, 240, 240));
        turnLabel.setText("2");

        exitButton.setBackground(new java.awt.Color(0, 51, 51));
        exitButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 51, 51));
        exitButton.setText("Thoát");
        exitButton.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 51, 51)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Điểm:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Điểm:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("3");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("3");

        keoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Keo.jpg"))); // NOI18N

        buaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bua.jpg"))); // NOI18N

        baoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bao.jpg"))); // NOI18N

        timeStartLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        timeStartLabel.setForeground(new java.awt.Color(255, 255, 255));
        timeStartLabel.setText("Trận đấu sẽ bắt đầu trong 1s");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(keoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buaButton)
                .addGap(59, 59, 59)
                .addComponent(baoButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(timeStartLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(user1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(user2Label)
                .addGap(114, 114, 114))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(turnLabel)
                .addGap(116, 116, 116)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(user1Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(user2Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(turnLabel))
                .addGap(15, 15, 15)
                .addComponent(timeStartLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user1Label)
                    .addComponent(user2Label))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(user1Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user2Image, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(timeLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buaButton)
                    .addComponent(baoButton)
                    .addComponent(keoButton))
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        user1Label.getAccessibleContext().setAccessibleName("user1Label");
        user2Label.getAccessibleContext().setAccessibleName("user2Label");
        timeLabel.getAccessibleContext().setAccessibleName("timeLabel");
        resultLabel.getAccessibleContext().setAccessibleName("resultLabel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Game game = new Game();
                game.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avtUser1;
    private javax.swing.JLabel avtUser2;
    private javax.swing.JButton baoButton;
    private javax.swing.JButton buaButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton keoButton;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel timeStartLabel;
    private javax.swing.JLabel turnLabel;
    private javax.swing.JPanel user1Image;
    private javax.swing.JLabel user1Label;
    private javax.swing.JPanel user2Image;
    private javax.swing.JLabel user2Label;
    // End of variables declaration//GEN-END:variables
}
