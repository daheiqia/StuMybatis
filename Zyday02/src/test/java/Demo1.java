import com.zy.mapper.CategoryMapper;
import com.zy.mapper.OrderItemMapper;
import com.zy.mapper.OrderMapper;
import com.zy.mapper.ProductMapper;
import com.zy.pojo.Category;
import com.zy.pojo.OrderItem;
import com.zy.pojo.Orders;
import com.zy.pojo.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * @Author:小黑洽
 * @Date：2021/7/5
 */
public class Demo1 {
    private SqlSession session;
    private CategoryMapper categoryMapper;
    private ProductMapper productMapper;
    private OrderMapper orderMapper;
    private OrderItemMapper orderItemMapper;

    @Before
    public void bef() throws IOException{

        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        session =sqlSessionFactory.openSession();

        categoryMapper =session.getMapper(CategoryMapper.class);
        productMapper =session.getMapper(ProductMapper.class);
        orderMapper =session.getMapper(OrderMapper.class);
        orderItemMapper = session.getMapper(OrderItemMapper.class);
    }
    @Test
    public void test(){
       List<Category> list =  categoryMapper.list();
        for(Category c : list){
            System.out.println(c);
            List<Product> products = c.getProducts();
            for(Product product : products){
                System.out.println("\t"+ product);
            }
            System.out.println("循环结束");
        }
    }


    @Test
    public void test02() {
        List<Product> list = productMapper.productList();
        for (Product p : list) {

            System.out.println(p + "对应的分类：" + p.getCategory());
        }
    }

    @Test
    public void test03(){
        List<Orders> list = orderMapper.orderList();

        for(Orders o : list){
            System.out.println(o.getCodes());
            List<OrderItem> orderItems = o.getOrderItemList();
            for(OrderItem oi : orderItems){
                System.out.println("商品："+oi.getProduct().getName()+"  价格："+oi.getProduct().getPrice()+"  数量："+oi.getNumber());
            }
        }
    }
    @Test
    public void tset06(){
        Product p = productMapper.getProduct(1);
        Orders o = orderMapper.getOrder(2);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(o);//设置订单项所属订单
        orderItem.setProduct(p);//订单项所属商品
        orderItem.setNumber(1001);
        orderItemMapper.addOrderItem(orderItem);
        test03();
        //不加这两句 添加的数据是不会应用到数据库的
        session.commit();
        session.close();
    }

    @Test
    public void del(){
        test03();//调用上面的查询所有订单的方法
        System.out.println("-----------------------------删除订单项后----------------------------：");
        Product p = productMapper.getProduct(1);
        Orders o = orderMapper.getOrder(2);
        OrderItem orderItem = new OrderItem();
        orderItem.setOrders(o);//设置订单项所属订单
        orderItem.setProduct(p);//订单项所属商品
        orderItemMapper.delOrderItem(orderItem);
        test03();
        session.commit();
        session.close();
    }

    @Test
    public void test10(){
        test03();
        orderMapper.delOrder(2);
        System.out.println("++++++++++++++");
        test03();
        session.commit();
        session.close();
    }




}
