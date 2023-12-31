package com.kevinraupp.studyws.config;

import com.kevinraupp.studyws.entities.*;
import com.kevinraupp.studyws.entities.enums.OrderStatus;
import com.kevinraupp.studyws.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        Category category01 = new Category("Eletronics");
        Category category02 = new Category("Books");
        Category category03 = new Category("Computers");

        Product p01 = new Product( "Game of Thrones", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p02 = new Product("Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p03 = new Product("Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p04 = new Product("PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p05 = new Product("Harry Potter", "Cras fringilla convallis sem vel faucibus.", 100.99, "");
        categoryRepository.saveAll(Arrays.asList(category01,category02,category03));
        productRepository.saveAll(Arrays.asList(p01,p02,p03,p04,p05));

        p01.getCategories().add(category02);
        p02.getCategories().add(category01);
        p02.getCategories().add(category03);
        p03.getCategories().add(category03);
        p04.getCategories().add(category03);
        p05.getCategories().add(category02);
        productRepository.saveAll(Arrays.asList(p01,p02,p03,p04,p05));

        User user01 = new User("Teste01","email@emailteste.com","21312312","password01");
        User user02 = new User("kevin","contato@kevinraupp.com","321312321","passwordkevin");
        User user03 = new User("Teste03","contato@testeemail.com","7657655465","password02");
        userRepository.saveAll(Arrays.asList(user01,user02,user03));


        Order order01 = new Order(Instant.now(),user01,OrderStatus.SHIPPED);
        Order order02 = new Order(Instant.now(),user02,OrderStatus.DELIVERED);
        Order order03 = new Order(Instant.now(),user02,OrderStatus.CANCELED);
        Order order04 = new Order(Instant.parse("2003-04-06T12:00:00Z"),user03, OrderStatus.PAID);
        orderRepository.saveAll(Arrays.asList(order01,order02,order03,order04));

        OrderItem oi01 = new OrderItem(order01, p01, 2, p01.getPrice());
        OrderItem oi02 = new OrderItem(order01, p03, 1, p03.getPrice());
        OrderItem oi03 = new OrderItem(order02, p03, 2, p03.getPrice());
        OrderItem oi04 = new OrderItem(order03, p05, 2, p05.getPrice());
        orderItemRepository.saveAll(Arrays.asList(oi01,oi02,oi03,oi04));

        Payment payment01 = new Payment(Instant.now().plus(15, ChronoUnit.MINUTES),order01);
        order01.setPayment(payment01);

        orderRepository.save(order01);

    }
}
