package com.ororura.backpack.creativeTabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.ororura.backpack.items.BackpackItem.LEATHER_BACKPACK;

public class BackpackTab {
    public static final CreativeModeTab BACKPACK_TAB = new CreativeModeTab("Backpack") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(LEATHER_BACKPACK.get());
        }
    };
}
