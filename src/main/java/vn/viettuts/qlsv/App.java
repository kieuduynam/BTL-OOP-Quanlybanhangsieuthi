package vn.viettuts.qlsv;
import vn.viettuts.qlsv.controller.LoginController;
import vn.viettuts.qlsv.view.LoginView;
import java.awt.EventQueue;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}