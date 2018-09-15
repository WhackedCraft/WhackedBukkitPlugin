package com.whackedblocks.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.logging.Logger;

public class WhackedPlugin extends JavaPlugin {

    static WhackedPlugin instance;
    static Logger logger;


    private Web3j web3j;
    private Exchange contract;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();
        saveDefaultConfig();
        setupWeb3();
        getCommand("tokenize").setExecutor(new TokenizeCommand());
        getCommand("untokenize").setExecutor(new UntokenizeCommand());
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("Super plugin wylaczony!");
    }

    private void setupWeb3() {
        String provider = WhackedPlugin.instance.getConfig().getString("web3provider");
        web3j = Web3j.build(new HttpService(provider));

        Credentials credentials = null;
        try {
            credentials = WalletUtils.loadCredentials("notarealpassword",
                    WhackedPlugin.instance.getDataFolder().getAbsolutePath().concat("/wallet.json"));
        } catch (IOException | CipherException e) {
            e.printStackTrace();
        }

        BigInteger gasPrice = new BigInteger(WhackedPlugin.instance.getConfig().getString("gasPrice"));
        BigInteger gasLimit = new BigInteger(WhackedPlugin.instance.getConfig().getString("gasLimit"));

        String contractAddress = WhackedPlugin.instance.getConfig().getString("contractAddress");

        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            WhackedPlugin.logger.info(web3ClientVersion.getWeb3ClientVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }

        WhackedPlugin.instance.getLogger().info("Provider: " + provider);
        WhackedPlugin.instance.getLogger().info("Contract address: " + contractAddress);
        WhackedPlugin.instance.getLogger().info("My address: " + credentials.getAddress());

        WhackedPlugin.instance.getLogger().info("Gas price: " + gasPrice.toString());
        WhackedPlugin.instance.getLogger().info("Gas limit: " + gasLimit.toString());

        this.contract = Exchange.load(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public Exchange getContract() {
        return contract;
    }

    public Web3j getWeb3j() {
        return web3j;
    }

}
