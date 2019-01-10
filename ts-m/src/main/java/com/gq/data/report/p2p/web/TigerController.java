package com.gq.data.report.p2p.web;

import com.gq.data.report.p2p.dao.mapper.TigerMapper;
import com.gq.data.report.p2p.service.TigerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TigerController {
    @Autowired private TigerService tigerService;
    @RequestMapping("/")
    public void threads(){
        tigerService.processTigersThreads();
    }
}
