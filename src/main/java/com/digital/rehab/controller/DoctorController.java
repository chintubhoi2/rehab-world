/*
 * package com.digital.rehab.controller;
 * 
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.DeleteMapping; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.digital.rehab.service.DoctorService;
 * 
 * 
 * @RestController
 * 
 * @RequestMapping("/doctor") public class DoctorController {
 * 
 * @Autowired private DoctorService doctorService;
 * 
 * 
 * @Autowired private DoctorAvailabilityService availabilityService;
 * 
 * 
 * 
 * 
 * public DoctorController(DoctorAvailabilityService availabilityService) {
 * this.availabilityService = availabilityService; }
 * 
 * 
 * @GetMapping("/getAll") public List<Doctor> getAllDoctor() {
 * 
 * return doctorService.getAllDoctor(); }
 * 
 * @GetMapping("/{id}") public Doctor getDoctorById(@PathVariable Long id) {
 * return doctorService.getDoctorById(id); }
 * 
 * @PostMapping("/save") public ResponseEntity<?> saveDoctor(@RequestBody
 * DoctorRequest doctorReq) { ResponseEntity<?> resp = null; DoctorResponse
 * dResp = new DoctorResponse(); dResp = doctorService.createDoctor(doctorReq);
 * resp = new ResponseEntity<>(dResp, HttpStatus.ACCEPTED); return resp;
 * 
 * }
 * 
 * 
 * @PostMapping("/update") public ResponseEntity<?> updateDoctor(@RequestBody
 * DoctorRequest doctorReq) { ResponseEntity<?> resp = null; DoctorResponse
 * dResp = new DoctorResponse();
 * 
 * dResp = doctorService.updateDoctor(doctorReq); resp = new
 * ResponseEntity<>(dResp, HttpStatus.ACCEPTED); return resp;
 * 
 * }
 * 
 * 
 * @DeleteMapping("/{id}") public void deleteDoctor(@PathVariable Long id) {
 * doctorService.deleteDoctor(id); }
 * 
 * @PostMapping("/{doctorId}/availability") public
 * ResponseEntity<DoctorAvailability> addAvailability(
 * 
 * @PathVariable Long doctorId,
 * 
 * @RequestBody DoctorAvailability availability) {
 * 
 * // Step 1: Validate the request if (availability == null ||
 * availability.getDayOfWeek() == null || availability.getStartTime() == null ||
 * availability.getEndTime() == null) { return
 * ResponseEntity.badRequest().build(); }
 * 
 * // Step 2: Check if the doctor with the given ID exists Doctor doctor =
 * doctorService.getDoctorById(doctorId); if (doctor == null) { return
 * ResponseEntity.notFound().build(); }
 * 
 * // Step 3: Associate the availability with the doctor
 * availability.setDoctor(doctor);
 * 
 * // Step 4: Save the availability slot DoctorAvailability savedAvailability =
 * availabilityService.addDoctorAvailability(availability);
 * 
 * if (savedAvailability != null) { // Step 5: Return the saved availability
 * slot return
 * ResponseEntity.status(HttpStatus.CREATED).body(savedAvailability); } else {
 * return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); } }
 * 
 * @GetMapping("/{doctorId}/availability") public
 * ResponseEntity<List<DoctorAvailability>> getAvailability(@PathVariable Long
 * doctorId) { return null; // Retrieve and return all availability slots for
 * the specified doctor }
 * 
 * }
 */