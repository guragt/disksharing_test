package guragt.disksharing.config;

import guragt.disksharing.model.Disk;
import guragt.disksharing.model.TakenItem;
import guragt.disksharing.model.User;
import guragt.disksharing.repository.DiskRepository;
import guragt.disksharing.repository.TakenItemRepository;
import guragt.disksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatabaseInitials {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DiskRepository diskRepository;

    @Autowired
    private TakenItemRepository takenItemRepository;


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            User user1 = new User();
            user1.setUsername("terminator");
            user1.setPassword(bcryptEncoder.encode("terminator"));
            userRepository.save(user1);

            User user2 = new User();
            user2.setUsername("baby");
            user2.setPassword(bcryptEncoder.encode("baby"));
            userRepository.save(user2);

            User user3 = new User();
            user3.setUsername("junior");
            user3.setPassword(bcryptEncoder.encode("junior"));
            userRepository.save(user3);

            User user4 = new User();
            user4.setUsername("sinior");
            user4.setPassword(bcryptEncoder.encode("sinior"));
            userRepository.save(user4);

            User user5 = new User();
            user5.setUsername("deputat");
            user5.setPassword(bcryptEncoder.encode("deputat"));
            userRepository.save(user5);

            Disk disk1 = new Disk();
            TakenItem takenItem1 = new TakenItem();
            disk1.setDescription("Manual for user");
            disk1.setOwner(user1);
            disk1.setTakenItem(takenItem1);
            takenItem1.setDisk(disk1);
            diskRepository.save(disk1);

            Disk disk2 = new Disk();
            TakenItem takenItem2 = new TakenItem();
            disk2.setDescription("How to save John");
            disk2.setOwner(user1);
            disk2.setTakenItem(takenItem2);
            takenItem2.setDisk(disk2);
            diskRepository.save(disk2);

            Disk disk3 = new Disk();
            TakenItem takenItem3 = new TakenItem();
            disk3.setDescription("How to save the world");
            disk3.setOwner(user1);
            disk3.setTakenItem(takenItem3);
            takenItem3.setDisk(disk3);
            diskRepository.save(disk3);

            Disk disk4 = new Disk();
            TakenItem takenItem4 = new TakenItem();
            disk4.setDescription("Baby tails");
            disk4.setOwner(user2);
            disk4.setTakenItem(takenItem4);
            takenItem4.setDisk(disk4);
            diskRepository.save(disk4);

            Disk disk5 = new Disk();
            TakenItem takenItem5 = new TakenItem();
            disk5.setDescription("Baby cartoons");
            disk5.setOwner(user2);
            disk5.setTakenItem(takenItem5);
            takenItem5.setDisk(disk5);
            diskRepository.save(disk5);

            Disk disk6 = new Disk();
            TakenItem takenItem6 = new TakenItem();
            disk6.setDescription("how to be happy");
            disk6.setOwner(user2);
            disk6.setTakenItem(takenItem6);
            takenItem6.setDisk(disk6);
            diskRepository.save(disk6);

            Disk disk7 = new Disk();
            TakenItem takenItem7 = new TakenItem();
            disk7.setDescription("javarush");
            disk7.setOwner(user2);
            disk7.setTakenItem(takenItem7);
            takenItem7.setDisk(disk7);
            diskRepository.save(disk7);

            Disk disk8 = new Disk();
            TakenItem takenItem8 = new TakenItem();
            disk8.setDescription("how to master java skill");
            disk8.setOwner(user4);
            disk8.setTakenItem(takenItem8);
            takenItem8.setDisk(disk8);
            diskRepository.save(disk8);

            Disk disk9 = new Disk();
            TakenItem takenItem9 = new TakenItem();
            disk9.setDescription("how to do nothing");
            disk9.setOwner(user4);
            disk9.setTakenItem(takenItem9);
            takenItem9.setDisk(disk9);
            diskRepository.save(disk9);

            takenItemRepository.takeDisk(1L, user3);
            takenItemRepository.takeDisk(2L, user5);
            takenItemRepository.takeDisk(8L, user3);
        };
    }
}
