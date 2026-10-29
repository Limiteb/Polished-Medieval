package net.Polished.polished_medieval;

import net.Polished.polished_medieval.Blocks.Blocks;
import net.Polished.polished_medieval.Items.Items;
import net.Polished.polished_medieval.Registry.blockEntityTypeRegistry;
import net.Polished.polished_medieval.Screen.ModScreenHandlers;
import net.Polished.polished_medieval.Tools.Tools;
import net.Polished.polished_medieval.player.ReputationData;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import static net.Polished.polished_medieval.Items.Bloodstone.Bloodstone.bloodstone;
import static net.Polished.polished_medieval.Items.Ichor.Ichor.drop_of_ichor;

public class Polished_medieval implements ModInitializer {
    public static String MOD_ID = "polished_medieval";
    @Override
    public void onInitialize() {
        Items.initializeItems();
        Tools.initializeTools();
        Blocks.initializeBlocks();
        blockEntityTypeRegistry.initializeBlockEntityTypes();
        ModScreenHandlers.registerScreenHandlers();

        ServerLivingEntityEvents.AFTER_DEATH.register((entity, source) -> {
            if (entity instanceof ServerPlayerEntity victim) {
                int victimRep = ((ReputationData) victim).getReputation();
                System.out.println(victim.getName() + " Reputation: " + victimRep);
                if (source.getAttacker() instanceof ServerPlayerEntity attacker) {
                    int attackerRep = ((ReputationData) attacker).getReputation();
                    System.out.println(attacker.getName() + " Reputation: " + attackerRep);
                    if (victimRep < 0) {
                        // Drop 1 Bloodstone at victim's death location
                        victim.dropStack(new ItemStack(bloodstone));
                        ((ReputationData) attacker).setReputation(attackerRep + 1);
                    } else {
                        // Drop 1 Ichor at victim's death location
                        victim.dropStack(new ItemStack(drop_of_ichor));
                        ((ReputationData) attacker).setReputation(attackerRep - 1);
                    }
                }
            }
        });
    }
}
