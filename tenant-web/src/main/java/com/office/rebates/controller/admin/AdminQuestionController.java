package com.office.rebates.controller.admin;

import com.office.rebates.controller.RouteKey;
import com.office.rebates.model.common.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户反馈问题控制器
 * Created by liyongfeng on 2016/3/10.
 */
@Controller
@RequestMapping(RouteKey.QUESTION)
public class AdminQuestionController {

    public void questionList(Page page,Model model){

    }
}
