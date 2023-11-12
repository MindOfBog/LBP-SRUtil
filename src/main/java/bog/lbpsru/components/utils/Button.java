package bog.lbpsru.components.utils;

/**
 * @author Bog
 */
public enum Button {

    //flags shamelessly stolen from racman https://github.com/MichaelRelaxen/racman/blob/master/RaCTrainer/Inputs.cs

    l2(0x1),
    r2(0x2),
    l1(0x4),
    r1(0x8),
    triangle(0x10),
    circle(0x20),
    cross(0x40),
    square(0x80),
    select(0x10000),
    l3(0x20000),
    r3(0x40000),
    start(0x80000),
    up(0x100000),
    right(0x200000),
    down(0x400000),
    left(0x800000);

    private final int flag;

    Button(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public static Button fromFlag(int flag) {
        for (Button enumFlag : Button.values())
            if (enumFlag.getFlag() == flag)
                return enumFlag;
        throw new IllegalArgumentException("No button with flag \"" + flag + "\".");
    }

    public static int fromName(String name)
    {
        for (Button button : Button.values())
            if(name.equalsIgnoreCase(button.name()))
                return button.getFlag();
        return -1;
    }
}