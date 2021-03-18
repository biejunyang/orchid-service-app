微服务应用：
商品服务，port:10001
订单服务，port:10011



## 1、rest api统一数据格式输出
### 1.1、定义统一返回对象：
```java
public class Result<T> {
    //响应状态：0失败,1成功,2未登录
    private int status;

    //业务状态码
    private int code;

    //提示消息
    private String msg;

    //返回数据
    private T data;

}
```


### 1.2、响应成功统一数据格式输出
a、请求直接返回对象的业务数据,使用@RestControllerAdvice对象对返回数据进行包装后,统一格式输出，如
```
    @GetMapping(value = "hello")
    public String hello(String name){
        if(StringUtils.isEmpty(name)){
            throw ExceptionBuilder.build(MiaoshaResultCode.LOGIN_PASSWORD_ERROR);
        }
        return "hello,"+name;
    }
```


b、或者直接调用Result.success()返回结果对象
```
    @GetMapping("hello2")
    public Result<String> hello2(String name){
        if(StringUtils.isEmpty(name)){
//            return Result.error("错误");
            return Result.error(MiaoshaResultCode.LOGIN_PASSWORD_ERROR);
        }
        return Result.success(MiaoshaResultCode.SUCCESS, "hello,"+name);
    }
```


### 1.3、响应失败统一数据格式输出
a、定义状态码信息
```java

/**
 * 返回状态码定义
 */
public enum MiaoshaResultCode implements ResultCode {

    /*成功状态码*/
    SUCCESS(1, "success"),

    /*登录错误码*/
    LOGIN_ERROR(1000, "登录错误"),
    LOGIN_USERNAME_ERROR(1001, "登录错误"),
    LOGIN_PASSWORD_ERROR(1002, "登录错误");


    private int code;
    private String message;

    MiaoshaResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String msg() {
        return message;
    }
}

```


b、抛出业务异常信息，然后对异常统一捕获后返回
```
throw ExceptionBuilder.build(MiaoshaResultCode.LOGIN_PASSWORD_ERROR);
```
ExceptionBuilder.build(ResultCode)构建异常，并抛出

c、或者直接调用Result.success()返回结果对象
```
return Result.error(MiaoshaResultCode.LOGIN_PASSWORD_ERROR);
```




## 2、JSR后端参数检验
https://www.cnblogs.com/huanchupkblog/p/12915268.html
https://www.pianshen.com/article/3474877828/


