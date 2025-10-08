package dk.ek.kinoxp.catalog.common;

import dk.ek.kinoxp.catalog.model.Staff;
import dk.ek.kinoxp.catalog.model.StaffSchedule;
import dk.ek.kinoxp.catalog.repository.StaffRepository;
import dk.ek.kinoxp.catalog.repository.StaffScheduleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
@Order(2)
public class InitStaffScheduleData implements CommandLineRunner {

    private final StaffRepository staffRepository;
    private final StaffScheduleRepository staffScheduleRepository;

    public InitStaffScheduleData(StaffRepository staffRepository, StaffScheduleRepository staffScheduleRepository) {
        this.staffRepository = staffRepository;
        this.staffScheduleRepository = staffScheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (staffScheduleRepository.count() > 0) {
            System.out.println("StaffSchedule data already exists, skipping initialization.");
            return;
        }

        List<Staff> staffList = staffRepository.findAll();

        if (staffList.isEmpty()) {
            System.out.println("No staff found. Make sure InitStaffData runs first.");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date monday = sdf.parse("2025-10-06");
        Date tuesday = sdf.parse("2025-10-07");
        Date wednesday = sdf.parse("2025-10-08");
        Date thursday = sdf.parse("2025-10-09");
        Date friday = sdf.parse("2025-10-10");

        Time morningShift = Time.valueOf("09:00:00");
        Time eveningShift = Time.valueOf("17:00:00");

        for (Staff staff : staffList) {
            staffScheduleRepository.save(new StaffSchedule(null, staff, monday, morningShift, eveningShift));
            staffScheduleRepository.save(new StaffSchedule(null, staff, tuesday, morningShift, eveningShift));
            staffScheduleRepository.save(new StaffSchedule(null, staff, wednesday, morningShift, eveningShift));
            staffScheduleRepository.save(new StaffSchedule(null, staff, thursday, morningShift, eveningShift));
            staffScheduleRepository.save(new StaffSchedule(null, staff, friday, morningShift, eveningShift));
        }

        System.out.println("StaffSchedule data inserted for " + staffList.size() + " staff members.");
    }
}
