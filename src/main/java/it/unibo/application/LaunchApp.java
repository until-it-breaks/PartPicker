package it.unibo.application;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.model.Model;
import it.unibo.application.view.View;
import java.sql.Connection;

public class LaunchApp {
    public static void main(String[] args) {
        Connection connection = DAOUtils.localMySQLConnection("partpicker", "root", "");
        Model model = new Model(connection);
        View view = new View();
        Controller controller = new Controller(model, view);
        view.setController(controller);
        view.setUp();
    }
}
