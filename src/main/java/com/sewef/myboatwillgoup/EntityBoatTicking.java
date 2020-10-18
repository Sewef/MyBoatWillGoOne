package com.sewef.myboatwillgoup;

import static java.lang.Double.max;
import java.util.List;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

public class EntityBoatTicking {
    
    public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
    }
    
    @SubscribeEvent
    public void EntityBoatTicking(WorldTickEvent.WorldTickEvent event) {
        List<Entity> entityList = event.world.loadedEntityList;
        if (event.world.isRemote)
            return;
        
        for (Entity entity : entityList) {
            
            if (entity instanceof EntityBoat) {
                
                EntityBoat boat = (EntityBoat)entity;
                BlockPos.PooledMutableBlockPos blockpos$pooledmutableblockpos = BlockPos.PooledMutableBlockPos.retain();
                AxisAlignedBB boundingBox = boat.getCollisionBoundingBox();
                
                double d = 0.0D;
                
                for(int i = 0; i < 5; i++) {
                    double d5 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(i + 0)) / 5.0D) - 0.125D;
                    double d9 = (boundingBox.minY + ((boundingBox.maxY - boundingBox.minY) * (double)(i + 1)) / 5.0D) - 0.125D;
                    
                    AxisAlignedBB axisalignedbb = new AxisAlignedBB(boundingBox.minX, d5, boundingBox.minZ, boundingBox.maxX, d9, boundingBox.maxZ);
                    
                    blockpos$pooledmutableblockpos.setPos(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
                    IBlockState iblockstate = boat.world.getBlockState(blockpos$pooledmutableblockpos);
                    
                    Boolean result = iblockstate.getBlock().isAABBInsideMaterial(boat.world, blockpos$pooledmutableblockpos, new AxisAlignedBB(blockpos$pooledmutableblockpos), Material.WATER);
                    if (result != null) {
                        System.out.println(result);
                        if (result)
                            d += 1.0D / 5;
                    }
                }
                                
                if(d < 1.0D) {
                    double d3 = d * 2D - 1.0D;
                    boat.motionY += 0.039999999105930328D * d3;
                }
                else {
                    if(boat.motionY < 0.0D)
                        boat.motionY /= 2D;
                    
                    boat.motionY += 0.0070000002160668373D;
                }
                
                //System.out.println(boundingBox + " - " + boat.motionY + " - " + d);
            }
        }
    }
}
