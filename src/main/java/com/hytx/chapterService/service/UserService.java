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
    
    
    /*一个cron表达式有至少6个（也可能7个）有空格分隔的时间元素。
    	按顺序依次为 
    1. 秒（0~59） 
    2. 分钟（0~59）
    3. 小时（0~23）
    4. 天（月）（0~31，但是你需要考虑你月的天数）
    5. 月（0~11）
    6. 天（星期）（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT）
    7. 年份（1970－2099）

    	其中每个元素可以是一个值(如6),一个连续区间(9-12),一个间隔时间(8-18/4)(/表示每隔4小时),一个列表(1,3,5),通配符。由于"月份中的日期"和"星期中的日期"这两个元素互斥的,必须要对其中一个设置?.

    k
    0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
    0 0 12 ? * WED 表示每个星期三中午12点 
    "0 0 12 * * ?" 每天中午12点触发 
    "0 15 10 ? * *" 每天上午10:15触发 
    "0 15 10 * * ?" 每天上午10:15触发 
    "0 15 10 * * ? *" 每天上午10:15触发 
    "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
    "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
    "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
    "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
    "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
    "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
    "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
    "0 15 10 15 * ?" 每月15日上午10:15触发 
    "0 15 10 L * ?" 每月最后一日的上午10:15触发 
    "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
    "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
    "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发*/
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
