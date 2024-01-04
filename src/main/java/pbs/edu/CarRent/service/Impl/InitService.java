package pbs.edu.CarRent.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pbs.edu.CarRent.model.*;
import pbs.edu.CarRent.repository.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
public class InitService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final SalonRepository salonRepository;
    private final CarRepository carRepository;
    private final EmployeeRepository employeeRepository;
    private final ReservationRepository reservationRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {

        //add roles to db
        Role r1 = new Role();
        r1.setId(1);
        r1.setName(RoleName.ROLE_USER);
        Role r2 = new Role();
        r2.setId(2);
        r2.setName(RoleName.ROLE_ADMIN);
        Role r3 = new Role();
        r3.setId(3);
        r3.setName(RoleName.ROLE_MODERATOR);
        Role r4 = new Role();
        r4.setId(4);
        r4.setName(RoleName.ROLE_EMPLOYEE);

        roleRepository.save(r1);
        roleRepository.save(r2);
        roleRepository.save(r3);
        roleRepository.save(r4);

        Role r5 = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(NoSuchElementException::new);
        Role r7 = roleRepository.findByName(RoleName.ROLE_EMPLOYEE).orElseThrow(NoSuchElementException::new);
        //add users
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        User u1 = new User("test", "test","test@test.com", "000000000", "testLocation", null, "test_u1", "test_u1");
        u1.setPassword(passwordEncoder.encode(u1.getPassword()));
        Set<Role> r_u1 = new HashSet<>();
        r_u1.add(r1);
        r_u1.add(r2);
        r_u1.add(r3);
        u1.setRoles(r_u1);

        userRepository.save(u1);


        User u2 = new User("Andrzej", "Nowak", "andrzej.n@wp.pl", "504930194", "Kwiatowa 14 Bydgoszcz", Date.valueOf(LocalDate.parse("12-05-1999", formatter)), "andrzejnowak", "98fniasni8x!");
        u2.setPassword(passwordEncoder.encode(u2.getPassword()));
        Set<Role> r_u2 = new HashSet<>();
        r_u2.add(r1);
        u2.setRoles(r_u2);
        userRepository.save(u2);

        User u3 = new User("Michał", "Kowalski", "michalk123@o2.pl", "501850185", "Orzechowa 30 Bydgoszcz", Date.valueOf(LocalDate.parse("19-01-1989", formatter)), "michal123", "micj293k*");
        u3.setPassword(passwordEncoder.encode(u3.getPassword()));
        Set<Role> r_u3 = new HashSet<>();
        r_u3.add(r1);
        u3.setRoles(r_u3);
        userRepository.save(u3);

        User u4 = new User("Ewa", "Kot", "ewakot3@gmail.com", "789104940", "Grunwaldzka 19/3 Bydgoszcz", Date.valueOf(LocalDate.parse("24-12-1980", formatter)), "ewakot55", "mirnai321)");
        u4.setPassword(passwordEncoder.encode(u4.getPassword()));
        Set<Role> r_u4 = new HashSet<>();
        r_u4.add(r1);
        u4.setRoles(r_u4);
        userRepository.save(u4);

        User u5 = new User("Artur", "Drukarski", "arturdk@wp.pl", "578109109", "Chlebowa 14 Biale Blota", Date.valueOf(LocalDate.parse("13-11-1992", formatter)), "arturrr3", "poon13ac3&");
        u5.setPassword(passwordEncoder.encode(u5.getPassword()));
        Set<Role> r_u5 = new HashSet<>();
        r_u5.add(r1);
        u5.setRoles(r_u5);
        userRepository.save(u5);

        User u6 = new User("Grzegorz", "Matczak", "grzemat33@interia.pl", "581059105", "Bydgoska 15 Poznań", Date.valueOf(LocalDate.parse("12-10-1993", formatter)), "grzeg123", "nonwq122;");
        u6.setPassword(passwordEncoder.encode(u6.getPassword()));
        Set<Role> r_u6 = new HashSet<>();
        r_u6.add(r1);
        u6.setRoles(r_u6);
        userRepository.save(u6);

        User u7 = new User("Hubert", "Tyczka", "hubtyc@gmail.com", "617598190", "Dworcowa 15 Poznań", Date.valueOf(LocalDate.parse("15-11-1996", formatter)), "hubm3233", "124mino5*");
        u7.setPassword(passwordEncoder.encode(u7.getPassword()));
        Set<Role> r_u7 = new HashSet<>();
        r_u7.add(r1);
        u7.setRoles(r_u7);
        userRepository.save(u7);

        User u8 = new User("Maciej", "Galaz", "macgal", "581895105", "Golebia 156 Poznan", Date.valueOf(LocalDate.parse("05-06-1998", formatter)), "maciej3", "1232mmkl4%");
        u8.setPassword(passwordEncoder.encode(u8.getPassword()));
        Set<Role> r_u8 = new HashSet<>();
        r_u8.add(r1);
        u8.setRoles(r_u8);
        userRepository.save(u8);

        User u9 = new User("Blazej", "Piotrowicz", "blaz3@interia.pl","123295155", "Jasna 15 Poznan", Date.valueOf(LocalDate.parse("12-07-1982", formatter)), "blaz123", "123mamp9{");
        u9.setPassword(passwordEncoder.encode(u9.getPassword()));
        Set<Role> r_u9 = new HashSet<>();
        r_u9.add(r1);
        u9.setRoles(r_u9);
        userRepository.save(u9);

        User u10 = new User("Krzysztof", "Nowak", "krzynow2@o2.pl", "123295144", "Dluga 2 Poznan", Date.valueOf(LocalDate.parse("09-01-1993", formatter)), "krzyfc", "aminon12)");
        u10.setPassword(passwordEncoder.encode(u10.getPassword()));
        Set<Role> r_u10 = new HashSet<>();
        r_u10.add(r1);
        u10.setRoles(r_u10);
        userRepository.save(u10);

        User u11 = new User("Tomasz", "Pat", "tomek123@gmail.com", "123295133","Piekna 12 Warszawa", Date.valueOf(LocalDate.parse("19-12-2000", formatter)), "tomm3", "fnlasn122!");
        u11.setPassword(passwordEncoder.encode(u11.getPassword()));
        Set<Role> r_u11 = new HashSet<>();
        r_u11.add(r1);
        u11.setRoles(r_u11);
        userRepository.save(u11);

        User u12 = new User("Bogdan", "Lewandowski", "boglew9@wp.pl", "123295122","Miejska 12 Warszawa", Date.valueOf(LocalDate.parse("12-09-1999", formatter)), "bogdan2", "fasnon12^");
        u12.setPassword(passwordEncoder.encode(u12.getPassword()));
        Set<Role> r_u12 = new HashSet<>();
        r_u12.add(r1);
        u12.setRoles(r_u12);
        userRepository.save(u12);

        User u13 = new User("Karolina", "Bednarek", "karolina.b@interia.pl", "123295105","Krzywa 13 Warszawa", Date.valueOf(LocalDate.parse("10-10-1990", formatter)), "karolina12", "ksan13m;1");
        u13.setPassword(passwordEncoder.encode(u13.getPassword()));
        Set<Role> r_u13 = new HashSet<>();
        r_u13.add(r4);
        u13.setRoles(r_u13);
        userRepository.save(u13);

        User u14 = new User("Szymon", "Kimala", "szymonn3fd@gmail.com", "123295112","Twarogowa 13 Warszawa", Date.valueOf(LocalDate.parse("12-11-1978", formatter)), "szmom31", "asmdm12@");
        u14.setPassword(passwordEncoder.encode(u14.getPassword()));
        Set<Role> r_u14 = new HashSet<>();
        r_u14.add(r4);
        u14.setRoles(r_u14);
        userRepository.save(u14);


        User u15 = new User("Karol", "Fontanna", "karolfontanna@gmail.com", "123295111","Poznanska 13 Warszawa", Date.valueOf(LocalDate.parse("11-12-1973", formatter)), "karolrr", "anson133*");
        u15.setPassword(passwordEncoder.encode(u15.getPassword()));
        Set<Role> r_u15 = new HashSet<>();
        r_u15.add(r4);
        u15.setRoles(r_u15);
        userRepository.save(u15);

        //salony
        Salon s1 = salonRepository.save(new Salon(0l, "Bydgoszcz", "Fordonska 123"));
        Salon s2 = salonRepository.save(new Salon(0l, "Warszawa", "Bydgoska 13"));
        Salon s3 = salonRepository.save(new Salon(0l, "Poznan", "Mewia 12"));

        //pracownicy
        Employee e1 = employeeRepository.save(new Employee(0l, u13, s1));
        Employee e2 = employeeRepository.save(new Employee(0l, u14, s2));
        Employee e3 = employeeRepository.save(new Employee(0l, u15, s3));

        // samochody
        Car c1 = carRepository.save(new Car(0l, "Passat", "2012, czerwony", "Volkswagen", null, s1, true));
        Car c2 = carRepository.save(new Car(0l, "Corolla", "2016, srebrny", "Toyota", null, s1, true));
        Car c3 = carRepository.save(new Car(0l, "Yaris", "2010, niebieski", "Toyota", null, s1, true));
        Car c4 = carRepository.save(new Car(0l, "Octavia", "2013, czarny", "Skoda", null, s1, true));
        Car c5 = carRepository.save(new Car(0l, "Duster", "2016, zielony", "Dacia", null, s1, true));
        Car c6 = carRepository.save(new Car(0l, "Fabia", "2015, czerwony", "Skoda", null, s2, true));
        Car c7 = carRepository.save(new Car(0l, "Sportage", "2019, niebieski", "Kia", null, s2, true));
        Car c8 = carRepository.save(new Car(0l, "Golf", "2010, granatowy", "Volkswagen", null, s2, true));
        Car c9 = carRepository.save(new Car(0l, "Corsa", "2011, niebieski", "Opel", null, s2, true));
        Car c10 = carRepository.save(new Car(0l, "Zafira", "2016, czarny", "Opel", null, s2, true));
        Car c11 = carRepository.save(new Car(0l, "X3", "2020, czarny", "BMW", null, s3, true));
        Car c12 = carRepository.save(new Car(0l, "C3", "2018, zielony", "Citroen", null, s3, true));
        Car c13 = carRepository.save(new Car(0l, "Leon", "2011, srebrny", "Seat", null, s3, true));
        Car c14 = carRepository.save(new Car(0l, "I20", "2017, czarny", "Hyundai", null, s3, true));
        Car c15 = carRepository.save(new Car(0l, "Q3", "2020, czarny", "Audi", null, s3, true));
    }
}