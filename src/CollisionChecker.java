public class CollisionChecker {

    GamePanels gp;

    public CollisionChecker(GamePanels gp){
        this.gp = gp;
    }
    public  void checkTile(Entity entity){
        int entityLeftWX = entity.worldX + entity.solidArea.x;
        int entityRightWX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWY = entity.worldY + entity.solidArea.y;
        int entityBottomWY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLCol = entityLeftWX / gp.tileSize;
        int entityRCol = entityRightWX / gp.tileSize;
        int entityTRow = entityTopWY / gp.tileSize;
        int entityBTRow = entityBottomWY / gp.tileSize;

        int tileNum1;
        int tileNum2;

        switch ((entity.direction)){
            case "up":
                entityTRow = (entityTopWY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityTRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBTRow = (entityBottomWY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityBTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBTRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right to left":
                entityLCol = (entityLeftWX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityLCol][entityBTRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left to right":
                entityRCol = (entityRightWX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRCol][entityTRow];
                tileNum2 = gp.tileM.mapTileNum[entityRCol][entityBTRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }
    }
}
