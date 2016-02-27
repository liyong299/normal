/**
 * 
 */
package com.dup.core.control.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dup.core.model.UserModel;
import com.dup.core.service.UserService;
import com.dup.core.util.Constants;
import com.dup.core.util.DecryptEncrypt;
import com.dup.core.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/userLogin")
	public String userLogin(UserModel userModel, Model model) {
		userModel
				.setPassword(DecryptEncrypt.md5Encrypt(userModel.getPassword()));
		User user = userService.findUserByByCondition(userModel);
		if (user == null) {
			model.addAttribute("errorInfo", "you password is error!");
			return "login";
		}

		Constants.LOGIN_USER = user.getUserName();
		return "redirect:/user/index";
	}

	/**
	 * 查询推荐男士信息列表
	 * 
	 * @param userModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/men")
	public String manWorld(UserModel userModel, Model model) 
	{
		return "men";
	}

	@RequestMapping("/addUser")
	public String addUser(UserModel userModel) {
		Date date = new Date();
		User user = new User();
		BeanUtils.copyProperties(userModel, user); // 实体属性复制，将userModel中的属性值复制到User中
		user.setPassword(DecryptEncrypt.md5Encrypt(userModel.getPassword()));
		user.setCreateTime(date.toString());
		user.setUpdateTime(date);
		user.setUpdateUser(Constants.LOGIN_USER);
		user.setCreateUser(Constants.LOGIN_USER);
		userService.saveUser(user);
		return "redirect:/user/index";
	}

	@RequestMapping("/index")
	public ModelAndView index(UserModel userModel, HttpServletRequest request) {
		if (StringUtils.isEmpty(Constants.LOGIN_USER)) {
			ModelAndView mav = new ModelAndView("login");
			mav.addObject("errorInfo", "user is overdue!");
			return mav;
		}
		return new ModelAndView("userList");
	}

	@RequestMapping("/getUser")
	public void getUser(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("userId");
		User user = userService.getUserById(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		model.addAttribute("user", user);
	}

	@RequestMapping("/updateUser")
	public String updateUser(UserModel userModel) {
		Date date = new Date();
		User user = userService.getUserById(userModel.getId());
		if (StringUtils.isEmpty(userModel.getPassword())) {
			userModel.setPassword(user.getPassword());
		} else {
			userModel.setPassword(DecryptEncrypt.md5Encrypt(userModel
					.getPassword()));
		}
		userModel.setCreateTime(user.getCreateTime());
		userModel.setUpdateTime(date);
		userModel.setCreateUser(Constants.LOGIN_USER);
		userModel.setUpdateUser(Constants.LOGIN_USER);
		BeanUtils.copyProperties(userModel, user);
		userService.updateUser(user);
		return "redirect:/user/index";
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request) {
		String id = request.getParameter("userId");
		User user = userService.getUserById(id);
		userService.deleteUser(user);
		return "redirect:/user/index";
	}

	@RequestMapping("/exit")
	public String exit() {
		Constants.LOGIN_USER = null;
		return "redirect:/";
	}

	// 返回json
	@RequestMapping("/getJsonUserList")
	@ResponseBody
	public ModelMap getJsonUserList(UserModel userModel,
			HttpServletRequest request, HttpServletResponse response) {
		ModelMap modelMap = new ModelMap();
		response.setHeader("Access-Control-Allow-Origin", "*");
		String sPage = request.getParameter("page");
		int page = 1;
		if (!StringUtils.isEmpty(sPage)) {
			page = Integer.parseInt(sPage);
		}
		// PageResultSet<User> userPageResult =
		// userService.findPageUserList(userModel, page, Constants.PAGE_SIZE);
		//
		// modelMap.addAttribute("event",userPageResult.getList());
		// modelMap.addAttribute("pageBean", userPageResult.getPage());
		// modelMap.addAttribute("pageCount",userPageResult.getPage().getTotalPage());
		return modelMap;
	}

}