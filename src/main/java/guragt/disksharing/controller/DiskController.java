package guragt.disksharing.controller;

import guragt.disksharing.model.Disk;
import guragt.disksharing.model.TakenItem;
import guragt.disksharing.model.User;
import guragt.disksharing.repository.DiskRepository;
import guragt.disksharing.repository.TakenItemRepository;
import guragt.disksharing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("disks")
public class DiskController {

    @Autowired
    private DiskRepository diskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TakenItemRepository takenItemRepository;

    //дістати всі вільні диски
    @GetMapping("/free")
    List<Disk> getFreeDisc() {
        return diskRepository.getFreeDisk();
    }

    // диски, які взяв юзер
    @GetMapping("/by-user")
    List<Disk> getTakenItemByUser(Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        return diskRepository.getDiskTakenByUser(user.getId());
    }

    // диски, які взяли у юзера
    @GetMapping("/from-user")
    List<Disk> getTakenItemFromUser(Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        return diskRepository.getDiskTakenFromUser(user.getId());
    }

    // створити новий диск
    @PostMapping()
    Disk addNew(@RequestBody Disk disk, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        TakenItem takenItem = new TakenItem();
        disk.setOwner(user);
        disk.setTakenItem(takenItem);
        takenItem.setDisk(disk);
        return diskRepository.save(disk);
    }

    // взяти диск
    @PutMapping("/take")
    String takeDisk(@RequestBody Disk disk, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        int result = takenItemRepository.takeDisk(disk.getId(), user);
        if (result == 1)
            return "Disk is taken";
        else
            return "Disk is not taken";
    }

    // повернути диск
    @PutMapping("/return")
    String returnDisk(@RequestBody Disk disk, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        int result = takenItemRepository.returnDisk(disk.getId(), user);
        if (result == 1)
            return "Disk is returned";
        else
            return "Disk is not returned";
    }

}
