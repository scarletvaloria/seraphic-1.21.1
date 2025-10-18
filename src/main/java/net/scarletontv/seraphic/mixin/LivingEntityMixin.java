package net.scarletontv.seraphic.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.scarletontv.seraphic.Seraphic;
import net.scarletontv.seraphic.index.ModDamageTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity self = (LivingEntity)(Object)this;

        if (self instanceof ServerPlayerEntity player) {
            if (source.isOf(ModDamageTypes.SERAPHIC_DAMAGE)) {
                teleportPlayer(player);
            }
        }
    }

    private void teleportPlayer(ServerPlayerEntity player) {
        ServerWorld destinationWorld = player.getServer().getWorld(
                RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Seraphic.MOD_ID, "false_heaven")));

        if (destinationWorld != null) {
            player.teleport(destinationWorld, player.getX(), player.getY(), player.getZ(), player.getYaw(), player.getPitch());
        }
    }
}


