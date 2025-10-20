package net.scarletontv.seraphic.item;

import net.acoyt.acornlib.api.item.CustomHitParticleItem;
import net.acoyt.acornlib.api.item.CustomHitSoundItem;
import net.acoyt.acornlib.api.item.CustomKillSourceItem;
import net.acoyt.acornlib.api.item.KillEffectItem;
import net.acoyt.acornlib.impl.client.particle.SweepParticleEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.scarletontv.seraphic.Seraphic;
import net.scarletontv.seraphic.index.ModDamageTypes;

import java.util.List;

public class AbsolvementItem extends SwordItem implements CustomKillSourceItem, CustomHitSoundItem, CustomHitParticleItem, KillEffectItem {
    public AbsolvementItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void killEntity(World world, ItemStack itemStack, LivingEntity user, LivingEntity victim) {
        if (victim instanceof PlayerEntity player) {
            if (player instanceof ServerPlayerEntity serverPlayerEntity) {
                teleportToPurgatory(serverPlayerEntity);
                serverPlayerEntity.setHealth(20f);
                ((ServerPlayerEntity) user).requestRespawn();
            }

        }
        if (world instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, victim.getX(), victim.getY(), victim.getZ(), 75, 0.1, 4, 0.1, 0.02);
        }
    }

    private void teleportToPurgatory(ServerPlayerEntity player) {
        RegistryKey<World> heavenWorldKey = RegistryKey.of(RegistryKeys.WORLD, Identifier.of(Seraphic.MOD_ID, "false_heaven"));

        MinecraftServer server = player.getServer();
        if (server == null) {
            Seraphic.LOGGER.error("Server is null!");
            return;
        }

        ServerWorld targetWorld = server.getWorld(heavenWorldKey);
        if (targetWorld != null) {
            BlockPos spawnPos = targetWorld.getSpawnPos();

            player.teleport(
                    targetWorld,
                    0,
                    spawnPos.getY() + 100,
                    0,
                    player.getYaw(),
                    player.getPitch()
            );
        } else {
            Seraphic.LOGGER.error("Could not find false_heaven dimension!");
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.seraphic.absolvement.tooltip").withColor(0x2b2b45));
        super.appendTooltip(stack, context, tooltip, type);
    }


    @Override
    public int getEnchantability() {
        return 0;
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    @Override
    public DamageSource getKillSource(LivingEntity livingEntity) {
        return ModDamageTypes.seraphic_kill(livingEntity);
    }

    @Override
    public void playHitSound(PlayerEntity playerEntity) {
        playerEntity.playSound(SoundEvents.BLOCK_GLASS_BREAK);
    }

    public static final SweepParticleEffect[] EFFECTS = new SweepParticleEffect[]{new SweepParticleEffect(0x4c6885, 0x2b2b45)};

    public void spawnHitParticles(PlayerEntity player) {
        double deltaX = -MathHelper.sin((float) (player.getYaw() * (Math.PI / 180.0F)));
        double deltaZ = MathHelper.cos((float) (player.getYaw() * (Math.PI / 180.0F)));
        World var7 = player.getWorld();
        if (var7 instanceof ServerWorld serverWorld) {
            serverWorld.spawnParticles(
                    EFFECTS[player.getRandom().nextInt(EFFECTS.length)],
                    player.getX() + deltaX,
                    player.getBodyY(0.5F),
                    player.getZ() + deltaZ,
                    0, deltaX, 0.0F, deltaZ, 0.0F
            );
        }
    }
}