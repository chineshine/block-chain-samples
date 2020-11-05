package c.s.sample.config;

import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.channel.client.Service;
import org.fisco.bcos.channel.handler.GroupChannelConnectionsConfig;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.Keys;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.channel.ChannelEthereumService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@Configuration
@Slf4j
public class FiscoConfig {

    @Bean
    @ConfigurationProperties(prefix = "group-channel-connections-config")
    public GroupChannelConnectionsConfig channelConnectionsConfig() {
        return new GroupChannelConnectionsConfig();
    }


    @Bean
    @ConfigurationProperties(prefix = "channel-service")
    public Service service() {
        return new Service();
    }

    @Bean
    public ChannelEthereumService channelEthereumService(Service service, GroupChannelConnectionsConfig channelConnectionsConfig) throws Exception {
        log.info("agency name: " + service.getAgencyName());
        service.setAllChannelConnections(channelConnectionsConfig);
        service.run();
        ChannelEthereumService ethereumService = new ChannelEthereumService();
        ethereumService.setChannelService(service);
        return ethereumService;
    }

    @Bean
    public Web3j web3j(ChannelEthereumService channelEthereumService) {
        return Web3j.build(channelEthereumService, 1);
    }

    @Bean
    Credentials credentials() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        return Credentials.create(Keys.createEcKeyPair());
    }

}

