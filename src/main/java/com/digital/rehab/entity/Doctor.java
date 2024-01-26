/*
 * package com.digital.rehab.entity;
 * 
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Entity; import
 * javax.persistence.FetchType; import javax.persistence.GeneratedValue; import
 * javax.persistence.GenerationType; import javax.persistence.ManyToMany; import
 * javax.persistence.OneToMany;
 * 
 * import com.fasterxml.jackson.annotation.JsonManagedReference;
 * 
 * import lombok.AllArgsConstructor; import lombok.Data; import lombok.Getter;
 * import lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Getter
 * 
 * @Setter
 * 
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Data
 * 
 * @Entity public class Doctor {
 * 
 * @javax.persistence.Id
 * 
 * @GeneratedValue(strategy = GenerationType.AUTO) private Long id; private
 * String empId; private String fistName; private String lastName; private
 * String specialty; private String educationalQualification; private String
 * professionalExperience; private String currentWorkingPlace; private String
 * email; private String phoneNumber; private String licenseNumber; private
 * double averageRating; private int totalRatings; private double latitude;
 * private double longitude;
 * 
 * @OneToMany(mappedBy = "doctor", cascade =
 * {CascadeType.ALL,CascadeType.REMOVE}, fetch = FetchType.LAZY) private
 * List<Address>addresses = new ArrayList<>();
 * 
 * @OneToMany(mappedBy = "doctor", cascade =
 * {CascadeType.ALL,CascadeType.REMOVE}, fetch = FetchType.LAZY) private
 * List<DoctorAvailability>availability = new ArrayList<>();
 * 
 * @JsonManagedReference
 * 
 * @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) private
 * List<Role> roles = new ArrayList<>();
 * 
 * }
 */