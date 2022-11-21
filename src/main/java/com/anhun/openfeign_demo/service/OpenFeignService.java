package com.anhun.openfeign_demo.service;

import com.anhun.openfeign_demo.Error.OpenFeignFallback;
import com.anhun.openfeign_demo.entity.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://127.0.0.1:5000", value = "pytest", fallback = OpenFeignFallback.class)
public interface OpenFeignService {
    @RequestMapping("/hello")
    public String hello();

    /**
     * <p>单个参数作为路径变量测试</p>
     *
     * @param username
     * @return
     */
    @RequestMapping("/finduser/{username}")
    public String finduser(@PathVariable String username);

    @RequestMapping("/test/requestparam")
    public String sendRequestParam(@RequestParam("id") int id);

    @RequestMapping("/test/requestparam")
    public String sendRequestParam(@RequestParam("param1") String param1, @RequestParam("param2") int param2);

    @RequestMapping("test/sendstring")
    public String sendParamByRequestBody(@RequestBody String name);

    @RequestMapping("/test/sendentity")
    public String sendEntity(@RequestBody Param param);

    /**
     * <p>返回数组元素测试</p>
     *
     * @return
     */
    @RequestMapping("/test/array")
    public String testArray();


}
