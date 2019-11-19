package com.iccm.system.controller;

import com.iccm.common.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2019/11/15.
 */
@RestController
@RequestMapping("/save_error_logger")
public class SaveErrorLog {

    @PostMapping
    public JsonResult saveErrorLog(){
        return JsonResult.ok();
    }
}
