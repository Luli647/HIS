package com.example.his_test1.service;

import com.example.his_test1.dao.*;
import com.example.his_test1.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicDoctorService {

    @Autowired
    RegisterDao registerDao;
    @Autowired
    MedicalRecordDao medicalRecordDao;
    @Autowired
    MedicalDiseaseDao medicalDiseaseDao;
    @Autowired
    CheckApplyDao checkApplyDao;
    @Autowired
    CheckTemplateDao checkTemplateDao;
    @Autowired
    PrescriptionDAO prescriptionDAO;
    @Autowired
    PrescriptionDetailedDAO prescriptionDetailedDAO;
    @Autowired
    DrugsTemplateDao drugsTemplateDao;
    @Autowired
    DrugsDetailedDao drugsDetailedDao;

    public List<Register> findRegisterByUserID(int UserID) {
        return registerDao.findByUserID(UserID);
    }

    public List<Register> findRegisterDoneByUserID(int UserID) {
        return registerDao.findDoneByUserID(UserID);
    }

    public List<Register> findRegisterByDeptID(int DeptID) {

        return registerDao.findByDeptID(DeptID);
    }

    public List<Register> findRegisterDoneByDeptID(int DeptID) {

        return registerDao.findDoneByDeptID(DeptID);
    }

    public MedicalRecord findMedicalRecordbyCaseNumber(String CaseNumber) {
        return medicalRecordDao.findMedicalRecordbyCaseNumber(CaseNumber);
    }

    public void creatMedicalRecord(MedicalRecord medicalRecord){
        medicalRecordDao.creatMedicalRecord(medicalRecord);
    }
    public void updateMedicalRecord(MedicalRecord medicalRecord){
        medicalRecordDao.updateMedicalRecord(medicalRecord);
    }

    public List<MedicalDisease> findmedicalDiseasebyMedicalID(int MedicalID){
        return medicalDiseaseDao.findmedicalDiseasebyMedicalID(MedicalID);
    }

    public List<CheckApply> findCheckApplybyMedicalID(int MedicalID){
        return checkApplyDao.findCheckApplybyMedicalID(MedicalID);
    }

    public List<CheckTemplate> findCheckTemplateByUserID(int UserID) {
        return checkTemplateDao.findCheckTemplateByUserID(UserID);
    }

    public List<Prescription> findPrescriptionbyUserID(int UserID) {
        return prescriptionDAO.findByUserID(UserID);
    }

    public List<PrescriptionDetailed> findPrescriptionDetailsbyPrescriptionID(int PrescriptionID) {
        return prescriptionDetailedDAO.findPrescriptionDetailsbyPrescriptionID(PrescriptionID);
    }

    public List<DrugsTemplate> findDrugsTemplateByUserID(int UserID) {
        return drugsTemplateDao.findDrugsTemplateByUserID(UserID);
    }

    public List<DrugsDetailed> findDrugsDetailedbyDrugsTempID(int DrugsTempID) {
        return drugsDetailedDao.findDrugsDetailedbyDrugsTempID(DrugsTempID);
    }
}
