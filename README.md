# Polished Medieval

Polished Medieval is a Fabric mod for Minecraft 1.21.1 that adds a compact medieval weapons-and-materials progression system. It introduces throwable daggers, two custom tool materials, resource items tied to player reputation, a custom Infusion Table, and a Bloodstone Lantern.

The mod is built around a risk-and-reward loop: player deaths create Bloodstone or Ichor depending on the victim's reputation, and those materials can be collected and used to upgrade Netherite equipment through the Infusion Table.

## Features

### Bloodstone and Ichor

- **Bloodstone** and **Ichor** are the mod's custom materials.
- Each material supports a sword, axe, pickaxe, shovel, hoe, and dagger.
- Bloodstone and Ichor tools use diamond-level mining requirements, have 1,500 durability, a mining speed of 10, and enchantability of 30.
- Bloodstone tools are repaired with Bloodstone.
- Ichor tools are repaired with Drops of Ichor.
- The mod also adds Drops of Ichor, Vials of Ichor, Buckets of Ichor, and a Bucket of Crystallized Blood as progression and crafting items.

### Reputation and player deaths

- Every player starts with a reputation of `0`.
- Reputation is stored with the player and copied when the player respawns.
- When a player kills another player:
  - If the victim has **negative reputation**, the victim drops one Bloodstone and the attacker gains one reputation.
  - Otherwise, the victim drops one Drop of Ichor and the attacker loses one reputation.
- Reputation is currently an internal gameplay value; the mod does not add a HUD, command, or dedicated screen for displaying it.

### Throwable daggers

Polished Medieval adds wooden, stone, iron, gold, diamond, Netherite, Bloodstone, and Ichor daggers.

- Daggers are usable as melee weapons and can be thrown.
- Thrown daggers use a visible flying-item projectile.
- A thrown dagger deals material-specific damage and returns as an item when it collides with the world.
- Standard-material daggers use the same material progression as their corresponding vanilla tools.
- Netherite Daggers are made by upgrading a Diamond Dagger with a Netherite Upgrade Smithing Template and a Netherite Ingot.

### Infusion Table

The Infusion Table is a custom two-block-wide workstation with a dedicated GUI.

1. Place an Infusion Table where the adjacent structural block can also fit.
2. Open it and place a Netherite tool or Netherite Dagger in the central slot.
3. Add Bloodstone or Ichor to the table:
   - One Bloodstone adds one Bloodstone charge.
   - One Drop of Ichor adds one Ichor charge.
   - One Vial of Ichor adds three Ichor charges.
   - A full bucket adds nine charges.
4. Interact with a Brush on the table to complete the conversion once the required nine charges are present.
5. Netherite equipment converts to Bloodstone equipment with nine Bloodstone charges. Bloodstone equipment then converts to its Ichor equivalent with nine Ichor charges.

The table can also fill buckets when nine charges are available. Empty buckets are placed in the table's resource slots and become a Bucket of Crystallized Blood or Bucket of Ichor. Breaking the table drops its stored items and resource charges.

### Bloodstone Lantern

The Bloodstone Lantern is a decorative light block that emits maximum light level 15. It is crafted from one Bloodstone surrounded by eight Iron Nuggets.

## Crafting highlights

| Item | Recipe |
| --- | --- |
| Infusion Table | Two Glass Bottles, three Wooden Slabs, and one Iron Block |
| Wooden/Stone/Iron/Gold/Diamond Dagger | A diagonal recipe using Glass, a Stick, and the matching material |
| Netherite Dagger | Smithing upgrade from a Diamond Dagger |
| Vial of Ichor | One Glass Bottle and three Drops of Ichor |
| Bloodstone Lantern | One Bloodstone and eight Iron Nuggets |

The complete recipe definitions are in `src/main/resources/data/polished_medieval/recipe`.

## Requirements

- Minecraft **1.21.1**
- Fabric Loader **0.19.5 or newer**
- Fabric API **0.116.17+1.21.1**
- Java **21**

Fabric Loader and Fabric API are required on both the client and server. Install the same mod version on both sides when playing multiplayer.

Important implementation areas include:

- `Polished_medieval.java` - mod initialization and reputation-based death handling
- `Tools/Dagger` - dagger items and thrown projectile behavior
- `Tools/Materials` - Bloodstone and Ichor tool materials
- `Blocks/InfusionTable` - workstation block, inventory, conversion logic, and persistence
- `player` and `mixin` - persistent player reputation data
- `src/main/resources/data/polished_medieval/recipe` - crafting and smithing recipes

## Development notes

- The mod ID is `polished_medieval`.
- Common and client code use Fabric's split environment source sets.
- The project is licensed under the terms in [LICENSE.txt](LICENSE.txt).
- The repository currently focuses on the systems listed above; it does not add custom world generation, mobs, dimensions, or commands.
