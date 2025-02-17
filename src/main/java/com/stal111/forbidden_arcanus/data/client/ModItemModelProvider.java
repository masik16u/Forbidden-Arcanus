package com.stal111.forbidden_arcanus.data.client;

import com.stal111.forbidden_arcanus.ForbiddenArcanus;
import com.stal111.forbidden_arcanus.block.ObsidianSkullBlock;
import com.stal111.forbidden_arcanus.init.NewModBlocks;
import com.stal111.forbidden_arcanus.init.NewModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.valhelsia.valhelsia_core.data.ValhelsiaItemModelProvider;

import java.util.Arrays;
import java.util.Objects;

/**
 * Mod Item Model Provider
 * Forbidden Arcanus - com.stal111.forbidden_arcanus.data.client.ModItemModelProvider
 *
 * @author stal111
 * @version 16.2.0
 * @since 2021-01-26
 */
public class ModItemModelProvider extends ValhelsiaItemModelProvider {

    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ForbiddenArcanus.REGISTRY_MANAGER, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Block Items
        getRemainingBlockItems().removeIf(item -> ((BlockItem) item.get()).getBlock() instanceof ObsidianSkullBlock);

        takeBlockItem(this::simpleModel,
                NewModBlocks.PIXIE_UTREM_JAR,
                NewModBlocks.CORRUPTED_PIXIE_UTREM_JAR,
                NewModBlocks.NIPA,
                NewModBlocks.FUNGYSS_DOOR,
                NewModBlocks.ARCANE_CRYSTAL_OBELISK
        );
        takeBlockItem(this::simpleModelBlockTexture,
                NewModBlocks.FUNGYSS
        );
        takeBlockItem(this::withParentInventory,
                NewModBlocks.FUNGYSS_BLOCK,
                NewModBlocks.FUNGYSS_BUTTON,
                NewModBlocks.FUNGYSS_FENCE,
                NewModBlocks.DARKSTONE_WALL,
                NewModBlocks.POLISHED_DARKSTONE_WALL,
                NewModBlocks.POLISHED_DARKSTONE_BUTTON,
                NewModBlocks.POLISHED_DARKSTONE_BRICK_WALL,
                NewModBlocks.ARCANE_POLISHED_DARKSTONE_WALL
        );
        takeBlockItem(this::utremJarModel, NewModBlocks.UTREM_JAR);
        takeBlockItem(item -> withParent(item, "arcane_polished_darkstone_pillar_single"), NewModBlocks.ARCANE_POLISHED_DARKSTONE_PILLAR);
        takeBlockItem(item -> withParent(item, "fungyss_trapdoor_bottom"), NewModBlocks.FUNGYSS_TRAPDOOR);
        takeBlockItem(item -> simpleModelBlockTexture(item, "arcane_glass"), NewModBlocks.ARCANE_GLASS_PANE);

        forEachBlockItem(this::withParent);

        //Items
        getRemainingItems().removeAll(Arrays.asList(NewModItems.LENS_OF_VERITATIS, NewModItems.OBSIDIAN_SKULL_SHIELD, NewModItems.ZOMBIE_ARM, NewModItems.SHINY_ZOMBIE_ARM));

        takeItem(this::bloodTestTubeModel, NewModItems.BLOOD_TEST_TUBE);

        forEachItem(this::simpleModel);
    }

    public <T extends Item> void utremJarModel(T item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();
        ModelFile waterModel = this.getBuilder("utrem_jar_water").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/utrem_jar_water");
        ModelFile lavaModel = this.getBuilder("utrem_jar_lava").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/utrem_jar_lava");

        this.getBuilder(name).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/" + name)
                .override().predicate(new ResourceLocation("water"), 1.0F).predicate(new ResourceLocation("lava"), 0.0F).model(waterModel).end()
                .override().predicate(new ResourceLocation("water"), 0.0F).predicate(new ResourceLocation("lava"), 1.0F).model(lavaModel).end();
    }

    public <T extends Item> void bloodTestTubeModel(T item) {
        String name = Objects.requireNonNull(item.getRegistryName()).getPath();

        ModelFile model0 = this.getBuilder("blood_test_tube_0").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/blood_test_tube_0");
        ModelFile model1 = this.getBuilder("blood_test_tube_1").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/blood_test_tube_1");
        ModelFile model2 = this.getBuilder("blood_test_tube_2").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/blood_test_tube_2");
        ModelFile model3 = this.getBuilder("blood_test_tube_3").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/blood_test_tube_3");
        ModelFile model4 = this.getBuilder("blood_test_tube_4").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/blood_test_tube_4");
        ModelFile model5 = this.getBuilder("blood_test_tube_5").parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/blood_test_tube_5");

        this.getBuilder(name).parent(getExistingFile(mcLoc("item/generated"))).texture("layer0", "item/test_tube")
                .override().predicate(new ResourceLocation("amount"), 0.1F).model(model0).end()
                .override().predicate(new ResourceLocation("amount"), 0.25F).model(model1).end()
                .override().predicate(new ResourceLocation("amount"), 0.5F).model(model2).end()
                .override().predicate(new ResourceLocation("amount"), 0.75F).model(model3).end()
                .override().predicate(new ResourceLocation("amount"), 0.9F).model(model4).end()
                .override().predicate(new ResourceLocation("amount"), 1.0F).model(model5).end();
    }
}
