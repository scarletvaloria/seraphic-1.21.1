package net.scarletontv.seraphic.index;

import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.scarletontv.seraphic.Seraphic;
import net.scarletontv.seraphic.item.AbsolvementItem;

import static net.acoyt.acornlib.api.util.ItemUtils.modifyItemNameColor;

public class ModItems {

    public static final Item ABSOLVEMENT = registerItem("absolvement",
            new AbsolvementItem(ModToolMaterials.SERAPHIC, new Item.Settings()
                    .attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterials.SERAPHIC, 4, -3.1f))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Seraphic.MOD_ID, name), item);
    }

    public static void registerModItems() {
        modifyItemNameColor(ABSOLVEMENT, 0x3fe4e1);

        Seraphic.LOGGER.info("Registering Mod Items for " + Seraphic.MOD_ID);
    }
}
