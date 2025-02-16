/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.infernalexp.world.gen;

import org.infernalstudios.infernalexp.InfernalExpansion;
import org.infernalstudios.infernalexp.entities.BasaltGiantEntity;
import org.infernalstudios.infernalexp.entities.BlackstoneDwarfEntity;
import org.infernalstudios.infernalexp.entities.BlindsightEntity;
import org.infernalstudios.infernalexp.entities.EmbodyEntity;
import org.infernalstudios.infernalexp.entities.GlowsilkMothEntity;
import org.infernalstudios.infernalexp.entities.GlowsquitoEntity;
import org.infernalstudios.infernalexp.entities.ShroomloinEntity;
import org.infernalstudios.infernalexp.entities.VolineEntity;
import org.infernalstudios.infernalexp.entities.WarpbeetleEntity;
import org.infernalstudios.infernalexp.init.IEEntityTypes;

import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

//import net.minecraft.entity.monster.MonsterEntity;

@Mod.EventBusSubscriber(modid = InfernalExpansion.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntitySpawns {

    @SubscribeEvent
    public static void spawnEntities(FMLLoadCompleteEvent event) {
        GlobalEntityTypeAttributes.put(IEEntityTypes.VOLINE.get(), VolineEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.SHROOMLOIN.get(), ShroomloinEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.WARPBEETLE.get(), WarpbeetleEntity.setCustomAttributes().create());
        //GlobalEntityTypeAttributes.put(IEEntityTypes.CEROBEETLE.get(), CerobeetleEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.EMBODY.get(), EmbodyEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.BASALT_GIANT.get(), BasaltGiantEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.BLACKSTONE_DWARF.get(), BlackstoneDwarfEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.GLOWSQUITO.get(), GlowsquitoEntity.setCustomAttributes().create());
        //GlobalEntityTypeAttributes.put(IEEntityTypes.PYRNO.get(), PyrnoEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.BLINDSIGHT.get(), BlindsightEntity.setCustomAttributes().create());
        GlobalEntityTypeAttributes.put(IEEntityTypes.GLOWSILK_MOTH.get(), GlowsilkMothEntity.setCustomAttributes().create());
    }
}
