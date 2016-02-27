package com.Flipnoter.callofthevoid.crafting;

import com.Flipnoter.callofthevoid.items.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Connor on 2/26/2016.
 */
public class InfusionRecipes {

    private static final InfusionRecipes classInstance = new InfusionRecipes();
    private Map<String, InfusionRecipe> infusionList = new HashMap<String, InfusionRecipe>();

    public static InfusionRecipes instance() { return classInstance; }

    private InfusionRecipes() {

        addInfusionRecipe("Impure Void Ingot", new ItemStack(ModItems.ImpureVoidIngot), Items.gold_ingot,
                new Item[] {Items.diamond, Items.diamond, Items.diamond, Items.diamond,
                        ModItems.CrystallizedEssence, ModItems.CrystallizedEssence, ModItems.CrystallizedEssence, ModItems.CrystallizedEssence,
                        null, null, null, null} );

    }

    public void addInfusionRecipe(String name, ItemStack output, Item altarItem, Item[] components) {

        if(components.length < 12) {

            throw new IllegalArgumentException("component list must be 12 long");

        }

        ItemStack[] conv = new ItemStack[12];

        for(int i = 0; i < 12; i++) {

            conv[i] = new ItemStack(components[i]);

        }

        this.infusionList.put(name, new InfusionRecipe(output, new ItemStack(altarItem), conv));

    }

    public void addInfusionRecipe(String name, ItemStack output, ItemStack altarItem, ItemStack[] components) {

        if(components.length < 12) {

            throw new IllegalArgumentException("component list must be 12 long");

        }

        this.infusionList.put(name, new InfusionRecipe(output, altarItem, components));

    }

    public Map getInfusionList() {

        return infusionList;

    }

    public InfusionRecipe getInfusionRecipe(String name) {

        return infusionList.get(name);

    }
}
