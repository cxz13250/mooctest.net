package com.mooctest.weixin.controller;

import com.mooctest.weixin.manager.Managers;
import com.mooctest.weixin.manager.WitestManager;
import com.mooctest.weixin.model.Account;
import com.mooctest.weixin.model.AccountInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**  
* 类说明   
*  
* @author  cxz
* @date 2017年3月20日  新建  
*/
@Controller
@RequestMapping("/account")
public class AccountController {
	
	@RequestMapping(value="/new")
	public ModelAndView toAccountBind(@RequestParam("openid")String openid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		
		ModelAndView mv=new ModelAndView();
		mv.addObject("openid", openid);
		mv.addObject("JSApiTicket", Managers.config.getTicket());
		mv.addObject("appid", Managers.config.getAppid());
		mv.setViewName("account_bind");
		return mv;
	}
	
	@RequestMapping(value="/check",method = RequestMethod.POST)
	public ModelAndView checkAccount(HttpServletRequest request,HttpServletResponse response) throws IOException{

		String openid = request.getParameter("openid");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");
		
		ModelAndView mv=new ModelAndView();
		boolean flag=Managers.accountManager.checkAccount1(username);
		
		if(!flag)
		{
			mv.setViewName("danger");
			mv.addObject("msg","该账号已被绑定！");
			mv.addObject("msg_title","绑定失败！");

		}else {

			flag = WitestManager.isMoocUser1(username, password);
			if (!flag) {
				mv.setViewName("fail");
				mv.addObject("msg","账户密码输入错误！");
				mv.addObject("msg_title","绑定失败！");
			} else {
				Account account = new Account();
				account.setUsername(username);
				account.setOpenid(openid);
				Managers.accountManager.saveAccount(account, openid);
				mv.addObject("msg", "您的账户已经成功绑定！");
				mv.addObject("msg_title","绑定成功！");
				mv.setViewName("success");
			}
		}
		return mv;
	}
	
	@RequestMapping(value="/info")
	public ModelAndView getAccountInfo(@RequestParam("openid")String openid,HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("content-type", "text/html;charset=UTF-8");

		AccountInfo accountInfo=Managers.accountManager.getAccountInfoByOpenid(openid);
		ModelAndView mv=new ModelAndView();
		if(accountInfo!=null){
			mv.addObject("accountInFo", accountInfo);
			mv.addObject("JSApiTicket", Managers.config.getTicket());
			mv.addObject("appid", Managers.config.getAppid());
			mv.setViewName("account_menu");
		}
		return mv;
	}
}





