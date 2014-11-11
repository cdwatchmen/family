package org.swz.com.family.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.swz.com.family.common.util.StringUtil;
import org.swz.com.family.entity.FamilyPersonResume;
import org.swz.com.family.entity.Person;
import org.swz.com.family.repository.mybatis.plugs.Page;
import org.swz.com.family.service.FamilyPersonResumeServer;
import org.swz.com.family.service.ShiroRealm;
import org.swz.com.family.web.dto.Result;

@Controller
@RequestMapping(value = "/resume")
public class FamilyPersonResumeController {
	
	private final static Log logger = LogFactory.getLog(FamilyPersonResumeController.class);
	
	@Autowired
	private FamilyPersonResumeServer familyPersonResumeServer;
	
	@ResponseBody
	@RequestMapping(value = "/getResumesByPersonId", method = RequestMethod.POST)
	public Result getResumesByPersonId(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		
		ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
		String personId = user.getPersonId();
		personId = request.getParameter("personId") == null ? personId : request.getParameter("personId");
		List<FamilyPersonResume> familyPersonResume = familyPersonResumeServer.selectFamilyPersonResumeByPersonId(personId);
		result.setData(familyPersonResume);
		return result;
	}
	
    @ResponseBody
   	@RequestMapping(value = "/getFamilyContribution", method = RequestMethod.POST)
   	public Result getFamilyContribution(HttpServletRequest request, HttpServletResponse response) { 
       	ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
       	Result result = new Result("0", "");
   	    int pageNum = StringUtil.strToInteger(request.getParameter("pageNum"));
        int pageSize = StringUtil.strToInteger(request.getParameter("pageSize"));
        String verifyStatus = request.getParameter("verifyStatus");//后续添加校验防止sql注入，正则检查
        
  		//为用户查找可能的家谱
        Page page = familyPersonResumeServer.getFamilyContribution(user.getPersonId(), pageNum, pageSize, verifyStatus);
  		result.setData(page);
  		return result;
     }
    
    
    @ResponseBody
	@RequestMapping(value = "/addPersonResumeForApp", method = RequestMethod.POST)
	public Result addPersonResume(HttpServletRequest request, HttpServletResponse response) { 
		Result result = new Result("0", "");
		try{
			ShiroRealm.ShiroUser user = (ShiroRealm.ShiroUser)SecurityUtils.getSubject().getPrincipal();
			FamilyPersonResume familyPersonResume = new FamilyPersonResume();  
			familyPersonResume.setResumeId(UUID.randomUUID().toString());
			familyPersonResume.setPersonId(request.getParameter("personId"));
			familyPersonResume.setResumeStartTime(request.getParameter("startTime"));
			familyPersonResume.setResumeEndTime(request.getParameter("endTime"));
			familyPersonResume.setFullName(request.getParameter("fullName"));
			familyPersonResume.setResumeDynasty(request.getParameter("dynasty"));
			familyPersonResume.setResumeContent(request.getParameter("resumeContent"));
			familyPersonResume.setCreateUserId(user.getUserId());
			familyPersonResume.setVerifyStatus(Integer.parseInt(request.getParameter("verifyStatus")));
			familyPersonResume.setHonoraryDegree(0);
			familyPersonResumeServer.addFamilyPersonResume(familyPersonResume); 
			result.setData(familyPersonResume);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("新增人物履历出错", e);
			result.setCode("-1");
			result.setMessage("新增人物履历失败");
		}
		return result;
	}

}
