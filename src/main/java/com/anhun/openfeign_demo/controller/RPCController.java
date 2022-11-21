package com.anhun.openfeign_demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.anhun.openfeign_demo.entity.Param;
import com.anhun.openfeign_demo.service.OpenFeignService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

@RestController
public class RPCController {

    public static final Logger log = Logger.getLogger("RPCController.class");

    @Resource
    OpenFeignService openFeignService;

    @RequestMapping("/")
    public String test() {
        return "hello";
    }

    @RequestMapping("/test/python")
    public String testpython() {
        return openFeignService.hello();
    }

    @RequestMapping("/finduser/{username}")
    public String findUser(@PathVariable String username) {
        System.out.println(openFeignService.finduser(username));
        return openFeignService.finduser(username);
    }

    @RequestMapping("/testarray")
    public String testArray() {
        String arrstr = openFeignService.testArray();
        JSONArray jsonArray = JSONArray.parseArray(arrstr);
        int[] nums = new int[jsonArray.size()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = jsonArray.getInteger(i);
        }
        for (int i : nums) {
            System.out.print(i + " ");
        }
        System.out.println();

        List<Integer> numlist = jsonArray.toJavaList(Integer.class);
        for (int i : numlist) {
            System.out.print(i + " ");
        }
        System.out.println();
        Integer[] numarr = jsonArray.toArray(new Integer[0]);
        for (int i : numarr) {
            System.out.print(i + " ");
        }
        return numarr.toString();
    }

    @RequestMapping("/test/sendsingleparam")
    public String testSendSingleParam() {
        String result = openFeignService.sendRequestParam(new Random().nextInt(5000));
        return result;
    }

    @RequestMapping("/test/sendmultiparam")
    public String testSendMultiParam() {
        String result = openFeignService.sendRequestParam("钻进参数1", 520);
        JSONObject jsonObject = JSON.parseObject(result);
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return jsonObject.toJSONString();
    }

    @RequestMapping("/test/sendstring")
    public String testSendParamInBody() {
        return openFeignService.sendParamByRequestBody("陈");
    }

    @RequestMapping("/test/sendentity")
    public String testSendEntity() {
        Param param = new Param();
        param.setDrillparam_origin_id(1);
        param.setDrilling_info_id(2);
        param.setConsumption_fluid("123");
        param.setDepth_drilling("321");
        return openFeignService.sendEntity(param);
    }

    @RequestMapping("/test/captcha")
    public String testCaptcha() {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        lineCaptcha.write("img//tmp.png");
        log.info("验证码图像已生成");
        return lineCaptcha.getCode();
    }

    @RequestMapping("/test/print/captcha")
    public String testPrintCaptcha() {
        return "Loading";
    }
}
