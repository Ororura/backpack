package com.ororura.backpack.event;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ororura.backpack.Backpack;
import com.ororura.backpack.api.Api;
import com.ororura.backpack.networking.BackpackPacketHandler;
import com.ororura.backpack.networking.packet.SimpleMessage;
import com.ororura.backpack.util.KeyboardHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderBlockScreenEffectEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Mod.EventBusSubscriber(modid = Backpack.MODID)
public class Event {
    private static final ExecutorService service = Executors.newFixedThreadPool(10);

    @SubscribeEvent
    public static void eventBrake(BlockEvent.BreakEvent breakEvent) {
        Block block = breakEvent.getState().getBlock();
        if (!(block.equals(Blocks.DIRT))) {
            return;
        }
        CompletableFuture.supplyAsync(() -> {
            String jsonData = Api.fetchDataFromApi("https://fakestoreapi.com/products/1");
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
            return jsonObject.get("title").getAsString();

        }).thenAccept((result) -> {
            breakEvent.getPlayer().sendSystemMessage(Component.literal("API data: " + result));
        });
    }

    @SubscribeEvent
    public static void eventEntityAttack(AttackEntityEvent attackEntityEvent) {
        if (!(attackEntityEvent.getTarget() instanceof Zombie)) {
            return;
        }
        attackEntityEvent.getEntity().sendSystemMessage(Component.literal("Вы ударили зомби!"));
    }

    @SubscribeEvent
    public static void eventHarvestCheck(PlayerEvent.HarvestCheck harvestCheck) {
        System.out.println(harvestCheck.getTargetBlock().getBlock().getName().getString());
        if (Blocks.CARROTS.equals(harvestCheck.getTargetBlock().getBlock())) {
            System.out.println("Ok");
        }
    }

    @SubscribeEvent
    public static void eventRenderBlock(RenderBlockScreenEffectEvent renderBlockScreenEffectEvent) {
        if (!(renderBlockScreenEffectEvent.getBlockState().getBlock().equals(Blocks.STONE))) {
            return;
        }
        System.out.println(renderBlockScreenEffectEvent.getBlockState().getBlock().getName().getString());
        renderBlockScreenEffectEvent.getPlayer().sendSystemMessage(Component.literal("Камень был загружен!"));
    }

    @SubscribeEvent
    public static void eventCreateCow(InputEvent.Key key) {
        if (KeyboardHelper.isHoldingK()) {
            BackpackPacketHandler.sendToServer(new SimpleMessage());
        }
    }
}
