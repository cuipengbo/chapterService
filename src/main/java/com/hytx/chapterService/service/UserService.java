package com.hytx.chapterService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hytx.chapterService.dao.UserDao;
import com.hytx.chapterService.model.UserDomain;

@Service
public class UserService {

	@Autowired
    private UserDao userDao;//这里会报错，但是并不会影响

    public int addUser(UserDomain user) {

        return userDao.insert(user);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo<UserDomain> result = new PageInfo<UserDomain>(userDomains);
        return result;
    }
    
    @Async
    @Scheduled(fixedRate = 3000)
    public void PrintString() {
    	System.out.println("定时任务测试.......");
    	List<UserDomain> userDomains = userDao.selectUsers();
    	userDomains.stream().forEach(user ->{
    		System.out.print(user.toString());
    	});
    }
}
