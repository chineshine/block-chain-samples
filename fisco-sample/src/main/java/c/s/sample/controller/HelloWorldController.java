package c.s.sample.controller;

import c.s.sample.constant.Constants;
import c.s.sample.contracts.HelloWorld;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.tx.gas.StaticGasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloWorldController {

    @Autowired
    private Web3j web3j;
    @Autowired
    private Credentials credentials;


    @GetMapping("/load")
    public String load() {
        HelloWorld helloWorld = HelloWorld.load(Constants.HelloWorld.ADDRESS, web3j,
                credentials, new StaticGasProvider(Constants.Gas.PRICE, Constants.Gas.LIMIT));
        return "加载成功, helloworld 的地址是: " + helloWorld.getContractAddress();
    }


}
