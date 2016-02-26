package com.Flipnoter.callofthevoid.world;

import com.Flipnoter.callofthevoid.blocks.ModBlocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Connor on 2/5/2016.
 */
public class OreGenerator implements IWorldGenerator {

    private WorldGenerator VoidEssenceOre;

    public OreGenerator() {

        VoidEssenceOre = new WorldGenMinable(ModBlocks.VoidEssenceOre.getDefaultState(), 8);

    }

    @Override
    public void generate(Random random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {

        switch(world.provider.getDimensionId()) {

            case 0:
                GenerateOre(VoidEssenceOre, world, random, x, z, 5, 0, 10);
                break;
            case -1:
                break;
            case 1:
                break;

        }
    }

    private void GenerateOre(WorldGenerator generator, World world, Random rand, int ChunkX, int ChunkZ, int SpawnChances, int MinHeight, int MaxHeight) {

        if(MinHeight < 0 || MaxHeight > world.getHeight() || MinHeight > MaxHeight)
            throw new IllegalArgumentException("Illegal Height Arguments");

        int heightDiff = MaxHeight - MinHeight + 1;

        for(int i = 0; i < SpawnChances; i++) {

            int x = ChunkX * 16 + rand.nextInt(16);
            int y = MinHeight + rand.nextInt(heightDiff);
            int z = ChunkZ * 16 + rand.nextInt(16);

            generator.generate(world, rand, new BlockPos(x, y, z));

        }
    }
}
