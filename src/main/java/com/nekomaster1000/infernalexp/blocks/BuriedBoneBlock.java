package com.nekomaster1000.infernalexp.blocks;

import com.nekomaster1000.infernalexp.init.IEBlocks;
import com.nekomaster1000.infernalexp.init.IETags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;

import javax.annotation.CheckForNull;

import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class BuriedBoneBlock extends HorizontalBushBlock {
    protected static final VoxelShape FLOOR_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    protected static final VoxelShape CEILING_SHAPE = Block.box(5.0D, 6.0D, 5.0D, 11.0D, 16.0D, 11.0D);

    public BuriedBoneBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACE, AttachFace.FLOOR));
    }

    @CheckForNull
    public BlockState getPlaceableState(Level world, BlockPos pos, Direction placeSide) {
        if (world.getBlockState(pos).getMaterial().isReplaceable() && world.getBlockState(pos).getBlock() != IEBlocks.BURIED_BONE.get()) {
            if (placeSide.getAxis() != Axis.Y) {
                placeSide = Direction.UP;
            }
            Direction attachdirection;
            if (this.isValidGround(world.getBlockState(pos.relative(placeSide.getOpposite())), world, pos)) {
                attachdirection = placeSide.getOpposite();
            } else if (this.isValidGround(world.getBlockState(pos.relative(placeSide)), world, pos)) {
                attachdirection = placeSide;
            } else {
                return null;
            }
            AttachFace attachface;
            if (attachdirection == Direction.UP) {
                attachface = AttachFace.CEILING;
            } else {
                attachface = AttachFace.FLOOR;
            }
            return this.defaultBlockState().setValue(FACE, attachface);
        }
        return null;
    }
    
    @Override
    protected boolean isValidGround(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return state.getBlock().is(IETags.Blocks.BURIED_BONE_BASE_BLOCKS);
    }

    public boolean canAttach(LevelReader reader, BlockPos pos, Direction direction) {
        BlockPos blockpos = pos.relative(direction);
        return isValidGround(reader.getBlockState(blockpos), reader, blockpos);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return !state.getValue(FACE).equals(AttachFace.WALL) && canAttach(worldIn, pos, getConnectedDirection(state).getOpposite());
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        Vec3 vector3d = state.getOffset(worldIn, pos);

        switch(state.getValue(FACE)){
            case FLOOR:
                return FLOOR_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
            case CEILING:
            default:
                return CEILING_SHAPE.move(vector3d.x, vector3d.y, vector3d.z);
        }
    }

    @Override
    public OffsetType getOffsetType() {
        return OffsetType.XZ;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builderIn) {
        builderIn.add(FACING, FACE);
    }

    @Override
    public Item asItem() {
        return Items.BONE;
    }
}
