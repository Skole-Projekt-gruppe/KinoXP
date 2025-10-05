package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.cglib.core.Local;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "StaffSchedule")

public class StaffSchedule
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer staffSchedule_id;
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    protected Staff staff;
    protected Date work_date;
    protected Time shift_start;
    protected Time shift_end;

    public StaffSchedule(){}

    public StaffSchedule(Integer staffSchedule_id, Staff staff, Date work_date, Time shift_start, Time shift_end)
    {
        this.staff = staff;
        this.work_date = work_date;
        this.shift_start = shift_start;
        this.shift_end = shift_end;
    }

    public Staff getStaff(){return staff;}
    public void setStaff(Staff staff){this.staff = staff;}

    public Date getWork_date(){return work_date;}
    public void setWork_date(Date work_date){this.work_date = work_date;}

    public Time getShift_start(){return shift_start;}
    public void setShift_start(Time shift_start){this.shift_start = shift_start;}

    public Time getShift_end(){return shift_end;}
    public void setShift_end(Time shift_end){this.shift_end = shift_end;}

}
