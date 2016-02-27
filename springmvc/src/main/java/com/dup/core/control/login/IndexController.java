/**
 * 
 */
package com.dup.core.control.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dup.core.model.UserModel;

/**
 * 默认进入的处理
 * 
 * @author Liyong
 * 
 */
@Controller
public class IndexController {

	@RequestMapping("index")
	public void userLogin(UserModel userModel, Model model) {
		model.addAttribute("str0121", "Hellow world");
//		return new ModelAndView("index");
	}
}
