package com.ororura.backpack.creativeTabs;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import static com.ororura.backpack.items.BackpackItem.LEATHER_BACKPACK;

public class BackpackTab {
    public static class Backpack extends CreativeModeTab {
        private Backpack(int index, String label) {
            super(index, label);
        }

        @Override
        public ItemStack makeIcon() {
            return new ItemStack(LEATHER_BACKPACK.get());
        }

    }
    public static final Backpack istance = new Backpack(CreativeModeTab.TABS.length, "Backpack");
}
