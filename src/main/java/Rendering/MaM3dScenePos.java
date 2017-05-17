package Rendering;

/**
 * Created by duckman on 23/05/2016.
 */
public enum MaM3dScenePos
{
    TopHalfOfsky (0x0000, 0, 8, 8, 1, 0x0000, 0),
    BottomHalfOfsky (0x0000, 1, 8, 25, 1, 0x0000, 0),
    Ground (0x0000, 0, 8, 67, 0, 0x0000, 1),
    SurfaceTile4StepsForward3Left (0x0000, 0, 8, 67, 0, 0x0000),
    SurfaceTile4StepsForward2Left (0x0000, 0, 38, 67, 0, 0x0000),
    SurfaceTile4StepsForward1Left (0x0000, 0, 84, 67, 0, 0x0000),
    SurfaceTile4StepsForward3Right (0x0000, 0, 134, 67, 0, 0x0000),
    SurfaceTile4StepsForward2Right (0x0000, 0, 117, 67, 0, 0x0000),
    SurfaceTile4StepsForward1Right (0x0000, 0, 117, 67, 0, 0x0000),
    SurfaceTileDirectly4StepsForward (0x0000, 0, 103, 67, 0, 0x0000),
    SurfaceTile3StepsForward3Left (0x0000, 0, 8, 73, 0, 0x0000),
    SurfaceTile3StepsForward2Left (0x0000, 0, 8, 73, 0, 0x0000),
    SurfaceTile3StepsForward1Left (0x0000, 0, 30, 73, 0, 0x0000),
    SurfaceTile3StepsForward3Right (0x0000, 0, 181, 73, 0, 0x0000),
    SurfaceTile3StepsForward2Right (0x0000, 0, 154, 73, 0, 0x0000),
    SurfaceTile3StepsForward1Right (0x0000, 0, 129, 73, 0, 0x0000),
    SurfaceTileDirectly3StepsForward (0x0000, 0, 87, 73, 0, 0x0000),
    SurfaceTile2StepsForward2Left (0x0000, 0, 8, 81, 0, 0x0000),
    SurfaceTile2StepsForward1Left (0x0000, 0, 8, 81, 0, 0x0000),
    SurfaceTile2StepsForward2Right (0x0000, 0, 202, 81, 0, 0x0000),
    SurfaceTile2StepsForward1Right (0x0000, 0, 145, 81, 0, 0x0000),
    SurfaceTileDirectly2StepsForward (0x0000, 0, 63, 81, 0, 0x0000),
    SurfaceTile1StepForward1Left (0x0000, 0, 8, 93, 0, 0x0000),
    SurfaceTile1StepForward1Right (0x0000, 0, 169, 93, 0, 0x0000),
    SurfaceTileDirectly1StepForward (0x0000, 0, 31, 93, 0, 0x0000),
    SurfaceTileDirectly1StepLeft (0x0000, 0, 8, 109, 0, 0x0000),
    SurfaceTileDirectly1StepRight (0x0000, 0, 201, 109, 0, 0x0000),
    SurfaceTileplayerIscurrentlyOn (0x0000, 0, 8, 109, 0, 0x0000),
    WallTile4StepsForward4StepsLeft (0x0000, 1, -64, 61, 14, 0x2000),
    WallTile4StepsForward3StepsLeft (0x0000, 1, -40, 61, 14, 0x0000),
    WallTile4StepsForward2StepsLeft (0x0000, 1, -16, 61, 14, 0x0000),
    WallTile4StepsForward1StepLeft (0x0000, 1, 8, 61, 14, 0x0000),
    WallTile4StepsForward4StepsRight (0x0000, 1, 128, 61, 14, 0xA000),
    WallTile4StepsForward3StepsRight (0x0000, 1, 104, 61, 14, 0x8000),
    WallTile4StepsForward2StepsRight (0x0000, 1, 80, 61, 14, 0x8000),
    WallTile4StepsForward1StepRight (0x0000, 1, 56, 61, 14, 0x8000),
    WallTileDirectly4StepsForward (0x0000, 1, 32, 61, 14, 0x0000),
    OutdoorObjectDirectly4StepsForward (0xFFFF, 0, -9, 61, 14, 0x0000),
    OutdoorObject4StepsForward1StepLeft(0xFFFF, 0, -58, 61, 14, 0x0000),
    OutdoorObject4StepsForward1StepRigt(0xFFFF, 0, 40, 61, 14, 0x0000),
    OutdoorObject4StepsForward2StepsLeft(0xFFFF, 0, -82, 61, 14, 0x0000),
    OutdoorObject4StepsForward2StepsRight(0xFFFF, 0, 64, 61, 14, 0x0000),
    Monsters4StepsForward (0xFFFF, 0, -41, 61, 14, 0x0000),  //?
//    Monsters4StepsForward (0xFFFF, 0, -26, 61, 14, 0x0000),  //?
//    Monsters4StepsForward (0xFFFF, 0, -34, 61, 14, 0x0000),  //?
//    Monsters4StepsForward (0xFFFF, 0, -16, 61, 14, 0x0000),  //?
//    Monsters4StepsForward (0xFFFF, 0, 23, 61, 14, 0x0000),   //?
//    Monsters4StepsForward (0xFFFF, 0, 16, 61, 14, 0x0000),   //?
//    Monsters4StepsForward (0xFFFF, 0, -58, 61, 14, 0x0000),  //?
//    Monsters4StepsForward (0xFFFF, 0, 40, 61, 14, 0x0000),   //?
//    Monsters4StepsForward (0xFFFF, 0, -17, 61, 14, 0x0000),  //?
//    Monsters4StepsForward (0xFFFF, 0, -1, 58, 14, 0x0000),   //?
//    Monsters4StepsForward (0xFFFF, 0, -9, 58, 14, 0x0000),   //?
    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 72, 58, 12, 0x0000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 72, 58, 12, 0x8000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 69, 63, 12, 0x0000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 75, 63, 12, 0x8000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 73, 53, 12, 0x0000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 71, 53, 12, 0x8000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 80, 57, 12, 0x0000),
//    ProjecTileWeaponSprite4StepsForward (0xFFFF, 0, 64, 57, 12, 0x8000),
    WallTile3StepsForward2StepsLeft (0x0000, 2, -11, 54, 8, 0x0000),
    WallTile3StepsForward1StepLeft (0x0000, 1, -21, 54, 11, 0x0000),
    WallTile3StepsForward2StepsRight (0x0000, 2, 165, 54, 8, 0x8000),
    WallTile3StepsForward1StepRight (0x0000, 1, 86, 54, 11, 0x8000),
    WallTileDirectly3StepsForward (0x0000, 1, 33, 54, 11, 0x0000),
    OutdoorObjectDirectly3StepsForward (0xFFFF, 0, -8, 54, 12, 0x0000),
    OutdoorObject3StepsForward1StepLeft(0xFFFF, 0, -73, 54, 12, 0x0000),
    OutdoorObject3StepsForward1StepRight(0xFFFF, 0, 57, 54, 12, 0x0000),
    Monsters3StepsForward (0xFFFF, 0, -65, 54, 12, 0x0000),  //?
//    Monsters3StepsForward (0xFFFF, 0, -81, 54, 12, 0x0000),  //?
//    Monsters3StepsForward (0xFFFF, 0, 49, 54, 12, 0x0000),   //?
//    Monsters3StepsForward (0xFFFF, 0, 65, 54, 12, 0x0000),   //?
//    Monsters3StepsForward (0xFFFF, 0, -24, 54, 12, 0x0000),  //?
//    Monsters3StepsForward (0xFFFF, 0, 9, 50, 12, 0x0000),    //?
//    Monsters3StepsForward (0xFFFF, 0, -8, 50, 12, 0x0000),   //?
    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 72, 53, 8, 0x0000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 72, 53, 8, 0x8000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 77, 58, 8, 0x0000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 67, 58, 8, 0x8000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 81, 47, 8, 0x0000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 63, 47, 8, 0x8000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 94, 52, 8, 0x0000),
//    ProjecTileWeaponSprite3StepsForward (0xFFFF, 0, 50, 52, 8, 0x8000),
    WallTile2StepsForward1StepLeft (0x0000, 2, 8, 40, 0, 0x0000),
    WallTile2StepsForward1StepRight (0x0000, 2, 146, 40, 0, 0x8000),
    WallTileDirectly2StepsForward (0x0000, 1, 32, 40, 6, 0x0000),
    OutdoorObjectDirectly2StepsForward (0xFFFF, 0, -7, 30, 7, 0x0000),
    OutdoorObject2StepsForward1StepLeft(0xFFFF, 0, -112, 30, 7, 0x20),
    OutdoorObject2StepsForward1StepRight(0xFFFF, 0, 98, 30, 7, 0x2000),
    Monsters2StepsForward(0xFFFF, 0, -112, 30, 8, 0x20),   //
//    Monsters2StepsForward(0xFFFF, 0, 98, 30, 8, 0x2000),   //
//    Monsters2StepsForward (0xFFFF, 0, -38, 30, 8, 0x0000), //
//    Monsters2StepsForward (0xFFFF, 0, 25, 30, 8, 0x0000),  //
//    Monsters2StepsForward (0xFFFF, 0, -7, 30, 8, 0x0000),  //
    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 72, 48, 4, 0x0000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 72, 48, 4, 0x8000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 85, 53, 4, 0x0000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 59, 53, 4, 0x8000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 89, 41, 4, 0x0000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 55, 41, 4, 0x8000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 106, 47, 4, 0x0000),
//    ProjecTileWeaponSprite2StepsForward (0xFFFF, 0, 38, 47, 4, 0x8000),
    WallTile1StepForward1StepLeft (0x0000, 0, 8, 24, 0, 0x0000),
    WallTile1StepForward1StepRight (0x0000, 0, 169, 24, 0, 0x8000),
    WallTileDirectly1StepForward (0x0000, 1, 32, 24, 0, 0x0000),
//    (0x0000, 0, -23, 40, 0, 0x2000),
//            (0x0000, 0, 200, 40, 0, 0xA000),
    WallTileDirectly1StepLeft (0x0000, 0, 8, 47, 0, 0x0000),
    WallTileDirectly1StepRight (0x0000, 0, 169, 47, 0, 0x8000),
    WallTileplayerIsCurrentlyOn (0x0000, 1, -56, -4, 0x8000, 0x6000),
    
//TODO    
/*(0xFFFF, 0, -5, 2, 0, 0x6000),
0xFFFF, 0, -67, 2, 0, 0x6000),
0xFFFF, 0, 44, 73, 0, 0x0000),
0xFFFF, 0, 44, 73, 0, 0x0000),
0xFFFF, 0, 58, 14, 0, 0x6000),
0xFFFF, 0, 169, 73, 0, 0x0000),
0xFFFF, 0, 169, 73, 0, 0x0000),
0xFFFF, 0, -5, 14, 0, 0x6000),
0xFFFF, 0, 110, 73, 0, 0x0000),
0xFFFF, 0, 110, 73, 0, 0x0000),
0xFFFF, 0, -5, 14, 0, 0x6000),
0xFFFF, 0, 110, 73, 0, 0x0000),
0xFFFF, 0, 110, 73, 0, 0x0000),*/
    
    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 72, 43, 0, 0x8000),
    MonsterCurrentMiddle(0x0000, 1, 32, 24, 0, 0x0000);


//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 72, 43, 0, 0x0000),
//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 93, 48, 0, 0x0000),
//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 51, 48, 0, 0x8000),
//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 97, 36, 0, 0x0000),
//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 47, 36, 0, 0x8000),
//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 118, 42, 0, 0x0000),
//    ProjecTileWeaponSprite1StepsForward (0xFFFF, 0, 26, 42, 0, 0x8000);



    int defaultSprite;
    int frame;
    int xPos;
    int yPos;
    double scale;
    int flags;
    int renderDepth;

    MaM3dScenePos(int defaultSprite, int frame, int xPos, int yPos,
                  double scale, int flags, int renderDepth) {
        this.defaultSprite = defaultSprite;
        this.frame = frame;
        this.xPos = xPos;
        this.yPos = yPos;
        this.scale = scale;
        this.flags = flags;
        this.renderDepth = renderDepth;
    }

    MaM3dScenePos(int defaultSprite, int frame, int xPos, int yPos,
                  double scale, int flags)
    {
        //this(defaultSprite, frame, xPos, yPos, 1.0/scale, flags, 5);
        this(defaultSprite, frame, xPos, yPos, 1.0, flags, 5);
    }

    public final RenderablePos getRenderablePosition()
    {
        return new RenderablePos(xPos, yPos, scale, RenderablePos.ScalePosition.Centre, renderDepth);
    }
}
