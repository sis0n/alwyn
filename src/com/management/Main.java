package com.management;

import com.management.repository.UserRepository;
import com.management.service.UserService;
import com.management.controller.UserController;
import com.management.view.UserView;

public class Main {
    public static void main(String[] args) {
        // Initialize layers
        UserRepository repository = new UserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service);
        UserView view = new UserView(controller);

        // Start the application
        view.showMenu();
    }
}
