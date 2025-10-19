package net.scarletontv.seraphic.index;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.scarletontv.seraphic.Seraphic;
import net.scarletontv.seraphic.block.ModBlocks;

public class ModItemGroups {
    public static final ItemGroup ABYSSAL_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Seraphic.MOD_ID, "seraphic_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModBlocks.EMPYREAL))
                    .displayName(Text.translatable("itemgroup.seraphic.seraphic_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.ABSOLVEMENT);
                        entries.add(ModBlocks.EMPYREAL);


                    }).build());




    public static void registerItemGroups() {
        Seraphic.LOGGER.info("Registering Item Groups for " + Seraphic.MOD_ID);
    }
}
