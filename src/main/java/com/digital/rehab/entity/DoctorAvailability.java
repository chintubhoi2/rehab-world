/*
 * package com.digital.rehab.entity;
 * 
 * import java.time.DayOfWeek; import java.time.LocalTime;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Entity; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.ManyToOne;
 * 
 * import lombok.AllArgsConstructor; import lombok.Data; import lombok.Getter;
 * import lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Entity
 * 
 * @Data
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @NoArgsConstructor
 * 
 * @AllArgsConstructor public class DoctorAvailability {
 * 
 * @Id
 * 
 * @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id; private
 * DayOfWeek dayOfWeek; private LocalTime startTime; private LocalTime endTime;
 * 
 * @ManyToOne(cascade = CascadeType.PERSIST)
 * 
 * @JoinColumn(name = "user_id") private Doctor doctor;
 * 
 * // Additional fields private int maxAppointmentsPerSlot; // Maximum number of
 * appointments allowed in this slot private boolean isRecurring; // Indicates
 * if this availability is recurring (e.g., weekly) private boolean isAvailable;
 * // Indicates if this slot is currently available for booking
 * 
 * 
 * // Additional fields if needed, e.g., a reference to the doctor
 * 
 * // Constructors, getters, setters }
 */