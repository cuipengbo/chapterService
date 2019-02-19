package com.hytx.chapterService.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.github.pagehelper.PageInfo;
import com.hytx.chapterService.model.Attach;
import com.hytx.chapterService.service.AttachService;

@Api(value = "Attach接口", tags = { "attach Api" })
@RestController
@RequestMapping("/attach")
public class AttachController {


	@Autowired
	private AttachService attachService;
	@Autowired
	private RestTemplate restTemplate;

	@ApiOperation(value = "添加attach信息", response = Integer.class, notes = "添加attach信息")
	@PostMapping("/add")
	public int addAttach() {
		System.out.println("保存attach！");
		Attach u = new Attach();
		u.setAuthorId(123);
		u.setCreated(321);
		u.setFkey("111");
		u.setFname("fn");
		u.setFtype("1");
		return attachService.addAttach(u);
	}

	@ApiOperation(value = "查询attach集合", response = Integer.class, notes = "查询attach集合")
	@GetMapping("/all")
	public PageInfo<Attach> findAllAttach(
			@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize) {
		return attachService.findAllUser(pageNum, pageSize);
	}

	@ApiOperation(value = "根据id查询attach集合", response = Integer.class, notes = "根据id查询attach集合")
	@GetMapping("/selectOne/{id}")
	public Attach selectById(@PathVariable("id") Integer id) {
		return attachService.selectById(id);
	}

	// @GetMapping("/findAll")
	// public List<UserDomain> findAll(){
	// return (List<UserDomain>)
	// this.restTemplate.getForObject("http://localhost:8080/user/all",
	// List.class);
	// }
}
