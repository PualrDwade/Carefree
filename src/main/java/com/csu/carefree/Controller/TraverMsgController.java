package com.csu.carefree.Controller;


import com.csu.carefree.Service.TraverMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TraverMsgController {
    @Autowired
    private TraverMsgService traverMsgService;
}
