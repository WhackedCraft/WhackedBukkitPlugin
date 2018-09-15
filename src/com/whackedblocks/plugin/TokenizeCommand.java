package com.whackedblocks.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;


public class TokenizeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            WhackedPlugin.instance.getLogger().info("Console can't use command " + s);
            return true;
        }
        Player player = (Player)commandSender;
        Web3j web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/9defdc016d654060a6d372cbe5b2de0c"));
        try {
            Web3ClientVersion web3ClientVersion = web3.web3ClientVersion().send();
            WhackedPlugin.logger.info(web3ClientVersion.getWeb3ClientVersion());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
