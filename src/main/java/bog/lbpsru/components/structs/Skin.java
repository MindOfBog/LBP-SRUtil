package bog.lbpsru.components.structs;

/**
 * @author Bog
 */
public class Skin {

    public String path;
    public String name;

    public TextureFormat base;

    public TextureFormat r3;
    public TextureFormat r3Press;
    public TextureFormat l3;
    public TextureFormat l3Press;

    public int analogPitch = 32;

    public TextureFormat dpadLeft;
    public TextureFormat dpadRight;
    public TextureFormat dpadDown;
    public TextureFormat dpadUp;

    public TextureFormat cross;
    public TextureFormat circle;
    public TextureFormat triangle;
    public TextureFormat square;

    public TextureFormat select;
    public TextureFormat start;

    public TextureFormat r1;
    public TextureFormat l1;
    public TextureFormat l2;
    public TextureFormat r2;

    public String image;

    public Skin(String name, String path)
    {
        this.name = name;
        this.path = path;
    }

    public static class TextureFormat
    {
        public int drawX;
        public int drawY;
        public int spriteX;
        public int spriteY;
        public int spriteWidth;
        public int spriteHeight;

        public TextureFormat(int drawX, int drawY, int spriteX, int spriteY, int spriteWidth, int spriteHeight) {
            this.drawX = drawX;
            this.drawY = drawY;
            this.spriteX = spriteX;
            this.spriteY = spriteY;
            this.spriteWidth = spriteWidth;
            this.spriteHeight = spriteHeight;
        }
    }
}
