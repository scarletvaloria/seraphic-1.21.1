package net.scarletontv.seraphic;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import net.scarletontv.seraphic.block.ModBlocks;
import net.scarletontv.seraphic.index.ModDamageTypes;
import net.scarletontv.seraphic.index.ModItemGroups;
import net.scarletontv.seraphic.index.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Seraphic implements ModInitializer {
	public static final String MOD_ID = "seraphic";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static Identifier id (String path){
        return Identifier.of(MOD_ID, path);
    }
	@Override
	public void onInitialize() {
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
	}
}