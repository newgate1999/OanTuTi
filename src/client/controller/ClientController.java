/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controller;

import client.view.Dashboard;
import client.view.Game;
import client.view.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Message;
import model.NguoiChoi;
import model.TranDau;

/**
 *
 * @author Okami
 */
public class ClientController {

    private static final int port = 8000;
    private static final String hostname = "localhost";
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    private NguoiChoi currentUser;
    private NguoiChoi competitorUser;

    private Login loginView;
    private Dashboard dashboard;
    private Game game;

    public ClientController() {
        try {

            this.socket = new Socket(hostname, port);
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            RequestListener requestListener = new RequestListener();
            requestListener.start();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến máy chủ");
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClientController(Login loginView) {
        try {
            this.loginView = loginView;
            this.socket = new Socket(hostname, port);
            this.oos = new ObjectOutputStream(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            RequestListener requestListener = new RequestListener();
            requestListener.start();
            this.loginView.setVisible(true);
            loginView.addLoginListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    NguoiChoi user = loginView.getUserFromInputs();
                    Message request = new Message("Login", user);
                    sendRequest(request);
                }
            });

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến máy chủ");
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getListUserOnline() {
        Message request = new Message("Get list user online", null);
        sendRequest(request);
    }

    public void sendChallengeMessage(NguoiChoi competitor) {
        Message request = new Message("Challenge to", competitor);
        sendRequest(request);
    }

    public void sendRequest(Message request) {
        try {
            oos.writeObject(request);
            oos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến máy chủ");
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Message receiveResponse() {
        try {
            synchronized (ois) {
                return (Message) ois.readObject();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Không thể kết nối đến máy chủ");
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    class RequestListener extends Thread {

        private Timer timerTurn = new Timer("Timer");
        private Timer timerStart = new Timer("Timer");

        private TranDau tranDau;
        private int totalTime;
        private int timeStart;
        private int turn;

        public void newTurn() {
            try {
                timeStart = 3;
                game.setTimeLabelStatus(false);
                game.setTurnLabel(turn);
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (timeStart > 0) {
                            game.setTimeStartLabelStatus(true);
                            game.setTimeStartLabel(timeStart);
                            timeStart = timeStart - 1;
                        } else if (timeStart == 0) {
                            timeStart = timeStart - 1;
                            game.setTimeStartLabelStatus(false);
                            setTimeCounter(15);
                            timerStart.cancel();
                        }
                    }
                };
                long delay = 1000L;
                timerTurn = new Timer(turn + "");
                timerStart.schedule(timerTask, 0, delay);
            } catch (Exception e) {

            }
        }

        public void setTimeCounter(int total) {
            try {
                game.setResultLabelStatus(false);
                this.totalTime = total;
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if (totalTime > 0) {
                            game.setTimeLabelStatus(true);
                            game.setTimeLabel(totalTime);
                            totalTime = totalTime - 1;
                        } else if (totalTime == 0) {
                            totalTime = totalTime - 1;
                            game.setTimeLabelStatus(false);
                            game.setResultLabelStatus(true);
                            if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNuocChoi());
                                Message request = new Message("Get result turn", tranDau.getListVanChoi().get(turn - 1).getNcvc1());
                                sendRequest(request);
                            } else {
                                System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc2().getNuocChoi());
                                Message request = new Message("Get result turn", tranDau.getListVanChoi().get(turn - 1).getNcvc2());
                                sendRequest(request);
                            }

                            turn = turn + 1;
                            if (turn < 4) {
                                newTurn();
                            } else {
                                turn = turn + 1;
                                sendRequest(new Message("Get result game", tranDau));
                            }
                            timerTurn.cancel();
                        }
                    }
                };
                long delay = 1000L;
                timerStart = new Timer(turn + "");
                timerTurn.schedule(timerTask, 0, delay);
            } catch (Exception e) {

            }
        }

        @Override
        public void run() {
            while (true) {
                Message response = receiveResponse();
                System.out.println(response.getMessage());
                switch (response.getMessage()) {
                    case "Login fail": {
                        JOptionPane.showMessageDialog(null, response.getObject().toString());
                        break;
                    }
                    case "Login success": {
                        currentUser = (NguoiChoi) response.getObject();
                        dashboard = new Dashboard(currentUser);
                        dashboard.addRefreshLisener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                getListUserOnline();
                            }
                        });
                        dashboard.addChallengeLisener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                NguoiChoi competitor = dashboard.getSelectedUser();
                                if (competitor == null) {
                                    JOptionPane.showMessageDialog(null, "Vui lòng chọn người chơi để thách đấu!!!");
                                } else {
                                    sendChallengeMessage(competitor);
                                }
                            }
                        });
                        loginView.dispose();
                        dashboard.setVisible(true);
                        getListUserOnline();
                        break;
                    }
                    case "List user online": {
                        dashboard.setListUser((ArrayList<NguoiChoi>) response.getObject());
                        break;
                    }
                    case "Challenge from": {
                        NguoiChoi competitor = (NguoiChoi) response.getObject();
                        int choice = JOptionPane.showConfirmDialog(dashboard, competitor.getUsername() + " muốn thách đấu với bạn!!", "Lời mời", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            sendRequest(new Message("Accept", competitor));
                        } else {
                            sendRequest(new Message("Decline", competitor));
                        }
                        break;
                    }
                    case "Start game": {
                        tranDau = (TranDau) response.getObject();
//                        JOptionPane.showMessageDialog(dashboard, "Đối thủ đã chấp nhận yêu cầu thách đấu của bạn!!!");
                        dashboard.setVisible(false);
                        game = new Game(tranDau);
                        game.addKeoButtonListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                    tranDau.getListVanChoi().get(turn - 1).getNcvc1().setNuocChoi(1);
                                } else {
                                    tranDau.getListVanChoi().get(turn - 1).getNcvc2().setNuocChoi(1);
                                }
                                if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                    System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNuocChoi());
                                    Message request = new Message("Send turn result", tranDau.getListVanChoi().get(turn - 1).getNcvc1());
                                    sendRequest(request);
                                } else {
                                    System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc2().getNuocChoi());
                                    Message request = new Message("Send turn result", tranDau.getListVanChoi().get(turn - 1).getNcvc2());
                                    sendRequest(request);
                                }
                            }
                        });
                        game.addBuaButtonListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                    tranDau.getListVanChoi().get(turn - 1).getNcvc1().setNuocChoi(2);
                                } else {
                                    tranDau.getListVanChoi().get(turn - 1).getNcvc2().setNuocChoi(2);
                                }
                                if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                    System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNuocChoi());
                                    Message request = new Message("Send turn result", tranDau.getListVanChoi().get(turn - 1).getNcvc1());
                                    sendRequest(request);
                                } else {
                                    System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc2().getNuocChoi());
                                    Message request = new Message("Send turn result", tranDau.getListVanChoi().get(turn - 1).getNcvc2());
                                    sendRequest(request);
                                }
                            }
                        });
                        game.addBaoButtonListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                    tranDau.getListVanChoi().get(turn - 1).getNcvc1().setNuocChoi(3);
                                } else {
                                    tranDau.getListVanChoi().get(turn - 1).getNcvc2().setNuocChoi(3);
                                }
                                if (tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNguoiChoi().equals(currentUser)) {
                                    System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc1().getNuocChoi());
                                    Message request = new Message("Send turn result", tranDau.getListVanChoi().get(turn - 1).getNcvc1());
                                    sendRequest(request);
                                } else {
                                    System.out.println(tranDau.getListVanChoi().get(turn - 1).getNcvc2().getNuocChoi());
                                    Message request = new Message("Send turn result", tranDau.getListVanChoi().get(turn - 1).getNcvc2());
                                    sendRequest(request);
                                }
                            }
                        });
                        game.addExitButtonListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Out");
                            }
                        });
                        game.setVisible(true);
                        turn = 1;
                        newTurn();
                        break;
                    }
                    case "Decline": {
                        NguoiChoi competitor = (NguoiChoi) response.getObject();
                        JOptionPane.showMessageDialog(dashboard, "Đối thủ đã từ chối yêu cầu thách đấu của bạn!!!");
                        break;
                    }
                    case "Result turn": {
                        String rs = (String) response.getObject();
                        System.out.println(rs);
                        game.setResultLabel(rs);
                        break;
                    }
                    case "Result game": {
                        String rs = (String) response.getObject();
                        System.out.println(rs);
                        game.dispose();
                        dashboard.setVisible(true);
                        JOptionPane.showMessageDialog(dashboard, rs);
                        break;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Login login = new Login();
        ClientController clientController = new ClientController(login);
    }
}
