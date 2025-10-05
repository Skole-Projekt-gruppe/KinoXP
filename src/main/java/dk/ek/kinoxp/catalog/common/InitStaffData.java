package dk.ek.kinoxp.catalog.common;

import dk.ek.kinoxp.catalog.model.Staff;
import dk.ek.kinoxp.catalog.repository.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class InitStaffData implements CommandLineRunner
{
    private final StaffRepository staffRepository;

    public InitStaffData(StaffRepository staffRepository)
    {
        this.staffRepository = staffRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Staff staff1 = new Staff(null, "Peter Jensen", "Manager");
        staffRepository.save(staff1);

        Staff staff2 = new Staff(null, "Maria Hansen", "Cashier");
        staffRepository.save(staff2);

        Staff staff3 = new Staff(null, "Jonas Nielsen", "Projectionist");
        staffRepository.save(staff3);

        Staff staff4 = new Staff(null, "Sofie Larsen", "Usher");
        staffRepository.save(staff4);

        Staff staff5 = new Staff(null, "Mads Kristensen", "Technician");
        staffRepository.save(staff5);

        Staff staff6 = new Staff(null, "Line Sørensen", "Customer Service");
        staffRepository.save(staff6);

        Staff staff7 = new Staff(null, "Anders Pedersen", "Cleaner");
        staffRepository.save(staff7);

        Staff staff8 = new Staff(null, "Camilla Mortensen", "Assistant Manager");
        staffRepository.save(staff8);

        Staff staff9 = new Staff(null, "Frederik Olsen", "Snack Bar Attendant");
        staffRepository.save(staff9);

        Staff staff10 = new Staff(null, "Emma Thomsen", "Ticket Checker");
        staffRepository.save(staff10);
    }

}
