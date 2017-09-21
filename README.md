## 服务端格式标准

使用lombok在编译时可以自动生成构造方法和getter和setter方法。

使用Hibernate的@DynamicUpdate注解动态更新，在更新对象的时候只修改对应的字段。

常用的变量可以封装到枚举类中:
<pre><code>
@AllArgsConstructor
@Getter
public enum ProductStatusEnum {

    UP(0, "在架"),
    DOW(1, "下架");
    private Integer code;
    private String message;
}
</code></pre>

为了信息安全考虑需要把客户端请求的结果封装到提前制定好的API中，不需要的字段不要返回，以免信息泄露，最好为每一种返回形式封装一个
ViewObject类:
<pre><cod>
@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productINfoVOList;
}
</code></pre>

将结果集封装起来，方便调用查看:
<pre><code>
@Data
public class ResultVO<T> {

    private Integer code;
    private String msg;
    private T data;
}
</code></pre>
<pre><code>
public class ResultVOUtil {

    public static ResultVO success(Object object) {

        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }

    public static ResultVO success() {
        return success(null);
    }

    public static ResultVO error(Integer code, String msg) {

        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
</code></pre>

利用Apache提供的帮助类将实体类中的数据拷贝到视图类中:
<pre><code>
BeanUtils.copyProperties(productInfo, productInfoVO);
</code></pre>

一个订单有订单详情列表，但是数据库中不需要该字段，为了保持实体类和数据库的一致性我们的实体类中不应该有订单详情列表字段，我们可以创建一个专门用于传输的对象（该对象包含订单详情列表字段）:

实体类:
<pre><code>
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderSatusEnum.NEW.getCode();
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
    private Date createTime;
    private Date updateTime;
}
</code></pre>
传输对象:
<pre><code>
@Data
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
</code></pre>

接下来定义我们的产品销售异常(继承RuntimeException类)：
<pre><code>
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {

        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }
}
</code></pre>

获取表单数据时应创建对应的接收对象放在form包下并进行参数验证

API:
<pre><code>
@PostMapping("/create")
public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
        log.error("【创建订单】参数不正确，orderForm={}", orderForm);
        throw new SellException(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
    }

    OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
        log.error("【创建订单】购物车不能为空");
        throw new SellException(ResultEnum.CART_EMPTY);
    }
    OrderDTO createResult = orderService.create(orderDTO);
    Map<String, String> map = new HashMap<>();
    map.put("orderId", createResult.getOrderId());

    return ResultVOUtil.success(map);
}
</code><.pre>
OrderForm
<pre><code>
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
</code></pre>

时间类型在返回给前端的时候是毫秒时间戳，编写转换类继承JsonSerializer我们可以按照自己的方式去返回。
工具类Date2LongSerializer：
<pre><code>
public class Date2LongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(date.getTime() / 1000);
    }
}
</pre></code>
传输类的字段上要加上注解：
<pre><code>
@JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;
</pre></code>