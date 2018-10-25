package com.wx.consumer2.com.wx.consumer2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

     @Autowired
     private ConsumerService2 consumerService;
     @Autowired
     private LoadBalancerClient loadBalancerClient;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getstr(@RequestParam String name)
    {
        //这里是拿到一个服务提供者的实例
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("PROVIDER");
        return consumerService.getStr2(name)+"port="+serviceInstance.getPort();
    }
}
