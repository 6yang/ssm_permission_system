package cn.yang.dao;

import cn.yang.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {


    @Select("select * from traveller where id in ( " +
            " select TRAVELLERID from ORDER_TRAVELLER where ORDERID = #{ordersId}) ")
    List<Traveller> findByOrdersId(String ordersId);
}
