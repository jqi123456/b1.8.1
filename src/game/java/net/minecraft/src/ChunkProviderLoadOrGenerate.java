package net.minecraft.src;

import java.io.IOException;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;

public class ChunkProviderLoadOrGenerate implements IChunkProvider {
	private Chunk blankChunk;
	private IChunkProvider chunkProvider;
	private IChunkLoader chunkLoader;
	private Long2ObjectMap<Chunk> chunks = new Long2ObjectOpenHashMap<>(1024);
	private World worldObj;
	int lastQueriedChunkXPos;
	int lastQueriedChunkZPos;
	private Chunk lastQueriedChunk;
	private int curChunkX;
	private int curChunkY;

	public void setCurrentChunkOver(int var1, int var2) {
		this.curChunkX = var1;
		this.curChunkY = var2;
	}

	public boolean canChunkExist(int var1, int var2) {
		byte var3 = 15;
		return var1 >= this.curChunkX - var3 && var2 >= this.curChunkY - var3 && var1 <= this.curChunkX + var3 && var2 <= this.curChunkY + var3;
	}

	public boolean chunkExists(int var1, int var2) {
		if(!this.canChunkExist(var1, var2)) {
			return false;
		} else if(var1 == this.lastQueriedChunkXPos && var2 == this.lastQueriedChunkZPos && this.lastQueriedChunk != null) {
			return true;
		} else {
			Chunk var3 = this.chunks.get(ChunkCoordIntPair.chunkXZ2Int(var1, var2));
			return var3 != null && (var3 == this.blankChunk || var3.isAtLocation(var1, var2));
		}
	}

	public Chunk loadChunk(int var1, int var2) {
		return this.provideChunk(var1, var2);
	}

	public Chunk provideChunk(int var1, int var2) {
		if(var1 == this.lastQueriedChunkXPos && var2 == this.lastQueriedChunkZPos && this.lastQueriedChunk != null) {
			return this.lastQueriedChunk;
		} else if(!this.worldObj.findingSpawnPoint && !this.canChunkExist(var1, var2)) {
			return this.blankChunk;
		} else {
			long var3 = ChunkCoordIntPair.chunkXZ2Int(var1, var2);
			Chunk var5 = this.chunks.get(var3);
			if(!this.chunkExists(var1, var2)) {
				if(var5 != null) {
					var5.onChunkUnload();
					this.saveChunk(var5);
					this.saveExtraChunkData(var5);
				}

				Chunk var6 = this.func_542_c(var1, var2);
				if(var6 == null) {
					if(this.chunkProvider == null) {
						var6 = this.blankChunk;
					} else {
						var6 = this.chunkProvider.provideChunk(var1, var2);
						var6.func_25124_i();
					}
				}

				this.chunks.put(var3, var6);
				var6.func_4143_d();
				if(var6 != null) {
					var6.onChunkLoad();
				}

				var6.func_35843_a(this, this, var1, var2);
				var5 = var6;
			}

			this.lastQueriedChunkXPos = var1;
			this.lastQueriedChunkZPos = var2;
			this.lastQueriedChunk = var5;
			return var5;
		}
	}

	private Chunk func_542_c(int var1, int var2) {
		if(this.chunkLoader == null) {
			return this.blankChunk;
		} else {
			try {
				Chunk var3 = this.chunkLoader.loadChunk(this.worldObj, var1, var2);
				if(var3 != null) {
					var3.lastSaveTime = this.worldObj.getWorldTime();
				}

				return var3;
			} catch (Exception var4) {
				var4.printStackTrace();
				return this.blankChunk;
			}
		}
	}

	private void saveExtraChunkData(Chunk var1) {
		if(this.chunkLoader != null) {
			try {
				this.chunkLoader.saveExtraChunkData(this.worldObj, var1);
			} catch (Exception var3) {
				var3.printStackTrace();
			}

		}
	}

	private void saveChunk(Chunk var1) {
		if(this.chunkLoader != null) {
			try {
				var1.lastSaveTime = this.worldObj.getWorldTime();
				this.chunkLoader.saveChunk(this.worldObj, var1);
			} catch (IOException var3) {
				var3.printStackTrace();
			}

		}
	}

	public void populate(IChunkProvider var1, int var2, int var3) {
		Chunk var4 = this.provideChunk(var2, var3);
		if(!var4.isTerrainPopulated) {
			var4.isTerrainPopulated = true;
			if(this.chunkProvider != null) {
				this.chunkProvider.populate(var1, var2, var3);
				var4.setChunkModified();
			}
		}

	}

	public boolean saveChunks(boolean var1, IProgressUpdate var2) {
		int var3 = 0;
		int var4 = 0;
		int var5;
		if(var2 != null) {
			for(Chunk var6 : this.chunks.values()) {
				if(var6 != null && var6.needsSaving(var1)) {
					++var4;
				}
			}
		}

		var5 = 0;

		for(Chunk var6 : this.chunks.values()) {
			if(var6 != null) {
				if(var1 && !var6.neverSave) {
					this.saveExtraChunkData(var6);
				}

				if(var6.needsSaving(var1)) {
					this.saveChunk(var6);
					var6.isModified = false;
					++var3;
					if(var3 == 2 && !var1) {
						return false;
					}

					if(var2 != null) {
						++var5;
						if(var5 % 10 == 0) {
							var2.setLoadingProgress(var5 * 100 / var4);
						}
					}
				}
			}
		}

		if(var1) {
			if(this.chunkLoader == null) {
				return true;
			}

			this.chunkLoader.saveExtraData();
		}

		return true;
	}

	public boolean unload100OldestChunks() {
		if(this.chunkLoader != null) {
			this.chunkLoader.func_814_a();
		}

		return this.chunkProvider.unload100OldestChunks();
	}

	public boolean canSave() {
		return true;
	}

	public String makeString() {
		return "ChunkCache: " + this.chunks.size();
	}
}
