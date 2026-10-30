package net.Polished.polished_medieval.client.Screen.InfusionTable;

import com.mojang.blaze3d.systems.RenderSystem;
import net.Polished.polished_medieval.Screen.InfusionTable.InfusionTableScreenHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.Polished.polished_medieval.Polished_medieval.MOD_ID;

public class InfusionTableScreen extends HandledScreen<InfusionTableScreenHandler> {


    // Identify the GUI texture for the infusion table, gui texture contains all assets for the gui
    // This variable gets called by the drawBackground method
    public static final Identifier GUI_TEXTURE =
            Identifier.of(MOD_ID, "textures/gui/infusiontable/infusiontable_gui.png");

    // The screen factory invokes this constructor when the infusion table is opened, supplying its handler,
    // the player's inventory, and the screen title. Pass them to HandledScreen to initialize the UI.
    public InfusionTableScreen(InfusionTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);

        this.backgroundWidth = 176;
        this.backgroundHeight = 177;

        this.playerInventoryTitleY = 83;
    }

    // This method is the actual GUI drawing step for the screen. Vanilla's HandledScreen
    // provides a default blank background, but the infusion table needs a custom texture,
    // so we override this method and render our own UI panel here.
    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        // GameRenderer::getPositionTexProgram is a method reference. The :: operator in Java
        // means "take this method and treat it like a function object". In other words,
        // GameRenderer::getPositionTexProgram is equivalent to saying "pass a reference to the
        // static method getPositionTexProgram from GameRenderer". The setShader method expects
        // a method reference that produces the shader program.

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(GUI_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);

        int bloodstoneQuantity = getScreenHandler().getBloodstoneQuantity();
        int ichorQuantity = getScreenHandler().getIchorQuantity();

        int bloodstoneU = ((9 - bloodstoneQuantity) * 16) + 1;
        int bloodstoneHeight = (bloodstoneQuantity * 7) + 4;
        int ichorV = 252 - (ichorQuantity * 7);
        int ichorU = (ichorQuantity * 16) + 74;
        int ichorHeight = (ichorQuantity * 7) + 3;
        int ichorYOffset = 78 - (ichorQuantity * 7);
        context.drawTexture(GUI_TEXTURE, x + 33, y + 16, bloodstoneU, 178, 16, bloodstoneHeight);
        context.drawTexture(GUI_TEXTURE, x + 127, y + ichorYOffset, ichorU, ichorV, 16, ichorHeight);

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {

        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
