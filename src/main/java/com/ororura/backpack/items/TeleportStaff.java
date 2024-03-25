package com.ororura.backpack.items;

import com.ororura.backpack.util.KeyboardHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.world.damagesource.DamageSource.IN_FIRE;

public class TeleportStaff extends Item {
    public TeleportStaff(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);
        if (!(world.getDayTime() > 13000)) {
            player.sendSystemMessage(Component.literal("Посох работает только ночью!"));
            return InteractionResultHolder.fail(itemInHand);
        }
        BlockHitResult ray = getCustomPlayerPOVHitResult(world, player);
        BlockPos lookPos = ray.getBlockPos().relative(ray.getDirection());
        player.getCooldowns().addCooldown(this, 100);
        if (!(player.getHealth() <= 5)) {
            player.hurt(IN_FIRE, 5);
        }
        world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, 1.0F);
        itemInHand.hurtAndBreak(1, player, (player1 -> player1.broadcastBreakEvent(player1.getUsedItemHand())));
        player.setPos(lookPos.getX(), lookPos.getY(), lookPos.getZ());
        return InteractionResultHolder.success(itemInHand);
    }

    private static BlockHitResult getCustomPlayerPOVHitResult(Level p_41436_, Player p_41437_) {
        float f = p_41437_.getXRot();
        float f1 = p_41437_.getYRot();
        Vec3 vec3 = p_41437_.getEyePosition();
        float f2 = Mth.cos(-f1 * 0.017453292F - 3.1415927F);
        float f3 = Mth.sin(-f1 * 0.017453292F - 3.1415927F);
        float f4 = -Mth.cos(-f * 0.017453292F);
        float f5 = Mth.sin(-f * 0.017453292F);
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double range = 30;
        Vec3 vec31 = vec3.add((double) f6 * range, (double) f5 * range, (double) f7 * range);
        return p_41436_.clip(new ClipContext(vec3, vec31, net.minecraft.world.level.ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, p_41437_));
    }

    @Override
    public void appendHoverText(@NotNull ItemStack p_41421_, @Nullable Level p_41422_, @NotNull List<Component> p_41423_, @NotNull TooltipFlag p_41424_) {
        if (!KeyboardHelper.isHoldingShift()) {
            p_41423_.add(Component.literal("Зажмите SHIFT для подробной информации!"));
        }
        if (KeyboardHelper.isHoldingShift()) {
            p_41423_.add(Component.literal("Телепортирует тебя!"));
        }
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
    }
}
