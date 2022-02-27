package com.yyy.eduorder.service.impl;

import com.yyy.eduorder.client.CourseClient;
import com.yyy.eduorder.client.UcenterClient;
import com.yyy.eduorder.entity.Order;
import com.yyy.eduorder.mapper.OrderMapper;
import com.yyy.eduorder.service.OrderService;
import com.yyy.eduorder.utils.OrderNoUtil;
import com.yyy.servicebase.vo.UcenterMember;
import com.yyy.servicebase.vo.WebCourseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-02-10
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private CourseClient courseClient;

    @Autowired
    private UcenterClient ucenterClient;

    //1、保存订单
    @Override
    public String addOrder(String courseId, String ucenterId) {
        if(StringUtils.isEmpty(ucenterId)){
            return "";
        }

        //远程调用：根据用户id查询（用户信息）
        UcenterMember member = ucenterClient.getMemberById(ucenterId);

        System.out.println("*******************************");

        //远程调用：根据课程id查询（课程信息）
        WebCourseVo course = courseClient.getCourseById(courseId);
        System.out.println(courseId + " : " + ucenterId);
        System.out.println(course);

        //创建Order对象：并且赋值
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());     //订单号ID
        order.setCourseId(courseId);                    //课程ID
        order.setCourseTitle(course.getTitle());        //课程名称
        order.setCourseCover(course.getCover());        //课程封面
        order.setTeacherName(course.getTeacherName());  //老师名称
        order.setTotalFee(course.getPrice());           //订单金额

        order.setMemberId(member.getId());              //会员ID
        order.setTeacherName(member.getNickname());     //会员名称
        order.setMobile(member.getMobile());            //会员手机

        order.setStatus(0);         //支付状态（0：未支付）, （1：已支付）
        order.setPayType(1);        //付款方式：微信


        //保存
        baseMapper.insert(order);
        return order.getOrderNo();
    }

    //2、根据(课程ID、用户ID)查询：订单状态 （status = 1 已购买; status = 0 未购买）
    @Override
    public boolean isBuyStatus(String courseId, String memberId) {
        if(StringUtils.isEmpty(courseId) || StringUtils.isEmpty(memberId)){
            return false;       //未购买
        }

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);

        return baseMapper.selectCount(wrapper) > 0;
    }
}
