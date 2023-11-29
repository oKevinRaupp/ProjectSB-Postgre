package com.kevinraupp.studyws.config;

import com.kevinraupp.studyws.entities.Category;
import com.kevinraupp.studyws.entities.Order;
import com.kevinraupp.studyws.entities.User;
import com.kevinraupp.studyws.entities.enums.OrderStatus;
import com.kevinraupp.studyws.repositories.CategoryRepository;
import com.kevinraupp.studyws.repositories.OrderRepository;
import com.kevinraupp.studyws.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category01 = new Category("Eletronics");
        Category category02 = new Category("Books");
        Category category03 = new Category("Computers");

        categoryRepository.saveAll(Arrays.asList(category01,category02,category03));


        User user01 = new User("Teste01","email@emailteste.com","21312312","password01");
        User user02 = new User("kevin","contato@kevinraupp.com","321312321","passwordkevin");
        User user03 = new User("Teste03","contato@testeemail.com","7657655465","password02");


        Order order01 = new Order(Instant.now(),user01,OrderStatus.SHIPPED);
        Order order02 = new Order(Instant.now(),user02,OrderStatus.DELIVERED);
        Order order03 = new Order(Instant.now(),user02,OrderStatus.CANCELED);
        Order order04 = new Order(Instant.parse("2003-04-06T12:00:00Z"),user03, OrderStatus.PAID);


        userRepository.saveAll(Arrays.asList(user01,user02,user03));
        orderRepository.saveAll(Arrays.asList(order01,order02,order03,order04));
    }
}
