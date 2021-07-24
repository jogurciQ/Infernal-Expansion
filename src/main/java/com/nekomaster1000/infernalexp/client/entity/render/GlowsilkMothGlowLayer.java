package com.nekomaster1000.infernalexp.client.entity.render;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.entities.GlowsilkMothEntity;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GlowsilkMothGlowLayer<T extends GlowsilkMothEntity, M extends EntityModel<T>> extends EyesLayer<T, M> {
    private static final RenderType RENDER_TYPE = RenderType.eyes(new ResourceLocation(InfernalExpansion.MOD_ID,
        "textures/entity/glowsilk_moth_glow.png"));

    public GlowsilkMothGlowLayer(RenderLayerParent<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public RenderType renderType() {
        return RENDER_TYPE;
    }
}
