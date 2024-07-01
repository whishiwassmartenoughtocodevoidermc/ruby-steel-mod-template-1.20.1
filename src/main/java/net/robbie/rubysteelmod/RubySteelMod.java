package net.robbie.rubysteelmod;

import net.fabricmc.api.ModInitializer;

import net.robbie.rubysteelmod.block.modblock;
import net.robbie.rubysteelmod.item.moditem;
import net.robbie.rubysteelmod.item.moditemgroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RubySteelMod implements ModInitializer {
    public static final String MOD_ID = "rubysteelmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		moditemgroups.registerItemGroups();
		moditem.registermoditem();
		modblock.registerModBlocks();


	}
}