package com.csu.carefree.Controller;

import com.csu.carefree.Model.TraverMsg.ScenicMsg;
import com.csu.carefree.Model.TraverMsg.TraverMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/*
 *   Great by WLX
 */
public class TravelInfoController {
    @Autowired
    private Travel_InfoService travel_infoService;

    //请求主界面
    @GetMapping("/index")
    public String viewIndex(HttpSession session , Model model){
        //行程信息存在session当中
        TraverMsg traverMsg = (TraverMsg)session.getAttribute("traverMsg");
        if (traverMsg==null)
            session.setAttribute("traverMsg", new TraverMsg());
        traverMsg = (TraverMsg)session.getAttribute("traverMsg");

        //将结果插入到model当中，用于返回给界面
        model.addAttribute("traverMsg", traverMsg);
        return "index";
    }

    //请求跳转地图界面
    @PostMapping("/Travel_Info/Map_Info")
    public String viewMap(@RequestParam("startTime") String startTime, @RequestParam("backTime") String backTime,
                          @RequestParam("adultNum") String adultNum, @RequestParam("childrenNum") String childrenNum,
                          @RequestParam("destination") String destination, HttpSession session, Model model){
        //行程数据
        TraverMsg traverMsg = (TraverMsg)session.getAttribute("traverMsg");
        //保证字段不为空
        traverMsg.setAdult_num(adultNum==null?"":adultNum);
        traverMsg.setChild_num(childrenNum==null?"":childrenNum);
        traverMsg.setEnd_city(destination==null?"":destination);

        //**********************景点数据
        //控制逻辑：若已经获得目的地的信息，则显示目的地城市景点、地图、酒店信息；若没有获得即为定位城市
        //1、判断城市名是否存在
        String map_city_name;
        if (travel_infoService.getCityMsg(traverMsg.getEnd_city()) == null){
            //定位城市
            map_city_name = (String)session.getAttribute("map_city_name");
        }
        else{
            map_city_name = traverMsg.getEnd_city();
        }
        List<ScenicMsg> scenicMsgList = travel_infoService.getScenicMsgList(map_city_name);

        //景点排序功能（前端展示）

        //插入model
        model.addAttribute("scenicMsgList", scenicMsgList);

        //**********************酒店数据

        return "Travel_Info/Map_Info";
    }

}
