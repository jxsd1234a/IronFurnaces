package ironfurnaces.blocks;

import ironfurnaces.init.Registration;
import ironfurnaces.tileentity.BlockDiamondFurnaceTile;
import ironfurnaces.tileentity.BlockIronFurnaceTileBase;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockDiamondFurnace extends BlockIronFurnaceBase {

    public static final String DIAMOND_FURNACE = "diamond_furnace";

    public BlockDiamondFurnace() {
        super(Properties.from(Blocks.DIAMOND_BLOCK));
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!player.isCreative()) {
            BlockIronFurnaceTileBase te = (BlockIronFurnaceTileBase) world.getTileEntity(pos);
            if (te.hasCustomName()) {
                ItemStack itemstack = new ItemStack(Registration.DIAMOND_FURNACE.get());
                itemstack.setDisplayName(te.getName());
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), itemstack));
            } else {
                world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(Registration.DIAMOND_FURNACE.get())));
            }
        }
        super.onBlockHarvested(world, pos, state, player);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 2;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new BlockDiamondFurnaceTile();
    }
}
