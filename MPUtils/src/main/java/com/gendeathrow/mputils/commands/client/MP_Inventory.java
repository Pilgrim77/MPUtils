package com.gendeathrow.mputils.commands.client;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

import com.gendeathrow.mputils.utils.Tools;

public class MP_Inventory extends MP_ItemDump
{

	@Override
	public String getCommand() 
	{
		return "inventory";
	}

	@Override
	public void runCommand(CommandBase command, ICommandSender sender, String[] args) 
	{
		if(sender != null && sender instanceof EntityPlayer)
		{
			if(sender.getEntityWorld().isRemote)
			{
				String clipboard = "";

				EntityPlayer player = (EntityPlayer) sender;
				
				for(int slot = 0; slot < player.inventory.mainInventory.length; slot++)
				{
					ItemStack stack = player.inventory.mainInventory[slot];
					
					if(stack == null)
					{
						continue;
					}
					
					clipboard += this.parseStackData(sender, args, stack);
				}

				if(Tools.CopytoClipbard(clipboard))
				{
					player.addChatMessage(new TextComponentTranslation(TextFormatting.YELLOW +" --Copied to Clipboard--"));
				}
			}
		}
		
	}

}
