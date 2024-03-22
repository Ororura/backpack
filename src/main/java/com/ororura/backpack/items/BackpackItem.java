package com.ororura.backpack.items;

import com.ororura.backpack.Backpack;
import com.ororura.backpack.creativeTabs.BackpackTab;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BackpackItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Backpack.MODID);
    public static final RegistryObject<Item> LEATHER_BACKPACK = ITEMS.register("leather_backpack", () ->
            new Item(new Item.Properties().stacksTo(1).tab(BackpackTab.istance)));

    public static final RegistryObject<Item> FOOD = ITEMS.register("food_test", () ->
            new Item(new Item.Properties().tab(BackpackTab.istance).food(new FoodProperties.Builder().nutrition(3).saturationMod(2).build()))
    );

    public static final RegistryObject<Item> FUEL_TEST = ITEMS.register("fuel_test", () ->
            new FuelTest(new Item.Properties().tab(BackpackTab.istance))
    );

    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff", () ->
            new TeleportStaff(new Item.Properties().tab(BackpackTab.istance).durability(20)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
