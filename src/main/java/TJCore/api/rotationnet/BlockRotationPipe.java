package TJCore.api.rotationnet;

import gregtech.api.GregTechAPI;
import gregtech.api.block.BuiltInRenderBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;

public class BlockRotationPipe extends BuiltInRenderBlock {




    public BlockRotationPipe() {
        super(Material.IRON);
        setRegistryName("axle_pipe");
        setCreativeTab(GregTechAPI.TAB_GREGTECH);
        setSoundType(SoundType.METAL);
        setHardness(1.5f);
        setResistance(3.0f);
        setLightOpacity(0);
        disableStats();

        setHarvestLevel("wrench", 1);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {

    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return RotationPipeRenderer.INSTANCE.getBlockRenderType();
    }

    @Override
    protected Pair<TextureAtlasSprite, Integer> getParticleTexture(World world, BlockPos blockPos) {
        return RotationPipeRenderer.INSTANCE.getParticleTexture((TileEntityRotationPipe)world.getTileEntity(blockPos));
    }
}
