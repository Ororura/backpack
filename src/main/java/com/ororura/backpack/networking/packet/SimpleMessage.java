package com.ororura.backpack.networking.packet;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.Fox;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SimpleMessage {
    public SimpleMessage() {
    }

    public SimpleMessage(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            assert player != null;
            ServerLevel serverLevel = player.getLevel();
            BlockPos spawnPos = player.blockPosition();
            Fox fox = EntityType.FOX.create(serverLevel);
            assert fox != null;
            fox.moveTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), player.getYRot(), 0);
            serverLevel.addFreshEntity(fox);
        });
    }


}
