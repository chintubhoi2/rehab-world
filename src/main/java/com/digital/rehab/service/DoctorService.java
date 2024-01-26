package com.digital.rehab.service;


import java.util.List;
import com.digital.rehab.model.DoctorRequest;
import com.digital.rehab.model.DoctorResponse;

public interface DoctorService {


	public List<DoctorResponse> getAllDoctor();
	public DoctorResponse getDoctorById(Long doctorId);
	public DoctorResponse createDoctor(DoctorRequest doctorReq);
	public DoctorResponse updateDoctor(DoctorRequest doctorReq);
	public void deleteDoctor(Long id);
	
	
}
