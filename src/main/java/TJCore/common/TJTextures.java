package TJCore.common;

import TJCore.TJValues;
import gregtech.client.renderer.texture.Textures;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.renderer.texture.cube.SimpleCubeRenderer;
import gregtech.client.renderer.texture.cube.SimpleOverlayRenderer;
import static gregtech.client.renderer.texture.Textures.*;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = TJValues.MODID, value = Side.CLIENT)
public class TJTextures {
    public static SimpleCubeRenderer Brick;
    public static SimpleOverlayRenderer TreeFarmerController;
    public static OrientedOverlayRenderer STEAM_ASSEMBLER_OVERLAY;
    
    // Casings
    public static SimpleOverlayRenderer DRACONIC_CASING;


    //public static TextureAtlasSprite RotationPipe;
    
    public static void preInit() {
        Brick = new SimpleCubeRenderer("minecraft:blocks/brick");
        TreeFarmerController = Textures.FROST_PROOF_CASING;
        STEAM_ASSEMBLER_OVERLAY = new OrientedOverlayRenderer("machines/steam_assembler", OrientedOverlayRenderer.OverlayFace.FRONT);
        
        // Casings
        DRACONIC_CASING = new SimpleOverlayRenderer("casings/draconic_casing/draconic_casing_normal");


    }


}
