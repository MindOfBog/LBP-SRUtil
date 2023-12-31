package bog.lbpsru.components.structs.tas;

import bog.lbpsru.components.structs.PlayerInputs;
import bog.lbpsru.components.utils.Button;
import bog.lbpsru.components.utils.Utils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 * @author Bog
 */
public class TasInput{

    public int frame;
    public boolean controller;
    public String wait;
    public PlayerInputs inputs;

    public TasInput() {}

    public TasInput(String wait)
    {
        this.wait = wait;
    }

    public TasInput(int frame, boolean controller, PlayerInputs inputs) {
        this.frame = frame;
        this.controller = controller;
        this.inputs = inputs;
    }

    @Override
    public TasInput clone()
    {
        TasInput input = new TasInput();
        input.frame = this.frame;
        input.controller = this.controller;
        input.wait = this.wait;
        if(input.inputs != null)
            input.inputs = this.inputs.clone();
        return input;
    }

    public static TasInput deserialize(JsonElement json, TasInput input) throws JsonParseException {
        JsonObject jsonTasInput = json.getAsJsonObject();

        if(jsonTasInput == null)
            return null;

        JsonElement controller = jsonTasInput.get("controller");
        if(controller != null)
            input.controller = controller.getAsBoolean();

        JsonElement wait = jsonTasInput.get("wait");
        if(wait != null)
            input.wait = wait.getAsString();
        else
            input.wait = null;

        if(input.wait != null && input.wait.equalsIgnoreCase("level"))
            input.frame = 0;

        JsonElement frame = jsonTasInput.get("frame");
        if(frame != null)
            input.frame += frame.getAsInt();

        JsonElement jsonPlayerInputs = jsonTasInput.get("inputs");
        if(jsonPlayerInputs != null)
            input.inputs = PlayerInputs.deserialize(jsonPlayerInputs.getAsJsonObject(), input.inputs);

        return input;
    }

    public JsonObject serialize(int prevFrame)
    {
        JsonObject json = new JsonObject();

        if(wait == null)
        {
            json.addProperty("frame", frame - prevFrame);
            json.addProperty("controller", controller);
            json.add("inputs", inputs.serialize());
        }
        else
            json.addProperty("wait", wait);

        return json;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof TasInput)
        {
            TasInput compare = (TasInput) obj;
            if(this.controller == compare.controller &&
                    this.wait == compare.wait &&
                    this.inputs.getButtons() == compare.inputs.getButtons() &&
                    Utils.round(this.inputs.getLeftStick()[0], 3) == Utils.round(compare.inputs.getLeftStick()[0], 3) &&
                    Utils.round(this.inputs.getLeftStick()[1], 3) == Utils.round(compare.inputs.getLeftStick()[1], 3) &&
                    Utils.round(this.inputs.getRightStick()[0], 3) == Utils.round(compare.inputs.getRightStick()[0], 3) &&
                    Utils.round(this.inputs.getRightStick()[1], 3) == Utils.round(compare.inputs.getRightStick()[1], 3))
                return true;
        }

        return false;
    }
}
