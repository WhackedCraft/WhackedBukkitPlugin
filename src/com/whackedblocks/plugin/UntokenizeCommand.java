package com.whackedblocks.plugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.BigInteger;

public class UntokenizeCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            WhackedPlugin.instance.getLogger().info("Console can't use command " + s);
            return true;
        }

        Player player = (Player)commandSender;
        Integer assetId = null;

        if(strings.length != 1) {
            return false;
        }

        try {
            assetId = Integer.parseInt(strings[0]);
        } catch (NumberFormatException e) {
            commandSender.sendMessage("Argument komendy musi byc wartoscia liczbowa");
            return true;
        }

        Exchange contract = WhackedPlugin.instance.getContract();

        try {
            String claimString = contract.getAssetClaimString(BigInteger.valueOf(assetId)).send();
            if(claimString.equals(player.getName())) {
                player.sendMessage("Najpierw ustaw claimString na Twoj nick w grze.");
                return true;
            }

            String assetData = contract.getAssetData(BigInteger.valueOf(assetId)).send();
            contract.burn(BigInteger.valueOf(assetId)).send();
            WhackedPlugin.instance.getLogger().info(assetData);
            String[] data = assetData.split(",");
            player.getInventory().addItem(new ItemStack(Material.getMaterial(data[0]), Integer.parseInt(data[1])));
            commandSender.sendMessage("Twoje " + data[0] + " zostaly zdetokenizowane!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
