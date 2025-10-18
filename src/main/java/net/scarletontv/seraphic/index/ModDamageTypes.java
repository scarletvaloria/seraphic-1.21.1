package net.scarletontv.seraphic.index;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.scarletontv.seraphic.Seraphic;
import org.jetbrains.annotations.Nullable;

public class ModDamageTypes extends DamageSource {
    public static final RegistryKey<DamageType> SERAPHIC_DAMAGE = RegistryKey.of(
            RegistryKeys.DAMAGE_TYPE, Identifier.of(Seraphic.MOD_ID, "seraphic_damage")
    );

    public ModDamageTypes(RegistryEntry<DamageType> type, @Nullable Entity source, @Nullable Entity attacker) {
        super(type, source, attacker);
    }
}

