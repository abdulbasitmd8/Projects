from email import header
from random import randint

from gdpc import geometry as GEO
from gdpc import interface as INTF
from gdpc import worldLoader as WL
from random import randint


STARTX, STARTY, STARTZ, ENDX, ENDY, ENDZ = INTF.requestBuildArea() 

WORLDSLICE = WL.WorldSlice(STARTX, STARTZ,
                           ENDX + 1, ENDZ + 1)

# Drawing C
def drawC(x,y,z):
    INTF.placeBlock(x, z, y, "beacon")
    INTF.placeBlock(x, z, y+1, "beacon")
    INTF.placeBlock(x, z, y+2, "beacon")

    INTF.placeBlock(x+1, z, y, "beacon")
    INTF.placeBlock(x+2, z, y, "beacon")
    INTF.placeBlock(x+3, z, y, "beacon")

    INTF.placeBlock(x+4, z, y, "beacon")
    INTF.placeBlock(x+4, z, y+1, "beacon")
    INTF.placeBlock(x+4, z, y+2, "beacon")

# Drawing S
def drawS(x,y,z):
    INTF.placeBlock(x, z, y, "beacon")
    INTF.placeBlock(x, z, y+1, "beacon")
    INTF.placeBlock(x, z, y+2, "beacon")

    INTF.placeBlock(x+1, z, y+2, "beacon")

    INTF.placeBlock(x+2, z, y, "beacon")
    INTF.placeBlock(x+2, z, y+1, "beacon")
    INTF.placeBlock(x+2, z, y+2, "beacon")

    INTF.placeBlock(x+3, z, y, "beacon")

    INTF.placeBlock(x+4, z, y, "beacon")
    INTF.placeBlock(x+4, z, y+1, "beacon")
    INTF.placeBlock(x+4, z, y+2, "beacon")

# Drawing 4
def drawFour(x,y,z):
    INTF.placeBlock(x+1, z, y, "beacon")
    INTF.placeBlock(x+2, z, y, "beacon")
    INTF.placeBlock(x+3, z, y, "beacon")
    INTF.placeBlock(x+4, z, y, "beacon")

    INTF.placeBlock(x+1, z, y, "beacon")
    INTF.placeBlock(x+1, z, y+1, "beacon")
    INTF.placeBlock(x+1, z, y+2, "beacon")
    INTF.placeBlock(x+1, z, y+3, "beacon")

    INTF.placeBlock(x+2, z, y+2, "beacon")
    INTF.placeBlock(x, z, y+2, "beacon")

# Drawing 3
def drawThree(x,y,z):
    INTF.placeBlock(x, z, y, "beacon")
    INTF.placeBlock(x, z, y+1, "beacon")
    INTF.placeBlock(x, z, y+2, "beacon")

    INTF.placeBlock(x+1, z, y+2, "beacon")

    INTF.placeBlock(x+2, z, y, "beacon")
    INTF.placeBlock(x+2, z, y+1, "beacon")
    INTF.placeBlock(x+2, z, y+2, "beacon")

    INTF.placeBlock(x+3, z, y+2, "beacon")

    INTF.placeBlock(x+4, z, y, "beacon")
    INTF.placeBlock(x+4, z, y+1, "beacon")
    INTF.placeBlock(x+4, z, y+2, "beacon")

# Drawing 0
def drawZero(x,y,z):
    INTF.placeBlock(x, z, y+1, "beacon")

    INTF.placeBlock(x, z, y, "beacon")
    INTF.placeBlock(x+1, z, y, "beacon")
    INTF.placeBlock(x+2, z, y, "beacon")
    INTF.placeBlock(x+3, z, y, "beacon")
    INTF.placeBlock(x+4, z, y, "beacon")

    INTF.placeBlock(x, z, y+2, "beacon")
    INTF.placeBlock(x+1, z, y+2, "beacon")
    INTF.placeBlock(x+2, z, y+2, "beacon")
    INTF.placeBlock(x+3, z, y+2, "beacon")
    INTF.placeBlock(x+4, z, y+2, "beacon")

    INTF.placeBlock(x+4, z, y+1, "beacon")
    
    
def buildHouse(x, z):
    
    #y = WORLDSLICE.heightmaps["MOTION_BLOCKING"][(x, z)]

    y = 5
    
    height = randint(5, 8)
    length = randint(7, 15)
    width = randint(6, 10)
    
    directions = ['north', 'east', 'west', 'south']
    direInv = ['south', 'west', 'east', 'north']
    doorDire = {'north': [length//2, 0], 'south': [0, width//2], 'west':[length//2, width], 'east':[length, width//2]}
    windowDire = {'north' : [length//3, 0], 'south' : [width//3, 0], 'west': [length//3, width], 'east':[length, width//3] }
    
    dire = randint(0,4)
    
    doorx = doorDire[directions[dire]][0]
    doorz = doorDire[directions[dire]][1]
    windowx = windowDire[directions[dire]][0]
    windowz = windowDire[directions[dire]][1] 

    door = direInv[dire] 
    
    for i in range(length):
        for j in range(width):
            INTF.placeBlock(x+i, y-1, z+j, 'clay')
            
    
    for i in range(height):
        for j in range(length):
            if randint(0, 10) < 2:
                INTF.placeBlock(x+j, y+i, z, 'bricks')
            else:
                INTF.placeBlock(x+j, y+i, z, 'dirt')
                
            if randint(0,10) < 2: 
                INTF.placeBlock(x+j, y+i, z+width, 'bricks')
            else:
                INTF.placeBlock(x+j, y+i, z+width, 'dirt')
            
            
        for k in range(width+1):
            if randint(0,10) < 2:
                INTF.placeBlock(x, y+i, z+k, 'bricks')
            else:
                INTF.placeBlock(x, y+i, z+k, 'dirt')
            if randint(0,10) < 2:
                INTF.placeBlock(x+length, y+i, z+k, 'bricks')
            else:
                INTF.placeBlock(x+length, y+i, z+k, 'dirt')
            
    roofHeight = height + y
    
    
    for i in range(length+1):
        for j in range(width+1):
            INTF.placeBlock(x+i, roofHeight, z+j, 'spruce_wood')
            
    for h in range(1, 3):
        for i in range(length+1):
            for j in range(width+1):
                INTF.placeBlock(x+i, roofHeight+h, z+j, 'hay_block')

    INTF.placeBlock(x+doorx, y, z+doorz, f'spruce_door[facing={door}, half=lower]')
    INTF.placeBlock(x+doorx, y+1, z+doorz, f'spruce_door[facing={door}, half=upper]')
    INTF.placeBlock(x+windowx, y+3, z+windowz, 'white_stained_glass_pane')
    
    INTF.placeBlock(x+1, y, z+width-1, 'crafting_table')
    INTF.placeBlock(x+1, y+1, z+width-1, 'lantern')
    INTF.placeBlock(x+length-1, y, z+width-1, 'chest')
    
    
    
def buildTownhall(x, z):
     
    #y = WORLDSLICE.heightmaps["MOTION_BLOCKING_NO_LEAVES"][(x, z)]
    y = 70
    
    height = 9
    width = 13
    length = 17
    
    for i in range(length+4):
        for j in range(width+4):
            INTF.placeBlock(x+i, y-1, z+j, 'stripped_oak_wood')
            INTF.placeBlock(x+i, y, z+j, 'oak_planks')
            
    INTF.placeBlock(x+14, y, z-1, 'stone_brick_stairs[facing=south]')
    INTF.placeBlock(x+13, y, z-1, 'stone_brick_stairs[facing=south]')
    
    INTF.placeBlock(x+7, y, z-1, 'stone_brick_stairs[facing=south]')
    INTF.placeBlock(x+6, y, z-1, 'stone_brick_stairs[facing=south]')
    
    INTF.placeBlock(x+14, y, z+width+4, 'stone_brick_stairs')
    INTF.placeBlock(x+13, y, z+width+4, 'stone_brick_stairs')
    
    INTF.placeBlock(x+7, y, z+width+4, 'stone_brick_stairs')
    INTF.placeBlock(x+6, y, z+width+4, 'stone_brick_stairs')
    
    # INTF.placeBlock(x-1, y, z+8, 'stone_brick_stairs[facing=east]')
    # INTF.placeBlock(x-1, y, z+7, 'stone_brick_stairs[facing=east]')
    
    # INTF.placeBlock(x+length+4, y, z+8, 'stone_brick_stairs[facing=west]')
    # INTF.placeBlock(x+length+4, y, z+7, 'stone_brick_stairs[facing=west]')
    
    for i in range(height):
        for j in range(3, length+1):
            if i == height-1:
                INTF.placeBlock(x+j, y+i, z+3, 'dark_oak_wood')
                INTF.placeBlock(x+j, y+i, z+width, 'dark_oak_wood')
            else:    
                if randint(1, 10) < 4:
                    INTF.placeBlock(x+j, y+i, z+3, 'infested_cracked_stone_bricks')
                    INTF.placeBlock(x+j, y+i, z+width, 'infested_cracked_stone_bricks')
                else:
                    INTF.placeBlock(x+j, y+i, z+3, 'stone_bricks')
                    INTF.placeBlock(x+j, y+i, z+width, 'stone_bricks')
                

        for j in range(3, width):
            if i == height-1:
                INTF.placeBlock(x+3, y+i, z+j, 'dark_oak_wood')
                INTF.placeBlock(x+length, y+i, z+j, 'dark_oak_wood')
            else:    
                if randint(1, 10) < 4:
                    INTF.placeBlock(x+3, y+i, z+j, 'infested_cracked_stone_bricks')
                    INTF.placeBlock(x+length, y+i, z+j, 'infested_cracked_stone_bricks')
                else:
                    INTF.placeBlock(x+3, y+i, z+j, 'stone_bricks')
                    INTF.placeBlock(x+length, y+i, z+j, 'stone_bricks')

        INTF.placeBlock(x+3, y+i, z+3, 'dark_oak_wood')
        INTF.placeBlock(x+3, y+i, z+width, 'dark_oak_wood')
        INTF.placeBlock(x+length, y+i, z+3, 'dark_oak_wood')
        INTF.placeBlock(x+length, y+i, z+width, 'dark_oak_wood')
         
        INTF.placeBlock(x+4, y+i, z+3, 'dark_oak_wood')
        INTF.placeBlock(x+4, y+i, z+width, 'dark_oak_wood')
        INTF.placeBlock(x+length-1, y+i, z+3, 'dark_oak_wood')
        INTF.placeBlock(x+length-1, y+i, z+width, 'dark_oak_wood')
        
        INTF.placeBlock(x+3, y+i, z+4, 'dark_oak_wood')
        INTF.placeBlock(x+3, y+i, z+width-1, 'dark_oak_wood')
        INTF.placeBlock(x+length, y+i, z+4, 'dark_oak_wood')
        INTF.placeBlock(x+length, y+i, z+width-1, 'dark_oak_wood')
        
            
    for i in range(3):
            INTF.placeBlock(x+5, y+i, z+2, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+8, y+i, z+2, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+12, y+i, z+2, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+15, y+i, z+2, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+5+i, y+3, z+2,'infested_mossy_stone_bricks')
            INTF.placeBlock(x+12+i, y+3, z+2,'infested_mossy_stone_bricks')
            INTF.placeBlock(x+8, y+3, z+2, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+15, y+3, z+2, 'infested_mossy_stone_bricks')
            
            INTF.placeBlock(x+5, y+i, z+1+width, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+8, y+i, z+1+width, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+12, y+i, z+1+width, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+15, y+i, z+1+width, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+5+i, y+3, z+1+width,'infested_mossy_stone_bricks')
            INTF.placeBlock(x+12+i, y+3, z+1+width,'infested_mossy_stone_bricks')
            INTF.placeBlock(x+8, y+3, z+1+width, 'infested_mossy_stone_bricks')
            INTF.placeBlock(x+15, y+3, z+1+width, 'infested_mossy_stone_bricks')
            
            
    INTF.placeBlock(x+10, y+2, z+1+ width, 'bell' )
    INTF.placeBlock(x+10, y+2, z+2, 'bell' )
     
    INTF.placeBlock(x+5, y+1, z+2+width, 'cornflower')
    INTF.placeBlock(x+8, y+1, z+2+width, 'cornflower')
    INTF.placeBlock(x+12, y+1, z+2+width, 'poppy')
    INTF.placeBlock(x+15, y+1, z+2+width, 'poppy')

    
    INTF.placeBlock(x+5, y+1, z+1, 'poppy')
    INTF.placeBlock(x+8, y+1, z+1, 'poppy')

    INTF.placeBlock(x+12, y+1, z+1, 'cornflower')
    INTF.placeBlock(x+15, y+1, z+1, 'cornflower')
            
            
    INTF.placeBlock(x+6, y+1, z+3, 'dark_oak_door[facing=south, half=lower, hinge=right]')
    INTF.placeBlock(x+6, y+2, z+3, 'dark_oak_door[facing=south, half=upper, hinge=right]')
    INTF.placeBlock(x+7, y+1, z+3, 'dark_oak_door[facing=south, half=lower, hinge=left]')
    INTF.placeBlock(x+7, y+2, z+3, 'dark_oak_door[facing=south, half=upper,hinge=left]')
    INTF.placeBlock(x+13, y+1, z+3, 'dark_oak_door[facing=south, half=lower, hinge=right]')
    INTF.placeBlock(x+13, y+2, z+3, 'dark_oak_door[facing=south, half=upper, hinge=right]')
    INTF.placeBlock(x+14, y+1, z+3, 'dark_oak_door[facing=south, half=lower,hinge=left]')
    INTF.placeBlock(x+14, y+2, z+3, 'dark_oak_door[facing=south, half=upper,hinge=left]')
    
    
    INTF.placeBlock(x+6, y+1, z+width, 'dark_oak_door[facing=south, half=lower, hinge=right]')
    INTF.placeBlock(x+6, y+2, z+width, 'dark_oak_door[facing=south, half=upper, hinge=right]')
    INTF.placeBlock(x+7, y+1, z+width, 'dark_oak_door[facing=south, half=lower, hinge=left]')
    INTF.placeBlock(x+7, y+2, z+width, 'dark_oak_door[facing=south, half=upper,hinge=left]')
    INTF.placeBlock(x+13, y+1, z+width, 'dark_oak_door[facing=south, half=lower, hinge=right]')
    INTF.placeBlock(x+13, y+2, z+width, 'dark_oak_door[facing=south, half=upper, hinge=right]')
    INTF.placeBlock(x+14, y+1, z+width, 'dark_oak_door[facing=south, half=lower,hinge=left]')
    INTF.placeBlock(x+14, y+2, z+width, 'dark_oak_door[facing=south, half=upper,hinge=left]')
    
    
    # for i in range(7):
    #     for j in range(length - i+4):
    #         for k in range(width-i):
    #             INTF.placeBlock(x+j+i, y+height+i, z+k, 'oak_wood')
        
    for i in range(5):
        INTF.placeBlock(x+8+i, y+1, z+6, 'spruce_slab' )
        INTF.placeBlock(x+8+i, y+1, z+9, 'spruce_slab' )
        
        INTF.placeBlock(x+8+i, y+1, z+7, 'spruce_fence[east=false, west=false, north=false, south=false]')
        INTF.placeBlock(x+8+i, y+1, z+8, 'spruce_fence[east=false, west=false, north=false, south=false]')
        INTF.placeBlock(x+8+i, y+1, z+7, 'spruce_fence[east=false, west=false, north=false, south=false]')
        INTF.placeBlock(x+8+i, y+1, z+8, 'spruce_fence[east=false, west=false, north=false, south=false]')
        
        INTF.placeBlock(x+8+i, y+2, z+7, 'spruce_slab')
        INTF.placeBlock(x+8+i, y+2, z+8, 'spruce_slab')
        
    INTF.placeBlock(x+5, y+1, z+8, 'brick_slab[type=bottom]')
    INTF.placeBlock(x+4, y+1, z+8, 'campfire')
    INTF.placeBlock(x+4, y+1, z+9, 'bricks')
    INTF.placeBlock(x+4, y+1, z+7, 'bricks')
    INTF.placeBlock(x+5, y+1, z+9, 'brick_slab[type=bottom]')
    INTF.placeBlock(x+5, y+1, z+7, 'brick_slab[type=bottom]')
    
    for i in range(2,height):
        INTF.placeBlock(x+5, y+i, z+8, 'bricks')
        INTF.placeBlock(x+4, y+i, z+9, 'bricks')
        INTF.placeBlock(x+4, y+i, z+7, 'bricks')
        INTF.placeBlock(x+5, y+i, z+9, 'bricks')
        INTF.placeBlock(x+5, y+i, z+7, 'bricks')
        
    INTF.placeBlock(x+5, y+2, z+8, 'air')
    INTF.placeBlock(x+5, y+2, z+7, 'air')
    INTF.placeBlock(x+5, y+2, z+9, 'air')
        
        
        
    
    INTF.placeBlock(x+length-1, y+1, z+6, 'stone_brick_stairs[facing=south]' )
    INTF.placeBlock(x+length-2, y+1, z+6, 'stone_brick_stairs[facing=south]' )
    INTF.placeBlock(x+length-1, y+2, z+7, 'stone_brick_stairs[facing=south]' )
    INTF.placeBlock(x+length-2, y+2, z+7, 'stone_brick_stairs[facing=south]' )
    INTF.placeBlock(x+length-1, y+3, z+8, 'stone_brick_stairs[facing=south]' )
    INTF.placeBlock(x+length-2, y+3, z+8, 'stone_brick_stairs[facing=south]' )
    
    INTF.placeBlock(x+length-1, y+3, z+9, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-2, y+3, z+9, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-3, y+3, z+9, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-4, y+3, z+9, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-1, y+3, z+10, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-2, y+3, z+10, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-3, y+3, z+10, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-4, y+3, z+10, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-1, y+3, z+11, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-2, y+3, z+11, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-3, y+3, z+11, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-4, y+3, z+11, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-1, y+3, z+12, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-2, y+3, z+12, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-3, y+3, z+12, 'nether_brick_slab[type=top]')
    INTF.placeBlock(x+length-4, y+3, z+12, 'nether_brick_slab[type=top]')
    
    INTF.placeBlock(x+length-4, y+4, z+10, 'spruce_fence_gate[facing=west]')
    INTF.placeBlock(x+length-4, y+4, z+11, 'spruce_fence_gate[facing=west]')
    INTF.placeBlock(x+length-4, y+4, z+12, 'spruce_fence' )
    INTF.placeBlock(x+length-4, y+5, z+12, 'redstone_torch' )
    
    INTF.placeBlock(x+length-4, y+4, z+9, 'spruce_fence[east=true]' )
    INTF.placeBlock(x+length-3, y+4, z+9, 'spruce_fence[west=true]' )
    INTF.placeBlock(x+length-4, y+4, z+9, 'spruce_fence[east=true]' )
    INTF.placeBlock(x+length-3, y+4, z+9, 'spruce_fence[west=true]' )
    INTF.placeBlock(x+length-3, y+5, z+9, 'redstone_torch' )
    INTF.placeBlock(x+length-1, y+2, z+width-1, 'lantern' )
    INTF.placeBlock(x+length-1, y+1, z+width-1, 'spruce_fence' )
    
    
    # making roof
    for k in range(length+1):
        for i in range(8):
            INTF.placeBlock(x+k+1, y+height-2+i, z+i+1, 'stripped_jungle_wood')    
            if (i == 7):
                for j in range(1,8):
                    INTF.placeBlock(x+k+1, y+height-2+i-j, z+i+j+1, 'stripped_jungle_wood')
    
    for i in range(8):
        for j in range(i, length-i-2):
            INTF.placeBlock(x+1, y+height-2+i, z+j+1, 'stripped_jungle_wood' )
            INTF.placeBlock(x+2+length, y+height-2+i, z+j+1, 'stripped_jungle_wood' )
            
    for i in range(length):
        for j in range(width-1):
            INTF.placeBlock(x+i+2, y+height-2, z+j+3, 'dark_oak_wood')
            
            
    INTF.placeBlock(x+1, y+height-3, z+1, 'lantern[hanging=true]')
    INTF.placeBlock(x+2+length, y+height-3, z+1, 'lantern[hanging=true]')
    INTF.placeBlock(x+1, y+height-3, z+width+2, 'lantern[hanging=true]')
    INTF.placeBlock(x+2+length, y+height-3, z+2+width, 'lantern[hanging=true]')
    
    INTF.placeBlock(x+length-1, y+height-3, z+width-1, 'lantern[hanging=true]')
    
    
    # chimney
    for i in range(7):
        INTF.placeBlock(x+3, y+height+2+i, z+width-1, 'stripped_spruce_log')
        INTF.placeBlock(x+4, y+height+2+i, z+width-1, 'stripped_spruce_log')
        INTF.placeBlock(x+5, y+height+2+i, z+width-1, 'stripped_spruce_log')
    
    for i in range(6):
        INTF.placeBlock(x+3, y+height+3+i, z+width-2, 'stripped_spruce_log')
        INTF.placeBlock(x+5, y+height+3+i, z+width-2, 'stripped_spruce_log')
        
    for i in range(5):
        INTF.placeBlock(x+3, y+height+4+i, z+width-3, 'stripped_spruce_log')
        INTF.placeBlock(x+4, y+height+4+i, z+width-3, 'stripped_spruce_log')
        INTF.placeBlock(x+5, y+height+4+i, z+width-3, 'stripped_spruce_log')
        
    INTF.placeBlock(x+4, y+height+3, z+width-2, 'campfire')
    INTF.placeBlock(x+4, y+height+4, z+width-2, 'campfire')
    INTF.placeBlock(x+4, y+height+5, z+width-2, 'campfire')
    
    
    # INTF.placeBlock(x+10, y+3, z+3, 'jungle_log' )
    # INTF.placeBlock(x+10, y+5, z+3, 'jungle_log' )
    INTF.placeBlock(x+11, y+4, z+3, 'glass_pane' )
    INTF.placeBlock(x+9, y+4, z+3,  'glass_pane' )
    INTF.placeBlock(x+11, y+5, z+3, 'jungle_log' )
    INTF.placeBlock(x+9, y+5, z+3,  'jungle_log' )
    INTF.placeBlock(x+11, y+3, z+3, 'jungle_log' )
    INTF.placeBlock(x+9, y+3, z+3,  'jungle_log' )
    INTF.placeBlock(x+10, y+3, z+3,  'glass_pane' )
    INTF.placeBlock(x+10, y+5, z+3,  'glass_pane' )
    INTF.placeBlock(x+10, y+4, z+3, 'jungle_log' )
                    
                    
    INTF.placeBlock(x+11, y+4, z+width, 'jungle_log' )
    INTF.placeBlock(x+9, y+4, z+width,   'jungle_log')
    
    INTF.placeBlock(x+11, y+5, z+width, 'glass_pane' )
    INTF.placeBlock(x+9, y+5, z+width,  'glass_pane' )
    INTF.placeBlock(x+11, y+3, z+width, 'glass_pane' )
    
    INTF.placeBlock(x+9, y+3, z+width,  'glass_pane' )
    INTF.placeBlock(x+10, y+3, z+width,  'jungle_log' )
    INTF.placeBlock(x+10, y+5, z+width,  'jungle_log')
    
    INTF.placeBlock(x+10, y+4, z+width, 'glass_pane')


            
            
    
    
    
    
    
def buildCannon(x,  z, dire):
    
    y = 5
    #y = WORLDSLICE.heightmaps["MOTION_BLOCKING_NO_LEAVES"][(x, z)]   
    direInverse = {'south': 'north', 'north':'south', 'west':'east', 'east':'west'}
    direction = direInverse[dire]
    sidestairs = {'east':'south', 'west': 'north', 'north':'west', 'south':'east'}
    sideDire = sidestairs[dire]
 
    check = 0
    if dire == 'south':
        length = -1
        width = -1
    elif dire == 'west':
        length = -1
        width = 1
        check = 1
    elif dire == 'east':
        length = 1
        width = -1
        check = 1
    else:
        length = 1
        width = 1
    
    
    for i in range(7):
        for j in range(12):
            for k in range( 12):
                if check == 0:
                    if i == 0:
                        if j >= 0 and k >= 0:
                            INTF.placeBlock(x+k*length-2, y-1, z+j*width-2, 'stone')
                    INTF.placeBlock(x+k*length-4*length, y+i,z+j*width-4*width, 'air') 
                else:
                    if i == 0:
                        if j >= 0 and k>=0:
                            INTF.placeBlock(x+j*width-2, y-1, z+i*length-2, 'stone')
                    INTF.placeBlock(x+j*width-4*width, y+i,z+k*length-4*length, 'air') 
    
    
    
    
    
    
    
    for i in range(7):
        for j in range(7):
            INTF.placeBlock(x+i*length, y, z+j*width, 'air') 
            
    for i in range(4):
        for j in range(6):    
            if check == 1:
                INTF.placeBlock(x+j*width, y, z+i*length, 'stone')
            else:
                INTF.placeBlock(x+i*length, y, z+j*width, 'stone')
            
    for i in range(1, 4):
        for j in range(6):
            j = width * j
            if check == 1:  
                INTF.placeBlock(x+j, y+i, z, 'stone')
                INTF.placeBlock(x+j, y+i, z+1*length, 'obsidian')
                INTF.placeBlock(x+j , y+i, z+2*length, 'obsidian')
                INTF.placeBlock(x+j, y+i, z+3*length, 'obsidian')
            else:
                INTF.placeBlock(x, y+i, z+j, 'stone')
                INTF.placeBlock(x+1*length, y+i, z+j, 'obsidian')
                INTF.placeBlock(x+2*length, y+i, z+j, 'obsidian')
                INTF.placeBlock(x+3*length, y+i, z+j, 'obsidian')
  
  
    for i in range(-1, 7):
        if check == 0:
            INTF.placeBlock(x-1*length, y+3, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x+4*length, y+3, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x-1*length, y+2, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x+4*length, y+2, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x-1*length, y+1, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x+4*length, y+1, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x-1*length, y, z+i*width, 'dark_oak_wood')
            INTF.placeBlock(x+4*length, y, z+i*length, 'dark_oak_wood')
            for j in range(4):
                INTF.placeBlock(x+3*length, y+j, z-1*width, 'dark_oak_wood')
                INTF.placeBlock(x, y+j, z+6*width, 'dark_oak_wood')
                INTF.placeBlock(x+3*length, y+j, z+6*width, 'dark_oak_wood')
                INTF.placeBlock(x, y+j, z-1*width, 'dark_oak_wood')
            
            if i == -1:
                continue
            elif i == 6:
                INTF.placeBlock(x-1*length, y+4, z-1*width, 'dark_oak_fence')
                INTF.placeBlock(x+4*length, y+4, z-1*width, 'dark_oak_fence')
                INTF.placeBlock(x+4*length, y+4, z+6*width, 'dark_oak_fence')
                INTF.placeBlock(x-1*length, y+4, z+6*width, 'dark_oak_fence')
                INTF.placeBlock(x-1*length, y+5, z-1*width, 'redstone_torch')
                INTF.placeBlock(x+4*length, y+5, z-1*width, 'redstone_torch')
                INTF.placeBlock(x+4*length, y+5, z+6*width, 'redstone_torch')
                INTF.placeBlock(x-1*length, y+5, z+6*width, 'redstone_torch')
                
            else:
                INTF.placeBlock(x-1*length, y+4, z+i*width, 'dark_oak_fence_gate[facing=west]')
                INTF.placeBlock(x+4*length, y+4, z+i*width, 'dark_oak_fence_gate[facing=east]')
        else:
            INTF.placeBlock(x +i*width, y+3, z-1*length, 'dark_oak_wood')
            INTF.placeBlock(x +i*width, y+3, z+4*length, 'dark_oak_wood')
            INTF.placeBlock(x +i*width, y+2, z-1*length, 'dark_oak_wood')
            INTF.placeBlock(x +i*width, y+2, z+4*length, 'dark_oak_wood')
            INTF.placeBlock(x +i*width, y+1, z-1*length, 'dark_oak_wood')
            INTF.placeBlock(x+ i*width, y+1, z+4*length, 'dark_oak_wood')
            INTF.placeBlock(x+i*width, y, z -1*length, 'dark_oak_wood')
            INTF.placeBlock(x+ i*width, y, z+ 4*length, 'dark_oak_wood')
            for j in range(4):
                INTF.placeBlock(x -1*width, y+j, z+3*length, 'dark_oak_wood')
                INTF.placeBlock(x+6*width, y+j, z, 'dark_oak_wood')
                INTF.placeBlock(x+6*width, y+j, z +3*length, 'dark_oak_wood')
                INTF.placeBlock(x-1*width, y+j, z, 'dark_oak_wood')
            
            if i == -1:
                continue
            elif i == 6:
                INTF.placeBlock(x-1*width, y+4, z -1*length, 'dark_oak_fence')
                INTF.placeBlock(x-1*width, y+4, z +4*length, 'dark_oak_fence')
                INTF.placeBlock(x+6*width, y+4, z +4*length, 'dark_oak_fence')
                INTF.placeBlock(x +6*width, y+4, z-1*length, 'dark_oak_fence')
                INTF.placeBlock(x -1*width, y+5, z -1*length, 'redstone_torch')
                INTF.placeBlock(x-1*width, y+5, z +4*length, 'redstone_torch')
                INTF.placeBlock(x+6*width, y+5, z +4*length, 'redstone_torch')
                INTF.placeBlock(x+6*width, y+5, z -1*length, 'redstone_torch')
                
            else:
                INTF.placeBlock(x+i*width, y+4, z -1*length, 'dark_oak_fence_gate[facing=north]')
                INTF.placeBlock(x+i*width, y+4, z +4*length, 'dark_oak_fence_gate[facing=south]')
            
            # INTF.placeBlock(x+2*length, y+3, z+j, 'obsidian')
            # INTF.placeBlock(x+3*length, y+3, z+j, 'obsidian')
        
            
          
    for i in range(1, 5):
        i = width*i
        if check == 1:
            INTF.placeBlock(x+i, y+2, z+2*length, 'redstone_wire')
        else: 
            INTF.placeBlock(x+2*length, y+2, z+i, 'redstone_wire')
    
    if check == 0:
        INTF.placeBlock(x+2*length, y+2, z+5*width, 'sticky_piston[facing=up]')
        INTF.placeBlock(x+2*length, y+3, z+5*width, 'slime_block')
        
        
        INTF.placeBlock(x+2*length, y+4, z+1*width, 'stone_button[face=floor]')
        INTF.placeBlock(x+2*length, y+4, z+2*width, f'sticky_piston[facing={direction}]')
        INTF.placeBlock(x+2*length, y+4, z+3*width, 'slime_block')
        INTF.placeBlock(x+2*length, y+4, z+4*width, 'tnt')
        
        INTF.placeBlock(x+1*length, y+4, z+1*width, 'redstone_wire' )
        INTF.placeBlock(x, y+4, z+1*width, 'redstone_wire' )
        INTF.placeBlock(x, y+4, z+2*width, 'redstone_wire' )
        INTF.placeBlock(x, y+4, z+3*width, 'redstone_wire' )
        INTF.placeBlock(x, y+4, z+4*width, 'redstone_wire' )
        INTF.placeBlock(x+1*length, y+4, z+4*width, 'redstone_wire' )
        
        INTF.placeBlock(x+2*length, y, z-3*width, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x+2*length, y+1, z-2*width, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x+2*length, y+2, z-1*width, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x+2*length, y+3, z, f'stone_brick_stairs[facing={direction}]')
        
        INTF.placeBlock(x+1*length, y, z-3*width, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x+1*length, y+1, z-2*width, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x+1*length, y+2, z-1*width, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x+1*length, y+3, z, f'stone_brick_stairs[facing={direction}]')
        
    
    else:
        INTF.placeBlock(x+5*width, y+2, z+2*length, 'sticky_piston[facing=up]')
        INTF.placeBlock(x+5*width, y+3, z+2*length, 'slime_block')
        
        
        INTF.placeBlock(x+1*width, y+4, z+2*length, 'stone_button[face=floor]')
        INTF.placeBlock(x+2*width, y+4, z+2*length, f'sticky_piston[facing={direction}]')
        INTF.placeBlock(x+3*width, y+4, z+2*length, 'slime_block')
        INTF.placeBlock(x+4*width, y+4, z+2*length, 'tnt')
        
        INTF.placeBlock(x+1*width, y+4, z+1*length, 'redstone_wire' )
        INTF.placeBlock(x+1*width, y+4, z, 'redstone_wire' )
        INTF.placeBlock(x+2*width, y+4, z, 'redstone_wire' )
        INTF.placeBlock(x+3*width, y+4, z, 'redstone_wire' )
        INTF.placeBlock(x+4*width, y+4, z, 'redstone_wire' )
        INTF.placeBlock(x+4*width, y+4, z+1*length, 'redstone_wire' )
        
        INTF.placeBlock(x-3*width, y, z+2*length, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x-2*width, y+1, z+2*length, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x-1*width, y+2, z +2*length, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x, y+3, z+2*length, f'stone_brick_stairs[facing={direction}]')
        
        INTF.placeBlock(x-3*width, y, z+1*length, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x-2*width, y+1, z+1*length, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x-1*width, y+2, z+1*length, f'stone_brick_stairs[facing={direction}]')
        INTF.placeBlock(x, y+3, z+1*length, f'stone_brick_stairs[facing={direction}]')
    
            
            
                
def clearLand(x, y, z):
    for i in range(20):
        for j in range(25):
            for k in range(25):
                if i == 0:
                    INTF.placeBlock(x+k, y-1, z+j, 'stone')
                INTF.placeBlock(x+k, y+i,z+j, 'air')    
         

if __name__ == '__main__':
    try:
        x = 18
        y = 9
        height = WORLDSLICE.heightmaps["MOTION_BLOCKING"][(x, y)]
        #INTF.runCommand(f"tp @a {STARTX} {height+30} {STARTZ}")
        # GEO.placeCenteredCylinder(21, height, 21, 1, 15, "end_stone_bricks")
        # GEO.placeCenteredCylinder(21, height, 21, 1, 14, "gold_block")
        # z = height
        # drawC(x,y,z)
        # y += 4
        # drawS(x,y,z)
        # y +=5
        # drawFour(x,y,z)
        # y+= 5
        # drawThree(x,y,z)
        # y+= 4
        # drawZero(x,y,z)
        # y+=4
        # drawThree(x,y,z)
        # y+=4
        # # Clearing upper tiles if any
        # GEO.placeCenteredCylinder(21, height + 1, 21, 3, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 4, 21, 2, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 6, 21, 1, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 7, 21, 1, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 8, 21, 1, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 9, 21, 1, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 10, 21, 1, 15, "air")
        # GEO.placeCenteredCylinder(21, height + 11, 21, 1, 15, "air")
        
        
        # for i in range(20):
        #     INTF.placeBlock(i, 64, 90, 'gold_block')
        # GEO.placeCenteredCylinder(20, 64, 90, 1, 15, "end_stone")
        
        #GEO.placeCuboid(101, 64, 147, 5, 5, 5, 'air')
        #clearLand(170, 70, 50)
        
        #buildHouse(78, 133)
        
        buildTownhall(173, 54)
        
        
        #buildCannon(173, 54, 'north')

        
        
        print("Done!")
    
    except:

        print("Pressed Ctrl-C to kill program.")

