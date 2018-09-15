package com.whackedblocks.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import com.whackedblocks.plugin.Exchange;

import java.io.IOException;
import java.math.BigInteger;


public class TokenizeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Web3j web3 = Web3j.build(new HttpService(WhackedPlugin.instance.getConfig().getString("web3provider")));

        BigInteger publicKey = new BigInteger(WhackedPlugin.instance.getConfig().getString("publicKey"), 16);
        BigInteger privateKey = new BigInteger(WhackedPlugin.instance.getConfig().getString("privateKey"), 16);
        ECKeyPair ecKeyPair = new ECKeyPair(privateKey, publicKey);
        Credentials credentials = Credentials.create(ecKeyPair);

        BigInteger gasPrice = new BigInteger(WhackedPlugin.instance.getConfig().getString("gasPrice"));
        BigInteger gasLimit = new BigInteger(WhackedPlugin.instance.getConfig().getString("gasLimit"));

        String contractAddress = WhackedPlugin.instance.getConfig().getString("contractAddress");

        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            WhackedPlugin.logger.info(web3ClientVersion.getWeb3ClientVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Exchange exchangeContract = Exchange.load(contractAddress, web3, credentials, gasPrice, gasLimit);

        if(!(commandSender instanceof Player)) {
            WhackedPlugin.instance.getLogger().info("Console can't use command " + s);

            return true;
        }
        Player player = (Player)commandSender;

        return false;
    }
}
