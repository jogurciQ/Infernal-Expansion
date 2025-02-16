package org.infernalstudios.infernalexp.entities;

import org.infernalstudios.infernalexp.init.IEBlocks;
import org.infernalstudios.infernalexp.init.IEEntityTypes;
import org.infernalstudios.infernalexp.init.IESoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GlassBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ThrowableNetherBrickEntity extends ProjectileItemEntity {

    public ThrowableNetherBrickEntity(EntityType<? extends ProjectileItemEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public ThrowableNetherBrickEntity(World world, LivingEntity livingEntity) {
        super(IEEntityTypes.THROWABLE_NETHER_BRICK.get(), livingEntity, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.NETHER_BRICK;
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    protected void func_230299_a_(BlockRayTraceResult result) {
        super.func_230299_a_(result);
        if (!this.world.isRemote) {
            BlockPos pos = result.getPos();
            Block block = this.world.getBlockState(pos).getBlock();
            if ((block == IEBlocks.QUARTZ_GLASS.get() || block == IEBlocks.QUARTZ_GLASS_PANE.get())) {
                this.world.playSound(null, pos, IESoundEvents.QUARTZ_GLASS_TYPE.getHitSound(), SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
            if ((block instanceof GlassBlock || block instanceof PaneBlock) && !(block == Blocks.IRON_BARS || block == IEBlocks.QUARTZ_GLASS.get() || block == IEBlocks.QUARTZ_GLASS_PANE.get())) {
                this.world.destroyBlock(pos, false);
                this.setMotion(this.getMotion().scale(0.69F));
            } else {
                ItemEntity itemEntity = new ItemEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), Items.NETHER_BRICK.getDefaultInstance());
                this.world.addEntity(itemEntity);
                this.remove();
            }
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        super.onEntityHit(result);
        if (!this.world.isRemote) {
            Entity entity = result.getEntity();
            entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getShooter()), 3);
            this.remove();
        }
    }
}
