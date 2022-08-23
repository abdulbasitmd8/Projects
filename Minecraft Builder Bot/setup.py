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
    
    
def buildHouse(x,y, z):
    
    #y = WORLDSLICE.heightmaps["MOTION_BLOCKING"][(x, z)]

    #y = 5
    
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
    
 
 
    
                
def clearLand(x, y, z):
    for i in range(13):
        for j in range(20):
            for k in range(20):
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
        clearLand(78, 5, 133)
        buildHouse(78, 133)

        
        
  
        print("Done!")
    
    except:

        print("Pressed Ctrl-C to kill program.")

