package com.github.thegoldcrayon.tutorialmod.client.gui;

import com.github.thegoldcrayon.tutorialmod.TutorialMod;
import com.github.thegoldcrayon.tutorialmod.container.TutorialGeneratorContainer;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TutorialGeneratorScreen extends ContainerScreen<TutorialGeneratorContainer>
{

    private static final ResourceLocation BACKGROUND_TEXTURE = new ResourceLocation(TutorialMod.MODID, "textures/gui/container/tutorial_generator.png");



    public TutorialGeneratorScreen(final TutorialGeneratorContainer container, final PlayerInventory inventory, ITextComponent title)
    {

        super(container, inventory, title);
    }


    @Override
    public void render(final int mouseX, final int mouseY, final float partialTicks)
    {

        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

        //GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.minecraft.getTextureManager().bindTexture(BACKGROUND_TEXTURE);
        int startX = this.guiLeft;
        int startY = this.guiTop;

        //This draws a part of the current texture to the screen.
        //Parameters are (x, y, u, v, width, height)
        this.blit(startX, startY, 0, 0, this.xSize, this.ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {

        drawString(Minecraft.getInstance().fontRenderer, "Energy: " + container.getEnergy(), 10, 10, 0xffffff);

    }

}
