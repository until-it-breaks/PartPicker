package it.unibo.application;

import it.unibo.application.controller.Controller;
import it.unibo.application.data.DAOUtils;
import it.unibo.application.model.Model;
import it.unibo.application.view.View;
import java.sql.Connection;

public class LaunchApp {
    public static void main(final String[] args) {
        final Connection connection = DAOUtils.localMySQLConnection("partpicker", "root", "");
        final Model model = new Model(connection);
        final View view = new View();
        final Controller controller = new Controller(model, view);
        view.setController(controller);
        view.setUp();
    }
}
