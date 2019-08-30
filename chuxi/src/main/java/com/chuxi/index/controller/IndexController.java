package com.chuxi.index.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tcwy.distribute.controller.BaseController;


@Controller
@Scope("prototype")
public class IndexController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(IndexController.class);


	@RequestMapping(value={"/"})
	public String index(){
		return "redirect:/html/index/index.html";
	}


}