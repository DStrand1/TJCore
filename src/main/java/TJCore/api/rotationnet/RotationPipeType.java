package TJCore.api.rotationnet;

import TJCore.api.TJOreDictionaryLoader;
import TJCore.api.material.materials.properties.RotationPipeProperties;
import gregtech.api.pipenet.block.material.IMaterialPipeType;
import gregtech.api.unification.ore.OrePrefix;

import javax.annotation.Nonnull;

public enum RotationPipeType implements IMaterialPipeType<RotationPipeProperties> {

    NORMAL("normal", .375f, TJOreDictionaryLoader.pipeNormalRotation);

    public final String name;
    public final float thickness;
    public final OrePrefix orePrefix;

    RotationPipeType(String name, float thickness, OrePrefix prefix) {
        this.name = name;
        this.thickness = thickness;
        this.orePrefix = prefix;
    }

    @Nonnull
    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getThickness() {
        return thickness;
    }

    @Override
    public OrePrefix getOrePrefix() {
        return orePrefix;
    }

    @Override
    public RotationPipeProperties modifyProperties(RotationPipeProperties baseProps) {
        return new RotationPipeProperties(baseProps.getMaxTorque());
    }

    @Override
    public boolean isPaintable() {
        return true;
    }


}
