package com.zxyono.lego.controller.admin;

import com.zxyono.lego.service.HistoryService;
import com.zxyono.lego.util.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/admin/history")
public class HistoryAdminController {
    @Autowired
    private HistoryService historyService;

    @GetMapping("/list/{page}/{limit}")
    public ResultMap list(@PathVariable("page") Integer page, @PathVariable("limit") Integer limit,
                          @RequestParam("username") String username,@RequestParam("type") String type) {
        return historyService.getHistoryListWithPage(page, limit, username, type);
    }


}