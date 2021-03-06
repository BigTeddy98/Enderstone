/* 
 * Enderstone
 * Copyright (C) 2014 Sander Gielisse and Fernando van Loenhout
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.enderstone.server.blocks;

import org.enderstone.server.api.Block;
import org.enderstone.server.api.World;
import org.enderstone.server.api.entity.Player;
import org.enderstone.server.entity.player.EnderPlayer;
import org.enderstone.server.inventory.ItemStack;
import org.enderstone.server.regions.BlockId;

/**
 *
 * @author gyroninja
 */
public class BlockDefinition {

	private final BlockId type;

	public BlockDefinition(BlockId type) {

		this.type = type;
	}

	public BlockId getType() {

		return type;
	}

	public boolean canBreak(Player player, World world, int x, int y, int z) {
		return true;
	}

	public int getMaxStackSize() {
		return 64;
	}

	public boolean doesInstantBreak() {
		return false;
	}

	
	public boolean onLeftClick(EnderPlayer player, Block block) {
		return false;
	}

	public boolean onRightClick(EnderPlayer player, Block block) {
		return false;
	}

	public String getPlaceSound() {
		return "";
	}

	public String getBreakSound() {
		return "";
	}

	public ItemStack getDrop(Player player, World world, int x, int y, int z) {
		return new ItemStack(BlockId.valueOf(type.getName().toUpperCase()), (byte) 1, world.getBlock(x, y, z).getData());
	}
}
