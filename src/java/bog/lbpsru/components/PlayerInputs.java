package bog.lbpsru.components;

import bog.lbpsru.components.utils.Button;
import bog.lbpsru.components.utils.Utils;

/**
 * @author Bog
 */
public class PlayerInputs {

    private int buttons = 0;
    private float leftX = 0;
    private float leftY = 0;
    private float rightX = 0;
    private float rightY = 0;

    public void update(int buttons, float leftX, float leftY, float rightX, float rightY)
    {
        this.buttons = buttons;
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.rightY = rightY;
    }

    public boolean isButtonPressed(Button button)
    {
        return (buttons & button.getFlag()) != 0;
    }

    public void setButtonPressed(Button button, boolean pressed)
    {
        if(!isButtonPressed(button) && pressed)
            buttons = buttons | button.getFlag();
        else if(isButtonPressed(button) && !pressed)
            buttons = buttons & ~button.getFlag();
    }

    public void setLeftStick(float x, float y)
    {
        leftX = x;
        leftY = y;
    }

    public void setRightStick(float x, float y)
    {
        rightX = x;
        rightY = y;
    }

    public float[] getLeftStick()
    {
        return new float[]{leftX, leftY};
    }

    public float[] getRightStick()
    {
        return new float[]{rightX, rightY};
    }

    public void putPlayerInputsIntoBuffer(byte[] buffer, int index)
    {
        Utils.putIntIntoBuffer(this.buttons, buffer, index);
        Utils.putIntIntoBuffer(Math.min(Math.round((this.leftX + 1) * 127.5f), 255), buffer, index + 1);
        Utils.putIntIntoBuffer(Math.min(Math.round((this.leftY + 1) * 127.5f), 255), buffer, index + 2);
        Utils.putIntIntoBuffer(Math.min(Math.round((this.rightX + 1) * 127.5f), 255), buffer, index + 3);
        Utils.putIntIntoBuffer(Math.min(Math.round((this.rightY + 1) * 127.5f), 255), buffer, index + 4);
    }

}
