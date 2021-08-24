/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import model.Message;
import model.TranDau;
import model.NguoiChoi;
import model.NguoiChoiVanChoi;
import model.VanChoi;
import server.view.ServerView;

/**
 *
 * @author Okami
 */
public class ServerController {

    private static final int port = 8000;
    static HashMap<Integer, String> listNuocChoi;
    
    private NguoiChoiDAO userDAO;
    private NguoiChoiVanChoiDAO ncvcDAO;
    private VanChoiDAO vanChoiDAO;
    private TranDauDAO tranDauDAO;

    HashMap<NguoiChoi, Socket> listSocket;
    HashMap<NguoiChoi, ObjectInputStream> listOis;
    HashMap<NguoiChoi, ObjectOutputStream> listOos;
    

    public ServerController() {
        listSocket = new HashMap<>();
        listOis = new HashMap<>();
        listOos = new HashMap<>();
        listNuocChoi = new HashMap<>();
        listNuocChoi.put(0, "Không chọn");
        listNuocChoi.put(1, "Kéo");
        listNuocChoi.put(2, "Búa");
        listNuocChoi.put(3, "Bao");
        this.userDAO = new NguoiChoiDAO();
        this.tranDauDAO = new TranDauDAO();
        this.vanChoiDAO = new VanChoiDAO();
        this.ncvcDAO = new NguoiChoiVanChoiDAO();
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        ServerView.log("Server is running...");
        while (true) {
            Socket socket = serverSocket.accept();
            ServerView.log("Connect success to: " + socket);
            RequestListener requestListener = new RequestListener(socket);
            requestListener.start();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerController serverController = new ServerController();
        serverController.start();
    }

    class RequestListener extends Thread {
        private Socket socket;
        private NguoiChoi currentUser;
        private ObjectInputStream ois;
        private ObjectOutputStream oos;
        private TranDau tranDau;

        public RequestListener(Socket socket) throws IOException {
            System.out.println("Start listen....");
            this.socket = socket;
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println(socket);
                    Message request = (Message) ois.readObject();
                    switch (request.getMessage()) {
                        case "Login": {
                            NguoiChoi user = (NguoiChoi) request.getObject();
                            ServerView.log("Request login username: " + user.getUsername());
                            ServerView.log("Request login password: " + user.getPassword());
                            Message response = new Message();
                            NguoiChoi result = userDAO.checkLogin(user);

                            if (result != null) {
                                if (listSocket.get(result) != null) {
                                    response.setMessage("Login fail");
                                    response.setObject("Tài khoản đang đăng nhập ở nơi khác!!!");
                                } else {
                                    result.setUserStatus(1);
                                    listSocket.put(result, socket);
                                    listOis.put(result, ois);
                                    listOos.put(result, oos);
                                    this.currentUser = result;
                                    userDAO.updateStatus(result);
                                    response.setMessage("Login success");
                                    response.setObject(result);
                                }
                            } else {
                                response.setMessage("Login fail");
                                response.setObject("Tài khoản hoặc mật khẩu không chính xác!!!");
                            }
                            oos.writeObject(response);
                            oos.flush();
                            break;
                        }
                        case "Get list user online": {
                            ServerView.log(this.currentUser + " request get list user online");
                            ArrayList<NguoiChoi> listUserOnline = userDAO.getListUser(1);
                            ServerView.log("Have " + listUserOnline.size() + " user online");
                            oos.writeObject(new Message("List user online", listUserOnline));
                            break;
                        }
                        case "Challenge to": {
                            NguoiChoi competitor = (NguoiChoi) request.getObject();
                            ServerView.log(this.currentUser.getUsername() + " challenge to " + competitor.getUsername());
                            listOos.get(competitor).writeObject(new Message("Challenge from", currentUser));
                            break;
                        }
                        case "Accept": {
                            NguoiChoi competitor = (NguoiChoi) request.getObject();
                            ServerView.log(this.currentUser.getUsername() + " accept challenge to " + competitor.getUsername());
                            initGame(currentUser, competitor);
                            Message response = new Message("Start game", tranDau);
                            listOos.get(competitor).writeObject(response);
                            listOos.get(currentUser).writeObject(response);
                            break;
                        }
                        case "Decline": {
                            NguoiChoi competitor = (NguoiChoi) request.getObject();
                            ServerView.log(this.currentUser.getUsername() + " decline challenge to " + competitor.getUsername());
                            request.setObject(this.currentUser);
                            listOos.get(competitor).writeObject(request);
                            break;
                        }
                        case "Send turn result": {
                            NguoiChoiVanChoi ncvc = (NguoiChoiVanChoi) request.getObject();
                            ServerView.log(ncvc.getNguoiChoi().getUsername() + " choose: " + listNuocChoi.get(ncvc.getNuocChoi()));
                            ncvcDAO.updateNCVC(ncvc);
                            break;
                        }
                        case "Get result turn": {
                            NguoiChoiVanChoi ncvc = (NguoiChoiVanChoi) request.getObject();
                            String rs = ncvcDAO.getResultTurn(ncvc.getVanChoi());
                            ServerView.log("Result: " + ncvc.getNguoiChoi().getUsername() + " " + rs);
                            Message response = new Message();
                            response.setMessage("Result turn");
                            response.setObject(rs);
                            oos.writeObject(response);
                            break;
                        }
                        case "Get result game": {
                            TranDau td = (TranDau) request.getObject();
                            String rs = ncvcDAO.getResultGame(td);
                            ServerView.log(currentUser.getUsername() + " get result game: " + rs);
                            Message response = new Message();
                            response.setMessage("Result game");
                            response.setObject(rs);
                            oos.writeObject(response);
                        }
                    }
                } catch (Exception e) {
                    listSocket.remove(currentUser);
                    listOis.remove(currentUser);
                    listOos.remove(currentUser);
                    currentUser.setUserStatus(0);
                    userDAO.updateStatus(currentUser);
                    ServerView.log(this.currentUser.getUsername() + " disconnected");
                    e.printStackTrace();
                    break;
                }
            }
        }
        
        public void initGame(NguoiChoi user1, NguoiChoi user2) {
            
            tranDau = tranDauDAO.createTranDau();
            
            user1.setUserStatus(2);
            userDAO.updateStatus(currentUser);
            user2.setUserStatus(2);
            userDAO.updateStatus(user2);
            
            ArrayList<VanChoi> listVanChoi = new ArrayList<>();

            for(int i = 1;i <= 3; i++) {
                VanChoi vanChoi = vanChoiDAO.createTurn(tranDau.getId());
                NguoiChoiVanChoi ncvc1 = ncvcDAO.createNCVC(user1.getId(), vanChoi.getId());
                ncvc1.setNguoiChoi(user1);
                ncvc1.setVanChoi(vanChoi);
                NguoiChoiVanChoi ncvc2 = ncvcDAO.createNCVC(user2.getId(), vanChoi.getId());
                ncvc2.setNguoiChoi(user2);
                ncvc2.setVanChoi(vanChoi);
                vanChoi.setNcvc1(ncvc1);
                vanChoi.setNcvc2(ncvc2);
                listVanChoi.add(vanChoi);
            }
            
            tranDau.setListVanChoi(listVanChoi);
        }
    }
}
