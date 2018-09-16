package com.whackedblocks.plugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.concurrent.CompletableFuture;


public class TokenizeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            WhackedPlugin.instance.getLogger().info("Console can't use command " + s);
            return true;
        }

        Player player = (Player)commandSender;

        if(strings.length != 2) {
            return false;
        }

        String address = strings[0];
        int count = Integer.valueOf(strings[1]);

        if(count <= 0) {
            commandSender.sendMessage("Liczba przedmiotow do tokenizacji nie moze byc mniejsza/rowna 0.");
            return true;
        }

        Exchange contract = WhackedPlugin.instance.getContract();

        ItemStack is = player.getInventory().getItemInMainHand();

        if(is.getType() == Material.AIR) {
            commandSender.sendMessage("Nie trzymasz przedmiotu w glownej rece.");
            return true;
        }

        if(is.getAmount() < count) {
            commandSender.sendMessage("Liczba przedmiotow do tokenizacji nie moze byc mniejsza/rowna 0.");
            return true;
        }

        String key = is.getType().name() + "," + Integer.toString(count);

        is.setAmount(is.getAmount() - count);

        try {
            CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = contract.assign(address, key).sendAsync();
            player.sendTitle("Transakcja wyslana", "oczekiwanie na potwierzenie...", 10, 40, 10);
            transactionReceiptCompletableFuture.thenAccept(transactionReceipt -> {
                String txId = transactionReceipt.getTransactionHash();
                String message = "Twoje " + count + " " + is.getType().name() + " zostaly zamienone w token!";
                commandSender.sendMessage(message);
                commandSender.sendMessage("txid: " + txId);
                player.sendTitle(count + " " + is.getType().name(), "zostaly zamienione w token!", 10, 40, 10);
            });
        } catch (Exception e) {
            player.sendTitle("Nie masz wystarczajaco etheru !!!", "", 10, 40, 10);
            e.printStackTrace();
        }
        return true;
    }
}
