package net.scarletontv.seraphic.index;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.scarletontv.seraphic.Seraphic;

public interface ModDamageTypes {
    RegistryKey<DamageType> SERAPHIC_KILL = of("seraph_damage");

    static DamageSource seraphic_kill(LivingEntity entity) {
        return entity.getDamageSources().create(SERAPHIC_KILL); }

    private static RegistryKey<DamageType> of(String name) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Seraphic.id(name));
    }
}

