package com.example.his_test1.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import com.example.his_test1.entity.Department;
import com.example.his_test1.entity.Drug;
import com.example.his_test1.service.DrugManagementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
public class DrugManagementController {

	@Autowired
	DrugManagementService drugManagementService;
	
	@RequestMapping("/drugManagement/allDrugs")
	public String getDepList(){
        int num = drugManagementService.getDrugNum();
        List<Drug> drugs =  drugManagementService.findAllDrugs();
        String res_string = JSON.toJSONString(drugs);
        return res_string;
    }
	
	@RequestMapping("/drugManagement/allMnemonicCodes")
	public String getAllMnemonicCodes() {
		List<String> mnemonicCodes = drugManagementService.findAllMnemonicCodes();
		JSONArray json = new JSONArray();
		for(String mnemonicCode : mnemonicCodes) {
			JSONObject jo = new JSONObject();
			jo.put("value", mnemonicCode);
			json.add(jo);
		}
		String res_string = JSON.toJSONString(json);
		return res_string;
	}
	
	@PostMapping("/drugManagement/searchDrug")
	public String getAllDrugs(@RequestBody String param) {
		JSONObject json = JSON.parseObject(param);
		System.out.println(param);
		List<Drug> drugs = drugManagementService.findDrugByMnemonicCode(json.getString("mnemonicCode"));
		String res_string = JSON.toJSONString(drugs);
		System.out.println(res_string);
		return res_string;
	}
	
	@PostMapping("/drugManagement/createDrug")
	public String createDrug(@RequestBody Drug drug) {
		System.out.println(drug);
		drugManagementService.insertDrug(drug);
		return "success";
	}
	
	
	
	@PostMapping("/drugManagement/importDrug")
	public String importDrug(@RequestBody MultipartFile file) throws IOException {

		//获取文件内字符串
		InputStream is = file.getInputStream();
		byte[] bytes = new byte[0];
		bytes = new byte[is.available()];
		is.read(bytes);
		String str = new String(bytes);
		
		//json解析
		JSONObject obj=JSONObject.parseObject(str);
		JSONArray array = obj.getJSONArray("data");
		String js = JSONObject.toJSONString(array);
	    List<Drug> drugs = JSON.parseArray(js, Drug.class);
	    for(Drug drug:drugs) {
	    	System.out.println(drug);
	    }
	    
	    drugManagementService.importDrugs(drugs);
	    
		return "success";
	}
	
	@PostMapping("/drugManagement/deleteDrug")
	public String deleteDrug(@RequestBody Drug drug) {
		System.out.println(drug);
		drugManagementService.deleteDrug(drug);
		return "success";
	}
	
	@PostMapping("/drugManagement/deleteMultipleDrug")
	public String deleteMultipleDrug(@RequestBody List<Drug> drugs) {
		for(Drug drug:drugs) {
			System.out.println(drug);
			drugManagementService.deleteDrug(drug);
		}
		return "success";
	}
	
	@PostMapping("/drugManagement/updateDrug")
	public String updateDrug(@RequestBody Drug drug) {
		drugManagementService.updateDrug(drug);
		return "success";
	}
	
    @GetMapping("/drugManagement/allDrugs/{pageNum}/{pageSize}")
    public PageInfo<Drug> selectByPage(@PathVariable int pageNum, @PathVariable int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Drug> drugs = drugManagementService.findAllDrugs();
        PageInfo<Drug> pageInfo = new PageInfo<>(drugs);
        
        return pageInfo;
    }
    
    
}
