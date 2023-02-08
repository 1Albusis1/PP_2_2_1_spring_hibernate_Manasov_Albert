package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class, CarService.class);

      User user1 = new User("User1", "LastName1", "user1@mail.ru");
      User user2 = new User("User2", "LastName2", "user2@mail.ru");
      User user3 = new User("User3", "LastName3", "user3@mail.ru");
      User user4 = new User("User4", "LastName4", "user4@mail.ru");

      Car car1 = new Car("model1", 11);
      Car car2 = new Car("model2", 22);
      Car car3 = new Car("model3", 33);
      Car car4 = new Car("model4", 44);

      user1.setUserCar(car1);
      user2.setUserCar(car2);
      user3.setUserCar(car3);
      user4.setUserCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Model = " + user.getUserCar().getModel());
         System.out.println("Series = " + user.getUserCar().getSeries());
         System.out.println();
      }


      List<User> users2 = userService.getUserByCar("model2", 22);
      for (User user : users2) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
