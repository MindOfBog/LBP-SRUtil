package bog.lbpsru.components.structs;

import bog.lbpsru.components.utils.Button;
import bog.lbpsru.components.utils.Utils;
import com.google.gson.*;

import java.lang.reflect.Type;

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

    public void setInputs(PlayerInputs inputs)
    {
        this.buttons = inputs.buttons;
        this.leftX = inputs.leftX;
        this.leftY = inputs.leftY;
        this.rightX = inputs.rightX;
        this.rightY = inputs.rightY;
    }

    @Override
    public PlayerInputs clone()
    {
        PlayerInputs inputs = new PlayerInputs();
        inputs.update(this.buttons, this.leftX, this.leftY, this.rightX, this.rightY);
        return inputs;
    }

    public static PlayerInputs deserialize(JsonObject jsonPlayerInputs, PlayerInputs inputs) throws JsonParseException {

        if(jsonPlayerInputs == null)
            return null;

        if(inputs == null)
            inputs = new PlayerInputs();

        for(Button button : Button.values())
            if(jsonPlayerInputs.get(button.name()) != null)
                inputs.setButtonPressed(button, jsonPlayerInputs.get(button.name()).getAsBoolean());

//        if(jsonPlayerInputs.get("l2") != null)
//            inputs.setButtonPressed(Button.l2, jsonPlayerInputs.get("l2").getAsBoolean());
//
//        if(jsonPlayerInputs.get("r2") != null)
//            inputs.setButtonPressed(Button.r2, jsonPlayerInputs.get("r2").getAsBoolean());
//
//        if(jsonPlayerInputs.get("l1") != null)
//            inputs.setButtonPressed(Button.l1, jsonPlayerInputs.get("l1").getAsBoolean());
//
//        if(jsonPlayerInputs.get("r1") != null)
//            inputs.setButtonPressed(Button.r1, jsonPlayerInputs.get("r1").getAsBoolean());
//
//        if(jsonPlayerInputs.get("triangle") != null)
//            inputs.setButtonPressed(Button.triangle, jsonPlayerInputs.get("triangle").getAsBoolean());
//
//        if(jsonPlayerInputs.get("circle") != null)
//            inputs.setButtonPressed(Button.circle, jsonPlayerInputs.get("circle").getAsBoolean());
//
//        if(jsonPlayerInputs.get("cross") != null)
//            inputs.setButtonPressed(Button.cross, jsonPlayerInputs.get("cross").getAsBoolean());
//
//        if(jsonPlayerInputs.get("square") != null)
//            inputs.setButtonPressed(Button.square, jsonPlayerInputs.get("square").getAsBoolean());
//
//        if(jsonPlayerInputs.get("select") != null)
//            inputs.setButtonPressed(Button.select, jsonPlayerInputs.get("select").getAsBoolean());
//
//        if(jsonPlayerInputs.get("l3") != null)
//            inputs.setButtonPressed(Button.l3, jsonPlayerInputs.get("l3").getAsBoolean());
//
//        if(jsonPlayerInputs.get("r3") != null)
//            inputs.setButtonPressed(Button.r3, jsonPlayerInputs.get("r3").getAsBoolean());
//
//        if(jsonPlayerInputs.get("start") != null)
//            inputs.setButtonPressed(Button.start, jsonPlayerInputs.get("start").getAsBoolean());
//
//        if(jsonPlayerInputs.get("up") != null)
//            inputs.setButtonPressed(Button.up, jsonPlayerInputs.get("up").getAsBoolean());
//
//        if(jsonPlayerInputs.get("right") != null)
//            inputs.setButtonPressed(Button.right, jsonPlayerInputs.get("right").getAsBoolean());
//
//        if(jsonPlayerInputs.get("down") != null)
//            inputs.setButtonPressed(Button.down, jsonPlayerInputs.get("down").getAsBoolean());
//
//        if(jsonPlayerInputs.get("left") != null)
//            inputs.setButtonPressed(Button.left, jsonPlayerInputs.get("left").getAsBoolean());

        JsonElement lstick = jsonPlayerInputs.get("lstick");
        JsonElement rstick = jsonPlayerInputs.get("rstick");

        if(lstick != null)
        {
            JsonObject ls = lstick.getAsJsonObject();
            JsonElement x = ls.get("x");
            JsonElement y = ls.get("y");

            float lsx = inputs.leftX;
            float lsy = inputs.leftY;

            if(x != null)
                lsx = x.getAsFloat();
            if(y != null)
                lsy = y.getAsFloat();

            inputs.setLeftStick(lsx, lsy);
        }
        if(rstick != null)
        {
            JsonObject rs = rstick.getAsJsonObject();
            JsonElement x = rs.get("x");
            JsonElement y = rs.get("y");

            float rsx = inputs.rightX;
            float rsy = inputs.rightY;

            if(x != null)
                rsx = x.getAsFloat();
            if(y != null)
                rsy = y.getAsFloat();

            inputs.setRightStick(rsx, rsy);
        }

        return inputs;
    }

    public JsonElement serialize() {
        JsonObject json = new JsonObject();

        for(Button button : Button.values())
        {
            json.addProperty(button.name(), isButtonPressed(button));
        }

        JsonObject lstick = new JsonObject();
        lstick.addProperty("x", leftX);
        lstick.addProperty("y", leftY);
        json.add("lstick", lstick);

        JsonObject rstick = new JsonObject();
        rstick.addProperty("x", rightX);
        rstick.addProperty("y", rightY);
        json.add("rstick", rstick);

        return json;
    }
}
