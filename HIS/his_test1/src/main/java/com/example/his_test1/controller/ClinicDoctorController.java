package com.example.his_test1.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.his_test1.entity.*;
import com.example.his_test1.service.ClinicDoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClinicDoctorController {

    @Autowired
    ClinicDoctorService clinicDoctorService;

    @RequestMapping("/clinicDoctor/patientbyUserID")
    public String getPatientListbyUserID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }
        }

        List<Register> registers =  clinicDoctorService.findRegisterByUserID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(registers);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/patientDonebyUserID")
    public String getPatientDoneListbyUserID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<Register> registers =  clinicDoctorService.findRegisterDoneByUserID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(registers);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/patientbyDeptID")
    public String getPatientListbyDeptID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<Register> registers =  clinicDoctorService.findRegisterByDeptID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(registers);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/patientDonebyDeptID")
    public String getPatientDoneListbyDeptID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<Register> registers =  clinicDoctorService.findRegisterDoneByDeptID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(registers);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/medicalRecordbyCaseNumber")
    public String getPatientRecordbyCaseNumber(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        MedicalRecord medicalRecords =  clinicDoctorService.findMedicalRecordbyCaseNumber(str2);
        String res_string = JSON.toJSONString(medicalRecords);
        if (res_string.equals("null")){
            String str_result = "{\"caseState\":0}";
            return str_result;
        }
        return res_string;
    }

    @PostMapping("/clinicDoctor/updateMedicalRecord")
    public String createDrug(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord m = clinicDoctorService.findMedicalRecordbyCaseNumber(medicalRecord.getCaseNumber());
        if(m==null){
            clinicDoctorService.creatMedicalRecord(medicalRecord);
        }
        else{
            clinicDoctorService.updateMedicalRecord(medicalRecord);
        }
        return "success";
    }

    @RequestMapping("/clinicDoctor/medicalDiseasebyMedicalID")
    public String getmedicalDiseasebyMedicalID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }
        }
        List<MedicalDisease> medicalDiseases =  clinicDoctorService.findmedicalDiseasebyMedicalID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(medicalDiseases);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/checkApplybyMedicalID")
    public String getCheckApplybyMedicalID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<CheckApply> checkApplies =  clinicDoctorService.findCheckApplybyMedicalID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(checkApplies);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/checkTemplatebyUserID")
    public String getCheckTemplatebyUserID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<CheckTemplate> checkTemplates =  clinicDoctorService.findCheckTemplateByUserID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(checkTemplates);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/prescriptionbyUserID")
    public String getPrescriptionbyUserID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<Prescription> prescriptions =  clinicDoctorService.findPrescriptionbyUserID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(prescriptions);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/prescriptionDetailedbyPrescriptionID")
    public String getPrescriptionDetailsbyPrescriptionID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        System.out.println(str2);
        List<PrescriptionDetailed> prescriptionDetaileds =  clinicDoctorService.findPrescriptionDetailsbyPrescriptionID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(prescriptionDetaileds);
        System.out.println(res_string);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/drugsTemplatebyUserID")
    public String getDrugsTemplatebyUserID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<DrugsTemplate> drugsTemplates =  clinicDoctorService.findDrugsTemplateByUserID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(drugsTemplates);
        return res_string;
    }

    @RequestMapping("/clinicDoctor/drugsDetailedbyDrugsTempID")
    public String getDrugsDetailedbyDrugsTempID(@RequestBody String str){
        String str2="";
        if(str != null && !"".equals(str)){
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)>=48 && str.charAt(i)<=57){
                    str2+=str.charAt(i);
                }
            }

        }
        List<DrugsDetailed> drugsDetaileds =  clinicDoctorService.findDrugsDetailedbyDrugsTempID(Integer.parseInt(str2));
        String res_string = JSON.toJSONString(drugsDetaileds);
        System.out.println(res_string);
        return res_string;
    }
}
