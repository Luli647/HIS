package com.example.his_test1.controller;

import com.example.his_test1.dao.RegisterDao;
import com.example.his_test1.dao.UserDao;
import com.example.his_test1.entity.Register;
import com.example.his_test1.entity.User;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
    @Autowired
    RegisterDao registerDao;

    @Autowired
    UserDao userDao;

    @RequestMapping("/addRegister")
    public String addDep(@RequestBody Register register){
        int userid = register.getUserID();
        int registLeID  = userDao.getRegistLeID(userid);
        register.setRegistLeID(registLeID);
        String caseNumber = registerDao.getCaseNumber();
        register.setCaseNumber(Integer.toString(Integer.parseInt(caseNumber)+1));
        System.out.println(register);
        int i = registerDao.addRegister(register);
        return i>0? "success": "error";
    }
}
