package com.ororura.backpack.items;

import com.ororura.backpack.Backpack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static com.ororura.backpack.creativeTabs.BackpackTab.BACKPACK_TAB;

public class BackpackItem {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Backpack.MODID);
    public static final RegistryObject<Item> LEATHER_BACKPACK = ITEMS.register("leather_backpack", () ->
            new Item(new Item.Properties().stacksTo(1).tab(BACKPACK_TAB)));

    public static final RegistryObject<Item> FOOD = ITEMS.register("food_test", () ->
            new Item(new Item.Properties().tab(BACKPACK_TAB).food(new FoodProperties.Builder().nutrition(3).saturationMod(2).build()))
    );

    public static final RegistryObject<Item> BOW = RegistryObject.create(new ResourceLocation("minecraft:bow"), ForgeRegistries.ITEMS);

    public static final RegistryObject<Item> FUEL_TEST = ITEMS.register("fuel_test", () ->
            new FuelTest(new Item.Properties().tab(BACKPACK_TAB))
    );

    public static final RegistryObject<Item> TELEPORT_STAFF = ITEMS.register("teleport_staff", () ->
            new TeleportStaff(new Item.Properties().tab(BACKPACK_TAB).durability(20)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
